package dev.stalla.model.podcastindex

import dev.stalla.builder.podcast.PodcastPodcastindexFundingBuilder
import dev.stalla.builder.validating.podcast.ValidatingPodcastPodcastindexFundingBuilder
import dev.stalla.model.BuilderFactory

/**
 * The funding information for the podcast.
 *
 * @param url The URL where listeners can find funding information for the podcast.
 * @param message The recommended CTA text to show users for the funding link.
 */
public data class Funding(
    val url: String,
    val message: String
) {

    public companion object Factory : BuilderFactory<Funding, PodcastPodcastindexFundingBuilder> {

        /** Returns a builder implementation for building [Funding] model instances. */
        @JvmStatic
        override fun builder(): PodcastPodcastindexFundingBuilder = ValidatingPodcastPodcastindexFundingBuilder()
    }
}