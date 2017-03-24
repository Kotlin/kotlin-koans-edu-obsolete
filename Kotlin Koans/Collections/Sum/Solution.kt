package collections.sum

import collections.*

fun Customer.getTotalOrderPrice(): Double =
    /*<taskWindow>*/orders.flatMap { it.products }.sumByDouble { it.price }/*</taskWindow>*/