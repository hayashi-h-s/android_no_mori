package com.haya.android_no_mori.ui.sample.onboarding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.haya.android_no_mori.databinding.ActivitySampleOnboardingBinding

class SampleOnBoardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySampleOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySampleOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}