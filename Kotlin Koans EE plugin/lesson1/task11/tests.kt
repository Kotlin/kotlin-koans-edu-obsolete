package task11.tests

import task11.*
import org.junit.Test
import org.junit.Assert

class TestObjectExpressions {
    @Test fun testSort() {
        Assert.assertEquals(errorMessage("getList"), listOf(5, 2, 1), getList())
    }
}
