/*
 * Copyright 2012-2020 CodeLibs Project and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.codelibs.empros.store;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.regex.Pattern;

import com.orangesignal.csv.CsvConfig;
import com.orangesignal.csv.CsvWriter;

import org.codelibs.core.io.CloseableUtil;
import org.codelibs.core.lang.StringUtil;
import org.codelibs.core.message.MessageFormatter;
import org.codelibs.empros.config.CsvStoreConfig;
import org.codelibs.empros.event.Event;
import org.codelibs.empros.exception.EmprosDataStoreException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * CsvStore stores events to CSV file.
 * 
 * @author shinsuke
 *
 */
public class CsvStore implements DataStore {

    private static final Logger logger = LoggerFactory
            .getLogger(CsvStore.class);

    protected CsvConfig csvConfig;

    protected List<String> columnList;

    protected String csvDirectoryPath = System.getProperty("java.io.tmpdir");

    protected String csvNamePrefix = "event_";

    protected String csvNameSuffix = ".csv";

    protected String rotationDatePattern;

    protected String outputEncoding = "UTF-8";

    protected long writerTimeout = 60000;

    protected CsvDataWriter csvDataWriter;

    private Object timestampKey;

    public CsvStore() {
        csvConfig = new CsvConfig();
        csvConfig.setQuoteDisabled(false);
        csvConfig.setEscapeDisabled(false);
        csvConfig.setBreakString("\n");
        csvConfig.setNullString("NULL");
        csvConfig.setIgnoreLeadingWhitespaces(true);
        csvConfig.setIgnoreTrailingWhitespaces(true);
        csvConfig.setIgnoreEmptyLines(true);
        csvConfig.setIgnoreLinePatterns(Pattern.compile("^#.*$"));
        csvConfig.setSkipLines(1);

        csvDataWriter = new CsvDataWriter();
        csvDataWriter.start();
    }

    public CsvStore(final CsvConfig csvConfig) {
        this.csvConfig = csvConfig;
        csvDataWriter = new CsvDataWriter();
        csvDataWriter.start();
    }

    @Override
    public void store(final List<Event> eventList,
            final DataStoreListener listener) {
        try {
            if (columnList == null || columnList.isEmpty()) {
                throw new EmprosDataStoreException("WEMC0001");
            }

            for (final Event event : eventList) {
                final List<String> valueList = new ArrayList<>(
                        columnList.size());
                for (final String key : columnList) {
                    final Object value = event.getObject(key);
                    valueList.add(value != null ? value.toString()
                            : StringUtil.EMPTY);
                }

                final CsvData csvData = new CsvData();
                final Object timestamp = event.get(timestampKey);
                if (timestamp instanceof Long) {
                    csvData.timestamp = ((Long) timestamp).longValue();
                } else {
                    csvData.timestamp = System.currentTimeMillis();
                }
                csvData.valueList = valueList;
                csvDataWriter.add(csvData);
            }
            csvDataWriter.flush();

            listener.onSuccess(this, eventList);
        } catch (final Exception e) {
            listener.onFailure(e);
        }
    }

    public void destroy() {
        csvDataWriter.destroy();
    }

    public List<String> getColumnList() {
        return columnList;
    }

    public void setColumnList(final List<String> columnList) {
        this.columnList = columnList;
    }

    public String getCsvDirectoryPath() {
        return csvDirectoryPath;
    }

    public void setCsvDirectoryPath(final String csvDirectoryPath) {
        this.csvDirectoryPath = csvDirectoryPath;
    }

    public String getCsvNamePrefix() {
        return csvNamePrefix;
    }

    public void setCsvNamePrefix(final String csvNamePrefix) {
        this.csvNamePrefix = csvNamePrefix;
    }

    public String getCsvNameSuffix() {
        return csvNameSuffix;
    }

    public void setCsvNameSuffix(final String csvNameSuffix) {
        this.csvNameSuffix = csvNameSuffix;
    }

