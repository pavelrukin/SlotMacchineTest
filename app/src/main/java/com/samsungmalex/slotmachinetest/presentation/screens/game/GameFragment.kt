package com.samsungmalex.slotmachinetest.presentation.screens.game

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.samsungmalex.slotmachinetest.R
import com.samsungmalex.slotmachinetest.databinding.FragmentGameBinding
import com.samsungmalex.slotmachinetest.databinding.FragmentSplashScreenBinding
import com.samsungmalex.slotmachinetest.presentation.screens.splash.SplashScreenViewModel

class GameFragment : Fragment() {
    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!
    private val viewModel: GameViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}