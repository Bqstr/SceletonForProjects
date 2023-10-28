package com.example.base_for_project_mvvm.model

import foundation.model.Repository


typealias ColorListener = (ColorClass) -> Unit

//Я твк понял что это единственный репаситорий в приложении
//именно с этим interface будет работать viewModel(not with InMemoeryColorRepositiory)
//а какая реализацтя будет решает класс App
interface ColorsRepository : Repository {

    var currentColor: ColorClass

    /**
     * Get the list of all available colors that may be chosen by the user.
     */
    fun getAvailableColors(): List<ColorClass>

    /**
     * Get the color content by its ID
     */
    fun getById(id: Long): ColorClass

    /**
     * Listen for the current color changes.
     * The listener is triggered immediately with the current value when calling this method.
     */
    fun addListener(listener: ColorListener)

    /**
     * Stop listening for the current color changes
     */
    fun removeListener(listener: ColorListener)
}