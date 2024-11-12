package ru.kpfu.itis.androidcoursefirst.homework

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.kpfu.itis.androidcoursefirst.homework.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val viewBinding: ActivityMainBinding by viewBinding(ActivityMainBinding::bind)

    private val fragmentContainerId: Int = R.id.fragment_container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(
                    fragmentContainerId,
                    FirstFragment(),
                    "FirstFragment",
                )
                .commit()
        }
    }

    fun getToSecondScreen(enteredText: String) {
        supportFragmentManager.beginTransaction()
            .replace(
                fragmentContainerId,
                SecondFragment.getInstance(context = this, enteredText = enteredText),
                "SecondFragment",
            )
            .addToBackStack(null)
            .commit()
    }

    fun getToThirdScreen(enteredText: String) {
        supportFragmentManager.beginTransaction()
            .replace(
                fragmentContainerId,
                ThirdFragment.getInstance(context = this, enteredText = enteredText),
                "ThirdFragment",
            )
            .addToBackStack(null)
            .commit()
    }

    fun getToThirdThroughSecondScreen(enteredText: String) {
        getToSecondScreen(enteredText)
        getToThirdScreen(enteredText)
    }

    fun showBottomSheet() {
        val bottomSheet = BottomSheetFragment()
        bottomSheet.show(supportFragmentManager, "TAG")
    }

    fun passTextToFirstFragment(enteredText: String) {
        supportFragmentManager.beginTransaction()
            .replace(
                fragmentContainerId,
                FirstFragment.getInstance(enteredText),
                "ThirdFragment"
                )
            .commit()
    }

}