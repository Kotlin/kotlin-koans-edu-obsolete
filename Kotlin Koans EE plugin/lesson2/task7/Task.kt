package task7

class Invokable {
    public var numberOfInvocations: Int = 0
        private set
    operator public fun invoke(): Invokable {
        TODO()
    }
}

fun invokeTwice(invokable: Invokable) = invokable()()