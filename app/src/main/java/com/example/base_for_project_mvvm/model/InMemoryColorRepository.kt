package com.example.base_for_project_mvvm.model

import android.graphics.Color
//это не совсем репазиторий - это реализация репазитория ColorsRepository


class InMemoryColorRepository :ColorsRepository{
    override var currentColor: ColorClass = AVAILABLE_COLORS[0]
        set(value) {
            if (field != value) {
                field = value
                listeners.forEach { it(value) }
            }
        }

    private val listeners = mutableSetOf<ColorListener>()

    override fun getAvailableColors(): List<ColorClass> = AVAILABLE_COLORS

    override fun addListener(listener: ColorListener) {
        listeners += listener
        listener(currentColor)
    }

    override fun removeListener(listener: ColorListener) {
        listeners -= listener
    }

    override fun getById(id: Long): ColorClass {
        return AVAILABLE_COLORS.first { it.id == id }
    }

    companion object {
        private val AVAILABLE_COLORS = listOf(
            ColorClass(1, "Red", Color.RED),
            ColorClass(2, "Green", Color.GREEN),
            ColorClass(3, "Blue", Color.BLUE),
            ColorClass(4, "Yellow", Color.YELLOW),
            ColorClass(5, "Magenta", Color.MAGENTA),
            ColorClass(6, "Cyan", Color.CYAN),
            ColorClass(7, "Gray", Color.GRAY),
            ColorClass(8, "Navy", Color.rgb(0, 0, 128)),
            ColorClass(9, "Pink", Color.rgb(255, 20, 147)),
            ColorClass(10, "Sienna", Color.rgb(160, 82, 45)),
            ColorClass(11, "Khaki", Color.rgb(240, 230, 140)),
            ColorClass(12, "Forest Green", Color.rgb(34, 139, 34)),
            ColorClass(13, "Sky", Color.rgb(135, 206, 250)),
            ColorClass(14, "Olive", Color.rgb(107, 142, 35)),
            ColorClass(15, "Violet", Color.rgb(148, 0, 211)),
        )
    }
}