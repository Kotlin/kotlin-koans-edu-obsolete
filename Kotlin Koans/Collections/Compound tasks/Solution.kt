fun Customer.getMostExpensiveDeliveredProduct(): Product? {
    return orders.filter { it.isDelivered }.flatMap { it.products }.maxBy { it.price }
}

fun Shop.getNumberOfTimesProductWasOrdered(product: Product): Int {
    return customers.flatMap { it.getOrderedProductsList() }.count { it == product }
}

<taskWindow>fun Customer.getOrderedProductsList(): List<Product> {
    return orders.flatMap { it.products }
}</taskWindow>