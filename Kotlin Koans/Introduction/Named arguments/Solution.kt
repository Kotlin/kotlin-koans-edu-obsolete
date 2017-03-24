package introduction.named_arguments

fun joinOptions(options: Collection<String>) = options.joinToString(/*<taskWindow>*/prefix = "[", postfix = "]"/*</taskWindow>*/)