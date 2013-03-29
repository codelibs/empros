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
import java.util.regex.Pattern;

import jp.sf.orangesignal.csv.CsvConfig;
import jp.sf.orangesignal.csv.CsvWriter;

import org.apache.commons.io.IOUtils;
import org.codelibs.empros.event.Event;
import org.codelibs.empros.exception.EmprosDataStoreException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    protected volatile String currentCsvFilePath;

    protected volatile CsvWriter csvWriter;

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
    }

    public CsvStore(final CsvConfig csvConfig) {
        this.csvConfig = csvConfig;
    }

    @Override
    public void store(final List<Event> eventList) {
        if (columnList == null || columnList.isEmpty()) {
            throw new EmprosDataStoreException("WEM0001");
        }

        final CsvWriter writer = getCsvWriter();

        for (final Event event : eventList) {
            final List<String> valueList = new ArrayList<>(columnList.size());
            for (final String key : columnList) {
                final Object value = event.get(key);
                valueList.add(value != null ? value.toString() : null);
            }
            try {
                synchronized (writer) {
                    writer.writeValues(valueList);
                }
            } catch (final Exception e) {
                throw new EmprosDataStoreException("EEM0004",
                        new Object[] { event }, e);
            }
        }

    }

    public void destroy() {
        closeCsvWriter();
    }

    protected CsvWriter getCsvWriter() {
        if (rotationDatePattern != null) {
            final SimpleDateFormat sdf = new SimpleDateFormat(
                    rotationDatePattern);
            final String baseName = sdf.format(new Date());
            return createCsvWriter(baseName);
        } else if (csvWriter != null) {
            return csvWriter;
        }
        return createCsvWriter("data");
    }

    protected synchronized CsvWriter createCsvWriter(final String baseName) {
        final File csvFile = new File(csvDirectoryPath, csvNamePrefix
                + baseName + csvNameSuffix);
        final String csvFilePath = csvFile.getAbsolutePath();
        if (csvFilePath.equals(currentCsvFilePath)) {
            return csvWriter;
        }
        if (csvWriter != null) {
            closeCsvWriter();
        }
        try {
            csvWriter = new CsvWriter(new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(csvFile),
                            outputEncoding)), csvConfig);
            currentCsvFilePath = csvFilePath;
            logger.info("Current CSV File: {}", csvFilePath);
        } catch (final Exception e) {
            throw new EmprosDataStoreException("EEM0003",
                    new Object[] { csvFilePath }, e);
        }
        return csvWriter;
    }

    private void closeCsvWriter() {
        try {
            csvWriter.flush();
        } catch (final IOException e) {
            logger.warn("Failed to flush a csv writer.", e);
        }
        IOUtils.closeQuietly(csvWriter);
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

}
