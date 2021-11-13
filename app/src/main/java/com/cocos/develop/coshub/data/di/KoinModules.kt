package com.cocos.develop.coshub.data.di

import android.content.Context
import androidx.room.Room
import com.cocos.develop.coshub.AndroidScreens
import com.cocos.develop.coshub.IScreens
import com.cocos.develop.coshub.data.datasource.GitHubApi
import com.cocos.develop.coshub.data.domain.EventBus
import com.cocos.develop.coshub.data.domain.NetworkStatusImpl
import com.cocos.develop.coshub.data.repository.GithubUserRepoCombinedImpl
import com.cocos.develop.coshub.data.repository.GithubUsersLocalRepoImpl
import com.cocos.develop.coshub.data.repository.GithubUsersRepo
import com.cocos.develop.coshub.data.repository.GithubUsersWebRepoImpl
import com.cocos.develop.coshub.data.room.GithubDatabase
import com.cocos.develop.coshub.rx.SchedulerProvider
import com.cocos.develop.coshub.ui.main.MainActivity
import com.cocos.develop.coshub.ui.main.MainPresenter
import com.cocos.develop.coshub.ui.profile.ProfilePresenter
import com.cocos.develop.coshub.ui.users.UsersPresenter
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Component
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * homework com.cocos.develop.coshub.data.di
 *
 * @author Amina
 * 06.11.2021
 */
const val DB_NAME = "githubDB"
const val BASE_URL = "https://api.github.com"

@Module
class AppModule(val context:Context){

    @Singleton
    @Provides
    fun provideContext(): Context{
        return context
    }

    @Singleton
    @Provides
    fun provideCreateDB(context:Context):GithubDatabase{
        return Room.databaseBuilder(context, GithubDatabase::class.java, DB_NAME).build()
    }

    @Singleton
    @Provides
    fun provideEventBus(): EventBus{
        return EventBus
    }

}

@Module
class CiceroneModule{
    @Singleton
    @Provides
    fun provideCiceroneCreate():Cicerone<Router>{
        return Cicerone.create()
    }
    @Singleton
    @Provides
    fun provideNavigateHolder(cicerone:Cicerone<Router>): NavigatorHolder{
        return cicerone.getNavigatorHolder()
    }
    @Singleton
    @Provides
    fun provideRouter(cicerone:Cicerone<Router>):Router{
        return  cicerone.router
    }
    @Singleton
    @Provides
    fun screens(): IScreens = AndroidScreens()
}

@Module
class ApiModule{

    @Singleton
    @Provides
    fun provideMoshiBuild(): Moshi{
        return Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }

    @Singleton
    @Provides
    fun provideStethoInterceptor(): OkHttpClient{
        return OkHttpClient.Builder().addNetworkInterceptor(StethoInterceptor()).build()
    }

    @Provides
    fun provideRetrofit(moshi: Moshi, okHttpClient: OkHttpClient): GitHubApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(GitHubApi::class.java)
    }

}

@Module
class RepoModule{
    @Singleton
    @Provides
    fun provideRepo(db: GithubDatabase,githubApi: GitHubApi, context: Context):GithubUsersRepo{
        return GithubUserRepoCombinedImpl(
            GithubUsersLocalRepoImpl(db),
            GithubUsersWebRepoImpl(githubApi),
            NetworkStatusImpl(context),
            SchedulerProvider()
        )
    }

}

@Singleton
@Component(modules = [AppModule::class,CiceroneModule::class,ApiModule::class,RepoModule::class])
interface AppComponent{
    fun inject(activity:MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(userPresenter: UsersPresenter)
    fun inject(profilePresenter: ProfilePresenter)
}