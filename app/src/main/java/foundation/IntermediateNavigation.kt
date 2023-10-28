package foundation

import foundation.navigation.Navigator
import foundation.utils.ResourceActions
import foundation.views.BaseScreen

class IntermediateNavigation:Navigator {
    val targetNavigator= ResourceActions<Navigator>()
    override fun launch(screen: BaseScreen)  =targetNavigator{
        it.launch(screen)
    }

    override fun goBack(result: Any?)  =targetNavigator{
        it.goBack(result)
    }
    fun setTarget(navigator:Navigator){
        targetNavigator.resource =navigator
    }
    fun clear(){
        targetNavigator.clear()
    }
}