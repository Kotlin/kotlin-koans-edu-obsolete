package task1.tests

import task1.*
import org.junit.Assert
import org.junit.Test

class TestStart {
    @Test fun testOk() {
        Assert.assertEquals("OK", start())
    }
}