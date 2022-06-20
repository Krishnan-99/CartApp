package com.assessment.cartapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.assessment.cartapp.model.Service
import com.assessment.cartapp.databinding.ServiceItemBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions


class ServiceListAdapter(val context: Context?, val services: List<Service>, val serviceListener: ServiceClickListener) :
    RecyclerView.Adapter<ServiceListAdapter.ViewHolder>() {
    var requestOptions = RequestOptions()

    init {
        requestOptions = RequestOptions()
        requestOptions = requestOptions.transforms(CenterCrop(), RoundedCorners(16));
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ServiceItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(services[position])
    }


    inner class ViewHolder(val binding: ServiceItemBinding) :
        RecyclerView.ViewHolder(binding.getRoot()) {
        fun bind(service: Service) {
            binding.service = service
            if (context != null) {
                Glide.with(context)
                    .load(service.image)
                    .transform(CenterCrop(), RoundedCorners(24))
                    .into(binding.ivService)
            }
            binding.root.setOnClickListener {
                serviceListener.onServiceClicked(service)
            }
        }
    }

        interface ServiceClickListener {
            fun onServiceClicked(service: Service?)
        }

        override fun getItemCount() = services.count()
    }