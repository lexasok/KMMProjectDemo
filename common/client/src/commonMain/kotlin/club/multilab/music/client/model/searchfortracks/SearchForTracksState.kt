package club.multilab.music.client.model.searchfortracks

import club.multilab.music.core.dto.error.Error
import club.multilab.music.core.dto.search.SearchQuery

data class SearchForTracksState(
    val loading: Boolean = false,
    val error: Error = Error.EMPTY,
    val searchQuery: SearchQuery = SearchQuery.EMPTY
)