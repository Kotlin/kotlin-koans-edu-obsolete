fun Shop.getCustomersSortedByNumberOfOrders(): List<Customer> =
    <taskWindow>customers.sortedBy { it.orders.size }</taskWindow>