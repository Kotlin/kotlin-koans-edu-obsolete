package introduction.nullable_types

/*<answer>*/fun sendMessageToClient(
        client: Client?, message: String?, mailer: Mailer
) {
    /*<taskWindow>*/val email = client?.personalInfo?.email
    if (email != null && message != null) {
        mailer.sendMessage(email, message)
    }/*</taskWindow>*/
}/*</answer>*/

class Client (val personalInfo: PersonalInfo?)
class PersonalInfo (val email: String?)
interface Mailer {
    fun sendMessage(email: String, message: String)
}