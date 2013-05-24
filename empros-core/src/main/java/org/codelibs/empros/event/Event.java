/*
 * Copyright 2013 the CodeLibs Project and the Others.
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
package org.codelibs.empros.event;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.seasar.util.lang.StringUtil;
import org.seasar.util.security.MessageDigestUtil;

/**
 * An event data is represented as Event instance.
 * 
 * @author shinsuke
 *
 */
public class Event extends LinkedHashMap<String, Object> {

    private static final long serialVersionUID = 1L;

    protected Date createdTime;

    protected String createdBy;

    public Event(final Map<?, ?> m) {
        super(m.size());

        for (final Map.Entry<?, ?> entry : m.entrySet()) {
            final Object key = entry.getKey();
            if (key != null) {
                put(key.toString(), entry.getValue());
            }
        }
    }

    public Date getCreatedTime() {
        return (Date) createdTime.clone();
    }

    public void setCreatedTime(final Date createdTime) {
        this.createdTime = (Date) createdTime.clone();
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(final String createdBy) {
        this.createdBy = createdBy;
    }

    public String getID(final String idKey) {
        if (StringUtil.isNotBlank(idKey)) {
            final Object idValue = get(idKey);
            if (idValue != null) {
                return idValue.toString();
            }
        }
        return MessageDigestUtil.digest("MD5", super.toString());
    }

    public Object getObject(final String path) {
        if (path == null) {
            return null;
        }
        final String[] paths = path.split("\\.");
        if (paths.length > 0) {
            return getObject(this, paths, 0);
        }
        return null;
    }

    protected Object getObject(final Object target, final String[] paths,
            final int depth) {
        if (target instanceof Map<?, ?>) {
            final Object value = ((Map<?, ?>) target).get(paths[depth]);
            if (depth == paths.length - 1 || value == null) {
                return value;
            }
            return getObject(value, paths, depth + 1);
        }
        return null;
    }
}
