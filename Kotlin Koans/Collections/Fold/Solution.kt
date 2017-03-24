package collections.fold

import collections.*

fun Shop.getSetOfProductsOrderedByEveryCustomer(): Set<Product> {
    /*<taskWindow>*/val allProducts = customers.flatMap { it.orders.flatMap { it.products }}.toSet()
    return customers.fold(allProducts, {
        orderedByAll, customer ->
        orderedByAll.intersect(customer.orders.flatMap { it.products }.toSet())
    })/*</taskWindow>*/
}