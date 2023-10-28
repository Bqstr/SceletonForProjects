package com.example.base_for_project_mvvm

import foundation.ActivityScopeViewModel

interface FragmentHolder {
    fun notifyScreenUpdate()

    fun getActivityScopeViewModel():ActivityScopeViewModel
}