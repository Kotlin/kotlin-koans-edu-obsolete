package collections.groupby

import org.junit.Assert
import org.junit.Test
import koans.util.toMessage
import collections.*

class TestGroupBy {
    @Test fun testGroupCustomersByCity() {
        Assert.assertEquals("groupCustomersByCity".toMessage(),
                groupedByCities, shop.groupCustomersByCity())
    }
}
