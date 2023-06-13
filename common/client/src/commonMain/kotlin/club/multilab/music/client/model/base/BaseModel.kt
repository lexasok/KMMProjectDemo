package club.multilab.music.client.model.base

import club.multilab.music.core.dto.error.getLogMessage
import club.multilab.music.core.dto.reply.ErrorValue
import club.multilab.music.core.dto.reply.Reply
import club.multilab.music.utils.log.Logger
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

// TODO add executor with lifecycle scopes
abstract class BaseModel<T>(state: T) :  Logger {

    protected val _state = MutableStateFlow(state)

    val state: StateFlow<T> = _state

    // TODO encapsulate with executor
    protected inline fun <T>  execute(noinline toExecute: () -> Flow<Reply<T>>, crossinline onReply: (Reply<T>) -> Unit) {
        logI("Start to execute: $toExecute")
        CoroutineScope(Dispatchers.Main).launch {
            toExecute()
                .catch {
                    val error = ErrorValue(
                        code = "1206231758",
                        message = "Error executing $toExecute",
                        it.message.orEmpty(),
                        it.stackTraceToString()
                    )
                    logW(error.getLogMessage())
                    emit(Reply.Error(error))
                }.collect {
                    logI("onReply: $it")
                    onReply(it)
                }
        }
    }

}
