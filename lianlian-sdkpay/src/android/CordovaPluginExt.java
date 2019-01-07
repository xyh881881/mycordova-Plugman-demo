package com.cheer.cordova.ext;

import java.lang.reflect.Method;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;

import android.app.Activity;
import android.view.View;


interface PluginAdapterDelegate {
	// context
	public Activity getActivity();
	public View getView();

	// send message from plugin to container on events
	public void fireEvent(String obj, String eventName, String jsonData);

	// send call result
	public void sendPluginResult(PluginResult result, CallbackContext context);
}

public class CordovaPluginExt extends CordovaPlugin  {

	protected PluginAdapterDelegate adapter = null;

	public void setAdapter(PluginAdapterDelegate theAdapter) {
		adapter = theAdapter;
	}

	public PluginAdapterDelegate getAdapter() {
		return adapter;
	}

	public View getView() {
		if(adapter != null) return adapter.getView();
		else {
			// Cordova 3.x, class CordovaWebView extends WebView, -> AbsoluteLayout -> ViewGroup -> View -> Object
			if(View.class.isAssignableFrom(CordovaWebView.class)) {
				return (View) webView;
			}

			// Cordova 4.0.0-dev, interface CordovaWebView { View getView(); }
			try {
				Method getViewMethod = CordovaWebView.class.getMethod("getView", (Class<?>[]) null);
				if(getViewMethod != null) {
					Object[] args = {};
					return (View) getViewMethod.invoke(webView, args);
				}
			} catch (Exception e) {
			}

			// or else we return the root view, but this should not happen
			return getActivity().getWindow().getDecorView().findViewById(android.R.id.content);
		}
	}

	public Activity getActivity() {
		if(adapter != null) return adapter.getActivity();
		return cordova.getActivity();
	}

	public void fireEvent(String obj, String eventName, String jsonData) {
		if(adapter != null) adapter.fireEvent(obj, eventName, jsonData);
		else {
			String js;
			if("window".equals(obj)) {
				js = "var evt=document.createEvent('UIEvents');evt.initUIEvent('" + eventName + "',true,false,window,0);window.dispatchEvent(evt);";
			} else {
				js = "javascript:cordova.fireDocumentEvent('" + eventName + "'";
				if(jsonData != null) {
					js += "," + jsonData;
				}
				js += ");";
			}
			webView.loadUrl(js);
		}
	}

	public void sendPluginResult(PluginResult result, CallbackContext context) {
		if(adapter != null) adapter.sendPluginResult(result, context);
		else {
			context.sendPluginResult(result);
		}
	}
}
