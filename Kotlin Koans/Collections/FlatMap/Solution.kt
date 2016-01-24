fun Customer.getOrderedProducts(): Set<Product> = <taskWindow>orders.flatMap { it.products }.toSet()</taskWindow>

fun Shop.getAllOrderedProducts(): Set<Product> = <taskWindow>customers.flatMap { it.getOrderedProducts() }.toSet()</taskWindow>