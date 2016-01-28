package task13.tests

import task13.*
import org.junit.Test
import org.junit.Assert

class TestExtensionsOnCollections {
    @Test fun testSort() {
        Assert.assertEquals(listOf(5, 2, 1), getList())
    }
}