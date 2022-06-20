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
import com.assessment.cartapp.viewmodels.CartViewModel
import com.assessment.cartapp.R
import com.assessment.cartapp.ui.adapter.ServiceListAdapter
import com.assessment.cartapp.model.CartItem
import com.assessment.cartapp.model.Service
import com.assessment.cartapp.databinding.FragmentCartBinding
import com.assessment.cartapp.room.CartDatabase
import com.assessment.cartapp.ui.activity.HomeActivity
import com.assessment.cartapp.ui.adapter.CartListAdapter

class CartListFragment : Fragment(), ServiceListAdapter.ServiceClickListener {
    private var rootView: View? = null
    private lateinit var binding: FragmentCartBinding
    private lateinit var adapter: CartListAdapter
    val cartViewModel: CartViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (rootView == null) {
            binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_cart, container, false)
            rootView = binding.getRoot()
            initViews()
        }
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initViews() {
        binding.rvCart.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        cartViewModel.loadServices(CartDatabase.getDatabase(requireContext()).wordDao())
        cartViewModel.getCartListData().observe(viewLifecycleOwner) {
            if (it != null) {
              setData(it)
            }
        }
    }

    private fun setData(data: List<CartItem>) {
        adapter = CartListAdapter(context, data)
        binding.rvCart.adapter = adapter
    }

    fun showProgress(show: Boolean) {
        (requireActivity() as HomeActivity).showProgress(show)
    }

    companion object {
        private var serviceListFragment: CartListFragment? = null
        fun newInstance(): CartListFragment? {
            if (serviceListFragment == null) {
                serviceListFragment = CartListFragment()
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