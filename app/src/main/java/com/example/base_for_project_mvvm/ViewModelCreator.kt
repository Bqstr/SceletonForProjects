package com.example.base_for_project_mvvm

import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.base_for_project_mvvm.databinding.ActivityMainBinding

typealias ViewModelCreator =() -> ViewModel?


class ViewModelFactory(
    private val creator: ViewModelCreator
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return creator() as T
    }



}

/**
 * Create view-model directly by calling its constructor.
 */
inline fun <reified VM : ViewModel> ComponentActivity.viewModelCreator(noinline creator: ViewModelCreator): Lazy<VM> {
    return viewModels { ViewModelFactory(creator) }
}