package task8.tests

import task8.*
import org.junit.Assert
import org.junit.Test
import koans.util.toMessage

class TestGroupBy {
    @Test fun testGroupCustomersByCity() {
        Assert.assertTrue("groupCustomersByCity".toMessage(),
                groupedByCities == shop.groupCustomersByCity())
    }
}
