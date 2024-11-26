package ru.kpfu.itis.androidcoursefirst.hw2.base

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ru.kpfu.itis.androidcoursefirst.hw2.utils.NavigationAction

abstract class BaseActivity : AppCompatActivity() {

    protected abstract val fragmentContainerId: Int

    fun navigate(
        destination: Fragment,
        destinationTag: String? = null,
        action: NavigationAction = NavigationAction.REPLACE,
        isAddToBackStack: Boolean = true,
        backStackTag: String? = null,
    ) {
        val transaction = supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                android.R.anim.slide_in_left,
                android.R.anim.fade_out,
                android.R.anim.fade_in,
                android.R.anim.slide_out_right,
            )

        when (action) {
            NavigationAction.ADD -> transaction.add(
                fragmentContainerId,
                destination,
                destinationTag
            )

            NavigationAction.REPLACE -> transaction.replace(
                fragmentContainerId,
                destination,
                destinationTag
            )

            NavigationAction.REMOVE -> transaction.remove(destination)

        }

        if (isAddToBackStack) {
            transaction.addToBackStack(backStackTag)
        }

        transaction.commit()
    }
}