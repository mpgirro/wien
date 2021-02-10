package dev.stalla.parser.namespace

import dev.stalla.builder.episode.EpisodeBuilder
import dev.stalla.builder.podcast.PodcastBuilder
import dev.stalla.dom.textOrNull
import dev.stalla.parser.NamespaceParser
import dev.stalla.util.FeedNamespace
import dev.stalla.util.InternalApi
import org.w3c.dom.Node

/**
 * Parser implementation for the Content namespace.
 *
 * The namespace URI is: `http://purl.org/rss/1.0/modules/content/`
 */
@InternalApi
internal object ContentParser : NamespaceParser() {

    override val namespace = FeedNamespace.CONTENT

    override fun Node.parseChannelData(builder: PodcastBuilder) {
        // No-op
    }

    override fun Node.parseItemData(builder: EpisodeBuilder) {
        when (localName) {
            "encoded" -> {
                val encoded = ifCanBeParsed { textOrNull() } ?: return
                builder.contentBuilder.encoded(encoded)
            }
            else -> pass
        }
    }
}