package br.com.digitalhouse.desafiomarvel.ui.authentication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import br.com.digitalhouse.desafiomarvel.R
import br.com.digitalhouse.desafiomarvel.databinding.FragmentRegisterBinding

class RegisterFragment: Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentRegisterBinding.inflate(inflater, container, false)
        val navController = findNavController()

        binding.toolbarRegister.setNavigationOnClickListener {
            navController.navigateUp()
        }

        binding.materialButton.setOnClickListener {
            navController.navigate(R.id.action_registerFragment_to_listComicsFragment)
        }

        return binding.root
    }
}