/*
 * Copyright (c) 2008-2016, GigaSpaces Technologies, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mycompany.app;

import com.gigaspaces.client.*;
import com.gigaspaces.query.aggregators.*;
import com.gigaspaces.utils.CsvReader;
import com.j_spaces.core.client.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openspaces.core.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class SpaceTestCase {
    private GigaSpace gigaSpace;

    @Before
    public void before() {
        gigaSpace = StrorageAdapters.getOrCreateSpace(null, "localhost");
    }

    @Test
    public void testSpaceOperations() throws IOException {

        System.out.println("Assert space is empty");
        Assert.assertEquals(0, gigaSpace.count(null));

        System.out.println("Loading employee entries from employees.csv...");
        try (Stream<Employee> stream = new CsvReader().read(getResourcePath("employees.csv"), Employee.class)) {
            stream.forEach(gigaSpace::write);
        }

        SQLQuery<Employee> allEmployees = new SQLQuery<>(Employee.class, "");
        int countAll = gigaSpace.count(allEmployees);
        SQLQuery<Employee> rndEmployees = new SQLQuery<>(Employee.class, "department = ?", "rnd");
        int countRnd = gigaSpace.count(rndEmployees);
        Assert.assertEquals(20, countRnd);
        System.out.printf("Total employees in space: %s, RnD employees: %s%n", countAll, countRnd);

        System.out.println("Reading all RnD employees...");
        for (Employee employee : gigaSpace.iterator(rndEmployees)) {
            System.out.println("  " + employee);
            Assert.assertEquals("rnd", employee.getDepartment());
        }

        System.out.println("Query min/max/average salaries of employees in RnD...");
        AggregationResult aggResult = gigaSpace.aggregate(rndEmployees, new AggregationSet()
                .minValue("salary").maxValue("salary").average("salary"));
        System.out.printf("RnD employees salary aggregation: min=%s, max=%s, avg=%s%n",
                aggResult.get("min(salary)"), aggResult.get("max(salary)"), aggResult.get("avg(salary)"));
        Assert.assertEquals(5499.6875, aggResult.get("avg(salary)"));

        System.out.println("Increase all RnD employees salary by 500 units...");
        ChangeResult<Employee> changeResult = gigaSpace.change(rndEmployees, new ChangeSet().increment("salary", 500.0));
        System.out.println("Changed entries: " + changeResult.getNumberOfChangedEntries());
        aggResult = gigaSpace.aggregate(rndEmployees, new AggregationSet()
                .minValue("salary").maxValue("salary").average("salary"));
        System.out.printf("RnD employees salary aggregation post-raise: min=%s, max=%s, avg=%s%n",
                aggResult.get("min(salary)"), aggResult.get("max(salary)"), aggResult.get("avg(salary)"));
        Assert.assertEquals(5999.6875d, (double)aggResult.get("avg(salary)"), 0.001);

        System.out.println("Clear all entries from space, and make sure it's empty...");
        gigaSpace.clear(null);
        Assert.assertEquals(0, gigaSpace.count(null));

        System.out.println("Completed");
    }

    private Path getResourcePath(String resource) {
        try {
            return Paths.get(getClass().getResource("/" + resource).toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
