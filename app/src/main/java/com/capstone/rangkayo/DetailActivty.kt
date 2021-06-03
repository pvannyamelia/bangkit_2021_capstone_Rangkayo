package com.capstone.rangkayo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import com.capstone.rangkayo.databinding.ActivityDetailActivtyBinding

class DetailActivty : AppCompatActivity() {

    private lateinit var binding: ActivityDetailActivtyBinding

    companion object {
        const val EXTRA_LINK = "link"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailActivtyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val link = intent.getStringExtra(EXTRA_LINK)


        with(binding) {

            webview.settings.javaScriptEnabled = true

            webview.webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView, url: String) {
                    view.loadUrl("javascript:alert('Loaded')")
                }
            }
            webview.webChromeClient = object : WebChromeClient() {
                override fun onJsAlert(view: WebView, url: String, message: String, result: android.webkit.JsResult): Boolean {
                    Toast.makeText(this@DetailActivty, message, Toast.LENGTH_LONG).show()
                    result.confirm()
                    return true
                }
            }
            webview.loadUrl(link.toString())

        }
    }
}