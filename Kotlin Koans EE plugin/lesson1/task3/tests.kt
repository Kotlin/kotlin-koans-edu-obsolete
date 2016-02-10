package task3.tests

import task3.*
import org.junit.Test
import org.junit.Assert

class TestNamedArguments() {

    @Test fun testJoinToString() {
        Assert.assertEquals(errorMessage("joinOptions"), , "[yes, no, may be]", joinOptions(listOf("yes", "no", "may be")))
    }

}