package denys.diomaxius.newzealandguide.data.local.room.converter

import androidx.room.TypeConverter
import com.google.gson.Gson

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromList(value: List<String>?): String {
        return gson.toJson(value ?: emptyList<String>())
    }

    @TypeConverter
    fun toList(value: String?): List<String> {
        if (value.isNullOrEmpty()) return emptyList()
        return gson.fromJson(value, Array<String>::class.java).toList()
    }
}