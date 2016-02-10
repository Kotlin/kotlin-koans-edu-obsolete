import org.junit.Assert
import org.junit.Test
import koans.util.errorMessage

class TestFlatMap {
    @Test fun testGetOrderedProductsSet() {
        Assert.assertEquals(errorMessage("getOrderedProducts"),
                setOf(idea) == customers[reka]!!.getOrderedProducts())
    }

    @Test fun testGetAllOrderedProducts() {
        Assert.assertEquals(errorMessage("getAllOrderedProducts"),
                orderedProducts == shop.getAllOrderedProducts())
    }
}
