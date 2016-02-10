package task6.tests

import task6.*
import org.junit.Assert
import org.junit.Test

class TestSort {
    @Test fun testGetCustomersSortedByNumberOfOrders() {
        Assert.assertTrue(errorMessage("getCustomersSortedByNumberOfOrders"), sortedCustomers == shop.getCustomersSortedByNumberOfOrders())
    }
}
