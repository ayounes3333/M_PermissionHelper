package com.example.aliyounes.intentstask;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import java.lang.reflect.Field;

/**
 * Created by ali.younes on 12/21/2016.
 */

public abstract class PermissionsHelper {

    public static boolean isPermissionGranted(Activity context , String Permission) throws Exception {
        if(!isValidPermission(Permission))
            throw new Exception("Requested Unknown Permission!");
        if (ContextCompat.checkSelfPermission(context, Permission) != PackageManager.PERMISSION_GRANTED) {
            return false;
        }return true;
    }
    public static boolean isValidPermission(String Permission)
    {
        for (Field f : Manifest.permission.class.getDeclaredFields()) {
            f.setAccessible(true); // you need this if variables are private
            String name = f.getName();
            try {
                String value = (String) f.get(null);
                if (value == Permission)
                    return true;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }return false;
    }
     public static void GetPermission(Activity context , String Permission  , final int REQUEST_CODE , String Explanation) throws Exception {

        if(!isValidPermission(Permission))
            throw new Exception("Requested Unknown Permission!");
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(context, Permission) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(context , Permission)) {

                showExplanationAsync show = new showExplanationAsync(context , Explanation);
                show.execute();
                Toast.makeText(context, "yeey!", Toast.LENGTH_LONG).show();
            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(context, new String[]{Permission}, REQUEST_CODE);
                Toast.makeText(context, "Haa!", Toast.LENGTH_LONG).show();
                // REQUEST_CODE is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }else {
            //Permission Already Granted but i need to request it anyway to allow the action to be performed
            ActivityCompat.requestPermissions(context, new String[]{Permission}, REQUEST_CODE);
        }
    }

}
class showExplanationAsync extends AsyncTask<String, Void, String> {
    AlertDialog alertDialog;
    String Message;
    public showExplanationAsync(Context context , String mes)
    {
        alertDialog = new AlertDialog.Builder(context).create();
        this.Message = mes;
    }
    @Override
    protected String doInBackground(String... strings) {
        return null;
    }
    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        alertDialog.setTitle("Info");
        alertDialog.setMessage(this.Message);
        alertDialog.setIcon(R.drawable.information);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

}
