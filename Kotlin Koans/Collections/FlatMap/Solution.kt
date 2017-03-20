val Customer.orderedProducts: Set<Product> get() {
    <taskWindow>return orders.flatMap { it.products }.toSet()</taskWindow>
}

val Shop.allOrderedProducts: Set<Product> get() {
    <taskWindow>return customers.flatMap { it.orderedProducts }.toSet()</taskWindow>
}