package denys.diomaxius.newzealandguide.data.mapper.event

import com.google.firebase.firestore.DocumentSnapshot
import denys.diomaxius.newzealandguide.data.model.event.SessionDto

fun parseSessions(snap: DocumentSnapshot): List<SessionDto> {
    val rawList = (snap.get("sessions") as? Map<*, *>)
        ?.get("sessions") as? List<*>
        ?: emptyList<Any?>()

    return rawList.mapNotNull { el ->
        (el as? Map<*, *>)?.get("datetime_summary")
            .let { it as? String }
            ?.let { SessionDto(it) }
    }
}

fun getImageUrl(snap: DocumentSnapshot): String {
    val imagesList = (snap.get("images") as? Map<*, *>)?.get("images") as? List<*>
    val first = imagesList?.firstOrNull() as? Map<*, *>
    val transforms = first?.get("transforms") as? Map<*, *>
    val tList = transforms?.get("transforms") as? List<*>
    return (tList?.getOrNull(2) as? Map<*, *>)?.get("url") as? String ?: ""
}