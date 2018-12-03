package org.fuzz.mystravastats

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.content.Intent
import android.net.Uri
import android.util.Log


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        handleIntent(intent)
    }

    fun buttonClicked(view: View) {
        //launchWebView
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse("https://www.strava.com/oauth/authorize?client_id=30428&redirect_uri=https://org.fuzz.mystravastats&response_type=code")
        startActivity(i)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent) {
        //https://org.fuzz.mystravastats/?state=test&code=4c7a21036ca39c2e552ee974ab901f39cc1aad22&scope=e
        val action: String? = intent.action
        if (action == android.content.Intent.ACTION_VIEW) {
            val data: Uri? = intent.data
            val code = data?.getQueryParameter("code")
            Log.d("test", data.toString())
            Log.d("test", code)
        }
    }
}
