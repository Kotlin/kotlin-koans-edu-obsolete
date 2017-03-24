package collections.partition

import collections.*

fun Shop.getCustomersWithMoreUndeliveredOrdersThanDelivered(): Set<Customer> = /*<taskWindow>*/customers.filter {
    val (delivered, undelivered) = it.orders.partition { it.isDelivered }
    undelivered.size > delivered.size
}.toSet()/*</taskWindow>*/