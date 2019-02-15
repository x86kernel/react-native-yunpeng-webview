package com.yunpeng.react.webview;

import com.facebook.react.views.webview.ReactWebViewManager;
import com.facebook.react.views.webview.WebViewConfig;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.ThemedReactContext;

import android.net.http.SslError;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.SslErrorHandler;
import android.content.res.Resources;

@ReactModule(name = YPWebViewManager.REACT_CLASS)
public class YPWebViewManager extends ReactWebViewManager {

  protected static final String REACT_CLASS = "RCTYPWebView";

  public YPWebViewManager() {
    super();
  }

  public YPWebViewManager(WebViewConfig webViewConfig) {
    super(webViewConfig);
  }

  @Override
  public String getName() {
    return REACT_CLASS;
  }

  protected static class YPWebViewClient extends ReactWebViewClient {
    @Override
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
      handler.proceed();
    }

	@Override
	public void onPageFinished(WebView view, String url) {
	  super.onPageFinished(view, url);
	  view.clearCache(true);
	}
  }

  @ReactProp(name = "scalesPageToFit")
  @Override
  public void setScalesPageToFit(WebView view, boolean enabled) {
    view.getSettings().setUseWideViewPort(enabled);
    view.getSettings().setLoadWithOverviewMode(enabled);
  }

  @Override
  protected void addEventEmitters(ThemedReactContext reactContext, WebView view) {
    // Do not register default touch emitter and let WebView implementation handle touches
    view.setWebViewClient(new YPWebViewClient());
  }



}
