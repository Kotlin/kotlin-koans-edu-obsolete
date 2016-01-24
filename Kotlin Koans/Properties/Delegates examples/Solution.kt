class LazyProperty(val initializer: () -> Int) {
    val lazyValue: Int by <taskWindow>lazy(initializer)</taskWindow>
}