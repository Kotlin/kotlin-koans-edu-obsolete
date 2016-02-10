package task8.tests

import task8.*
import org.junit.Assert
import org.junit.Test
import koans.util.errorMessage

class TestGroupBy {
    @Test fun testGroupCustomersByCity() {
        Assert.assertTrue(errorMessage("groupCustomersByCity"),
                groupedByCities == shop.groupCustomersByCity())
    }
}
