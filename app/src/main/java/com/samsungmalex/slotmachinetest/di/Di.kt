package com.samsungmalex.slotmachinetest.di

import com.samsungmalex.slotmachinetest.data.data_store.DataStoreManger
import com.samsungmalex.slotmachinetest.data.repository.GameRepositoryImpl
import com.samsungmalex.slotmachinetest.domain.repository.GameRepository
import com.samsungmalex.slotmachinetest.presentation.screens.game.GameViewModel
import com.samsungmalex.slotmachinetest.presentation.screens.main_menu.MainMenuViewModel
import com.samsungmalex.slotmachinetest.presentation.screens.splash.SplashScreenViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val dataModules = module {
    single<GameRepository> { GameRepositoryImpl(get()) }
    single { DataStoreManger(androidContext()) }
}


val presentationModule = module {
    viewModel { MainMenuViewModel() }
    viewModel { SplashScreenViewModel() }
    viewModel { GameViewModel(get()) }

}
