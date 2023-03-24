package com.example.bootcounter.view.counter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.bootcounter.R
import com.example.bootcounter.domain.data.BootTimeStorage
import com.example.bootcounter.viewModel.CounterViewModel


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class BootCountFragment : Fragment() {

    private val storage = BootTimeStorage
    private lateinit var viewModel: CounterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(requireActivity()).get(CounterViewModel::class.java)

        //todo: add ListItemadapter

        viewModel.bootTimeModelLiveData.observe(viewLifecycleOwner,
            Observer {
                //todo: update UI
            }
        )
    }

}
