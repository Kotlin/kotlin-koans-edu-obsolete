package lesson3.task1.tests

import lesson3.task1.*
import org.junit.Assert
import org.junit.Test

class TestIntroduction {
    @Test fun testSetOfCustomers(){
        Assert.assertEquals(shop.getSetOfCustomers(), customers.values.toSet())
    }
}
