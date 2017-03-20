fun Shop.checkAllCustomersAreFrom(city: City): Boolean =
    <taskWindow>customers.all { it.city == city }</taskWindow>

fun Shop.hasCustomerFrom(city: City): Boolean =
    <taskWindow>customers.any { it.city == city }</taskWindow>

fun Shop.countCustomersFrom(city: City): Int =
    <taskWindow>customers.count { it.city == city }</taskWindow>

fun Shop.findAnyCustomerFrom(city: City): Customer? =
    <taskWindow>customers.find { it.city == city }</taskWindow>