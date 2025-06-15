package ru.kpfu.itis.customview

import android.os.Bundle
import ru.kpfu.itis.customview.base.BaseActivity
import ru.kpfu.itis.springpractice.customview.R


class MainActivity : BaseActivity() {

    override val fragmentContainerId: Int = R.id.fragment_container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        navigate(
            destination = MainFragment(),
            destinationTag = MainFragment.TAG,
            action = NavigationAction.ADD,
            isAddToBackStack = false
        )
    }
}