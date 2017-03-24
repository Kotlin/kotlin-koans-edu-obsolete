package collections.groupby

import collections.*

fun Shop.groupCustomersByCity(): Map<City, List<Customer>> =
    /*<taskWindow>*/customers.groupBy { it.city }/*</taskWindow>*/