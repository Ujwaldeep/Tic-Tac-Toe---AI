package com.example.employeeexample.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.employeeexample.R
import com.example.employeeexample.data.Employee
import com.example.employeeexample.data.Gender
import com.example.employeeexample.data.Role
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.fragment_employee_detail.*
import kotlinx.android.synthetic.main.fragment_employee_list.*
import kotlinx.android.synthetic.main.list_item.*


@Suppress("DEPRECATION")
class EmployeeAdapter(private val listener: (Long) -> Unit):
        ListAdapter<Employee, EmployeeAdapter.ViewHolder>(
                DiffCallback()
        ){

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ViewHolder {
        val itemLayout = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item, parent, false)

        return ViewHolder(itemLayout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder (override val containerView: View) : RecyclerView.ViewHolder(containerView),
            LayoutContainer {
        init{
            itemView.setOnClickListener{
                listener.invoke(getItem(adapterPosition).id)
            }
        }

        @SuppressLint("StringFormatInvalid")
        fun bind(employee: Employee){
            with(employee) {

                name_employee.text = name
                role_employee.text =  Role.values()[employee.role].name
                age_employee.text = containerView.context.resources
                        .getString(R.string.years, employee.age)

                gender_employee.text = Gender.values()[employee.gender].name
            }
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<Employee>() {
    override fun areItemsTheSame(oldItem: Employee, newItem: Employee): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Employee, newItem: Employee): Boolean {
        return oldItem == newItem
    }
}