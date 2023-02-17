package com.example.newsapp.userDetails.userEdit

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.newsapp.R
import com.example.newsapp.database.RegisterDatabase
import com.example.newsapp.database.RegisterRepository
import com.example.newsapp.databinding.FragmentUserDetailsBinding
import com.example.newsapp.databinding.FragmentUserEditBinding
import com.example.newsapp.register.RegisterViewModelFactory

class UserEditFragment : Fragment() {

    private lateinit var viewModel: UserEditViewModel
    private lateinit var binding: FragmentUserEditBinding
    private var userId = 0
    private var userName = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserEditBinding.inflate(layoutInflater,container,false)
        val application = requireNotNull(this.activity).application

        val dao = RegisterDatabase.getInstance(application).registerDatabaseDao

        val repository = RegisterRepository(dao)

        val factory = UserEditViewModelFactory(repository, application)

        viewModel =
            ViewModelProvider(this, factory)[UserEditViewModel::class.java]

        binding.editViewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userId = UserEditFragmentArgs.fromBundle(requireArguments()).userId
        userName = UserEditFragmentArgs.fromBundle(requireArguments()).userName

        viewModel.userId = userId

        binding.textView.text = userName

        viewModel.navigateto.observe(viewLifecycleOwner){hasFinished->
            if (hasFinished == true){
                navigateBack()
                Toast.makeText(requireContext(),"User Updated",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun navigateBack(){
        view ?: return
        try {
            Navigation.findNavController(requireView()).popBackStack()
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        }
    }
}