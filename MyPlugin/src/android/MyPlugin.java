package xyh;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class echoes a string called from JavaScript.
 */
public class MyPlugin extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if ("plus".equals(action)) {//相加action
            int A = args.getInt(0);
            int B = args.getInt(1);
            this.plus(A, B, callbackContext);
            return true;//注意一定要返回 true
        } else if ("minus".equals(action)) {//相减action
            int A = args.getInt(0);
            int B = args.getInt(1);
            this.minus(A, B, callbackContext);
            return true;  //注意一定要返回 true
        }else if("multiply".equals(action)){//相乘action
            int A = args.getInt(0);
            int B = args.getInt(1);
            this.multiply(A, B, callbackContext);
            return true;  //注意一定要返回 true
        }else if("division".equals(action)){//相除action
            int A = args.getInt(0);
            int B = args.getInt(1);
            this.division(A, B, callbackContext);
            return true;  //注意一定要返回 true
        }
        return false;
    }

    //相加方法
    private void plus(int A, int B, CallbackContext callbackContext) {
        if(A!=0&&B!=0){
            int result=A+B;
            String re=A+"+"+B+"="+result;
            callbackContext.success(re);
        }else{
            callbackContext.error("Two Numbers can't be zero!");
        }
    }

    //相减方法
    private void minus(int A, int B, CallbackContext callbackContext) {
        if(A!=0&&B!=0){
            int result=A-B;
            String re=A+"-"+B+"="+result;
            callbackContext.success(re);
        }else{
            callbackContext.error("Two Numbers can't be zero!");
        }
    }

    //相乘方法
    private void multiply(int A, int B, CallbackContext callbackContext) {
        if(A!=0&&B!=0){
            int result=A*B;
            String re=A+"*"+B+"="+result;
            callbackContext.success(re);
        }else{
            callbackContext.error("Two Numbers can't be zero!");
        }
    }

    //相除方法
    private void division(int A, int B, CallbackContext callbackContext) {
        if(A!=0&&B!=0){
            int result=A/B;
            String re=A+"÷"+B+"="+result;
            callbackContext.success(re);
        }else{
            callbackContext.error("Two Numbers can't be zero!");
        }
    }
}
