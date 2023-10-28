package foundation

import android.os.Bundle
import android.util.Log
import android.view.AbsSavedState
import android.view.View
import androidx.annotation.AnimRes
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainer
import androidx.fragment.app.FragmentManager
import com.example.base_for_project_mvvm.MainActivity

import com.example.base_for_project_mvvm.views.currentColor.CurrentColorFragment
import foundation.navigation.Navigator
import foundation.utils.Event
import foundation.views.BaseFragment
import foundation.views.BaseScreen
import foundation.views.HasScreenTitle

class NavigationRealization(
    private val activity:AppCompatActivity,
    @IdRes  private val containerId:Int,
    private val defaultTitle: String,
    private val animations: Animations,
    val initialScreenCreator :() ->BaseScreen

): Navigator {


    var result : Event<Any>? =null
    override fun launch(screen: BaseScreen) {
        launchFragment(screen)
    }

    override fun goBack(result: Any?) {
        activity.supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentCallbacks, false)



        if(result != null){

           val res:Event<Any>? =Event(result)
               this.result   =res
        }
        activity.onBackPressedDispatcher.onBackPressed()


    }
    fun onCreate(savedInstaceState: Bundle?){
        if(savedInstaceState==null){
            launchFragment(initialScreenCreator(),false)
        }


    }


     fun onDestroy() {
        activity.supportFragmentManager.unregisterFragmentLifecycleCallbacks(fragmentCallbacks)

    }

    fun launchFragment( screen: BaseScreen, addToBackStack: Boolean = true) {
        // as screen classes are inside fragments -> we can create fragment directly from screen
        val fragment = screen.javaClass.enclosingClass.newInstance() as Fragment
        // set screen object as fragment's argument
        fragment.arguments = bundleOf(ARG_SCREEN to screen)

        val transaction = activity.supportFragmentManager.beginTransaction()
        if (addToBackStack) transaction.addToBackStack(null)
        transaction
            .setCustomAnimations(
                animations.enterAnimation,
                animations.exitAnimation,
                animations.pop_enter,
                animations.pop_exit
            )
            .replace(containerId, fragment)
            .commit()
    }

    private val fragmentCallbacks =object: FragmentManager.FragmentLifecycleCallbacks(){
        //Это активипруется Когда содается фрамент
        // (В нашем случае когда фрагмент из backstack'a становится активным ,нажали назад)
        override fun onFragmentViewCreated(
            fm: FragmentManager,
            f: Fragment,
            v: View,
            savedInstanceState: Bundle?
        ) {
            super.onFragmentViewCreated(fm, f, v, savedInstanceState)
            notifyScreenUpdate()
            //here we just check result
            val result :Any= result?.getValue() ?: return

            if(f is BaseFragment){
                f.viewModel.onResult(result)
            }

        }
    }

    //we call thi functoin everytime the screen is become active
    fun notifyScreenUpdate(){
        val f=activity.supportFragmentManager.findFragmentById(containerId)

        if(activity.supportFragmentManager.backStackEntryCount>0){
            activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        else{
            activity.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }

        if (f is HasScreenTitle && f.getScreenTitle() != null) {
            // fragment has custom screen title -> display it
            activity.supportActionBar?.title = f.getScreenTitle()
        } else {
            activity.supportActionBar?.title = defaultTitle
        }



    }
    class Animations(
       @AnimRes val enterAnimation:Int,
       @AnimRes val exitAnimation:Int,
       @AnimRes val pop_enter :Int,
       @AnimRes val pop_exit:Int
    ){


    }

}