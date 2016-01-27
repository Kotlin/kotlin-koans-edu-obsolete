fun Customer.getMostExpensiveDeliveredProduct(): Product? {
    <taskWindow>return orders.filter { it.isDelivered }.flatMap { it.products }.maxBy { it.price }</taskWindow>
}

fun Shop.getNumberOfTimesProductWasOrdered(product: Product): Int {
    <taskWindow>return customers.flatMap { it.getOrderedProductsList() }.count { it == product }</taskWindow>
}

<taskWindow>fun Customer.getOrderedProductsList(): List<Product> {
    return orders.flatMap { it.products }
}</taskWindow>