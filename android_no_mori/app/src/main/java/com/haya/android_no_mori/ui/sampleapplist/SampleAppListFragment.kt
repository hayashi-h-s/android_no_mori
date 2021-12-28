package com.haya.android_no_mori.ui.sampleapplist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.haya.android_no_mori.databinding.FragmentSampleAppListBinding

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
        val root: View = binding.root

        val textView: TextView = binding.textDashboard
        sampleAppListViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}