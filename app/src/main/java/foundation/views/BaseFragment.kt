package foundation.views

import androidx.fragment.app.Fragment
import com.example.base_for_project_mvvm.FragmentHolder
import com.example.base_for_project_mvvm.MainActivity


abstract class BaseFragment : Fragment() {




    /**
     * View-model that manages this fragment
     */
    abstract val viewModel: BaseViewModel

    /**
     * Call this method when activity controls (e.g. toolbar) should be re-rendered
     */
    //call this method when you want to update title on toolbar
    fun notifyScreenUpdates() {
        // if you have more than 1 activity -> you should use a separate interface instead of direct
        // cast to MainActivity
        (requireActivity() as FragmentHolder).notifyScreenUpdate()
    }
}