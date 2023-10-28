package com.example.base_for_project_mvvm

import android.app.Application
import com.example.base_for_project_mvvm.model.InMemoryColorRepository
import foundation.model.Repository
import foundation.views.BaseApplication

class App() : Application(),BaseApplication{
    override val repositories: List<Repository> = listOf<Repository>(
        InMemoryColorRepository()
    )
}