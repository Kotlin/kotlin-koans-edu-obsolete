<answer>class Invokable {
    public var numberOfInvocations: Int = 0
        private set
    operator public fun invoke(): Invokable {
        <taskWindow>numberOfInvocations++
        return this</taskWindow>
    }
}</answer>

fun invokeTwice(invokable: Invokable) = invokable()()