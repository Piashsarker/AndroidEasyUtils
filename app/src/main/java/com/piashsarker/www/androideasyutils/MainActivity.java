package com.piashsarker.www.androideasyutils;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.piashsarker.www.easy_utils_lib.Utils;
import com.upaybd.www.androideasyutils.R;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private Object obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /** Validators **/
        Utils.log(TAG, "VALID URL : " + String.valueOf(Utils.isValidURL("www.google.com")));
        Utils.log(TAG, "VALID EMAIL: " + String.valueOf(Utils.isValidEmail("sarkerpt@gmail.com")));
        Utils.log(TAG, "VALID IP: " + String.valueOf(Utils.isValidIP("121.23.23.3")));

        /** Date Time **/
        /** Date Time **/

        /** Get Format https://developer.android.com/reference/java/text/SimpleDateFormat **/


        Utils.log(TAG,"CURRENT DATE TIME: " +Utils.getCurrentDateTime());
        Utils.log(TAG, "CURRENT TIME: " +Utils.getCurrentTime());
        Utils.log(TAG, "CURRENT TIME BY FORMAT: " +Utils.getCurrentTime("h:mm a"));  // Supported format https://developer.android.com/reference/java/text/SimpleDateFormat
        Utils.log(TAG,"CURRENT DATE BY FORMAT " +Utils.getCurrentDateTime("EEE, MMM d, ''yy"));
        Utils.log(TAG,"CURRENT TIME IN MILIS: " +String.valueOf(Utils.getCurrentTimeInMillis()));
        Utils.log(TAG, "LOCAL TIME FROM UTC: " +Utils.getLocalTimeFromUTCTime("2017-10-22T18:22:37.000+06:00"));  //   2017-10-22T18:22:37.000+06:00 or  2017-08-15T10:29:20.000Z
        Utils.log(TAG, "LOCAL DATE FROM UTC: " +Utils.getLocalDateFromUTCTime("2017-08-15T10:29:20.000Z"));  //   2017-10-22T18:22:37.000+06:00 or  2017-08-15T10:29:20.000Z
        Utils.log(TAG, "GET LOCAL DATE TIME FROM UTC: "+ Utils.getLocalFromUTC("2017-08-15T10:29:20.000Z"));

        /** Log & Toast **/

        Utils.log(TAG, "Log Message");
        Utils.shortToast(this, "This is a short toast message");
        Utils.longToast(this, "This is a long toast message");
        Utils.isObjectNull(obj);

        /** Amount Format **/
        Utils.log(TAG, "AMOUNT FORMAT "+ Utils.getFormatFromDecimalValue(33333.05));
        Utils.log(TAG, "AMOUNT FORMAT "+Utils.getFormatFromIntegerValue(33333));




/*
        */
/** Dialog **//*

        Utils.showExitDialog(this, "Exit", "Are you sure to exit");
        Utils.showExitDialog(this, "Exit", "Are you sure to exit", "Yes", "No");
        Utils.showDialog(this, "Title", "Dialog Message");
        Utils.showGPSEnableDialog(this);
        Utils.showMessageDialog(this, "Title", "Message dialog description", R.drawable.ic_launcher_foreground);
        Utils.showProgressDialog(this);
        Utils.showProgressDialog(this, "Title", "Please wait.");
        Utils.hideProgressDialog();
*/

        /** Connectivity **/
        Utils.isNetworkAvailable(this);
        Utils.isGPSEnable(this);
        Utils.getWifiIpAddress(this);

       // Utils.getDeviceIMEI(this); // Require READ_PHONE_STATE RUNTIME  PERMISSION






        /** Arraylist && HashMap **/

        ArrayList<String> nameList = new ArrayList<>();
        nameList.add("Apple");
        nameList.add("CNN");
        nameList.add("Google");

        HashMap<String, String> map = new HashMap<>();
        map.put("name", "Piash Sarker");
        map.put("id", "131-35-422");

        Utils.removeFromArrayListByName(nameList, "CNN");
        ArrayList<String> values = Utils.getValuesFromHashMap(map);
        Object o = Utils.getKeyFromValue(map, "Piash Sarker");


        /** others **/


    }


    public void btnCheckInternet(View view) {
        if (Utils.isNetworkAvailable(this)) {
            Toast.makeText(this, "Internet Connected.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No internet connection.", Toast.LENGTH_SHORT).show();
        }
    }
}
