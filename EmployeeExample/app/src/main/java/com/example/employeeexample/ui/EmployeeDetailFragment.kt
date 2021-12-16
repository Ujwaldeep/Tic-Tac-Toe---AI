package com.example.employeeexample.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.employeeexample.EmployeeDetailFragmentArgs
import com.example.employeeexample.R
import com.example.employeeexample.data.Employee
import com.example.employeeexample.data.Gender
import com.example.employeeexample.data.Role
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_employee_detail.*
import java.io.File
import java.io.IOException
import java.nio.file.Files.createFile
import java.util.jar.Manifest


const val PERMISSION_REQUEST_CAMERA = 0
const val CAMERA_PHOTO_REQUEST = 1
const val GALLERY_PHOTO_REQUEST = 2
@Suppress("DEPRECATION")
class EmployeeDetailFragment : Fragment() {

   private lateinit var viewModel: EmployeeDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       viewModel = ViewModelProviders.of(this).get(EmployeeDetailViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_employee_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val roles = mutableListOf<String>()
        Role.values().forEach { roles.add(it.name) }
        val arrayAdapter = ArrayAdapter(requireActivity(),android.R.layout.simple_spinner_item,roles)
        employee_role.adapter = arrayAdapter

        val ages = mutableListOf<Int>()
        for(i in 18 until 81){ages.add(i)}
        employee_age.adapter = ArrayAdapter(requireActivity(),android.R.layout.simple_spinner_item,ages)

        val id = EmployeeDetailFragmentArgs.fromBundle(requireArguments()).id
        viewModel.setEmployeeId(id)

        viewModel.employee.observe(viewLifecycleOwner, Observer {
            it?.let{setData(it)}
        })

        save_employee.setOnClickListener{
            saveEmployee()
        }
        delete_employee.setOnClickListener{
            deleteEmployee()
        }

        employee_photo.setOnClickListener{
            employee_photo.setImageResource(R.drawable.blank_photo)
            employee_photo.tag=""
        }

        photo_from_camera.setOnClickListener{
            clickPhotoAfterPermission(it)

        }
    }

    private fun clickPhotoAfterPermission(view: View) {
        if(ActivityCompat.checkSelfPermission(requireActivity(),android.Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED){
            clickPhoto()
        }else{
            requestCameraPermission(view)
        }
    }

    @SuppressLint("QueryPermissionsNeeded")
    private fun clickPhoto() {
       Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent->
           takePictureIntent.resolveActivity(requireActivity().packageManager)?.also{
               val photoFile: File? =try{
                   createFile(requireActivity(),Environment.DIRECTORY_PICTURES,"jpg")
               }catch (ex:IOException){
                   Toast.makeText(requireContext(),"Error occurred while creating file: {ex.meessage}",Toast.LENGTH_LONG).show()
                   null
               }
               photoFile?.also{
                   selectedPhotoPath = it.absoluteFile(
                           val photoURI:Uri = FileProvider.getUriForFile
                   )
               }
           }
       }
    }



    private fun requestCameraPermission(view: View) {

        if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), android.Manifest.permission.CAMERA)) {
            val snack = Snackbar.make(view, getString(R.string.camera_permission), Snackbar.LENGTH_INDEFINITE)
            snack.setAction("OK", View.OnClickListener {
                requestPermissions(arrayOf(android.Manifest.permission.CAMERA),
                        PERMISSION_REQUEST_CAMERA)
            })
            snack.show()
        } else {
            requestPermissions(arrayOf(android.Manifest.permission.CAMERA),
                    PERMISSION_REQUEST_CAMERA)
        }
    }

    private fun saveEmployee() {
        val name = employee_name.text.toString()
        val age = employee_age.selectedItemPosition+18
        val role = employee_role.selectedItemPosition

        val selectedStatusButton = gender_group.findViewById<RadioButton>(gender_group.checkedRadioButtonId)
        var gender = Gender.Other.ordinal
        if(selectedStatusButton.text == Gender.Male.name){
            gender = Gender.Male.ordinal
        }else if(selectedStatusButton.text == Gender.Female.name){
            gender = Gender.Female.ordinal
        }
        val employee = Employee(viewModel.employeeId.value!!, name, role,age,gender,"")
        viewModel.saveEmployee(employee)

        requireActivity().onBackPressed()

    }

    private fun deleteEmployee() {
        viewModel.deleteEmployee()

        requireActivity().onBackPressed()
    }

    private fun setData(employee:Employee) {
        employee_name.setText(employee.name)
        employee_role.setSelection(employee.role)
        employee_age.setSelection(employee.age-18)
        when(employee.gender){
            Gender.Male.ordinal->{
                gender_male.isChecked = true
            }
            Gender.Female.ordinal->{
                gender_female.isChecked = true
            }
            else->{
                gender_other.isChecked = true
            }
        }
    }


}