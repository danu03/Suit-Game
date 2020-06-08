package com.danusuhendra.suitgamev3.di

import android.content.Context
import com.danusuhendra.suitgamev3.ui.history.view.HistoryFragment
import com.danusuhendra.suitgamev3.ui.login.view.LoginActivity
import com.danusuhendra.suitgamev3.ui.multiplayer.view.MultiPlayerActivity
import com.danusuhendra.suitgamev3.ui.register.view.RegisterActivity
import com.danusuhendra.suitgamev3.ui.savegame.view.SaveBattleActivity
import com.danusuhendra.suitgamev3.ui.singleplayer.view.SinglePlayerActivity
import com.danusuhendra.suitgamev3.ui.splashscreen.view.SplashScreenActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [MyModule::class])
interface AppComponent {
    @Component.Factory
    interface Factory{
        fun create(@BindsInstance context: Context) : AppComponent
    }
    fun inject(loginActivity: LoginActivity)
    fun inject(registerActivity: RegisterActivity)
    fun inject(splashScreenActivity: SplashScreenActivity)
    fun inject(saveBattleActivity: SaveBattleActivity)
    fun inject(singlePlayerActivity: SinglePlayerActivity)
    fun inject(multiPlayerActivity: MultiPlayerActivity)
    fun inject(historyFragment: HistoryFragment)
}