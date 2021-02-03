package io.hemin.wien.builder

import io.hemin.wien.model.ITunesStyleCategory
import io.hemin.wien.util.whenNotNull

/** Builder for constructing [ITunesStyleCategory] instances. */
interface ITunesStyleCategoryBuilder : Builder<ITunesStyleCategory> {

    /** Set the category value. */
    fun category(category: String): ITunesStyleCategoryBuilder

    /** Set the subcategory value. */
    fun subcategory(subcategory: String?): ITunesStyleCategoryBuilder

    override fun from(model: ITunesStyleCategory?): ITunesStyleCategoryBuilder = whenNotNull(model) { category ->
        when (category) {
            is ITunesStyleCategory.Simple -> category(category.name)
            is ITunesStyleCategory.Nested -> {
                category(category.name)
                subcategory(category.subcategory.name)
            }
        }
    }
}
