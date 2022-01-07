package com.haya.android_no_mori.ui.sample.onboarding.viewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.haya.android_no_mori.R
import com.haya.android_no_mori.databinding.FragmentSecondScreenBinding

class SecondScreen : Fragment() {
    private var _binding: FragmentSecondScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondScreenBinding.inflate(inflater, container, false)
        val viewPager = activity?.findViewById<ViewPager2>(R.id.view_pager)
        binding.next2.setOnClickListener {
            viewPager?.currentItem = THIRD_VIEW_PAGER_NUM
        }
        return binding.root
    }

    companion object {
        const val THIRD_VIEW_PAGER_NUM = 2
    }
}