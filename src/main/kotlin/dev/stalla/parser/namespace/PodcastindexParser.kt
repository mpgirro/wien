package dev.stalla.parser.namespace

import dev.stalla.builder.episode.EpisodePodcastindexChaptersBuilder
import dev.stalla.builder.episode.EpisodePodcastindexSoundbiteBuilder
import dev.stalla.builder.episode.EpisodePodcastindexTranscriptBuilder
import dev.stalla.builder.episode.ProvidingEpisodeBuilder
import dev.stalla.builder.podcast.PodcastPodcastindexFundingBuilder
import dev.stalla.builder.podcast.PodcastPodcastindexLockedBuilder
import dev.stalla.builder.podcast.ProvidingPodcastBuilder
import dev.stalla.dom.getAttributeByName
import dev.stalla.dom.getAttributeValueByName
import dev.stalla.dom.parseAsMediaTypeOrNull
import dev.stalla.dom.textAsBooleanOrNull
import dev.stalla.model.StyledDuration
import dev.stalla.model.podcastindex.TranscriptType
import dev.stalla.parser.NamespaceParser
import dev.stalla.util.FeedNamespace
import dev.stalla.util.InternalApi
import dev.stalla.util.allNotNull
import dev.stalla.util.trimmedOrNullIfBlank
import org.w3c.dom.Node
import java.util.Locale

/**
 * Parser implementation for the PodcastIndex namespace.
 *
 * The namespace URI is: `https://podcastindex.org/namespace/1.0`
 */
@InternalApi
internal object PodcastindexParser : NamespaceParser() {

    override val namespace: FeedNamespace = FeedNamespace.PODCAST

    override fun Node.parseChannelData(builder: ProvidingPodcastBuilder) {
        when (localName) {
            "locked" -> {
                val lockedBuilder = ifCanBeParsed {
                    toLockedBuilder(builder.createLockedBuilder())
                } ?: return
                builder.podcastPodcastindexBuilder.lockedBuilder(lockedBuilder)
            }
            "funding" -> {
                val fundingBuilder = ifCanBeParsed {
                    toFundingBuilder(builder.createFundingBuilder())
                } ?: return
                builder.podcastPodcastindexBuilder.addFundingBuilder(fundingBuilder)
            }
            else -> pass
        }
    }

    private fun Node.toLockedBuilder(
        lockedBuilder: PodcastPodcastindexLockedBuilder
    ): PodcastPodcastindexLockedBuilder? {
        val owner = getAttributeByName("owner")?.value.trimmedOrNullIfBlank()
        val locked = textAsBooleanOrNull()

        if (!allNotNull(owner, locked)) return null
        return lockedBuilder.owner(owner)
            .locked(locked)
    }

    private fun Node.toFundingBuilder(
        fundingBuilder: PodcastPodcastindexFundingBuilder
    ): PodcastPodcastindexFundingBuilder? {
        val url = getAttributeByName("url")?.value.trimmedOrNullIfBlank()
        val message = textContent.trimmedOrNullIfBlank()

        if (!allNotNull(url, message)) return null
        return fundingBuilder.url(url)
            .message(message)
    }

    override fun Node.parseItemData(builder: ProvidingEpisodeBuilder) {
        when (localName) {
            "chapters" -> {
                val chaptersBuilder = ifCanBeParsed {
                    toChaptersBuilder(builder.createChaptersBuilder())
                } ?: return
                builder.podcastindexBuilder.chaptersBuilder(chaptersBuilder)
            }
            "soundbite" -> {
                val soundbiteBuilder = ifCanBeParsed {
                    toSoundbiteBuilder(builder.createSoundbiteBuilder())
                } ?: return
                builder.podcastindexBuilder.addSoundbiteBuilder(soundbiteBuilder)
            }
            "transcript" -> {
                val transcriptBuilder = ifCanBeParsed {
                    toTranscriptBuilder(builder.createTranscriptBuilder())
                } ?: return
                builder.podcastindexBuilder.addTranscriptBuilder(transcriptBuilder)
            }
            else -> pass
        }
    }

    private fun Node.toChaptersBuilder(
        chaptersBuilder: EpisodePodcastindexChaptersBuilder
    ): EpisodePodcastindexChaptersBuilder? {
        val url = getAttributeValueByName("url").trimmedOrNullIfBlank()
        val type = getAttributeValueByName("type").parseAsMediaTypeOrNull()

        if (!allNotNull(url, type)) return null
        return chaptersBuilder.url(url)
            .type(type)
    }

    private fun Node.toSoundbiteBuilder(
        soundbiteBuilder: EpisodePodcastindexSoundbiteBuilder
    ): EpisodePodcastindexSoundbiteBuilder? {
        val startTime = StyledDuration.of(getAttributeByName("startTime")?.value.trimmedOrNullIfBlank())
            ?.ensureFractionalFormat()
        val duration = StyledDuration.of(getAttributeByName("duration")?.value.trimmedOrNullIfBlank())
            ?.ensureFractionalFormat()
        val title = textContent?.trimmedOrNullIfBlank()

        if (startTime !is StyledDuration.SecondsAndFraction || startTime.isNegative) return null
        if (duration !is StyledDuration.SecondsAndFraction || !duration.isStrictlyPositive) return null

        return soundbiteBuilder.startTime(startTime)
            .duration(duration)
            .title(title)
    }

    private fun StyledDuration.ensureFractionalFormat(): StyledDuration.SecondsAndFraction =
        if (this !is StyledDuration.SecondsAndFraction) {
            StyledDuration.SecondsAndFraction(rawDuration)
        } else this

    private fun Node.toTranscriptBuilder(
        transcriptBuilder: EpisodePodcastindexTranscriptBuilder
    ): EpisodePodcastindexTranscriptBuilder? {
        val url = getAttributeByName("url")?.value.trimmedOrNullIfBlank()
        val type = getAttributeByName("type")?.value.trimmedOrNullIfBlank()?.let { rawType ->
            TranscriptType.of(rawType)
        }
        val language = getAttributeByName("language")?.value.trimmedOrNullIfBlank()?.let { rawLocale ->
            Locale.forLanguageTag(rawLocale)
        }
        val rel = getAttributeByName("rel")?.value.trimmedOrNullIfBlank()

        if (!allNotNull(url, type)) return null
        return transcriptBuilder.url(url)
            .type(type)
            .language(language)
            .rel(rel)
    }
}
