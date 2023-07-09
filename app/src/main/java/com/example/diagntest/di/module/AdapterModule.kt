package com.example.diagntest.di.module

import com.example.diagntest.MainActivity
import com.example.diagntest.adapter.RecyclerViewAdapter
import com.sgemin.daggertwoex.di.scopes.ActivityScope
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by srikanth.saini on 09/07/2023
 */
@Module()
@InstallIn(SingletonComponent::class)
class AdapterModule {

    @Provides
    @ActivityScope
    fun getStarWarsPeopleLIst(clickListener: RecyclerViewAdapter.ClickListener): RecyclerViewAdapter {
        return RecyclerViewAdapter(clickListener)
    }

    @Provides
    @ActivityScope
    fun getClickListener(mainActivity: MainActivity): RecyclerViewAdapter.ClickListener {
        return mainActivity
    }
}