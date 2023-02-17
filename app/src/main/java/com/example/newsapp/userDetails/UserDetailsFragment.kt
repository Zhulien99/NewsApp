package com.example.newsapp.userDetails

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.adapters.OnUserSelectListener
import com.example.newsapp.adapters.UserDetailsAdapter
import com.example.newsapp.database.RegisterDatabase
import com.example.newsapp.database.RegisterEntity
import com.example.newsapp.database.RegisterRepository
import com.example.newsapp.databinding.FragmentUserDetailsBinding
import com.example.newsapp.login.LoginFragmentDirections

class UserDetailsFragment : Fragment(),OnUserSelectListener {

    private lateinit var viewModel: UserDetailsViewModel
    private lateinit var binding: FragmentUserDetailsBinding
    private lateinit var adapter: UserDetailsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserDetailsBinding.inflate(layoutInflater,container,false)

        val application = requireNotNull(this.activity).application

        val dao = RegisterDatabase.getInstance(application).registerDatabaseDao

        val repository = RegisterRepository(dao)

        val factory = UserDetailsFactory(repository, application)

        viewModel =
            ViewModelProvider(this, factory)[UserDetailsViewModel::class.java]

        binding.userDelailsLayout = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.navigateto.observe(viewLifecycleOwner) { hasFinished ->
            if (hasFinished == true) {
                val action = UserDetailsFragmentDirections.actionUserDetailsFragmentToLoginFragment()
                NavHostFragment.findNavController(this).navigate(action)
                viewModel.doneNavigating()
            }
        }

        viewModel.users.observe(viewLifecycleOwner) {
            initRecyclerView(it)
        }

    }
    private fun initRecyclerView(users: List<RegisterEntity>) {
        adapter = UserDetailsAdapter(this)
        binding.usersRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.usersRecyclerView.adapter = adapter
        adapter.setItems(users)
    }

    override fun onUserSelected(userName: String, userId: Int) {
        val action = UserDetailsFragmentDirections.actionUserDetailsFragmentToUserEditFragment(userId,userName)
        NavHostFragment.findNavController(this).navigate(action)
    }
}