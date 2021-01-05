package io.hemin.wien.builder.validating.podcast

import io.hemin.wien.builder.HrefOnlyImageBuilder
import io.hemin.wien.builder.ITunesCategoryBuilder
import io.hemin.wien.builder.podcast.PodcastGooglePlayBuilder
import io.hemin.wien.model.Podcast

internal class ValidatingPodcastGooglePlayBuilder : PodcastGooglePlayBuilder {

    private var author: String? = null
    private var owner: String? = null
    private var categoryBuilders: MutableList<ITunesCategoryBuilder> = mutableListOf()
    private var description: String? = null
    private var explicit: Boolean? = null
    private var block: Boolean? = null
    private var imageBuilder: HrefOnlyImageBuilder? = null

    override fun author(author: String?): PodcastGooglePlayBuilder = apply { this.author = author }

    override fun owner(email: String?): PodcastGooglePlayBuilder = apply { this.owner = email }

    override fun addCategoryBuilder(categoryBuilder: ITunesCategoryBuilder): PodcastGooglePlayBuilder = apply {
        categoryBuilders.add(categoryBuilder)
    }

    override fun description(description: String?): PodcastGooglePlayBuilder = apply { this.description = description }

    override fun explicit(explicit: Boolean?): PodcastGooglePlayBuilder = apply { this.explicit = explicit }

    override fun block(block: Boolean?): PodcastGooglePlayBuilder = apply { this.block = block }

    override fun imageBuilder(imageBuilder: HrefOnlyImageBuilder?): PodcastGooglePlayBuilder = apply { this.imageBuilder = imageBuilder }

    override fun build(): Podcast.GooglePlay? {
        val categories = categoryBuilders.mapNotNull { it.build() }
        if (categories.isEmpty() && allNull(author, owner, description, explicit, block, imageBuilder)) {
            return null
        }

        return Podcast.GooglePlay(
            author = author,
            owner = owner,
            categories = categories,
            description = description,
            explicit = explicit,
            block = block,
            image = imageBuilder?.build()
        )
    }
}
