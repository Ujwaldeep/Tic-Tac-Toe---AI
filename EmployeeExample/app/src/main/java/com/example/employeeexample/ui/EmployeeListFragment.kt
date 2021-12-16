package com.example.employeeexample.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.employeeexample.EmployeeListFragmentDirections
import com.example.employeeexample.R
import kotlinx.android.synthetic.main.fragment_employee_list.*


@Suppress("DEPRECATION")
class EmployeeListFragment : Fragment() {
    private lateinit var viewModel: EmployeeListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(EmployeeListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_employee_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        with(employee_list){
            layoutManager = LinearLayoutManager(activity)
            adapter = EmployeeAdapter{
                findNavController().navigate(EmployeeListFragmentDirections.actionEmployeeListFragmentToEmployeeDetailFragment(it))
            }
        }
        add_employee.setOnClickListener{
            findNavController().navigate(EmployeeListFragmentDirections.actionEmployeeListFragmentToEmployeeDetailFragment(0))
        }
        viewModel.employees.observe(viewLifecycleOwner, Observer { (employee_list.adapter as EmployeeAdapter).submitList(it) })
    }

}