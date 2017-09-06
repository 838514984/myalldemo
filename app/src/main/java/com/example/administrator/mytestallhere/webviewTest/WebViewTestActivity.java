package com.example.administrator.mytestallhere.webviewTest;

import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.mytestallhere.BaseActivity;
import com.example.administrator.mytestallhere.R;
import com.example.administrator.mytestallhere.util.Logger;

import butterknife.BindView;

public class WebViewTestActivity extends BaseActivity {
    @BindView(R.id.tv_loadpercent)
    TextView mTvLoadPercent;
    @BindView(R.id.tv_webTitle)
    TextView mTvWebTitle;
    @BindView(R.id.vb)
    WebView mWebview;
    @BindView(R.id.iv_title_icon)
    ImageView mIvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WebSettings settings = mWebview.getSettings();
          settings.setAppCacheEnabled(true);
          settings.setAllowFileAccess(true);
          settings.setDomStorageEnabled(true);    //原来是这个玩意儿用了不能放视频，坑爹,,,只有百度的不能播放？？？
          settings.setJavaScriptCanOpenWindowsAutomatically(true);

        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true); // 关键点
        settings.setAllowFileAccess(true); // 允许访问文件
        settings.setSupportZoom(true); // 支持缩放
        settings.setLoadWithOverviewMode(true);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE); // 不加载缓存内容

        mWebview.setWebViewClient(new WebViewClient() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                Logger.Erroe("shouldOverrideUrlLoading,request.method: "
                        + request.getMethod() + ", request.url: "
                        + request.getUrl() + ", request.getHeader: "
                        + request.getRequestHeaders());
                view.loadUrl(String.valueOf(request.getUrl()));
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                Logger.Erroe("onPageStarted: url: " + url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Logger.Erroe("onPageFinished, url: " + url);
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
                Logger.Erroe("onLoadResource, url : " + url);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                Logger.Erroe("onReceivedError.");
            }

            @Override
            public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
                super.onReceivedHttpError(view, request, errorResponse);
                Logger.Erroe("onReceivedHttpError.");
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                super.onReceivedSslError(view, handler, error);
                Logger.Erroe("onReceivedSslError.");
            }

            @Override
            public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
                Logger.Erroe("shouldOverrideKeyEvent. event.getKeyCode: " + event.getKeyCode());
                return super.shouldOverrideKeyEvent(view, event);
            }

            @Override
            public void onScaleChanged(WebView view, float oldScale, float newScale) {
                super.onScaleChanged(view, oldScale, newScale);
                Logger.Erroe("onScaleChanged: oldScale: " + oldScale + " ,newScale: " + newScale);
            }

            @Override
            public void onReceivedLoginRequest(WebView view, String realm, String account, String args) {
                super.onReceivedLoginRequest(view, realm, account, args);
                Logger.Erroe("onReceivedLoginRequest, realm: " + realm + " ,args: " + args);
            }
        });

        mWebview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                mTvLoadPercent.setText("loading percent: " + newProgress);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                mTvWebTitle.setText(title);
            }

            @Override
            public void onReceivedIcon(WebView view, Bitmap icon) {
                super.onReceivedIcon(view, icon);
                if (icon != null)
                    mIvTitle.setImageBitmap(icon);
            }

            @Override
            public void onShowCustomView(View view, CustomViewCallback callback) {
                super.onShowCustomView(view, callback);
                Logger.Erroe("onShowCustomView: ");
            }

            @Override
            public void onRequestFocus(WebView view) {
                super.onRequestFocus(view);
                Logger.Erroe("onRequestFocus.");
            }

            @Override
            public void onCloseWindow(WebView window) {
                super.onCloseWindow(window);
                Logger.Erroe("onCloseWindow.");
            }

            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                Logger.Erroe("onJsAlert");
                return super.onJsAlert(view, url, message, result);
            }

            @Override
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
                Logger.Erroe("onShowFileChooser.");
                return super.onShowFileChooser(webView, filePathCallback, fileChooserParams);
            }
        });
        mWebview.loadUrl("https://www.baidu.com");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web_view_test;
    }


    @Override
    public void onBackPressed() {
        if (mWebview.canGoBack()){
            mWebview.goBack();
            return;
        }
        super.onBackPressed();
    }
}
