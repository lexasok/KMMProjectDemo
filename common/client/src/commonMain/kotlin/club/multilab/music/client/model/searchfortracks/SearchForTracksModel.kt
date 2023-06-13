package club.multilab.music.client.model.searchfortracks

import club.multilab.music.client.model.base.BaseModel

class SearchForTracksModel : BaseModel<SearchForTracksState>(SearchForTracksState()) {

    override fun logTag(): String = this::class.simpleName.orEmpty()
}