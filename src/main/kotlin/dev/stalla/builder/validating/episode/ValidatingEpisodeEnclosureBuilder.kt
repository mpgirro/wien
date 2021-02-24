package dev.stalla.builder.validating.episode

import dev.stalla.builder.episode.EpisodeEnclosureBuilder
import dev.stalla.model.rss.Enclosure
import dev.stalla.util.InternalApi

@InternalApi
internal class ValidatingEpisodeEnclosureBuilder : EpisodeEnclosureBuilder {

    private lateinit var urlValue: String
    private var lengthValue: Long = -1
    private lateinit var typeValue: String

    override fun url(url: String): EpisodeEnclosureBuilder = apply { this.urlValue = url }

    override fun length(length: Long): EpisodeEnclosureBuilder = apply { this.lengthValue = length }

    override fun type(type: String): EpisodeEnclosureBuilder = apply { this.typeValue = type }

    override val hasEnoughDataToBuild: Boolean
        get() = ::urlValue.isInitialized && lengthValue >= 0 && ::typeValue.isInitialized

    override fun build(): Enclosure? {
        if (!hasEnoughDataToBuild) {
            return null
        }

        return Enclosure(
            url = urlValue,
            length = lengthValue,
            type = typeValue
        )
    }
}
