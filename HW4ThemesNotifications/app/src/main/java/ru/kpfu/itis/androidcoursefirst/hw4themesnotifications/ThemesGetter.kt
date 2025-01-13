package ru.kpfu.itis.androidcoursefirst.hw4themesnotifications

import android.content.Context
import androidx.core.content.ContextCompat
import ru.kpfu.itis.androidcoursefirst.hw4themesnotifications.model.Theme

object ThemesGetter {

    fun getThemesList(context: Context): List<Theme> {
        return listOf(
            Theme(
                id = "YELLOW",
                color = ContextCompat.getColor(context, R.color.yellow)
            ),
            Theme(
                id = "GREEN",
                color = ContextCompat.getColor(context, R.color.green)
            ),
            Theme(
                id = "PINK",
                color = ContextCompat.getColor(context, R.color.pink)
            )
        )
    }
}