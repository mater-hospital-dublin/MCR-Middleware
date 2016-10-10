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
package ie.mater.common.service;

import ie.mater.common.types.MaterRepoSourceTypes;
import org.apache.commons.lang3.StringUtils;
import org.rippleosi.common.repo.Repository;
import org.rippleosi.common.types.RepoSourceType;
import org.springframework.beans.factory.annotation.Value;

public abstract class AbstractMaterService implements Repository {

    @Value("${repository.config.mater.tie:700}")
    private int priority;

    @Override
    public RepoSourceType getSource() {
        return MaterRepoSourceTypes.TIE;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    protected String addTrailingSpacesToPatientId(String patientId) {
        final int lengthRequired = 7;
        int currentLength = patientId.length();

        for (int i = currentLength; i < lengthRequired; i++) {
            if (lengthRequired - i != 0) {
                patientId = StringUtils.join("0", patientId);
            }
        }

        return patientId;
    }
}
