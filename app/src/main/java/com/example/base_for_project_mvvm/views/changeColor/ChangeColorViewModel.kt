package com.example.base_for_project_mvvm.views.changeColor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.example.base_for_project_mvvm.ColorClassListItem
import foundation.views.BaseViewModel
import com.example.base_for_project_mvvm.model.ColorClass
import androidx.lifecycle.SavedStateHandle
import foundation.navigation.Navigator
import foundation.uiactions.UiActions
import com.example.base_for_project_mvvm.model.ColorsRepository

class ChangeColorViewModel(
    screen: ChangeColorFramgent.Screen,
    private val navigator: Navigator,
    private val uiActions: UiActions,
    private val colorsRepository: ColorsRepository,
    savedStateHandle: SavedStateHandle
) : BaseViewModel(), ColorAdapter.Listener {

    // just list of color (ColorClass)
    private val _availableColors = MutableLiveData<List<ColorClass>>()
    //chosenColorId
    private val _currentColorId = savedStateHandle.getLiveData("currentColorId", screen.currentColorId)

    //list of color but (ColorClassListItem) , so it have info about currently chosen one
    private val _colorsList = MediatorLiveData<List<ColorClassListItem>>()
    val colorsList: LiveData<List<ColorClassListItem>> = _colorsList

    // side destination, also the same result can be achieved by using Transformations.map() function.
    private val _screenTitle = MutableLiveData<String>()
    val screenTitle: LiveData<String> = _screenTitle

    init {
        _availableColors.value = colorsRepository.getAvailableColors()
        // initializing MediatorLiveData
        _colorsList.addSource(_availableColors) { mergeSources() }
        _colorsList.addSource(_currentColorId) { mergeSources() }
    }

    override fun onColorChosen(namedColor: ColorClass) {
        _currentColorId.value = namedColor.id
    }

    fun onSavePressed() {
        val currentColorId = _currentColorId.value ?: return
        val currentColor = colorsRepository.getById(currentColorId)
        colorsRepository.currentColor = currentColor
        navigator.goBack(result = currentColor)
    }

    fun onCancelPressed() {
        navigator.goBack()
    }

    /**
     * [MediatorLiveData] can listen other LiveData instances (even more than 1)
     * and combine their values.
     * Here we listen the list of available colors ([_availableColors] live-data) + current color id
     * ([_currentColorId] live-data), then we use both of these values in order to create a list of
     * [NamedColorListItem], it is a list to be displayed in RecyclerView.
     */
    private fun mergeSources() {
        val colors = _availableColors.value ?: return
        val currentColorId = _currentColorId.value ?: return
        val currentColor = colors.first { it.id == currentColorId }
        _colorsList.value = colors.map { ColorClassListItem(it, currentColorId == it.id) }
        _screenTitle.value =  currentColor.name
    }

}