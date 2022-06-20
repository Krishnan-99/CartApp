package com.assessment.cartapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.assessment.cartapp.R
import com.assessment.cartapp.model.CartItem
import com.assessment.cartapp.databinding.CartItemBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions


class CartListAdapter(
    val context: Context?,
    val services: List<CartItem>
) :
    RecyclerView.Adapter<CartListAdapter.ViewHolder>() {
    var requestOptions = RequestOptions()

    init {
        requestOptions = RequestOptions()
        requestOptions = requestOptions.transforms(CenterCrop(), RoundedCorners(16));
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            CartItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(services[position])
    }


    inner class ViewHolder(val binding: CartItemBinding) :
        RecyclerView.ViewHolder(binding.getRoot()) {
        fun bind(cartItem: CartItem) {
            binding.cartItem = cartItem
            /*if (context != null) {
                Glide.with(context)
                    .load(service.image)
                    .transform(CenterCrop(), RoundedCorners(24))
                    .into(binding.ivService)
            }*/
            for (employee in cartItem.employees) {
                val image = LayoutInflater.from(context)
                    .inflate(R.layout.layout_image, binding.container, false)
                val iv: AppCompatImageView =
                    image.rootView.findViewById(R.id.image) as AppCompatImageView
                if (context != null) {
                    Glide.with(context)
                        .load(employee.image)
                        .transform(CenterCrop(), RoundedCorners(60))
                        .into(iv)
                }
                binding.llImages.addView(image.rootView)
            }
            for (service in cartItem.service) {
                val container = LayoutInflater.from(context)
                    .inflate(R.layout.layout_price, binding.container, false)
                val tvName: AppCompatTextView =
                    container.rootView.findViewById(R.id.tv_name) as AppCompatTextView
                val tvPrice: AppCompatTextView =
                    container.rootView.findViewById(R.id.tv_price) as AppCompatTextView
                tvName.text = service.name
                tvPrice.text = "$" + service.price
                binding.llPrices.addView(container)
            }
        }
    }


    override fun getItemCount() = services.count()
}