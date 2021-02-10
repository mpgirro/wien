package dev.stalla.parser.namespace

import dev.stalla.builder.episode.EpisodeBuilder
import dev.stalla.builder.episode.EpisodePodloveSimpleChapterBuilder
import dev.stalla.builder.podcast.PodcastBuilder
import dev.stalla.dom.asListOfNodes
import dev.stalla.dom.getAttributeValueByName
import dev.stalla.parser.NamespaceParser
import dev.stalla.util.FeedNamespace
import dev.stalla.util.InternalApi
import org.w3c.dom.Node

/**
 * Parser implementation for the Podlove Simple Chapter namespace.
 *
 * The namespace URI is: `http://podlove.org/simple-chapters`
 */
@InternalApi
internal object PodloveSimpleChapterParser : NamespaceParser() {

    override val namespace = FeedNamespace.PODLOVE_SIMPLE_CHAPTER

    override fun Node.parseChannelData(builder: PodcastBuilder) {
        // No-op
    }

    override fun Node.parseItemData(builder: EpisodeBuilder) {
        when (localName) {
            "chapters" -> {
                val chapters = ifCanBeParsed { toPodloveSimpleChapterBuilders(builder) } ?: return
                builder.podloveBuilder.addSimpleChapterBuilders(chapters)
            }
            else -> pass
        }
    }

    private fun Node.toPodloveSimpleChapterBuilders(builder: EpisodeBuilder): List<EpisodePodloveSimpleChapterBuilder> =
        childNodes.asListOfNodes().asSequence()
            .filter { c -> c.localName == "chapter" }
            .map { node -> node.ifCanBeParsed { toPodloveSimpleChapterBuilder(builder.createPodloveSimpleChapterBuilder()) } }
            .filterNotNull()
            .toList()

    private fun Node.toPodloveSimpleChapterBuilder(chapterBuilder: EpisodePodloveSimpleChapterBuilder): EpisodePodloveSimpleChapterBuilder? {
        val start = getAttributeValueByName("start")
        val title = getAttributeValueByName("title")
        if (start == null || title == null) return null

        return chapterBuilder.start(start)
            .title(title)
            .href(getAttributeValueByName("href"))
            .image(getAttributeValueByName("image"))
    }
}