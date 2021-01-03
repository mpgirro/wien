package io.hemin.wien.parser

import assertk.all
import assertk.assertThat
import assertk.assertions.containsExactly
import assertk.assertions.isEmpty
import assertk.assertions.isEqualTo
import assertk.assertions.isNull
import assertk.assertions.prop
import io.hemin.wien.builder.fake.FakeImageBuilder
import io.hemin.wien.builder.fake.episode.FakeEpisodeBuilder
import io.hemin.wien.builder.fake.episode.FakeEpisodeEnclosureBuilder
import io.hemin.wien.builder.fake.episode.FakeEpisodeGuidBuilder
import io.hemin.wien.builder.fake.podcast.FakePodcastBuilder
import io.hemin.wien.parser.namespace.RssParser
import org.junit.jupiter.api.Test
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

/** Provides unit tests for [RssParser]. */
internal class RssParserTest : NamespaceParserTest() {

    override val parser = RssParser()

    private val expectedDate: Date = Calendar.Builder()
        .setLocale(Locale.ENGLISH)
        .setTimeZone(TimeZone.getTimeZone("UTC"))
        .setDate(2018, Calendar.MARCH, 16) // "Fri, 16 Mar 2018 22:49:08 +0000"
        .setTimeOfDay(22, 49, 8)
        .build()
        .time

    private val expectedEnclosureBuilder = FakeEpisodeEnclosureBuilder().apply {
        urlValue = "http://example.org/episode1.m4a"
        lengthValue = 78589133
        typeValue = "audio/mp4"
    }

    private val expectedGuidBuilder = FakeEpisodeGuidBuilder().apply {
        textContent = "1fa609024fdf097"
        isPermalink = true
    }

    private val expectedImageBuilder = FakeImageBuilder().apply {
        urlValue = "http://example.org/podcast-cover.jpg"
        title = "Lorem Ipsum"
        link = "http://example.org"
        width = 600
        height = 600
        description = "Lorem Ipsum"
    }

    @Test
    fun `should extract all RSS fields from channel when present`() {
        val node = nodeFromResource("channel", "/xml/channel.xml")
        val builder = FakePodcastBuilder()
        parseChannelNode(builder, node)

        assertThat(builder, "channel").all {
            prop(FakePodcastBuilder::titleValue).isEqualTo("Lorem Ipsum")
            prop(FakePodcastBuilder::linkValue).isEqualTo("http://example.org")
            prop(FakePodcastBuilder::descriptionValue).isEqualTo("Lorem Ipsum")
            prop(FakePodcastBuilder::pubDate).isEqualTo(expectedDate)
            prop(FakePodcastBuilder::lastBuildDate).isEqualTo(expectedDate)
            prop(FakePodcastBuilder::languageValue).isEqualTo("de-DE")
            prop(FakePodcastBuilder::generator).isEqualTo("Lorem Ipsum")
            prop(FakePodcastBuilder::copyright).isEqualTo("Lorem Ipsum")
            prop(FakePodcastBuilder::docs).isEqualTo("Lorem Ipsum")
            prop(FakePodcastBuilder::managingEditor).isEqualTo("editor@example.org")
            prop(FakePodcastBuilder::webMaster).isEqualTo("webmaster@example.org")
            prop(FakePodcastBuilder::imageBuilder).isEqualTo(expectedImageBuilder)
        }
    }

    @Test
    fun `should not extract RSS fields from channel when absent`() {
        val node = nodeFromResource("channel", "/xml/channel-incomplete.xml")
        val builder = FakePodcastBuilder()
        parseChannelNode(builder, node)

        assertThat(builder, "channel").all {
            prop(FakePodcastBuilder::titleValue).isEqualTo("Lorem Ipsum")
            prop(FakePodcastBuilder::linkValue).isEqualTo("http://example.org")
            prop(FakePodcastBuilder::descriptionValue).isEqualTo("Lorem Ipsum")
            prop(FakePodcastBuilder::pubDate).isNull()
            prop(FakePodcastBuilder::lastBuildDate).isNull()
            prop(FakePodcastBuilder::languageValue).isNull()
            prop(FakePodcastBuilder::generator).isNull()
            prop(FakePodcastBuilder::copyright).isNull()
            prop(FakePodcastBuilder::docs).isNull()
            prop(FakePodcastBuilder::managingEditor).isNull()
            prop(FakePodcastBuilder::webMaster).isNull()
            prop(FakePodcastBuilder::imageBuilder).isNull()
        }
    }

    @Test
    fun `should extract all RSS fields from item when present`() {
        val node = nodeFromResource("item", "/xml/item.xml")
        val builder = FakeEpisodeBuilder()
        parseItemNode(builder, node)

        assertThat(builder, "item").all {
            prop(FakeEpisodeBuilder::titleValue).isEqualTo("Lorem Ipsum")
            prop(FakeEpisodeBuilder::link).isEqualTo("http://example.org/episode1")
            prop(FakeEpisodeBuilder::description).isEqualTo("Lorem Ipsum")
            prop(FakeEpisodeBuilder::pubDate).isEqualTo(expectedDate)
            prop(FakeEpisodeBuilder::author).isEqualTo("author@example.org")
            prop(FakeEpisodeBuilder::comments).isEqualTo("http://example.org/episode1/comments")
            prop(FakeEpisodeBuilder::categories).containsExactly("category1", "category2")
            prop(FakeEpisodeBuilder::enclosureBuilderValue).isEqualTo(expectedEnclosureBuilder)
            prop(FakeEpisodeBuilder::guidBuilder).isEqualTo(expectedGuidBuilder)
            prop(FakeEpisodeBuilder::source).isEqualTo("http://example.org/rss")
        }
    }

    @Test
    fun `should not extract RSS fields from item when absent`() {
        val node = nodeFromResource("item", "/xml/item-incomplete.xml")
        val builder = FakeEpisodeBuilder()
        parseItemNode(builder, node)

        assertThat(builder, "item").all {
            prop(FakeEpisodeBuilder::titleValue).isEqualTo("Lorem Ipsum")
            prop(FakeEpisodeBuilder::link).isEqualTo("http://example.org/episode1")
            prop(FakeEpisodeBuilder::description).isEqualTo("Lorem Ipsum")
            prop(FakeEpisodeBuilder::pubDate).isNull()
            prop(FakeEpisodeBuilder::author).isNull()
            prop(FakeEpisodeBuilder::comments).isNull()
            prop(FakeEpisodeBuilder::categories).isEmpty()
            prop(FakeEpisodeBuilder::enclosureBuilderValue).isNull()
            prop(FakeEpisodeBuilder::guidBuilder).isNull()
            prop(FakeEpisodeBuilder::source).isNull()
        }
    }
}
