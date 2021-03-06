package dev.stalla.writer.namespace

import dev.stalla.dom.appendElement
import dev.stalla.dom.appendRssCategoryElements
import dev.stalla.dom.appendRssImageElement
import dev.stalla.model.Episode
import dev.stalla.model.Podcast
import dev.stalla.model.rss.Enclosure
import dev.stalla.model.rss.Guid
import dev.stalla.util.BooleanStringStyle
import dev.stalla.util.FeedNamespace
import dev.stalla.util.InternalAPI
import dev.stalla.util.asBooleanString
import dev.stalla.util.asDateString
import dev.stalla.util.isNeitherNullNorBlank
import dev.stalla.writer.NamespaceWriter
import org.w3c.dom.Element

/**
 * Writer implementation for the RSS namespace.
 *
 * Note that RSS 2.0 feeds do not have a namespace URI specified. The document specification is described here:
 *
 * `http://www.rssboard.org/rss-2-0`
 */
@InternalAPI
internal object RssWriter : NamespaceWriter() {

    /** Standard RSS 2.0 elements do not have a namespace. This value is therefore null. */
    override val namespace: FeedNamespace? = null

    @Suppress("ComplexMethod")
    override fun Element.appendPodcastData(podcast: Podcast) {
        if (podcast.title.isNotBlank()) {
            appendElement("title") { textContent = podcast.title.trim() }
        }

        if (podcast.link.isNotBlank()) {
            appendElement("link") { textContent = podcast.link.trim() }
        }

        if (podcast.description.isNotBlank()) {
            appendElement("description") { textContent = podcast.description.trim() }
        }

        if (podcast.pubDate != null) {
            appendElement("pubDate") { textContent = podcast.pubDate.asDateString() }
        }

        if (podcast.lastBuildDate != null) {
            appendElement("lastBuildDate") { textContent = podcast.lastBuildDate.asDateString() }
        }

        if (podcast.generator.isNeitherNullNorBlank()) {
            appendElement("generator") { textContent = podcast.generator.trim() }
        }

        appendElement("language") { textContent = podcast.language.toLanguageTag() }

        if (podcast.copyright.isNeitherNullNorBlank()) {
            appendElement("copyright") { textContent = podcast.copyright.trim() }
        }

        if (podcast.docs.isNeitherNullNorBlank()) {
            appendElement("docs") { textContent = podcast.docs.trim() }
        }

        if (podcast.managingEditor.isNeitherNullNorBlank()) {
            appendElement("managingEditor") { textContent = podcast.managingEditor.trim() }
        }

        if (podcast.webMaster.isNeitherNullNorBlank()) {
            appendElement("webMaster") { textContent = podcast.webMaster.trim() }
        }

        if (podcast.ttl != null) {
            appendElement("ttl") { textContent = podcast.ttl.toString() }
        }

        appendRssCategoryElements(podcast.categories)

        if (podcast.image != null) appendRssImageElement(podcast.image)
    }

    override fun Element.appendEpisodeData(episode: Episode) {
        if (episode.title.isNotBlank()) {
            appendElement("title") { textContent = episode.title.trim() }
        }

        if (episode.link.isNeitherNullNorBlank()) {
            appendElement("link") { textContent = episode.link.trim() }
        }

        if (episode.description.isNeitherNullNorBlank()) {
            appendElement("description") { textContent = episode.description.trim() }
        }

        if (episode.author.isNeitherNullNorBlank()) {
            appendElement("author") { textContent = episode.author.trim() }
        }

        appendRssCategoryElements(episode.categories)

        if (episode.comments.isNeitherNullNorBlank()) {
            appendElement("comments") { textContent = episode.comments.trim() }
        }

        appendEnclosureElement(episode.enclosure)

        if (episode.guid != null) {
            appendGuidElement(episode.guid)
        }

        if (episode.pubDate != null) {
            appendElement("pubDate") { textContent = episode.pubDate.asDateString() }
        }

        if (episode.source.isNeitherNullNorBlank()) {
            appendElement("source") { textContent = episode.source.trim() }
        }
    }

    private fun Element.appendEnclosureElement(enclosure: Enclosure) {
        if (enclosure.url.isBlank()) return
        appendElement("enclosure") {
            setAttribute("url", enclosure.url.trim())
            setAttribute("length", enclosure.length.toString())
            setAttribute("type", enclosure.type.toString())
        }
    }

    private fun Element.appendGuidElement(guid: Guid) {
        if (guid.guid.isBlank()) return
        appendElement("guid") {
            if (guid.isPermalink != null) {
                setAttribute("isPermalink", guid.isPermalink.asBooleanString(BooleanStringStyle.TRUE_FALSE))
            }
            textContent = guid.guid.trim()
        }
    }
}
