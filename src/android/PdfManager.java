package cordova.plugin.pdfmanager;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;


import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.File;

/**
 * This class echoes a string called from JavaScript.
 */
public class PdfManager extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("coolMethod")) {
            String message = args.getString(0);
            this.coolMethod(message, callbackContext);
            return true;
        }
        return false;
    }

    private void coolMethod(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
               this.getPdfManage(message);
            callbackContext.success(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)+message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }



    public void getPdfManage(String pdf_filename)
    {


        /** PDF manager code */
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)+"/"+pdf_filename);

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file),"application/pdf");
        intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try
        {
            this.cordova.getActivity().startActivity(intent);
        }
        catch (ActivityNotFoundException e)
        {
            Log.e("Exception e",e.getMessage());

        }
    }





}
