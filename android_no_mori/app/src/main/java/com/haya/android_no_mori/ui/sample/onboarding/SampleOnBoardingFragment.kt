package com.haya.android_no_mori.ui.sample.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.haya.android_no_mori.databinding.FragmentSampleOnBoardingBinding
import com.haya.android_no_mori.ui.sample.onboarding.viewpager.FirstScreen
import com.haya.android_no_mori.ui.sample.onboarding.viewpager.SecondScreen
import com.haya.android_no_mori.ui.sample.onboarding.viewpager.ThirdScreen
import com.haya.android_no_mori.ui.sample.onboarding.viewpager.ViewPagerAdapter

class SampleOnBoardingFragment : Fragment() {
    private var _binding: FragmentSampleOnBoardingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSampleOnBoardingBinding.inflate(inflater, container, false)
        val fragmentList = arrayListOf(
            FirstScreen(),
            SecondScreen(),
            ThirdScreen()
        )
        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )
        binding.viewPager.adapter = adapter
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}