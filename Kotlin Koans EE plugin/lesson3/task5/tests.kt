package lesson3.task5.tests

import lesson3.task5.*
import org.junit.Assert
import org.junit.Test

class TestMaxMin {
    @Test fun testCustomerWithMaximumNumberOfOrders() {
        Assert.assertEquals(customers[reka], shop.getCustomerWithMaximumNumberOfOrders())
    }

    @Test fun testTheMostExpensiveOrderedProduct() {
        Assert.assertEquals(rubyMine, customers[nathan]!!.getMostExpensiveOrderedProduct())
    }
}
