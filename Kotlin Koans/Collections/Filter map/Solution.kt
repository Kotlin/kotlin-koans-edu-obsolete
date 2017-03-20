fun Shop.getCitiesCustomersAreFrom(): Set<City> =
    <taskWindow>customers.map { it.city }.toSet()</taskWindow>

fun Shop.getCustomersFrom(city: City): List<Customer> =
    <taskWindow>customers.filter { it.city == city }</taskWindow>