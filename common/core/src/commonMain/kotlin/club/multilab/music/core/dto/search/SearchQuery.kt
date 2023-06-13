package club.multilab.music.core.dto.search

import kotlinx.serialization.Serializable

// TODO add pagination params
@Serializable
data class SearchQuery(
    val raw: String
) {

    companion object {
        val EMPTY = SearchQuery("")
    }
}