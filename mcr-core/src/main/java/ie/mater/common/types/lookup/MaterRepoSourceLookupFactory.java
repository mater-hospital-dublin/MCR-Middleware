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
package ie.mater.common.types.lookup;

import ie.mater.common.types.MaterRepoSourceTypes;
import org.rippleosi.common.types.RepoSourceType;
import org.rippleosi.common.types.lookup.RepoLookup;
import org.springframework.stereotype.Service;

/**
 */
@Service
public class MaterRepoSourceLookupFactory implements RepoLookup {

    @Override
    public RepoSourceType getSource(String sourceName) {
        return MaterRepoSourceTypes.fromString(sourceName);
    }
}
