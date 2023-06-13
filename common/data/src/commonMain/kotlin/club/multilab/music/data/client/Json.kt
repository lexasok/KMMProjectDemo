package club.multilab.music.data.client

import club.multilab.music.core.dto.intent.Intent
import club.multilab.music.core.dto.reply.Reply
import club.multilab.music.core.dto.search.SearchQuery
import club.multilab.music.core.dto.track.Track
import kotlinx.serialization.PolymorphicSerializer
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import kotlinx.serialization.json.Json

val JSON = Json {
    ignoreUnknownKeys = true
//            coerceInputValues = true
//            encodeDefaults = true
    serializersModule = SerializersModule {
        polymorphic(Any::class) {
            subclass(Intent.SearchForTracks::class)
            subclass(Intent.SearchForTracks.Result::class)
            subclass(Intent.GetTrackMedia::class)
            subclass(Track.Media::class)
            subclass(SearchQuery::class)
            subclass(Reply.Success.serializer(PolymorphicSerializer(Any::class)))
            subclass(Reply.Error.serializer())
        }
    }
}