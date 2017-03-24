package collections.introduction

import org.junit.Assert
import org.junit.Test
import koans.util.toMessage
import collections.*

class TestIntroduction {
    @Test fun testSetOfCustomers(){
        Assert.assertEquals("getSetOfCustomers".toMessage(),
                customers.values.toSet(), shop.getSetOfCustomers())
    }
}
