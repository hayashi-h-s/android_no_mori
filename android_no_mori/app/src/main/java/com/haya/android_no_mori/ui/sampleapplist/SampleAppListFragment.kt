package com.haya.android_no_mori.ui.sampleapplist

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.haya.android_no_mori.databinding.FragmentSampleAppListBinding
import com.haya.android_no_mori.ui.sample.firestore.FireStoreSampleActivity
import com.haya.android_no_mori.ui.sample.gridview.SampleGridActivity
import com.haya.android_no_mori.ui.sample.onboarding.SampleOnBoardingActivity

class SampleAppListFragment : Fragment() {
    private lateinit var sampleAppListViewModel: SampleAppListViewModel
    private var _binding: FragmentSampleAppListBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        sampleAppListViewModel =
            ViewModelProvider(this).get(SampleAppListViewModel::class.java)
        _binding = FragmentSampleAppListBinding.inflate(inflater, container, false)
        binding.transitionGridSampleViewButton.setOnClickListener {
            val intent = Intent(activity?.application, SampleGridActivity::class.java)
            startActivity(intent)
        }
        binding.transitionOnBoardingSampleViewButton.setOnClickListener {
            val intent = Intent(activity?.application, SampleOnBoardingActivity::class.java)
            startActivity(intent)
        }
        binding.transitionFirestoreSampleScreenButton.setOnClickListener {
            val intent = Intent(activity?.application, FireStoreSampleActivity::class.java)
            startActivity(intent)
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}