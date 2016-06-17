/*
 *   Copyright 2015 Ripple OSI
 *
 *      Licensed under the Apache License, Version 2.0 (the "License");
 *      you may not use this file except in compliance with the License.
 *      You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *      Unless required by applicable law or agreed to in writing, software
 *      distributed under the License is distributed on an "AS IS" BASIS,
 *      WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *      See the License for the specific language governing permissions and
 *      limitations under the License.
 */
package org.icrripple.patient.clinicalnotes.search;

import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.collections4.Transformer;
import org.rippleosi.common.util.DateFormatter;
import org.icrripple.patient.clinicalnotes.model.ClinicalNoteDetails;

public class ClinicalNoteDetailsTransformer implements Transformer<Map<String, Object>, ClinicalNoteDetails> {

    @Override
    public ClinicalNoteDetails transform(Map<String, Object> input) {

        String uid = MapUtils.getString(input, "uid");
        String type = MapUtils.getString(input, "type");
        String note = MapUtils.getString(input, "note");
        String author = MapUtils.getString(input, "author");
        String dateCreated = MapUtils.getString(input, "date_created");

        ClinicalNoteDetails clinicalNote = new ClinicalNoteDetails();

        clinicalNote.setSource("openehr");
        clinicalNote.setSourceId(uid);
        clinicalNote.setType(type);
        clinicalNote.setNote(note);
        clinicalNote.setAuthor(author);
        clinicalNote.setDateCreated(DateFormatter.toDate(dateCreated));

        return clinicalNote;
    }
}
