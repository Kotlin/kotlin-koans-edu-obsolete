package koans.converter

import org.pegdown.Extensions
import org.pegdown.LinkRenderer
import org.pegdown.PegDownProcessor
import org.pegdown.ToHtmlSerializer
import org.pegdown.ast.*
import java.util.*

fun convertMarkdownToHtml(markdownText: String, links: Properties): String {
    val processor = PegDownProcessor(Extensions.FENCED_CODE_BLOCKS)
    return MarkdownToHtmlConverter(links).toHtml(processor.parseMarkdown(markdownText.toCharArray()));
}

private class MarkdownToHtmlConverter(val links: Properties) : ToHtmlSerializer(DocumentationLinkRenderer(links)) {

    override fun visit(node: VerbatimNode) {
        val codeMirrorType = when (node.type) {
            "java" -> "text/x-java"
            else -> "text/x-kotlin"
        }
        printer.print("<pre><code data-lang=\"$codeMirrorType\">")
        printer.printEncoded(node.text)
        printer.print("</code></pre>")
    }

    override fun visit(node: ExpLinkNode) {
        val isLocal = !links.getProperty(node.url).startsWith("http")
        if (isLocal) {
            val text = this.printChildrenToString(node)
            printer.print("<code>$text</code>")
        } else {
            super.visit(node)
        }
    }
}

private class DocumentationLinkRenderer(val links: Properties) : LinkRenderer() {
    override fun render(node: ExpLinkNode, text: String?): Rendering? {
        return LinkRenderer.Rendering(links.getProperty(node.url), text)
    }
}