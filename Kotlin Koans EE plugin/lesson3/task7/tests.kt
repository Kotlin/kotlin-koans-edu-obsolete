package task7.tests

import task7.*
import org.junit.Assert
import org.junit.Test

class TestSum {
    @Test fun testGetTotalOrderPrice() {
        Assert.assertTrue(errorMessage("getTotalOrderPrice"), 148.0 == customers[nathan]!!.getTotalOrderPrice(), 0.1)
    }
}
