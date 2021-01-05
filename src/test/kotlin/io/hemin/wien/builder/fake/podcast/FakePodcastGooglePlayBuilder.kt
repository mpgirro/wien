package io.hemin.wien.builder.fake.podcast

import io.hemin.wien.builder.ImageBuilder
import io.hemin.wien.builder.fake.FakeBuilder
import io.hemin.wien.builder.podcast.PodcastGooglePlayBuilder
import io.hemin.wien.model.Podcast

@Suppress("MemberVisibilityCanBePrivate", "Unused")
internal class FakePodcastGooglePlayBuilder : FakeBuilder<Podcast.GooglePlay>(), PodcastGooglePlayBuilder {

    var author: String? = null
    var owner: String? = null
    var categories: MutableList<String> = mutableListOf()
    var description: String? = null
    var explicit: Boolean? = null
    var block: Boolean? = null
    var imageBuilder: ImageBuilder? = null

    override fun author(author: String?): PodcastGooglePlayBuilder = apply { this.author = author }

    override fun owner(email: String?): PodcastGooglePlayBuilder = apply { this.owner = email }

    override fun addCategory(category: String): PodcastGooglePlayBuilder = apply {
        categories.add(category)
    }

    override fun description(description: String?): PodcastGooglePlayBuilder = apply { this.description = description }

    override fun explicit(explicit: Boolean?): PodcastGooglePlayBuilder = apply { this.explicit = explicit }

    override fun block(block: Boolean?): PodcastGooglePlayBuilder = apply { this.block = block }

    override fun imageBuilder(imageBuilder: ImageBuilder?): PodcastGooglePlayBuilder = apply { this.imageBuilder = imageBuilder }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FakePodcastGooglePlayBuilder) return false

        if (author != other.author) return false
        if (owner != other.owner) return false
        if (categories != other.categories) return false
        if (description != other.description) return false
        if (explicit != other.explicit) return false
        if (block != other.block) return false
        if (imageBuilder != other.imageBuilder) return false

        return true
    }

    override fun hashCode(): Int {
        var result = author?.hashCode() ?: 0
        result = 31 * result + (owner?.hashCode() ?: 0)
        result = 31 * result + categories.hashCode()
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + (explicit?.hashCode() ?: 0)
        result = 31 * result + (block?.hashCode() ?: 0)
        result = 31 * result + (imageBuilder?.hashCode() ?: 0)
        return result
    }

    override fun toString() =
        "FakePodcastGooglePlayBuilder(author=$author, owner=$owner, categories=$categories, description=$description, " +
                "explicit=$explicit, block=$block, imageBuilder=$imageBuilder)"

}
