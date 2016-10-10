/*
 * Copyright 2016 Mater Care Record
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package ie.mater.common.types;

import org.rippleosi.common.types.RepoSourceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 */
public enum MaterRepoSourceTypes implements RepoSourceType {

    TIE("TIE");
    
    private static final Logger LOGGER = LoggerFactory.getLogger(MaterRepoSourceTypes.class);

    private final String sourceName;

    MaterRepoSourceTypes(String sourceName) {
        this.sourceName = sourceName;
    }

    @Override
    public String getSourceName() {
        return sourceName;
    }

    public static RepoSourceType fromString(String sourceName) {
        if (sourceName == null) {
            return null;
        }

        for (RepoSourceType enumValue : MaterRepoSourceTypes.values()) {
            if (enumValue.getSourceName().equalsIgnoreCase(sourceName)) {
                return enumValue;
            }
        }

        LOGGER.warn("Could not find an enumeration for '" + sourceName +"'");
        return null;
    }
}
