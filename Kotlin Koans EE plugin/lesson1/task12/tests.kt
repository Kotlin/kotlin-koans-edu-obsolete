package task12.tests

import task12.*
import org.junit.Test
import org.junit.Assert

class TestSamConversions {
    @Test fun testSort() {
        Assert.assertEquals(listOf(5, 2, 1), getList())
    }
}
