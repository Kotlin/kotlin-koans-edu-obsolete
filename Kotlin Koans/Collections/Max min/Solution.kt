fun Shop.getCustomerWithMaximumNumberOfOrders(): Customer? =
    <taskWindow>customers.maxBy { it.orders.size }</taskWindow>

fun Customer.getMostExpensiveOrderedProduct(): Product? =
    <taskWindow>orders.flatMap { it.products }.maxBy { it.price }</taskWindow>
