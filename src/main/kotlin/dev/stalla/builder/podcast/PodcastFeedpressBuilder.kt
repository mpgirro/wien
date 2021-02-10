package dev.stalla.builder.podcast

import dev.stalla.builder.Builder
import dev.stalla.model.Podcast
import dev.stalla.util.whenNotNull

/** Builder for constructing [Podcast.Feedpress] instances. */
public interface PodcastFeedpressBuilder : Builder<Podcast.Feedpress> {

    /** Set the newsletterId value. */
    public fun newsletterId(newsletterId: String?): PodcastFeedpressBuilder

    /** Set the locale value. */
    public fun locale(locale: String?): PodcastFeedpressBuilder

    /** Set the podcastId value. */
    public fun podcastId(podcastId: String?): PodcastFeedpressBuilder

    /** Set the cssFile value. */
    public fun cssFile(cssFile: String?): PodcastFeedpressBuilder

    /** Set the link value. */
    public fun link(link: String?): PodcastFeedpressBuilder

    override fun from(model: Podcast.Feedpress?): PodcastFeedpressBuilder = whenNotNull(model) { feedpress ->
        newsletterId(feedpress.newsletterId)
        locale(feedpress.locale)
        podcastId(feedpress.podcastId)
        cssFile(feedpress.cssFile)
        link(feedpress.link)
    }
}