package com.haya.android_no_mori.ui.sample.onboarding.viewpager

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.haya.android_no_mori.R
import com.haya.android_no_mori.databinding.FragmentSampleOnBoardingBinding

class ViewPagerFragment : Fragment() {
    private var _binding: FragmentSampleOnBoardingBinding? = null
    private val binding get() = _binding!!

    private lateinit var dots: Array<TextView?>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSampleOnBoardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
        binding.viewPager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                changeIndicatorColor(position)
                changePagerChangeButtonText(position)
            }
        })
        setClickListener()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpView() {
        setupIndicator()
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
    }

    private fun setClickListener() {
        binding.next.setOnClickListener {
            if (binding.viewPager.currentItem == MAX_PAGE_NUM) {
                findNavController().navigate(R.id.action_viewPagerFragment_to_homeFragment)
            } else {
                binding.viewPager.currentItem = binding.viewPager.currentItem + 1
            }
        }
    }

    private fun changeIndicatorColor(position: Int) {
        for (i in dots.indices) {
            dots[i]?.setTextColor(
                resources.getColor(
                    R.color.light_gray,
                    activity?.applicationContext?.theme
                )
            )
        }
        dots[position]?.setTextColor(
            resources.getColor(
                R.color.gray,
                activity?.applicationContext?.theme
            )
        )
    }

    private fun setupIndicator() {
        dots = arrayOfNulls(MAX_INDICATOR_NUM)
        binding.indicatorLayout.removeAllViews()
        for (i in dots.indices) {
            dots[i] = TextView(activity)
            dots[i]!!.text = Html.fromHtml("&#8226")
            dots[i]?.textSize = 35f
            dots[i]?.setTextColor(
                resources.getColor(
                    R.color.light_gray,
                    activity?.applicationContext?.theme
                )
            )
            binding.indicatorLayout.addView(dots[i])
        }
        dots[0]?.setTextColor(
            resources.getColor(
                R.color.gray,
                activity?.applicationContext?.theme
            )
        )
    }

    private fun changePagerChangeButtonText(position: Int) {
        if (position == MAX_PAGE_NUM) {
            binding.next.text = getString(R.string.text_finish)
        } else {
            binding.next.text = getString(R.string.text_next)
        }
    }

    companion object {
        const val MAX_PAGE_NUM = 2
        const val MAX_INDICATOR_NUM = 3
    }
}