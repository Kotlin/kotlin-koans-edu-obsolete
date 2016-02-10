import org.junit.Assert
import org.junit.Test

class TestGroupBy {
    @Test fun testGroupCustomersByCity() {
        Assert.assertTrue(errorMessage("groupCustomersByCity"),
                groupedByCities == shop.groupCustomersByCity())
    }
}
