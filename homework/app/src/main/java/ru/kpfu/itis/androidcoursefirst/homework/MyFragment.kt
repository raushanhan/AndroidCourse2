//package ru.kpfu.itis.androidcoursefirst.homework
//
//import androidx.core.os.bundleOf
//import androidx.fragment.app.Fragment
//
//open interface MyFragment {
//
//    companion object {
//        private const val ENTERED_TEXT_KEY = "ENTERED_TEXT"
//
//        fun getInstance(
//            enteredText: String,
//        ): MyFragment {
//            return MyFragment().apply {
//                arguments = bundleOf(ENTERED_TEXT_KEY to enteredText)
//            }
//        }
//    }
//}