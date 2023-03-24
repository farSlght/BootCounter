package com.example.bootcounter.view.counter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.bootcounter.R
import com.example.bootcounter.domain.data.BootTimeStorage
import com.example.bootcounter.viewModel.CounterViewModel


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class BootCountFragment : Fragment() {

    private val viewModel: CounterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.updateBootTimes()

        val bootTimesList = view.findViewById<android.widget.ListView>(R.id.boot_times_list)
        
        viewModel.bootTimeModelLiveData.observe(viewLifecycleOwner
        ) {
            val bootTimeStrings = it.map { bootTimeModel -> bootTimeModel.toString() }
            if (bootTimeStrings.isNotEmpty()) {
                bootTimesList.adapter = ArrayAdapter<String>(
                    requireContext(),
                    android.R.layout.simple_list_item_1,
                    bootTimeStrings
                )
            } else {
                bootTimesList.adapter = ArrayAdapter<String>(
                    requireContext(),
                    android.R.layout.simple_list_item_1,
                    listOf(getString(R.string.no_boots_detected))
                )
            }
        }
    }

}
