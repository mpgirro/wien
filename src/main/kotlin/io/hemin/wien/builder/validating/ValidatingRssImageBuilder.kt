package io.hemin.wien.builder.validating

import io.hemin.wien.builder.RssImageBuilder
import io.hemin.wien.model.Image

internal class ValidatingRssImageBuilder : RssImageBuilder {

    private lateinit var urlValue: String
    private lateinit var titleValue: String
    private lateinit var linkValue: String

    private var width: Int? = null
    private var height: Int? = null
    private var description: String? = null

    override fun url(url: String): RssImageBuilder = apply { this.urlValue = url }

    override fun title(title: String): RssImageBuilder = apply { this.titleValue = title }

    override fun link(link: String): RssImageBuilder = apply { this.linkValue = link }

    override fun width(width: Int?): RssImageBuilder = apply { this.width = width }

    override fun height(height: Int?): RssImageBuilder = apply { this.height = height }

    override fun description(description: String?): RssImageBuilder = apply { this.description = description }

    override fun build(): Image.RssImage? {
        if (!::urlValue.isInitialized || !::titleValue.isInitialized || !::linkValue.isInitialized) {
            return null
        }

        return Image.RssImage(
            url = urlValue,
            title = titleValue,
            link = linkValue,
            width = width,
            height = height,
            description = description
        )
    }
}
