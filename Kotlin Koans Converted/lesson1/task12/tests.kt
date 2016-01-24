package lesson1.task12.tests

import lesson1.task12.*
import org.junit.Test
import org.junit.Assert

class TestSamConversions {
    @Test fun testSort() {
        Assert.assertEquals(listOf(5, 2, 1), getList())
    }
}
