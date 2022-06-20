package com.assessment.cartapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.assessment.cartapp.viewmodels.ServiceListViewModel
import com.assessment.cartapp.R
import com.assessment.cartapp.ui.adapter.ServiceListAdapter
import com.assessment.cartapp.model.Service
import com.assessment.cartapp.model.Status
import com.assessment.cartapp.databinding.FragmentServiceListBinding
import com.assessment.cartapp.ui.activity.HomeActivity

class ServiceListFragment : Fragment(), ServiceListAdapter.ServiceClickListener {
    private var rootView: View? = null
    private lateinit var binding: FragmentServiceListBinding
    private lateinit var adapter: ServiceListAdapter
    val serviceListViewModel: ServiceListViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
        serviceListViewModel.loadServices()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_service_list, container, false)
        rootView = binding.getRoot()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {

        binding.rvServices.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        serviceListViewModel.getServiceListData().observe(viewLifecycleOwner) {
            if (it != null) {
                when (it.status) {
                    Status.LOADING -> {
                        showProgress(true)
                    }
                    Status.SUCCESS -> {
                        showProgress(false)
                        setData(it.data)
                    }
                    Status.ERROR -> {
                        showProgress(true)
                    }
                }
            }
        }
    }

    private fun setData(data: List<Service>) {
        adapter = ServiceListAdapter(context, data, this)
        binding.rvServices.adapter = adapter
    }

    fun showProgress(show: Boolean) {
        (requireActivity() as HomeActivity).showProgress(show)
    }

    companion object {
        private var serviceListFragment: ServiceListFragment? = null
        fun newInstance(): ServiceListFragment? {
            if (serviceListFragment == null) {
                serviceListFragment = ServiceListFragment()
            }
            return serviceListFragment
        }
    }

    override fun onServiceClicked(service: Service?) {
        val bundle = Bundle()
        bundle.putParcelable("service", service)
        findNavController().navigate(R.id.action_servicesFragment_to_serviceDetailFragment, bundle)
    }
}