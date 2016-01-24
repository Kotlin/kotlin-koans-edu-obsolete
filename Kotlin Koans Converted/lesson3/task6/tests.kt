package lesson3.task6.tests

import lesson3.task6.*
import org.junit.Assert
import org.junit.Test

class TestSort {
    @Test fun testGetCustomersSortedByNumberOfOrders() {
        Assert.assertEquals(sortedCustomers, shop.getCustomersSortedByNumberOfOrders())
    }
}
