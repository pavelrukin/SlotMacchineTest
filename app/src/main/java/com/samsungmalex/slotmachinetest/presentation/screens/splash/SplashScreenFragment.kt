package com.samsungmalex.slotmachinetest.presentation.screens.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.samsungmalex.slotmachinetest.databinding.FragmentSplashScreenBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class SplashScreenFragment : Fragment() {
    private val viewModel: SplashScreenViewModel by viewModels()
    private var _binding: FragmentSplashScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSplashScreenBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.navigateToNextScreen.observe(viewLifecycleOwner) { navigate ->
            if (navigate) {
                val action =
                    SplashScreenFragmentDirections.actionFragmentSplashScreenToFragmentMainMenu()
                findNavController().navigate(action)
            }
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}