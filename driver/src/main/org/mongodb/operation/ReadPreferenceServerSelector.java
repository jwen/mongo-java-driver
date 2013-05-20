/*
 * Copyright (c) 2008 - 2013 10gen, Inc. <http://10gen.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.mongodb.operation;

import org.mongodb.ReadPreference;
import org.mongodb.connection.ClusterDescription;
import org.mongodb.connection.ServerDescription;
import org.mongodb.connection.ServerSelector;

import java.util.List;

public class ReadPreferenceServerSelector implements ServerSelector {
    private final ReadPreference readPreference;

    public ReadPreferenceServerSelector(final ReadPreference readPreference) {
        // TODO: this is hiding bugs:
        // notNull("readPreference", readPreference);
        this.readPreference = readPreference == null ? ReadPreference.primary() : readPreference;
    }

    @Override
    public List<ServerDescription> choose(final ClusterDescription clusterDescription) {
        return readPreference.choose(clusterDescription);
    }
}