package com.assessment.cartapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.assessment.cartapp.model.Employee
import com.assessment.cartapp.databinding.EmployeeItemBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions


class EmployeeListAdapter(
    val context: Context?,
    val employees: List<Employee>,
    val employeeSelectedListener: EmployeeSelectedListener
) :
    RecyclerView.Adapter<EmployeeListAdapter.ViewHolder>() {
    var requestOptions = RequestOptions()

    init {
        requestOptions = RequestOptions()
        requestOptions = requestOptions.transforms(CenterCrop(), RoundedCorners(16));
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            EmployeeItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(employees[position])
    }


    inner class ViewHolder(val binding: EmployeeItemBinding) :
        RecyclerView.ViewHolder(binding.getRoot()) {
        fun bind(employee: Employee) {
            binding.employee = employee
            if (context != null) {
                Glide.with(context)
                    .load(employee.image)
                    .transform(CenterCrop(), RoundedCorners(60))
                    .into(binding.ivService)
            }
            binding.cbSelect.setOnCheckedChangeListener { checkBox, isChecked ->
                run {
                    employeeSelectedListener.onEmployeeChecked(employee, isChecked)
                }
            }
        }
    }

    interface EmployeeSelectedListener {
        fun onEmployeeChecked(service: Employee, checked: Boolean)
    }

    override fun getItemCount() = employees.count()
}