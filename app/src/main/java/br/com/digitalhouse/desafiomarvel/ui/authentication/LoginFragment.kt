package br.com.digitalhouse.desafiomarvel.ui.authentication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import br.com.digitalhouse.desafiomarvel.R
import br.com.digitalhouse.desafiomarvel.databinding.ActivityMainBinding.inflate
import br.com.digitalhouse.desafiomarvel.databinding.FragmentLoginBinding

class LoginFragment: Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentLoginBinding.inflate(inflater, container, false)

        val navController = findNavController()

        binding.btnLogin.setOnClickListener {
            navController.navigate(R.id.action_loginFragment_to_listComicsFragment)
        }

        binding.txvCreate.setOnClickListener {
            navController.navigate(R.id.action_registerFragment_to_listComicsFragment)
        }
        return binding.root
    }
}