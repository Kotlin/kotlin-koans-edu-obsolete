<answer>class LazyProperty(val initializer: () -> Int) {
    <taskWindow>var value: Int? = null</taskWindow>
    val lazy: Int
        get() {
            <taskWindow>if (value == null) {
                value = initializer()
            }
            return value!!</taskWindow>
        }
}</answer>