package com.cheer.cordova.lianlianpay;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.cheer.cordova.ext.CordovaPluginExt;
import com.yintong.pay.utils.Constants;
import com.yintong.pay.utils.MobileSecurePayer;

public class LLPayPlugin extends CordovaPluginExt {
    private static final String LOGTAG = "LLPayPlugin";

    /**
     * Cordova Actions.
     */
    public static final String ACTION_START_PAY = "startPay";
    public static final String ACTION_BANKAPP_PAY = "bankAppPay";

    protected String __getProductShortName() {
        return "LLPay";
    }

    @Override
    public boolean execute(String action, JSONArray inputs, CallbackContext callbackContext) throws JSONException {
        PluginResult result = null;

        if (ACTION_START_PAY.equals(action)) {
            JSONObject args = inputs.optJSONObject(0);

            boolean isOk = this.startPay(args);
            result = new PluginResult(isOk ? Status.OK : Status.ERROR);

        } else if (ACTION_BANKAPP_PAY.equals(action)) {
            JSONObject args = inputs.optJSONObject(0);

            boolean isOk = this.bankAppPay(args);
            result = new PluginResult(isOk ? Status.OK : Status.ERROR);
        } else {
            Log.w(LOGTAG, String.format("Invalid action passed: %s", action));
            result = new PluginResult(Status.INVALID_ACTION);
        }

        if (result != null) sendPluginResult(result, callbackContext);

        return true;
    }

    @Override
    protected void pluginInitialize() {
        super.pluginInitialize();

    }

    public boolean startPay(JSONObject args) {
        Log.d(LOGTAG, "startPay");

        String content4Pay = args.toString();
        MobileSecurePayer msp = new MobileSecurePayer();
        boolean bRet = msp.payAuth(content4Pay, mHandler, Constants.RQF_PAY, this.getActivity(), false);

        return bRet;
    }

    public boolean bankAppPay(JSONObject args) {
        Log.d(LOGTAG, "bankAppPay");

        String content4Pay = args.toString();
        MobileSecurePayer msp = new MobileSecurePayer();
        boolean bRet = msp.payMobileBank(content4Pay, mHandler, Constants.RQF_PAY, this.getActivity(), false);

        return bRet;
    }

    public void firePayEndEvent(final String strRet) {
        final Activity activity = getActivity();
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.d(LOGTAG, "onLLPayEnd: " + strRet);
                fireEvent("LLPay", "onLLPayEnd", "{\"ret\":" + strRet + "}");
            }
        });
    }

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            String strRet = (String) msg.obj;
            switch (msg.what) {
                case Constants.RQF_PAY: {
                    firePayEndEvent(strRet);
                }
                break;
            }
            super.handleMessage(msg);
        }
    };
}
