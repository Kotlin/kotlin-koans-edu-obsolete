package task6.tests

import task6.*
import org.junit.Assert
import org.junit.Test

class TestMultiAssignment {
    @Test fun testIsLeapDay() {
        Assert.assertTrue(isLeapDay(MyDate(2016, 2, 29)))
        Assert.assertFalse(isLeapDay(MyDate(2015, 2, 29)))
    }
}