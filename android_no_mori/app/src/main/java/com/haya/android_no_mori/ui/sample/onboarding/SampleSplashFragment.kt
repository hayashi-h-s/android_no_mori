package com.haya.android_no_mori.ui.sample.onboarding

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.haya.android_no_mori.R

class SampleSplashFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Handler().postDelayed({
            // TODO: sharedPreferences等で起動後に1度だけ表示するように修正
            if (false) {
                findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
            } else {
                findNavController().navigate(R.id.action_splashFragment_to_viewPagerFragment)
            }
        }, SPLASH_DISPLAY_TIME)
        return inflater.inflate(R.layout.fragment_sample_splash, container, false)
    }

    companion object {
        const val SPLASH_DISPLAY_TIME: Long = 1000
    }
}
