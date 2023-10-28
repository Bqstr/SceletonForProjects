package foundation

import android.content.Context
import android.widget.Toast
import foundation.uiactions.UiActions

class UiActionRealization(private val appContext: Context): UiActions {
    override fun toast(message: String) {
        Toast.makeText(appContext, message, Toast.LENGTH_SHORT).show()
    }

}