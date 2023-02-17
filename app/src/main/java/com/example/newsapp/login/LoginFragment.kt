package com.example.newsapp.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import com.example.newsapp.R
import com.example.newsapp.database.RegisterDatabase
import com.example.newsapp.database.RegisterRepository
import com.example.newsapp.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(layoutInflater,container,false)

        val application = requireNotNull(this.activity).application

        val dao = RegisterDatabase.getInstance(application).registerDatabaseDao

        val repository = RegisterRepository(dao)

        val factory = LoginViewModelFactory(repository, application)

        viewModel = ViewModelProvider(this, factory)[LoginViewModel::class.java]

        binding.myLoginViewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.navigatetoRegister.observe(viewLifecycleOwner) { hasFinished->
            if (hasFinished == true){
                Log.i("MYTAG","insidi observe")
                displayUsersList()
                viewModel.doneNavigatingRegiter()
            }
        }

        viewModel.errotoast.observe(viewLifecycleOwner) { hasError->
            if(hasError==true){
                Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
                viewModel.donetoast()
            }
        }

        viewModel.errotoastUsername .observe(viewLifecycleOwner) { hasError->
            if(hasError==true){
                Toast.makeText(requireContext(), "User doesnt exist,please Register!", Toast.LENGTH_SHORT).show()
                viewModel.donetoastErrorUsername()
            }
        }

        viewModel.errorToastInvalidPassword.observe(viewLifecycleOwner) { hasError->
            if(hasError==true){
                Toast.makeText(requireContext(), "Please check your Password", Toast.LENGTH_SHORT).show()
                viewModel.donetoastInvalidPassword()
            }
        }

        viewModel.navigatetoUserDetails.observe(viewLifecycleOwner) { hasFinished->
            if (hasFinished == true){
                Log.i("MYTAG","insidi observe")
                navigateUserDetails()
                viewModel.doneNavigatingUserDetails()
            }
        }
    }

    private fun displayUsersList() {
        val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
        NavHostFragment.findNavController(this).navigate(action)

    }

    private fun navigateUserDetails() {
        val action = LoginFragmentDirections.actionLoginFragmentToUserDetailsFragment()
        NavHostFragment.findNavController(this).navigate(action)
    }
}