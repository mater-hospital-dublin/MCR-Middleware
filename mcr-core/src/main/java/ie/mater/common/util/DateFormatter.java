/*
 * Copyright 2015 Ripple OSI
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


import org.apache.commons.lang3.StringUtils;

/**
 */
public final class DateFormatter {

    private DateFormatter() {
        // Prevent construction
    }

    public static String stripOddDate(String input) {
        if (input == null) {
            return null;
        }

        String date = input;

        if (date.contains("/")) {
            date = StringUtils.substringAfter(date, "/");
        }
        if (date.contains("/")) {
            date = StringUtils.substringBefore(date, "/");
        }
        if (date.contains("+")) {
            date = StringUtils.substringBefore(date, "+");
        }

        return date;
    }
}
