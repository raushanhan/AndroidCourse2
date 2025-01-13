package ru.kpfu.itis.androidcoursefirst.hw4themesnotifications

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ru.kpfu.itis.androidcoursefirst.hw4themesnotifications.base.BaseActivity
import ru.kpfu.itis.androidcoursefirst.hw4themesnotifications.fragment.NotificationMakerFragment
import ru.kpfu.itis.androidcoursefirst.hw4themesnotifications.util.NavigationAction

class MainActivity : BaseActivity() {

    override val fragmentContainerId: Int = R.id.fragment_container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTheme()

        setContentView(R.layout.activity_main)
        navigate(
            destination = NotificationMakerFragment(),
            destinationTag = NotificationMakerFragment.TAG,
            action = NavigationAction.ADD,
            isAddToBackStack = false
        )
    }

    private fun setTheme() {
        intent?.extras?.getString("THEME_ID")?.let { themeId ->
            setTheme(getThemeResId(themeId))
        }
    }

    fun changeTheme(themeId: String) {
        with (intent) {
            putExtra("THEME_ID", themeId)
        }
        recreate()
    }

    private fun getThemeResId(themeId: String): Int {
        return when(themeId) {
            "PINK" -> R.style.Theme_HW4ThemesNotifications_Pink
            "GREEN" -> R.style.Theme_HW4ThemesNotifications_Green
            "YELLOW" -> R.style.Theme_HW4ThemesNotifications_Yellow
            else -> R.style.Base_Theme_HW4ThemesNotifications
        }
    }
}