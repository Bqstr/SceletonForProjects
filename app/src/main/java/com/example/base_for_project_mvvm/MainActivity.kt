package com.example.base_for_project_mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import foundation.views.BaseFragment
import com.example.base_for_project_mvvm.views.changeColor.ColorAdapter
import com.example.base_for_project_mvvm.databinding.ActivityMainBinding
import foundation.views.HasScreenTitle
import com.example.base_for_project_mvvm.views.currentColor.CurrentColorFragment
import foundation.ActivityScopeViewModel
import foundation.IntermediateNavigation
import foundation.NavigationRealization
import foundation.UiActionRealization

class MainActivity() : AppCompatActivity(), FragmentHolder {
    lateinit var binding : ActivityMainBinding
    lateinit var adapter: ColorAdapter
    private val activityViewModel by viewModelCreator<ActivityScopeViewModel> {
        ActivityScopeViewModel(
            uiActions = UiActionRealization(applicationContext),
            navigator = IntermediateNavigation()
        )

    }//{ViewModelProvider.AndroidViewModelFactory(application)  }
    private lateinit var navigator:NavigationRealization

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding =ActivityMainBinding.inflate(layoutInflater)
        navigator =NavigationRealization(
            activity =this ,
            initialScreenCreator = { CurrentColorFragment.Screen() } ,
            containerId = R.id.fragmentContainerView ,
            animations = NavigationRealization.Animations(  R.anim.enter,
                R.anim.exit,
                R.anim.pop_enter,
                R.anim.pop_exit),
            defaultTitle = getString(R.string.app_name)
            ,

            )

        navigator.onCreate(savedInstanceState)


        //supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentCallbacks, false)

        setContentView(binding.root)
    }


    override fun onDestroy() {
        navigator.onDestroy()
        super.onDestroy()
    }







    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
//in these functions we tell whenActivityActive that activity is active or inActive
    override fun onResume() {
        super.onResume()
    // execute navigation actions only when activity is active
        activityViewModel.navigator.setTarget(navigator)
    }

    override fun onPause() {
        super.onPause()
        // postpone navigation actions if activity is not active
        activityViewModel.navigator.clear()
    }

    override fun notifyScreenUpdate() {
        navigator.notifyScreenUpdate()
    }

    override fun getActivityScopeViewModel(): ActivityScopeViewModel {
        return activityViewModel
    }


}