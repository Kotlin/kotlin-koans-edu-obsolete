package lesson3.task4.tests

import lesson3.task4.*
import org.junit.Assert
import org.junit.Test

class TestFlatMap {
    @Test fun testGetOrderedProductsSet() {
        Assert.assertEquals(setOf(idea), customers[reka]!!.getOrderedProducts())
    }

    @Test fun testGetAllOrderedProducts() {
        Assert.assertEquals(orderedProducts, shop.getAllOrderedProducts())
    }
}
