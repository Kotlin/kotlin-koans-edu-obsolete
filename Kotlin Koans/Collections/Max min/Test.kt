package collections.max_min

import org.junit.Assert
import org.junit.Test
import koans.util.toMessage
import collections.*

class TestMaxMin {
    @Test fun testCustomerWithMaximumNumberOfOrders() {
        Assert.assertEquals("getCustomerWithMaximumNumberOfOrders".toMessage(),
                customers[reka], shop.getCustomerWithMaximumNumberOfOrders())
    }

    @Test fun testTheMostExpensiveOrderedProduct() {
        Assert.assertEquals("getMostExpensiveOrderedProduct".toMessage(),
                rubyMine, customers[nathan]!!.getMostExpensiveOrderedProduct())
    }
}
