/****************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one   *
 * or more contributor license agreements.  See the NOTICE file *
 * distributed with this work for additional information        *
 * regarding copyright ownership.  The ASF licenses this file   *
 * to you under the Apache License, Version 2.0 (the            *
 * "License"); you may not use this file except in compliance   *
 * with the License.  You may obtain a copy of the License at   *
 *                                                              *
 *   http://www.apache.org/licenses/LICENSE-2.0                 *
 *                                                              *
 * Unless required by applicable law or agreed to in writing,   *
 * software distributed under the License is distributed on an  *
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY       *
 * KIND, either express or implied.  See the License for the    *
 * specific language governing permissions and limitations      *
 * under the License.                                           *
 ****************************************************************/

package org.apache.james.jmap.cassandra.vacation;

import org.apache.james.backends.cassandra.CassandraCluster;
import org.apache.james.backends.cassandra.init.CassandraModuleComposite;
import org.apache.james.backends.cassandra.init.CassandraZonedDateTimeModule;
import org.apache.james.jmap.api.vacation.AbstractVacationRepositoryTest;
import org.apache.james.jmap.api.vacation.VacationRepository;
import org.junit.After;

public class CassandraVacationRepositoryTest extends AbstractVacationRepositoryTest {

    private CassandraCluster cassandra;

    @Override
    protected VacationRepository createVacationRepository() {
        cassandra = CassandraCluster.create(new CassandraModuleComposite(new CassandraVacationModule(), new CassandraZonedDateTimeModule()));
        return new CassandraVacationRepository(new CassandraVacationDAO(cassandra.getConf(), cassandra.getTypesProvider()));
    }

    @After
    public void tearDown() {
        cassandra.clearAllTables();
    }
}
