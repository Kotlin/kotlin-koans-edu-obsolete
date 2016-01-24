package koans.converter

import org.pegdown.LinkRenderer
import org.pegdown.ToHtmlSerializer
import org.pegdown.ast.VerbatimNode

internal class GFMNodeSerializer : ToHtmlSerializer(LinkRenderer()) {

    override fun visit(node: VerbatimNode) {
        val codeMirrorType: String
        when (node.type) {
            "kotlin" -> codeMirrorType = "text/x-kotlin"
            "java" -> codeMirrorType = "text/x-java"
            else -> codeMirrorType = "text/x-kotlin"
        }
        if (codeMirrorType != "") {
            printer.print("<pre><code data-lang=\"$codeMirrorType\">")
        } else {
            printer.print("<pre><code>")
        }
        printer.printEncoded(node.text)
        printer.print("</code></pre>")
    }
}