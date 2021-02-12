package dev.stalla.writer.namespace

import assertk.assertAll
import assertk.assertThat
import assertk.assertions.hasSize
import dev.stalla.hasNoChildren
import dev.stalla.hasNoDifferences
import dev.stalla.hasValue
import dev.stalla.model.HrefOnlyImage
import dev.stalla.model.anHrefOnlyImage
import dev.stalla.model.episode.anEpisode
import dev.stalla.model.episode.anEpisodeGoogleplay
import dev.stalla.model.itunes.ItunesCategory
import dev.stalla.model.podcast.aPodcast
import dev.stalla.model.podcast.aPodcastGoogleplay
import org.junit.jupiter.api.Test

internal class GoogleplayWriterTest : NamespaceWriterTest() {

    override val writer = GoogleplayWriter

    @Test
    internal fun `should write the correct googleplay tags to the channel when there is data to write`() {
        assertAll {
            writePodcastData("author") { element ->
                val diff = element.diffFromExpected("/rss/channel/googleplay:author")
                assertThat(diff).hasNoDifferences()
            }
            writePodcastData("owner") { element ->
                val diff = element.diffFromExpected("/rss/channel/googleplay:owner")
                assertThat(diff).hasNoDifferences()
            }
            writePodcastData("category") { element ->
                val diff = element.diffFromExpected("/rss/channel/googleplay:category")
                assertThat(diff).hasNoDifferences()
            }
            writePodcastData("description") { element ->
                val diff = element.diffFromExpected("/rss/channel/googleplay:description")
                assertThat(diff).hasNoDifferences()
            }
            writePodcastData("explicit") { element ->
                val diff = element.diffFromExpected("/rss/channel/googleplay:explicit")
                assertThat(diff).hasNoDifferences()
            }
            writePodcastData("block") { element ->
                val diff = element.diffFromExpected("/rss/channel/googleplay:block")
                assertThat(diff).hasNoDifferences()
            }
            writePodcastData("image") { element ->
                val diff = element.diffFromExpected("/rss/channel/googleplay:image")
                assertThat(diff).hasNoDifferences()
            }
        }
    }

    @Test
    internal fun `should not write googleplay tags to the channel when there is no data to write`() {
        assertAll {
            assertTagIsNotWrittenToPodcast(aPodcast(googleplay = null), "author")
            assertTagIsNotWrittenToPodcast(aPodcast(googleplay = null), "owner")
            assertTagIsNotWrittenToPodcast(aPodcast(googleplay = null), "category")
            assertTagIsNotWrittenToPodcast(aPodcast(googleplay = null), "description")
            assertTagIsNotWrittenToPodcast(aPodcast(googleplay = null), "explicit")
            assertTagIsNotWrittenToPodcast(aPodcast(googleplay = null), "block")
            assertTagIsNotWrittenToPodcast(aPodcast(googleplay = null), "image")
        }
    }

    @Test
    internal fun `should not write googleplay tags to the channel when the data is blank`() {
        val categories = listOf(
            ItunesCategory.Simple(" "),
            ItunesCategory.Nested(" ", ItunesCategory.Simple("subcategory")),
            ItunesCategory.Nested("nested", ItunesCategory.Simple(" "))
        )
        val podcast = aPodcast(
            googleplay = aPodcastGoogleplay(
                author = " ",
                owner = " ",
                description = " ",
                categories = categories,
                image = HrefOnlyImage(" "),
                block = false,
                explicit = null
            )
        )
        assertAll {
            assertTagIsNotWrittenToPodcast(podcast, "author")
            assertTagIsNotWrittenToPodcast(podcast, "owner")
            writePodcastDataXPathMultiple("//googleplay:category", podcast) { elements ->
                assertThat(elements).hasSize(1)
            }
            writePodcastData("category", podcast = podcast) { element ->
                assertThat(element, "category element").hasAttribute("text", namespace = null).hasValue("nested")
                assertThat(element, "category element children").hasNoChildren()
            }
            assertTagIsNotWrittenToPodcast(podcast, "description")
            assertTagIsNotWrittenToPodcast(podcast, "explicit")
            assertTagIsNotWrittenToPodcast(podcast, "block")
            assertTagIsNotWrittenToPodcast(podcast, "image")
        }
    }

