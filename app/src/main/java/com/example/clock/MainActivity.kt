package com.example.clock

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import java.lang.StringBuilder
import java.text.SimpleDateFormat
import java.util.*
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.graphics.Bitmap
import android.text.Editable
import android.text.TextWatcher
import android.webkit.*
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    val handler = Handler()
    val text = findViewById<TextView>(R.id.time)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val webview:WebView = findViewById(R.id.webview)
        val editText:EditText = findViewById(R.id.url)

        webview.webViewClient =object :WebViewClient(){
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                if (view != null) {
                    editText.setText(view.url)
                }
            }
        }
        webview.run {

            webChromeClient = object : WebChromeClient(){
            }
            loadUrl("https://www.google.co.jp")
            settings.javaScriptEnabled=true
           // settings.builtInZoomControls=true
            settings.useWideViewPort=true

        }




        val clock = clock()

        object : Runnable {
            override fun run() {
                clock.gettime()
                text.text = clock.print()
                handler.postDelayed(this, 500)
            }

        }.apply {
            run()
        }

        editText.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (!s.isNullOrEmpty())
                     webview.loadUrl(s.toString())//処理
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }


        })

    }


    override fun onBackPressed() {
        if(webview.canGoBack())
            webview.goBack()
        else
            super.onBackPressed()
    }





   /* private class MyWebViewClient : WebViewClient() {

        override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean { // ->(3)
            return false
        }

        override fun onPageStarted(view: WebView, url: String, favicon: Bitmap) { // ->(4)
            super.onPageStarted(view, url, favicon)
        }

        override fun onPageFinished(view: WebView, url: String) { // ->(5)
            super.onPageFinished(view, url)

        }

        override fun onReceivedError(view: WebView, request: WebResourceRequest, error: WebResourceError) { // ->(6)
            super.onReceivedError(view, request, error)
        }

    }*/


}







