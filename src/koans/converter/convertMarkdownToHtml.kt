package koans.converter

import org.pegdown.Extensions
import org.pegdown.LinkRenderer
import org.pegdown.PegDownProcessor
import org.pegdown.ToHtmlSerializer
import org.pegdown.ast.ExpLinkNode
import org.pegdown.ast.VerbatimNode
import java.util.*

fun convertMarkdownToHtml(markdownText: String, links: Properties): String {
    val processor = PegDownProcessor(Extensions.FENCED_CODE_BLOCKS)
    return MarkdownToHtmlConverter(links).toHtml(processor.parseMarkdown(markdownText.toCharArray()));
}

private class MarkdownToHtmlConverter(links: Properties) : ToHtmlSerializer(DocumentationLinkRenderer(links)) {

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

private class DocumentationLinkRenderer(val links: Properties): LinkRenderer() {
    override fun render(node: ExpLinkNode, text: String?): Rendering? {
        return LinkRenderer.Rendering(links.getProperty(node.url), text)
    }
}