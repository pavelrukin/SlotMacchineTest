package com.samsungmalex.slotmachinetest.di

import com.samsungmalex.slotmachinetest.data.repository.GameRepositoryImpl
import com.samsungmalex.slotmachinetest.domain.repository.GameRepository
import com.samsungmalex.slotmachinetest.presentation.screens.game.GameViewModel
import com.samsungmalex.slotmachinetest.presentation.screens.main_menu.MainMenuViewModel
import com.samsungmalex.slotmachinetest.presentation.screens.splash.SplashScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val dataModules = module {
    single<GameRepository> { GameRepositoryImpl() }
}



val presentationModule = module {
    viewModel { MainMenuViewModel() }
    viewModel { SplashScreenViewModel( ) }
    viewModel { GameViewModel( ) }

}
