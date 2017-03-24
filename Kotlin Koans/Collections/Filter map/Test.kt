package collections.filter_map

import org.junit.Assert
import org.junit.Test
import koans.util.toMessage
import collections.*

class TestFilterMap {
    @Test fun testCitiesCustomersAreFrom() {
        Assert.assertEquals("getCitiesCustomersAreFrom".toMessage(),
                setOf(Canberra, Vancouver, Budapest, Ankara, Tokyo), shop.getCitiesCustomersAreFrom())
    }

    @Test fun testCustomersFromCity() {
        Assert.assertEquals("getCustomersFrom".toMessage(),
                listOf(customers[lucas], customers[cooper]), shop.getCustomersFrom(Canberra))
    }
}
