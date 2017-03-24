package collections.sort

import collections.*

fun Shop.getCustomersSortedByNumberOfOrders(): List<Customer> =
    /*<taskWindow>*/customers.sortedBy { it.orders.size }/*</taskWindow>*/