package com.example.newsapp.register

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import com.example.newsapp.database.RegisterDatabase
import com.example.newsapp.database.RegisterRepository
import com.example.newsapp.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {

    private lateinit var registerViewModel: RegisterViewModel
    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(layoutInflater,container,false)
        val application = requireNotNull(this.activity).application

        val dao = RegisterDatabase.getInstance(application).registerDatabaseDao

        val repository = RegisterRepository(dao)

        val factory = RegisterViewModelFactory(repository, application)

        registerViewModel = ViewModelProvider(this, factory)[RegisterViewModel::
        class.java]

        binding.myViewModel = registerViewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        registerViewModel.navigateto.observe(viewLifecycleOwner) {
                hasFinished->
            if (hasFinished == true) {
                displayUsersList()
                registerViewModel.doneNavigating()
            }
        }

        registerViewModel.userDetailsLiveData.observe(viewLifecycleOwner)
        {
            Log.i("MYTAG", it.toString() + "000000000000000000000000")
        }


        registerViewModel.errotoast.observe(viewLifecycleOwner)
        {
                hasError->
            if (hasError == true) {
                Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
                registerViewModel.donetoast()
            }
        }

        registerViewModel.errotoastUsername.observe(viewLifecycleOwner)
        {
                hasError->
            if (hasError == true) {
                Toast.makeText(requireContext(), "UserName Already taken", Toast.LENGTH_SHORT).show()
                registerViewModel.donetoastUserName()
            }
        }
    }


    private fun displayUsersList() {
        Log.i("MYTAG", "insidisplayUsersList")
        val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
        NavHostFragment.findNavController(this).navigate(action)
    }
}
