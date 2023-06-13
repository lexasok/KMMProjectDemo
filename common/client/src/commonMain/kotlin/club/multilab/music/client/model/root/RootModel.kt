package club.multilab.music.client.model.root

import club.multilab.music.client.model.base.BaseModel
import org.koin.core.component.KoinComponent

class RootModel : BaseModel<RootState>(RootState()), KoinComponent {

    override fun logTag(): String = this::class.simpleName.orEmpty()

}