package io.hemin.wien.builder

import io.hemin.wien.model.Episode
import java.util.Date

/** Builder class for [Episode] instances. */
class EpisodeBuilder : Builder<Episode> {

    private lateinit var titleValue: String
    private lateinit var enclosureValue: Episode.Enclosure

    private var link: String? = null
    private var description: String? = null
    private var author: String? = null
    private val categories: MutableList<String> = mutableListOf()
    private var comments: String? = null
    private var guid: Episode.Guid? = null
    private var pubDate: Date? = null
    private var source: String? = null

    /** The builder for data from the Content namespace. */
    val content: EpisodeContentBuilder = EpisodeContentBuilder()

    /** The builder for data from the iTunes namespace. */
    val itunes: EpisodeItunesBuilder = EpisodeItunesBuilder()

    /** The builder for data from the Atom namespace. */
    val atom: EpisodeAtomBuilder = EpisodeAtomBuilder()

    /** The builder for data from namespaces of the Podlove standards. */
    val podlove: EpisodePodloveBuilder = EpisodePodloveBuilder()

    /** The builder for data from the Google Play namespace. */
    val googlePlay: EpisodeGooglePlayBuilder = EpisodeGooglePlayBuilder()

    /** The builder for data from the Bitlove namespace. */
    val bitlove: EpisodeBitloveBuilder = EpisodeBitloveBuilder()

    /** Set the title value. */
    fun title(title: String) = apply { this.titleValue = title }

    /** Set the link value. */
    fun link(link: String?) = apply { this.link = link }

    /** Set the description value. */
    fun description(description: String?) = apply { this.description = description }

    /** Set the author value. */
    fun author(author: String?) = apply { this.author = author }

    /**
     * Adds a category to the list of categories.
     *
     * @param category The comment to add.
     */
    fun addCategory(category: String?) = apply {
        category?.let { this.categories.add(it) }
    }

    /** Set the comments value. */
    fun comments(comments: String?) = apply { this.comments = comments }

    /**
     * Set the Enclosure.
     *
     * @param enclosure The data of an `<enclosure>` element held in a [Episode.Enclosure].
     */
    fun enclosure(enclosure: Episode.Enclosure) = apply { this.enclosureValue = enclosure }

    /** Set the Guid. */
    fun guid(guid: Episode.Guid?) = apply { this.guid = guid }

    /** Set the pubDate value. */
    fun pubDate(pubDate: Date?) = apply { this.pubDate = pubDate }

    /** Set the source value. */
    fun source(source: String?) = apply { this.source = source }

    /**
     * Creates an instance of [Episode] with the properties set in this builder.
     *
     * @return The create instance.
     */
    override fun build(): Episode {
        require(::titleValue.isInitialized) { "The episode title is mandatory" }
        require(::enclosureValue.isInitialized) { "The episode enclosure is mandatory" }

        return Episode(
            title = titleValue,
            link = link,
            description = description,
            author = author,
            categories = immutableCopyOf(categories),
            comments = comments,
            enclosure = enclosureValue,
            guid = guid,
            pubDate = pubDate,
            source = source,
            content = content.build(),
            itunes = itunes.build(),
            atom = atom.build(),
            podlove = podlove.build(),
            googlePlay = googlePlay.build(),
            bitlove = bitlove.build()
        )
    }
}
