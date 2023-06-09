package com.samsungmalex.slotmachinetest.presentation.screens.main_menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.samsungmalex.slotmachinetest.databinding.FragmentMainMenuBinding


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class MainMenuFragment : Fragment() {

private var _binding: FragmentMainMenuBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

      _binding = FragmentMainMenuBinding.inflate(inflater, container, false)
      return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val action =
            MainMenuFragmentDirections.actionFragmentMainMenuToGameFragment()
        binding.playButton.setOnClickListener { findNavController().navigate(action) }
        binding.exitButton.setOnClickListener { activity?.finish() }

    }
override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}