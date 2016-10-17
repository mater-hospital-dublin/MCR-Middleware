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
package ie.mater.common.util;

import java.text.ParseException;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

/**
 */
public final class MaterDateFormatter {

    public static Date toDate(String input) {
        if (input == null) {
            return null;
        }

        TimeZone.setDefault(TimeZone.getTimeZone("Europe/London"));

        try {
            return DateUtils.parseDate(input, "yyyyMMddHHmm",
                                       "yyyyMMddSSSS",
                                       "yyyy-MM-dd",
                                       "dd-MM-yyyy",
                                       "yyyy-MM-dd'T'HH:mm:ss",
                                       "yyyy-MM-dd'T'HH:mm:ss'Z'",
                                       "yyyy-MM-dd'T'HH:mm:ss.SSS",
                                       "yyyy-MM-dd'T'HH:mm:ss.SSSX",
                                       "yyyy-MM-dd'T'HH:mm:ss.SSSXX",
                                       "yyyy-MM-dd'T'HH:mm:ss.SSSXXX",
                                       "HH:mm:ss",
                                       "yyyy.MM.dd",
                                       "yyyyMMdd");
        }
        catch (ParseException ignore) {
            return null;
        }
    }
}
