package task9.tests

import task9.*
import org.junit.Assert
import org.junit.Test
import koans.util.toMessage

class TestPartition {
    @Test fun testGetCustomersWhoHaveMoreUndeliveredOrdersThanDelivered() {
        Assert.assertTrue("getCustomerWithMaximumNumberOfOrders".toMessage(),
                setOf(customers[reka]) == shop.getCustomersWithMoreUndeliveredOrdersThanDelivered())
    }
}
