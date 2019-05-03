package io.hemin.wien.util

import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory

/**
 * Factory for the API to obtain DOM Document instances from
 * XML documents. This factory applies required configuration
 * to the API instance, for consistent usage in this library.
 */
class DomBuilderFactory {

    companion object Factory {
        private val factory = DomBuilderFactory()

        /**
         * Instantiates a new API instance to obtain DOM Documents from
         * XML documents with the required configuration settings.
         *
         * @return The created API instance.
         */
        fun newBuilder(): DocumentBuilder = factory.newBuilder()
    }

    private val factory: DocumentBuilderFactory

    init {
        factory = DocumentBuilderFactory.newInstance()
        factory.isNamespaceAware = true
    }

    private fun newBuilder(): DocumentBuilder = factory.newDocumentBuilder()

}
