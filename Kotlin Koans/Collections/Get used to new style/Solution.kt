fun doSomethingStrangeWithCollection(collection: Collection<String>): Collection<String>? {

    val groupsByLength = collection. groupBy { s -> <taskWindow>s.length</taskWindow> }

    val maximumSizeOfGroup = groupsByLength.values.map { group -> <taskWindow>group.size</taskWindow> }.max()

    return groupsByLength.values.firstOrNull { group -> <taskWindow>group.size == maximumSizeOfGroup</taskWindow> }
}