    @Test
    internal fun `should not write googleplay tags to the channel when the data is empty`() {
        val categories = listOf(
            ItunesCategory.Simple(""),
            ItunesCategory.Nested("", ItunesCategory.Simple("subcategory")),
            ItunesCategory.Nested("nested", ItunesCategory.Simple(""))
        )
        val podcast = aPodcast(
            googleplay = aPodcastGoogleplay(
                author = "",
                owner = "",
                description = "",
                categories = categories,
                image = HrefOnlyImage(""),
                block = false,
                explicit = null
            )
        )
        assertAll {
            assertTagIsNotWrittenToPodcast(podcast, "author")
            assertTagIsNotWrittenToPodcast(podcast, "owner")
            writePodcastDataXPathMultiple("//googleplay:category", podcast) { elements ->
                assertThat(elements).hasSize(1)
            }
            writePodcastData("category", podcast = podcast) { element ->
                assertThat(element, "category element").hasAttribute("text", namespace = null).hasValue("nested")
                assertThat(element, "category element children").hasNoChildren()
            }
            assertTagIsNotWrittenToPodcast(podcast, "description")
            assertTagIsNotWrittenToPodcast(podcast, "explicit")
            assertTagIsNotWrittenToPodcast(podcast, "block")
            assertTagIsNotWrittenToPodcast(podcast, "image")
        }
    }

    @Test
    internal fun `should write the correct googleplay tags to the item when there is data to write`() {
        assertAll {
            writeEpisodeData("description") { element ->
                val diff = element.diffFromExpected("/rss/channel/item[1]/googleplay:description")
                assertThat(diff).hasNoDifferences()
            }
            writeEpisodeData("explicit") { element ->
                val diff = element.diffFromExpected("/rss/channel/item[1]/googleplay:explicit")
                assertThat(diff).hasNoDifferences()
            }
            writeEpisodeData("block") { element ->
                val diff = element.diffFromExpected("/rss/channel/item[1]/googleplay:block")
                assertThat(diff).hasNoDifferences()
            }
            writeEpisodeData("image") { element ->
                val diff = element.diffFromExpected("/rss/channel/item[1]/googleplay:image")
                assertThat(diff).hasNoDifferences()
            }
        }
    }

    @Test
    internal fun `should not write googleplay tags to the item when there is no data to write`() {
        val episode = anEpisode(googleplay = null)
        assertAll {
            assertTagIsNotWrittenToEpisode(episode, "description")
            assertTagIsNotWrittenToEpisode(episode, "explicit")
            assertTagIsNotWrittenToEpisode(episode, "block")
            assertTagIsNotWrittenToEpisode(episode, "image")
        }
    }

    @Test
    internal fun `should not write googleplay tags to the item when the data is blank`() {
        val episode = anEpisode(googleplay = anEpisodeGoogleplay(description = " ", image = anHrefOnlyImage(" "), block = false, explicit = null))
        assertAll {
            assertTagIsNotWrittenToEpisode(episode, "description")
            assertTagIsNotWrittenToEpisode(episode, "explicit")
            assertTagIsNotWrittenToEpisode(episode, "block")
            assertTagIsNotWrittenToEpisode(episode, "image")
        }
    }

    @Test
    internal fun `should not write googleplay tags to the item when the data is empty`() {
        val episode = anEpisode(googleplay = anEpisodeGoogleplay(description = "", image = anHrefOnlyImage(""), block = false, explicit = null))
        assertAll {
            assertTagIsNotWrittenToEpisode(episode, "description")
            assertTagIsNotWrittenToEpisode(episode, "explicit")
            assertTagIsNotWrittenToEpisode(episode, "block")
            assertTagIsNotWrittenToEpisode(episode, "image")
        }
    }
}
