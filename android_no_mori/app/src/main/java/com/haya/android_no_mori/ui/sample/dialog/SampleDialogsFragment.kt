package com.haya.android_no_mori.ui.sample.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.haya.android_no_mori.databinding.FragmentSampleDialogsBinding

class SampleDialogsFragment : Fragment() {
    private var _binding: FragmentSampleDialogsBinding? = null
    private val binding: FragmentSampleDialogsBinding get() = _binding!!

    companion object {
        fun newInstance() = SampleDialogsFragment()
    }

    private lateinit var viewModel: SampleDialogsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSampleDialogsBinding.inflate(inflater, container, false)
        return binding.root
    }
}