    public String getRotationDatePattern() {
        return rotationDatePattern;
    }

    public void setRotationDatePattern(final String rotationDatePattern) {
        this.rotationDatePattern = rotationDatePattern;
    }

    public String getOutputEncoding() {
        return outputEncoding;
    }

    public void setOutputEncoding(final String outputEncoding) {
        this.outputEncoding = outputEncoding;
    }

    public long getWriterTimeout() {
        return writerTimeout;
    }

    public void setWriterTimeout(final long writerTimeout) {
        this.writerTimeout = writerTimeout;
    }

    protected static class CsvData {
        protected long timestamp;

        protected List<String> valueList;

        @Override
        public String toString() {
            return "CsvData [timestamp=" + timestamp + ", valueList="
                    + valueList + "]";
        }
    }

    protected class CsvDataWriter extends Thread {
        protected Queue<CsvData> queue = new ConcurrentLinkedQueue<>();

        protected CsvWriter csvWriter;

        protected String currentCsvFilePath;

        protected boolean running;

        public void add(final CsvData csvData) {
            queue.add(csvData);
        }

        public void flush() {
            synchronized (CsvStore.this) {
                CsvStore.this.notifyAll();
            }
        }

        public void destroy() {
            running = false;
            interrupt();
        }

        @Override
        public void run() {
            running = true;
            boolean alive = true;
            while (alive) {
                if (queue.isEmpty()) {
                    if (running) {
                        synchronized (CsvStore.this) {
                            try {
                                CsvStore.this.wait(writerTimeout);
                            } catch (final InterruptedException e) {
                                // do nothing
                            }
                        }
                        if (queue.isEmpty()) {
                            closeCsvWriter();
                        }
                    } else {
                        alive = false;
                    }
                } else {
                    final CsvData csvData = queue.poll();
                    try {
                        final CsvWriter writer = getCsvWriter(csvData.timestamp);
                        writer.writeValues(csvData.valueList);
                        if (logger.isDebugEnabled()) {
                            writer.flush();
                        }
                    } catch (final Exception e) {
                        logger.error(MessageFormatter.getSimpleMessage(
                                "EEMC0004", csvData ), e);
                    }
                }
            }
            closeCsvWriter();
        }

        protected CsvWriter getCsvWriter(final long timestamp) {
            if (rotationDatePattern != null) {
                final SimpleDateFormat sdf = new SimpleDateFormat(
                        rotationDatePattern);
                final String baseName = sdf.format(new Date(timestamp));
                return createCsvWriter(baseName);
            } else if (csvWriter != null) {
                return csvWriter;
            }
            return createCsvWriter("data");
        }

        protected CsvWriter createCsvWriter(final String baseName) {
            final File csvFile = new File(csvDirectoryPath, csvNamePrefix
                    + baseName + csvNameSuffix);
            final String csvFilePath = csvFile.getAbsolutePath();
            if (csvWriter != null) {
                if (csvFilePath.equals(currentCsvFilePath)) {
                    return csvWriter;
                }
                closeCsvWriter();
            }
            try {
                csvWriter = new CsvWriter(new BufferedWriter(
                        new OutputStreamWriter(new FileOutputStream(csvFile),
                                outputEncoding)), csvConfig);
                currentCsvFilePath = csvFilePath;
                logger.info("Current CSV File: {}", csvFilePath);
            } catch (final Exception e) {
                throw new EmprosDataStoreException("EEMC0003",
                        new Object[] { csvFilePath }, e);
            }
            return csvWriter;
        }

        protected void closeCsvWriter() {
            if (csvWriter != null) {
                try {
                    csvWriter.flush();
                } catch (final IOException e) {
                    logger.warn("Failed to flush a csv writer.", e);
                }
                CloseableUtil.closeQuietly(csvWriter);
                csvWriter = null;
            }
        }
    }

}
