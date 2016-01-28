package task3.tests

import task3.*
import kotlin.test.*
import org.junit.Test
import org.junit.Assert

class TestNamedArguments() {

    @Test fun testJoinToString() {
        Assert.assertEquals("[yes, no, may be]", joinOptions(listOf("yes", "no", "may be")))
    }

}