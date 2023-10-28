package com.example.base_for_project_mvvm.views.currentColor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import foundation.navigation.Navigator
import foundation.uiactions.UiActions
import foundation.views.BaseViewModel
import com.example.base_for_project_mvvm.views.changeColor.ChangeColorFramgent
import com.example.base_for_project_mvvm.model.ColorClass
import com.example.base_for_project_mvvm.model.ColorListener
import com.example.base_for_project_mvvm.model.ColorsRepository

class CurrentColorViewModel(
    private val navigator: Navigator,
    val uiActions: UiActions,
    private val colorsRepository: ColorsRepository
): BaseViewModel() {

    private val _currentColor =MutableLiveData<ColorClass>()
    val currentColor :LiveData<ColorClass> =_currentColor


    private val colorListener :ColorListener ={
        _currentColor.postValue(it)
    }
    init {
        colorsRepository.addListener(colorListener)
    }

    override fun onResult(result: Any) {
        super.onResult(result)
        if(result is ColorClass) {
            val message  =result.name
            uiActions.toast(message)

        }
    }
    fun changeColor(){
        val currentColor =currentColor.value ?: return
        val screen = ChangeColorFramgent.Screen(currentColor.id)
        navigator.launch(screen)

    }

    fun goToChange(){
        val color =currentColor.value ?: return
        var screen = ChangeColorFramgent.Screen(currentColorId = color.id)
        navigator.launch(screen)
    }

    override fun onCleared() {
        super.onCleared()
        colorsRepository.removeListener { colorListener }
    }



}