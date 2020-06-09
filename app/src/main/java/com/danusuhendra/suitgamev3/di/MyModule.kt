package com.danusuhendra.suitgamev3.di

import android.content.Context
import androidx.room.Room
import com.danusuhendra.suitgamev3.data.database.AppDatabase
import com.danusuhendra.suitgamev3.data.database.SaveDataDao
import com.danusuhendra.suitgamev3.data.network.ApiResource
import com.danusuhendra.suitgamev3.data.network.ApiService
import com.danusuhendra.suitgamev3.data.prefs.PreferenceHelper
import com.danusuhendra.suitgamev3.repository.AuthRepository
import com.danusuhendra.suitgamev3.repository.BattleRepository
import com.danusuhendra.suitgamev3.repository.ProfileRepository
import com.danusuhendra.suitgamev3.repository.SaveBattleRepository
import com.danusuhendra.suitgamev3.ui.editprofile.presenter.EditProfilePresenter
import com.danusuhendra.suitgamev3.ui.history.presenter.HistoryPresenter
import com.danusuhendra.suitgamev3.ui.login.presenter.LoginPresenter
import com.danusuhendra.suitgamev3.ui.multiplayer.presenter.MultiPlayerPresenter
import com.danusuhendra.suitgamev3.ui.profile.presenter.ProfilePresenter
import com.danusuhendra.suitgamev3.ui.register.presenter.RegisterPresenter
import com.danusuhendra.suitgamev3.ui.savegame.presenter.SaveBattlePresenter
import com.danusuhendra.suitgamev3.ui.singleplayer.presenter.SinglePlayerPresenter
import com.danusuhendra.suitgamev3.ui.splashscreen.presenter.SplashScreenPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MyModule {

    @Singleton
    @Provides
    fun provideApiService() : ApiService {
        return ApiResource.apiServices()
    }

    @Singleton
    @Provides
    fun dbRoom(context: Context) : AppDatabase {
        return Room
            .databaseBuilder(context, AppDatabase::class.java, "saveDatabase")
            .build()
    }
    @Singleton
    @Provides
    fun provideAuthRepository(apiService: ApiService) : AuthRepository {
        return AuthRepository(apiService)
    }

    @Singleton
    @Provides
    fun provideBattleRepository(apiService: ApiService) : BattleRepository {
        return BattleRepository(apiService)
    }

    @Singleton
    @Provides
    fun provideSaveBattleRepository(appDatabase: AppDatabase) : SaveBattleRepository {
        return SaveBattleRepository(appDatabase)
    }

    @Singleton
    @Provides
    fun provideProfileRepository(apiService: ApiService) : ProfileRepository {
        return ProfileRepository(apiService)
    }

    @Singleton
    @Provides
    fun provideEditProfilePresenter(profileRepository: ProfileRepository) : EditProfilePresenter {
        return EditProfilePresenter(profileRepository)
    }

    @Singleton
    @Provides
    fun provideSaveBattlePresenter(saveBattleRepository: SaveBattleRepository) : SaveBattlePresenter {
        return SaveBattlePresenter(saveBattleRepository)
    }

    @Singleton
    @Provides
    fun provideSinglePlayerPresenter(battleRepository: BattleRepository) : SinglePlayerPresenter {
        return SinglePlayerPresenter(battleRepository)
    }

    @Singleton
    @Provides
    fun provideMultiPlayerPresenter(battleRepository: BattleRepository) : MultiPlayerPresenter {
        return MultiPlayerPresenter(battleRepository)
    }

    @Singleton
    @Provides
    fun provideSplashScreenPresenter(authRepository: AuthRepository) : SplashScreenPresenter {
        return SplashScreenPresenter(authRepository)
    }

    @Singleton
    @Provides
    fun provideLoginPresenter(authRepository: AuthRepository) : LoginPresenter {
        return LoginPresenter(authRepository)
    }

    @Singleton
    @Provides
    fun provideRegisterPresenter(authRepository: AuthRepository) : RegisterPresenter {
        return RegisterPresenter(authRepository)
    }

    @Singleton
    @Provides
    fun provideHistoryPresenter(battleRepository: BattleRepository) : HistoryPresenter {
        return HistoryPresenter(battleRepository)
    }

    @Singleton
    @Provides
    fun provideProfilePresenter(profileRepository: ProfileRepository) : ProfilePresenter {
        return ProfilePresenter(profileRepository)
    }

    @Singleton
    @Provides
    fun providePreferenceHelper(context: Context) : PreferenceHelper {
        return PreferenceHelper(context)
    }
}