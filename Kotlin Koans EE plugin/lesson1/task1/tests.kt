package lesson1.task1.tests

import lesson1.task1.*
import org.junit.Assert
import org.junit.Test

class TestStart {
    @Test fun testOk() {
        Assert.assertEquals("OK", start())
    }
}