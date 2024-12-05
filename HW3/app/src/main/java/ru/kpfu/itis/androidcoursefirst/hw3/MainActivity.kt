package ru.kpfu.itis.androidcoursefirst.hw3

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ru.kpfu.itis.androidcoursefirst.hw3.base.BaseActivity
import ru.kpfu.itis.androidcoursefirst.hw3.fragments.viewpager.ViewPagerFragment
import ru.kpfu.itis.androidcoursefirst.hw3.fragments.vpquestionlist.QuestionFragment
import ru.kpfu.itis.androidcoursefirst.hw3.util.NavigationAction

class MainActivity : BaseActivity() {

    override val fragmentContainerId: Int = R.id.fragment_container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigate(
            destination = ViewPagerFragment(),
            destinationTag = ViewPagerFragment.TAG,
            action = NavigationAction.ADD,
            isAddToBackStack = false
        )
    }
}