package com.example.administrator.mytestallhere.webviewTest;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.example.administrator.mytestallhere.BaseActivity;
import com.example.administrator.mytestallhere.R;
import com.example.administrator.mytestallhere.util.Logger;

import butterknife.BindView;
import butterknife.OnClick;

public class WebViewAndJsInvokeMethodActivity extends BaseActivity {
    @BindView(R.id.vb)
    WebView mWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WebSettings settings=mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        mWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
                Logger.error("onJsAlert...."+" utl; "+url+" message: "+message);
                AlertDialog alertDialog=new AlertDialog.Builder(WebViewAndJsInvokeMethodActivity.this).setMessage(message).setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        result.confirm();
                    }
                }).setCancelable(false).create();
                alertDialog.show();
                return true;
            }

            @Override
            public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
                return super.onJsConfirm(view, url, message, result);
            }

            @Override
            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
                return super.onJsPrompt(view, url, message, defaultValue, result);
            }
        });
        mWebView.addJavascriptInterface(new MyHtmlEvent(),"myAndroid");//通过反射调用
        mWebView.loadUrl("file:///android_asset/myHtml.html");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web_view_and_js_invoke_method;
    }

    @OnClick(R.id.btn)
    public void onClick(){
        //mWebView.loadUrl("javascript:callJS()"); 方法一
        mWebView.evaluateJavascript("javascript:callJS()", new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String value) {

            }
        });
    }

    class MyHtmlEvent{
        @JavascriptInterface
        public void callAndroid(String msg){
            Toast.makeText(WebViewAndJsInvokeMethodActivity.this,msg,0).show();
        }
    }


}
