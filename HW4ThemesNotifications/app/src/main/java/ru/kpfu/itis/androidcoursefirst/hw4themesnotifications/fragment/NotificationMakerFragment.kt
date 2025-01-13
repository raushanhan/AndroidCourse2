package ru.kpfu.itis.androidcoursefirst.hw4themesnotifications.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ArrayAdapter
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import ru.kpfu.itis.androidcoursefirst.hw4themesnotifications.MainActivity
import ru.kpfu.itis.androidcoursefirst.hw4themesnotifications.PhotoResource
import ru.kpfu.itis.androidcoursefirst.hw4themesnotifications.R
import ru.kpfu.itis.androidcoursefirst.hw4themesnotifications.ThemesGetter
import ru.kpfu.itis.androidcoursefirst.hw4themesnotifications.adapter.ColorsRVAdapter
import ru.kpfu.itis.androidcoursefirst.hw4themesnotifications.databinding.FragmentNotificationMakerBinding
import ru.kpfu.itis.androidcoursefirst.hw4themesnotifications.model.Theme
import ru.kpfu.itis.androidcoursefirst.hw4themesnotifications.util.NotificationHandler

class NotificationMakerFragment : Fragment(R.layout.fragment_notification_maker) {

    private val viewBinding: FragmentNotificationMakerBinding by viewBinding(
        FragmentNotificationMakerBinding::bind
    )
    private var rvAdapter: ColorsRVAdapter? = null

    private var notificationHandler: NotificationHandler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().applicationContext?.let { appCtx ->
            if (notificationHandler == null) {
                notificationHandler = NotificationHandler(appCtx = appCtx)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvAdapter = ColorsRVAdapter(
            onClickAction = { theme: Theme ->
                changeTheme(theme.id)
            },
            themes = ThemesGetter.getThemesList(requireContext())
        )

        with(viewBinding) {
            themesRv.adapter = rvAdapter

            icon.setOnClickListener {
                if (themesRv.visibility == View.GONE) {
                    themesRv.visibility = View.VISIBLE
                    icon.setImageResource(R.drawable.baseline_keyboard_double_arrow_up_24)
                } else {
                    themesRv.visibility = View.GONE
                    icon.setImageResource(R.drawable.baseline_keyboard_double_arrow_down_24)
                }
            }

            resetThemeButton.setOnClickListener {
                changeTheme("BASE")
            }

            ivCard.setOnClickListener {
                Glide.with(requireContext()).apply {
                    load(PhotoResource.url)
                        .into(photo)
                }
                removePhotoBtn.visibility = View.VISIBLE
            }

            removePhotoBtn.setOnClickListener {
                photo.setImageResource(R.drawable.emptiness)
                removePhotoBtn.visibility = View.GONE
            }

            showNotificationButton.setOnClickListener {

                val notificationText = notificationTextTextInputEt.text.toString()
                val notificationTitle = notificationTitleTextInputEt.text.toString()
                val notificationImportance = menu.text.toString()

                println("TEST TAG $notificationImportance")

                if (notificationTitle.isEmpty()) {
                    showMissingFieldSnackbar(view, "Заголовок уведомления не введён")
                } else if (notificationText.isEmpty()) {
                    showMissingFieldSnackbar(view, "Текст уведомления не введён")
                }

                if (notificationText.isNotEmpty() && notificationTitle.isNotEmpty()) {
                    notificationHandler?.showNotification(
                        channelId = "sampleChannel",
                        notificationId = System.currentTimeMillis().toInt(),
                        notificationText = notificationText,
                        notificationTitle = notificationTitle,
                        notificationImportance = notificationImportance
                    )
                }
            }

            val items = listOf("Max", "High", "Default", "Low")
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, items)
            menu.setAdapter(adapter)
            menu.setText(items[0], false)

        }
    }

    fun showMissingFieldSnackbar(view: View, text: String) {
        Snackbar.make(view, text, Snackbar.LENGTH_LONG)
            .show()
    }

    private fun changeTheme(themeId: String) {
        (requireActivity() as? MainActivity)?.changeTheme(themeId)
    }

    companion object {
        const val TAG = "NOTIFICATION_MAKER_FRAGMENT"
    }

    override fun onDestroy() {
        super.onDestroy()
        notificationHandler = null
    }
}