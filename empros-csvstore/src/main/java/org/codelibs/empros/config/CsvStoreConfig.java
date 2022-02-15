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
package org.codelibs.empros.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "datastore.csvstore")
public class CsvStoreConfig {
    private List<String> columnList;

    private String csvDirectoryPath = System.getProperty("java.io.tmpdir");

    private String csvNamePrefix = "event_";

    private String csvNameSuffix = ".csv";

    private String rotationDatePattern;

    private String outputEncoding = "UTF-8";

    private long writerTimeout = 60000;


    public List<String> getColumnList() {
        return columnList;
    }

    public void setColumnList(List<String> columnList) {
        this.columnList = columnList;
    }

    public String getCsvDirectoryPath() {
        return csvDirectoryPath;
    }

    public void setCsvDirectoryPath(String csvDirectoryPath) {
        this.csvDirectoryPath = csvDirectoryPath;
    }

    public String getCsvNamePrefix() {
        return csvNamePrefix;
    }

    public void setCsvNamePrefix(String csvNamePrefix) {
        this.csvNamePrefix = csvNamePrefix;
    }

    public String getCsvNameSuffix() {
        return csvNameSuffix;
    }

    public void setCsvNameSuffix(String csvNameSuffix) {
        this.csvNameSuffix = csvNameSuffix;
    }

    public String getRotationDatePattern() {
        return rotationDatePattern;
    }

    public void setRotationDatePattern(String rotationDatePattern) {
        this.rotationDatePattern = rotationDatePattern;
    }

    public String getOutputEncoding() {
        return outputEncoding;
    }

    public void setOutputEncoding(String outputEncoding) {
        this.outputEncoding = outputEncoding;
    }

    public long getWriterTimeout() {
        return writerTimeout;
    }

    public void setWriterTimeout(long writerTimeout) {
        this.writerTimeout = writerTimeout;
    }
}
