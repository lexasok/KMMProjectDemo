package club.multilab.music.data.zaycev

import club.multilab.music.core.dto.track.Track

fun mapTrackList(searchResponse: SearchResponse, baseURL: String): List<Track> =
    searchResponse.info.map {
        Track(
            id = it.key,
            name = it.value.name,
            artist = it.value.artist,
            duration = it.value.duration,
            imageURL = "$baseURL${it.value.imageURL}"
        )
    }
