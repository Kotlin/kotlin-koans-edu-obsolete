class PropertyExample() {
    var counter = 0
    var propertyWithCounter: Int? = null
        <taskWindow>set(v: Int?) {
            field = v
            counter++
        }</taskWindow>
}