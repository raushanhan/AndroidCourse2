package ru.kpfu.itis.androidcoursefirst.hw2

import android.os.Bundle
import ru.kpfu.itis.androidcoursefirst.hw2.base.BaseActivity
import ru.kpfu.itis.androidcoursefirst.hw2.screens.ListContentFragment
import ru.kpfu.itis.androidcoursefirst.hw2.utils.NavigationAction

class MainActivity : BaseActivity() {

    override val fragmentContainerId: Int = R.id.fragment_container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        navigate(
            destination = ListContentFragment(),
            destinationTag = ListContentFragment.TAG,
            action = NavigationAction.ADD,
            isAddToBackStack = false,
        )
    }
}