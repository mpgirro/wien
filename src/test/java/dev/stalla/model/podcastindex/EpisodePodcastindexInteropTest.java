package dev.stalla.model.podcastindex;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static dev.stalla.model.EpisodeFixturesKt.anEpisodePodcastindexPerson;
import static dev.stalla.model.EpisodeFixturesKt.anEpisodePodcastindex;
import static dev.stalla.model.EpisodeFixturesKt.anEpisodePodcastindexSoundbite;
import static dev.stalla.model.EpisodeFixturesKt.anEpisodePodcastindexTranscript;
import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EpisodePodcastindexInteropTest {

    private final EpisodePodcastindex episodePodcastindex;

    public EpisodePodcastindexInteropTest() {
        // Add extra elements to list properties, because for a single element
        // Kotlin's listOf() method produces an unmodifiable list by default
        episodePodcastindex = EpisodePodcastindex.builder()
            .applyFrom(anEpisodePodcastindex())
            .addSoundbiteBuilder(Soundbite.builder().applyFrom(anEpisodePodcastindexSoundbite()))
            .addTranscriptBuilder(Transcript.builder().applyFrom(anEpisodePodcastindexTranscript()))
            .build();
    }

    @Test
    @DisplayName("should build an unmodifiable list of Episode Podcastindex soundbites")
    void shouldBuildUnmodifiableEpisodePodcastindexSoundbites() {
        final List<Soundbite> soundbites = requireNonNull(episodePodcastindex.getSoundbites());
        assertThrows(UnsupportedOperationException.class, () -> soundbites.add(anEpisodePodcastindexSoundbite()));
    }

    @Test
    @DisplayName("should build an unmodifiable list of Episode Podcastindex transcripts")
    void shouldBuildUnmodifiableEpisodePodcastindexTranscripts() {
        final List<Transcript> transcripts = requireNonNull(episodePodcastindex.getTranscripts());
        assertThrows(UnsupportedOperationException.class, () -> transcripts.add(anEpisodePodcastindexTranscript()));
    }

    @Test
    @DisplayName("should build an unmodifiable list of Episode Podcastindex persons")
    void shouldParseEpisodePodcastindexUnmodifiablePerson() {
        final List<PodcastindexPerson> personList = requireNonNull(episodePodcastindex.getPersons());
        assertThrows(UnsupportedOperationException.class, () -> personList.add(anEpisodePodcastindexPerson()));
    }

}
