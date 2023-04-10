package com.samsungmalex.slotmachinetest.presentation.screens.game

import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.samsungmalex.slotmachinetest.R
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.samsungmalex.slotmachinetest.databinding.FragmentGameBinding
import com.samsungmalex.slotmachinetest.presentation.screens.game.ButtonState.*
import kotlinx.coroutines.*
import kotlin.random.Random

class GameFragment : Fragment() {
    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!
    private val viewModel: GameViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imagePayTableObserve()
        imageBetOneObserve()
        imageBetMaxObserve()
        imageSpinObserve()



        viewModel.bet.observe(viewLifecycleOwner) { bet ->
            binding.tvBet.text = "$${bet.toString()}"
        }

        viewModel.credits.observe(viewLifecycleOwner) { credits ->
            binding.tvCredit.text = "$${credits.toString()}"

        }
        viewModel.win.observe(viewLifecycleOwner) { win ->

            binding.tvWin.text = "$${win.toString()}"
        }

        binding.ivPayTable.setOnClickListener {
            viewModel.imagePayTableClick()
        }
        binding.ivBetOne.setOnClickListener {
            viewModel.imageBetOneClick()
        }
        binding.ivBetMax.setOnClickListener {
            viewModel.imageBetMaxClick()
        }
        binding.ivSpin.setOnClickListener {
            viewModel.imageSpinClick()
            spinAnimation()

        }
    }

    private fun spinAnimation() {

        viewModel.imageItem1.observe(viewLifecycleOwner) {
            binding.ivItem1.setImageResource(it)
        }
        viewModel.imageItem2.observe(viewLifecycleOwner) {
            binding.ivItem2.setImageResource(it)
        }
        viewModel.imageItem3.observe(viewLifecycleOwner) {
            binding.ivItem3.setImageResource(it)
        }

        binding.ivItem1.setImageResource(R.drawable.animation)
        val slot1Anim: AnimationDrawable = binding.ivItem1.drawable as AnimationDrawable
        slot1Anim.start()



        binding.ivItem2.setImageResource(R.drawable.animation)
        val slot2Anim: AnimationDrawable = binding.ivItem2.drawable as AnimationDrawable
        slot2Anim.start()

        binding.ivItem3.setImageResource(R.drawable.animation)
        val slot3Anim: AnimationDrawable = binding.ivItem3.drawable as AnimationDrawable
        slot3Anim.start()
    }


    private fun imagePayTableObserve() {
        viewModel.imagePayTable.observe(viewLifecycleOwner) { state ->

            viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
                val defaultImg = R.drawable.btn_table_default
                val selectedImg = R.drawable.btn_table_pressed
                val disableImg = R.drawable.btn_table_disabled

                when (state) {
                    DEFAULT -> {
                        withContext(Dispatchers.Main) {
                            val drawable = ContextCompat.getDrawable(requireContext(), defaultImg)
                            binding.ivPayTable.setImageDrawable(drawable)
                            binding.ivPayTable.isClickable = true
                        }
                    }
                    SELECTED -> {
                        withContext(Dispatchers.Main) {
                            val drawable = ContextCompat.getDrawable(requireContext(), selectedImg)
                            binding.ivPayTable.setImageDrawable(drawable)
                            binding.ivPayTable.isClickable = false
                        }
                    }
                    DISABLED -> {
                        withContext(Dispatchers.Main) {
                            val drawable = ContextCompat.getDrawable(requireContext(), disableImg)
                            binding.ivPayTable.setImageDrawable(drawable)
                            binding.ivPayTable.isClickable = false
                        }
                    }
                }

            }
        }
    }

    private fun imageBetOneObserve() {
        viewModel.imageBetOne.observe(viewLifecycleOwner) { state ->
            viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
                val defaultImg = R.drawable.btn_one_default
                val selectedImg = R.drawable.btn_one_pressed
                val disableImg = R.drawable.btn_one_disabled

                when (state) {
                    DEFAULT -> {
                        withContext(Dispatchers.Main) {
                            val drawable = ContextCompat.getDrawable(requireContext(), defaultImg)
                            binding.ivBetOne.setImageDrawable(drawable)
                            binding.ivBetOne.isClickable = true
                        }
                    }
                    SELECTED -> {
                        withContext(Dispatchers.Main) {
                            val drawable = ContextCompat.getDrawable(requireContext(), selectedImg)
                            binding.ivBetOne.setImageDrawable(drawable)
                            binding.ivBetOne.isClickable = false
                        }
                    }
                    DISABLED -> {
                        withContext(Dispatchers.Main) {
                            val drawable = ContextCompat.getDrawable(requireContext(), disableImg)
                            binding.ivBetOne.setImageDrawable(drawable)
                            binding.ivBetOne.isClickable = false
                        }
                    }
                }

            }
        }
    }

    private fun imageBetMaxObserve() {
        viewModel.imageBetMax.observe(viewLifecycleOwner) { state ->
            viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
                val defaultImg = R.drawable.btn_max_default
                val selectedImg = R.drawable.btn_max_pressed
                val disableImg = R.drawable.btn_max_disabled

                when (state) {
                    DEFAULT -> {
                        withContext(Dispatchers.Main) {
                            val drawable = ContextCompat.getDrawable(requireContext(), defaultImg)
                            binding.ivBetMax.setImageDrawable(drawable)
                            binding.ivBetMax.isClickable = true
                        }
                    }
                    SELECTED -> {
                        withContext(Dispatchers.Main) {
                            val drawable = ContextCompat.getDrawable(requireContext(), selectedImg)
                            binding.ivBetMax.setImageDrawable(drawable)
                            binding.ivBetMax.isClickable = false
                        }
                    }
                    DISABLED -> {
                        withContext(Dispatchers.Main) {
                            val drawable = ContextCompat.getDrawable(requireContext(), disableImg)
                            binding.ivBetMax.setImageDrawable(drawable)
                            binding.ivBetMax.isClickable = false
                        }
                    }
                }
            }
        }
    }

    private fun imageSpinObserve() {
        viewModel.imageSpin.observe(viewLifecycleOwner) { state ->
            viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
                val defaultImg = R.drawable.btn_spin_default
                val selectedImg = R.drawable.btn_spin_pressed
                val disableImg = R.drawable.btn_spin_disabled

                when (state) {
                    DEFAULT -> {
                        withContext(Dispatchers.Main) {
                            val drawable = ContextCompat.getDrawable(requireContext(), defaultImg)
                            binding.ivSpin.setImageDrawable(drawable)
                            binding.ivSpin.isClickable = true
                        }
                    }
                    SELECTED -> {
                        withContext(Dispatchers.Main) {
                            val drawable = ContextCompat.getDrawable(requireContext(), selectedImg)
                            binding.ivSpin.setImageDrawable(drawable)
                            binding.ivSpin.isClickable = false
                        }
                    }
                    DISABLED -> {
                        withContext(Dispatchers.Main) {
                            val drawable = ContextCompat.getDrawable(requireContext(), disableImg)
                            binding.ivSpin.setImageDrawable(drawable)
                            binding.ivSpin.isClickable = false
                        }
                    }
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycleScope.coroutineContext.cancel()
    }
}