package io.hemin.wien.builder

import io.hemin.wien.model.Image

/** Builder class for [Image] instances. */
class ImageBuilder : Builder<Image> {

    private lateinit var urlValue: String

    private var title: String? = null
    private var link: String? = null
    private var width: Int? = null
    private var height: Int? = null
    private var description: String? = null

    /** Set the url value. */
    fun url(url: String) = apply { this.urlValue = url }

    /** Set the title value. */
    fun title(title: String?) = apply { this.title = title }

    /** Set the link value. */
    fun link(link: String?) = apply { this.link = link }

    /** Set the width value. */
    fun width(width: Int?) = apply { this.width = width }

    /** Set the height value. */
    fun height(height: Int?) = apply { this.height = height }

    /** Set the description value. */
    fun description(description: String?) = apply { this.description = description }

    override fun build(): Image {
        require(::urlValue.isInitialized) { "The image url is mandatory" }
        return Image(
            url = urlValue,
            title = title,
            link = link,
            width = width,
            height = height,
            description = description
        )
    }
}
