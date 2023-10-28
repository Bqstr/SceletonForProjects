package foundation

import android.app.Application
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.base_for_project_mvvm.MainActivity
import com.example.base_for_project_mvvm.R
import foundation.views.BaseScreen
import foundation.navigation.Navigator
import foundation.uiactions.UiActions
import foundation.utils.Event
import foundation.utils.ResourceActions


const val ARG_SCREEN = "ARG_SCREEN"

/**
 * Implementation of [Navigator] and [UiActions].
 * It is based on activity view-model because instances of [Navigator] and [UiActions]
 * should be available from fragments' view-models (usually they are passed to the view-model constructor).
 *
 * This view-model extends [AndroidViewModel] which means that it is not "usual" view-model and
 * it may contain android dependencies (context, bundles, etc.).
 */
class ActivityScopeViewModel( val uiActions: UiActions,
                     val navigator: IntermediateNavigation
) : ViewModel(),
    Navigator by navigator,
    UiActions by uiActions {
/*    val whenActivityActive = ResourceActions<MainActivity>()

    private val _result = MutableLiveData<Event<Any>>()
    val result: LiveData<Event<Any>> = _result

    */









    override fun onCleared() {
        super.onCleared()
        navigator.clear()
    }
}