fun TODO(message: String? = null): Nothing = throw Exception(message ?: "Not implemented")
fun <T> T.TODO(message: String? = null): Nothing = throw Exception(message ?: "Not implemented")

fun errorMessage(functionName: String) = "The function '$functionName' is implemented incorrectly"