package task7.tests

import task7.*
import kotlin.test.*
import org.junit.Test
import org.junit.Assert


public class TestDataClasses {
    @Test fun testListOfPeople() {
        Assert.assertEquals("[Person(name=Alice, age=29), Person(name=Bob, age=31)]", getPeople().toString())
    }
}