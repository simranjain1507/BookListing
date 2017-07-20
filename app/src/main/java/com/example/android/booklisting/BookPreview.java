package com.example.android.booklisting;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

public class BookPreview extends AppCompatActivity {

    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_preview);
        WebView webView = (WebView) findViewById(R.id.wv_preview);
        Bundle extras = getIntent().getExtras();
        url = extras.getString("link");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
    }


}
