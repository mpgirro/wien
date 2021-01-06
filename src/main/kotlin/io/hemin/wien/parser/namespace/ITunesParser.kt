package io.hemin.wien.parser.namespace

import io.hemin.wien.builder.PersonBuilder
import io.hemin.wien.builder.episode.EpisodeBuilder
import io.hemin.wien.builder.podcast.PodcastBuilder
import io.hemin.wien.dom.parseAsInt
import io.hemin.wien.dom.textAsBooleanOrNull
import io.hemin.wien.dom.textOrNull
import io.hemin.wien.dom.toHrefOnlyImageBuilder
import io.hemin.wien.dom.toITunesCategoryBuilder
import io.hemin.wien.dom.toPersonBuilder
import io.hemin.wien.parser.NamespaceParser
import io.hemin.wien.util.FeedNamespace
import org.w3c.dom.Node

/**
 * Parser implementation for the iTunes namespace.
 *
 * The namespace URI is: `http://www.itunes.com/dtds/podcast-1.0.dtd`
 */
internal class ITunesParser : NamespaceParser() {

    override val namespace = FeedNamespace.ITUNES

    override fun Node.parseChannelData(builder: PodcastBuilder) {
        when (localName) {
            "author" -> builder.iTunes.author(ifCanBeParsed { textOrNull() })
            "block" -> builder.iTunes.block(ifCanBeParsed { textAsBooleanOrNull() })
            "category" -> {
                val categoryBuilder = ifCanBeParsed {
                    toITunesCategoryBuilder(builder.createITunesCategoryBuilder(), namespace)
                } ?: return
                builder.iTunes.addCategoryBuilder(categoryBuilder)
            }
            "complete" -> builder.iTunes.complete(ifCanBeParsed { textAsBooleanOrNull() })
            "explicit" -> {
                val explicit = ifCanBeParsed { textAsBooleanOrNull() } ?: return
                builder.iTunes.explicit(explicit)
            }
            "image" -> {
                val image = ifCanBeParsed { toHrefOnlyImageBuilder(builder.createHrefOnlyImageBuilder()) } ?: return
                builder.iTunes.imageBuilder(image)
            }
            "keywords" -> builder.iTunes.keywords(ifCanBeParsed { textOrNull() })
            "owner" -> {
                val ownerBuilder = ifCanBeParsed { toOwnerBuilder(builder.createPersonBuilder()) }
                builder.iTunes.ownerBuilder(ownerBuilder)
            }
            "subtitle" -> builder.iTunes.subtitle(ifCanBeParsed { textOrNull() })
            "summary" -> builder.iTunes.summary(ifCanBeParsed { textOrNull() })
            "type" -> builder.iTunes.type(ifCanBeParsed { textOrNull() })
            "title" -> builder.iTunes.title(ifCanBeParsed { textOrNull() })
            "new-feed-url" -> builder.iTunes.newFeedUrl(ifCanBeParsed { textOrNull() })
            else -> pass
        }
    }

    private fun Node.toOwnerBuilder(personBuilder: PersonBuilder): PersonBuilder {
        toPersonBuilder(personBuilder, namespace)
        personBuilder.uri(null) // <itunes:owner> tags don't support <uri> tags
        return personBuilder
    }

    override fun Node.parseItemData(builder: EpisodeBuilder) {
        when (localName) {
            "block" -> builder.iTunes.block(ifCanBeParsed { textAsBooleanOrNull() })
            "duration" -> builder.iTunes.duration(ifCanBeParsed { textOrNull() })
            "episode" -> builder.iTunes.episode(ifCanBeParsed { parseAsInt() })
            "episodeType" -> builder.iTunes.episodeType(ifCanBeParsed { textOrNull() })
            "explicit" -> builder.iTunes.explicit(ifCanBeParsed { textAsBooleanOrNull() })
            "image" -> {
                val imageBuilder = toHrefOnlyImageBuilder(builder.createHrefOnlyImageBuilder())
                builder.iTunes.imageBuilder(imageBuilder)
            }
            "season" -> builder.iTunes.season(ifCanBeParsed { parseAsInt() })
            "title" -> builder.iTunes.title(ifCanBeParsed { textOrNull() })
            "author" -> builder.iTunes.author(ifCanBeParsed { textOrNull() })
            "subtitle" -> builder.iTunes.subtitle(ifCanBeParsed { textOrNull() })
            "summary" -> builder.iTunes.summary(ifCanBeParsed { textOrNull() })
            else -> pass
        }
    }
}
