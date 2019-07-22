package com.example.view.WebView;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.view.R;

public class MyWebVIew extends AppCompatActivity {
    private WebView  webView;
    private ProgressBar progressBar;
    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_web_view);
        webView = findViewById(R.id.webview);
        progressBar = findViewById(R.id.wv_progreebar);
        webView.loadUrl("http://www.baidu.com");
        //添加js监听,这样html就能调用客户端
        webView.addJavascriptInterface(this,"android");
        webView.setWebChromeClient(webChromeClient);
        webView.setWebViewClient(webViewClient);
        WebSettings webSettings = webView.getSettings();
        //允许使用JS
        webSettings.setJavaScriptEnabled(true);
        //LOAD_CACHE_ONLY:不使用网络，只读取本地缓存数据
        //LOAD_DEFAULT:根据cache_control决定是否从网络上取数据
        //LOAD_NO_CACHE:不使用缓存，只从网络上获取数据
        //LOAD_CACHE_ELSE_NETWORK,只要本地有，无论是否过期，或者no-cache.都使用缓存中的数据
        webSettings.setCacheMode(webSettings.LOAD_NO_CACHE);
        //支持屏幕缩放
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
    }

    /**
     * WebViewClient主要帮助WebView处理各种通知、请求事件
     */
    private WebViewClient webViewClient = new WebViewClient(){
        @Override
        public void onPageFinished(WebView view, String url) {
            //页面加载完成
            progressBar.setVisibility(View.GONE);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            //页面开始加载
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.i("mouse","拦截url" + url);
            if (url.equals("http://www.google.com/")){
                Toast.makeText(MyWebVIew.this,"拦截URL",Toast.LENGTH_LONG).show();
                return true;
            }
            return super.shouldOverrideUrlLoading(view,url);
        }

    };
    /**
     * WebChormClient主要辅助WebView处理JavaScript的对话框、网站图标、网站 Title
     * 加载进度
     */
    private WebChromeClient webChromeClient = new WebChromeClient(){
        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            AlertDialog.Builder builder = new AlertDialog.Builder(webView.getContext());
            builder.setMessage(message).setPositiveButton("确定",null);
            builder.setCancelable(false);
            builder.create().show();
            //注意：
            //必须要有result.confim()
            //表示处理结果为确定状态同时唤醒WebCore线程
            //否则不能继续点击按钮
            result.confirm();
            return true;
        }
        //获取网页标题
        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            Log.i("mouse",title);
        }

        //加载进度回调
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            progressBar.setProgress(newProgress);
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.i("mouse","是否有上一个页面 ： "+webView.canGoBack());
        if (webView.canGoBack() && keyCode == KeyEvent.KEYCODE_BACK){
            //点击返回按钮的时候判断有没有上一页
            webView.goBack();//返回WebAView的上一页
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    /**
     * JS调用安卓的方法
     */
    @JavascriptInterface ///任然必不可少
    public void getClient(String str){
        Log.i("mosue","html调用客户端: "+str);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //释放资源
        webView.destroy();
        webView = null;
    }
}
