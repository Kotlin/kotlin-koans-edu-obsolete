import org.junit.Assert
import org.junit.Test

class TestSort {
    @Test fun testGetCustomersSortedByNumberOfOrders() {
        Assert.assertTrue(errorMessage("getCustomersSortedByNumberOfOrders"), sortedCustomers == shop.getCustomersSortedByNumberOfOrders())
    }
}
