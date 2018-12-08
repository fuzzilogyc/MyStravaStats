package org.fuzz.mystravastats

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Job
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private val viewModel : MainActivityViewModel by viewModel()

    private var oAuthJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        handleIntent(intent)
    }

    override fun onDestroy() {
        oAuthJob?.cancel()
        super.onDestroy()
    }

    fun loginButtonClicked(view: View) {
        val intent = Intent(Intent.ACTION_VIEW)
        val url = getString(R.string.oauth_url)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent) {
        val action: String? = intent.action
        if (action == android.content.Intent.ACTION_VIEW) {
            val data: Uri? = intent.data
            val code = data?.getQueryParameter("code")
            Log.d("test", code)
            // you must put the clientid and clientsecret into XML resources
            val clientId = getString(R.string.client_id)
            val clientSecret = getString(R.string.client_secret)
            viewModel.doAuth(clientId, clientSecret, code)
        }
    }

}
