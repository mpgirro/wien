package dev.stalla.builder.validating.podcast

import dev.stalla.builder.podcast.PodcastFyydBuilder
import dev.stalla.model.fyyd.Fyyd
import dev.stalla.util.InternalAPI2

@InternalAPI2
internal class ValidatingPodcastFyydBuilder : PodcastFyydBuilder {

    private lateinit var verifyValue: String

    override fun verify(verify: String): PodcastFyydBuilder = apply { this.verifyValue = verify }

    override val hasEnoughDataToBuild: Boolean
        get() = ::verifyValue.isInitialized

    override fun build(): Fyyd? {
        if (!hasEnoughDataToBuild) {
            return null
        }

        return Fyyd(verify = verifyValue)
    }
}
