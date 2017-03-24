package collections.sort

import org.junit.Assert
import org.junit.Test
import koans.util.toMessage
import collections.*

class TestSort {
    @Test fun testGetCustomersSortedByNumberOfOrders() {
        Assert.assertEquals("getCustomersSortedByNumberOfOrders".toMessage(),
                sortedCustomers, shop.getCustomersSortedByNumberOfOrders())

    }
}
