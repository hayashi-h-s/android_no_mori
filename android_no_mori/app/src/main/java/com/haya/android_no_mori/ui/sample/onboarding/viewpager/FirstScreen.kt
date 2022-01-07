package com.haya.android_no_mori.ui.sample.onboarding.viewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.haya.android_no_mori.R
import com.haya.android_no_mori.databinding.FragmentFirstScreenBinding

class FirstScreen : Fragment() {
    private var _binding: FragmentFirstScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstScreenBinding.inflate(inflater, container, false)
        val viewPager =  activity?.findViewById<ViewPager2>(R.id.view_pager)
        binding.next.setOnClickListener {
            viewPager?.currentItem = SECOND_VIEW_PAGER_NUMBER
        }
        return binding.root
    }

    companion object {
        const val SECOND_VIEW_PAGER_NUMBER = 1
    }
}