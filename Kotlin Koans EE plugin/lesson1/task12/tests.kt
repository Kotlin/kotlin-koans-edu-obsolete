package task12.tests

import task12.*
import org.junit.Test
import org.junit.Assert
import koans.util.errorMessage

class TestSamConversions {
    @Test fun testSort() {
        Assert.assertEquals(errorMessage("getList"), listOf(5, 2, 1), getList())
    }
}
