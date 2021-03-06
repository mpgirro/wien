package dev.stalla.model.atom

import dev.stalla.builder.LinkBuilder
import dev.stalla.builder.validating.ValidatingLinkBuilder
import dev.stalla.model.BuilderFactory
import dev.stalla.model.MediaType

/**
 * Model class for elements describing hyperlinks.
 *
 * Direct instantiation in Java is discouraged. Use the [builder][Link.Factory.builder]
 * method to obtain a [LinkBuilder] instance for expressive construction instead.
 *
 * @property href The `href` attribute text content of the `<atom:link>` element.
 * @property hrefLang The `hrefLang` attribute text content of the `<atom:link>` element.
 * @property hrefResolved The `hrefResolved` attribute text content of the `<atom:link>` element.
 * @property length The `length` attribute text content of the `<atom:link>` element.
 * @property rel The `rel` attribute text content of the `<atom:link>` element.
 * @property title The `title` attribute text content of the `<atom:link>` element.
 * @property type The `type` attribute text content of the `<atom:link>` element as a [MediaType].
 *
 * @since 1.0.0
 */
public data class Link(
    val href: String,
    val hrefLang: String? = null,
    val hrefResolved: String? = null,
    val length: String? = null,
    val rel: String? = null,
    val title: String? = null,
    val type: MediaType? = null
) {

    /** Provides a builder for the [Link] class. */
    public companion object Factory : BuilderFactory<Link, LinkBuilder> {

        /** Returns a builder implementation for building [Link] model instances. */
        @JvmStatic
        override fun builder(): LinkBuilder = ValidatingLinkBuilder()
    }
}
