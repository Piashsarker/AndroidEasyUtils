package com.piashsarker.www.easy_utils_lib;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;
import android.util.Log;
import android.util.Patterns;
import android.widget.Toast;
import com.upaybd.www.easy_utils_lib.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    private static   final Pattern IP_ADDRESS
            = Pattern.compile(
            "((25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9])\\.(25[0-5]|2[0-4]"
                    + "[0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1]"
                    + "[0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}"
                    + "|[1-9][0-9]|[0-9]))");


    private static ProgressDialog dialog ;
    private static Pattern pattern;
    private static Matcher matcher;
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private static final String TAG = "EasyUtils";


    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static boolean isValidURL(String urlStr) {
      return  Patterns.WEB_URL.matcher(urlStr).matches();
    }

    public static Bitmap getBitmapFromFile(String path) {
        File imageFile = new File(path);
        Bitmap bitmap = null;
        if (imageFile.exists()) {
            bitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
        }

        return bitmap;
    }

    public static Bitmap getBitmapFromURI(Uri uri, Context context) {
        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return bitmap;
    }

    public static void removeFromArrayListByName(ArrayList<String> arrayList, String name) {

        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).equalsIgnoreCase(name)) {
                arrayList.remove(i);
            }
        }

    }

    public static ArrayList<String> getValuesFromHashMap(HashMap<String, String> hashMap) {
        ArrayList<String> values = new ArrayList<>();
        for (String key : hashMap.keySet()) {
            values.add(hashMap.get(key));
        }
        return values;
    }

    public static Object getKeyFromValue(Map hm, Object value) {
        for (Object  o : hm.keySet()) {
            if (hm.get(o).equals(value)) {
                return o;
            }
        }
        return null;
    }


    public static ArrayList<String> getTagList(ArrayList<String> tagList) {

        //declare a ArrayList for storing tag
        ArrayList<String> finalTagList = new ArrayList<>();

        for (int i = 0; i < tagList.size(); i++) {

            //spliting the tagList element
            String[] tag = tagList.get(i).split(",");
            for (int j = 0; j < tag.length; j++) {

                if(!finalTagList.contains(tag[j])){
                    finalTagList.add(tag[j]);
                }
            }
        }
        return finalTagList;
    }
    public static void showProgressDialog(Context context, String title , String message) {

        /**
         * Progress Dialog for User Interaction
         */
        if(title == null  || message == null || title.equals("") || message.equals("")){
            Log.d(TAG, "Specify title or message");
            return ;
        }

        if(dialog ==null){
            dialog = new ProgressDialog(context);
            dialog.setTitle(title);
            dialog.setMessage(message);
            dialog.show();
        }

    }

    public static void hideProgressDialog() {

        if (dialog != null) {
            dialog.dismiss();
        }

    }

    public static boolean isValidEmail(String email){
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }



    public static void showDialog(Context context ,  String title , String message ){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle(title);
        // builder.setIcon(R.drawable.warning);
        builder.setMessage(message);


        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });


        AlertDialog alertDialog = builder.create();


        alertDialog.show();

    }
    public static void shortToast(Context context, String message) {
        try {
            Toast.makeText(context,
                    message, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void longToast(Context context, String message) {
        try {
            Toast.makeText(context,
                    message, Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void log(String TAG , String message) {
        Log.d(TAG, message);
    }



    public static String getWifiIpAddress(Context context) {
        WifiManager wifiMgr = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiMgr.getConnectionInfo();
        int ip = wifiInfo.getIpAddress();

        return Formatter.formatIpAddress(ip);
    }

    public static void writeToSDFile(String string) {

        boolean mExternalStorageAvailable;
        boolean mExternalStorageWritable;

        String state = Environment.getExternalStorageState();

        if (Environment.MEDIA_MOUNTED.equals(state)) {
            // Can read and write the media
            mExternalStorageAvailable = mExternalStorageWritable = true;
        } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            // Can only read the media
            mExternalStorageAvailable = true;
            mExternalStorageWritable = false;
        } else {
            // Can't read or write
            mExternalStorageAvailable = mExternalStorageWritable = false;
        }
        log(TAG,"External Media: readable=" + mExternalStorageAvailable
                + " writable=" + mExternalStorageWritable);

        // Find the root of the external storage.
        File root = android.os.Environment.getExternalStorageDirectory();

        File dir = new File(root.getAbsolutePath() + "/SERVER");
        dir.mkdirs();

        File file = new File(dir, "SERVER.txt");

        try {
            FileOutputStream f = new FileOutputStream(file);
            PrintWriter pw = new PrintWriter(f);
            pw.println(string);
            pw.flush();
            pw.close();
            f.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Utils.log(TAG, "File not found. Did you" + " add a WRITE_EXTERNAL_STORAGE permission to the manifest?");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getCurrentDateTime(String format){
        return getTimeByFormat(format);
    }

    public static String getCurrentTime(String format){
        return getTimeByFormat(format);
    }

    public static String getCurrentDateTime() {
        return  getTimeByFormat("dd-MMM-yyyy hh:mm:ss");
    }



    public static  String getCurrentTime(){
       return  getTimeByFormat("hh:mm");
    }



    private static String getTimeByFormat(String format){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat(format);
        df.setTimeZone(TimeZone.getTimeZone(TimeZone.getDefault().getID()));
        String formattedDate = df.format(c.getTime());
        return formattedDate ;
    }


    public static boolean isValidIP(String ip ){
        boolean validate = false ;
        Matcher matcher = IP_ADDRESS.matcher(ip);
        if (matcher.matches()) {
            return true ;
        }
        return validate  ;
    }

    public static void showMessageDialog(Context context, String title, String message, int imageId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setIcon(imageId)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }


    public static boolean isGPSEnable(Context context){

        final LocationManager manager = (LocationManager)context.getSystemService( Context.LOCATION_SERVICE );

        if ( !manager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
            return false ;
        }
        return  true ;
    }


    public static void showGPSEnableDialog(final Context context){
       showGPSEnableDialog(context,"Your GPS seems to be disabled, do you want to enable it?","Yes","No");
    }

    public static  void showGPSEnableDialog(final Context context , String message , String positiveButtonText, String negativeButtonText){
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton(positiveButtonText, new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        context.startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton(negativeButtonText, new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }


    public static byte[] getByteFromBitmap(Bitmap bitmap){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100,stream );
        byte[] outBuffer = stream.toByteArray();
        return  outBuffer;
    }
    public static Bitmap getBitmapFromByte(byte[] bytes){
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0, bytes.length);
        return  bitmap;
    }


    @SuppressLint("MissingPermission")
    public static String getDeviceIMEI(Context context){
        String imei="";
        try{
            TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);

            if (android.os.Build.VERSION.SDK_INT >= 26) {
                imei=telephonyManager.getImei();
            }
            else
            {
                imei=telephonyManager.getDeviceId();
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

        return imei ;
    }




    public static String getMonthName(String value){

        if(value.equals("01") || value.equals("1")){
            return "Jan";
        }
        if(value.equals("02") || value.equals("2")){
            return "Feb";
        }
        if(value.equals("03") || value.equals("3")){
            return "Mar";
        }
        if(value.equals("04") || value.equals("4")){
            return "Apr";
        }
        if(value.equals("05") || value.equals("5")){
            return "May";
        }
        if(value.equals("06") || value.equals("6")){
            return "Jun";
        }
        if(value.equals("07") || value.equals("7")){
            return "Jul";
        }
        if(value.equals("08") || value.equals("8")){
            return "Aug";
        }
        if(value.equals("09") || value.equals("9")){
            return "Sep";
        }
        if(value.equals("10") || value.equals("10")){
            return "Oct";
        }
        if(value.equals("11")){
            return "Nov";
        }
        if(value.equals("12") ){
            return "Dec";
        }
        return "Not Found";
    }

    public static String getLocalFromUTC(String utcTime){
        /**  2017-10-22T18:22:37.000+06:00  **/

        try{
            String newUtcTime = utcTime.substring(0,utcTime.indexOf("+"));
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
            Date date = dateFormat.parse(newUtcTime);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
            return  simpleDateFormat.format(date);
        }
        catch (Exception ex){
            ex.printStackTrace();
            try{
                /** "created_at": "2017-08-15T10:29:20.000Z"  &&  **/
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                dateFormat.setTimeZone(TimeZone.getTimeZone("BST"));
                Date date = dateFormat.parse(utcTime);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                return  simpleDateFormat.format(date);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }





        return  null;
    }

    public static String getLocalTimeFromUTCTime(String utcTime){
        String tempTime = "";
        try{
            String time = getLocalFromUTC(utcTime);
            String [] timePice = time.split("T");
            tempTime = timePice[1].substring(0,8);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

        return tempTime ;
    }

    public static String getLocalDateFromUTCTime(String utcTime)  {

        String dateString = "";

        try{

            String time = getLocalFromUTC(utcTime);

            String [] timePice = time.split("T");
            String tempDate = timePice[0];
            String tempTime = timePice[1].substring(0,8);

            String [] date = tempDate.split("-");

            String year = date[0];
            String month = date[1];
            String day = date[2];


            dateString = day+"/"+month+"/"+year;
        }catch (Exception ex){
            ex.printStackTrace();
        }


        return  dateString;
    }

    public static void showProgressDialog(Context context) {

        /**
         * Progress Dialog for User Interaction
         */
        if(isObjectNull(dialog) && !isObjectNull(context))
        dialog = new ProgressDialog(context);
        dialog.setTitle("Please wait");
        dialog.setMessage("Loading...");
        dialog.show();
    }






    public static String getFormatFromIntegerValue(int  value){
        String amount = String.valueOf(value);
        amount = amount.replaceAll(",","");
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        return   decimalFormat.format(Double.valueOf(amount));
    }

    public static String getFormatFromDecimalValue(Double value){
        String amount = String.valueOf(value);
        amount = amount.replaceAll(",","");
        DecimalFormat decimalFormat = new DecimalFormat("#,###.##");
        return   decimalFormat.format(Double.valueOf(amount));
    }

    public static void showExitDialog(final Context context, String title , String message){
        if(isObjectNull(context) || isObjectNull(title) || isObjectNull(message)){
            Log.d(TAG, "Null Object are not allowed.");
            return;
        }
        showExitDialog(context, title, message, "Exit", "Cancel");
    }

    public static void showExitDialog(final Context context , String title , String message, String exitButtonText , String cancelButtonText) {

        if(isObjectNull(context) || isObjectNull(title) || isObjectNull(message) || isObjectNull(exitButtonText) || isObjectNull(cancelButtonText)){
            Log.d(TAG, "Null Object are not allowed.");
            return;
        }

        new AlertDialog.Builder(context)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(exitButtonText, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }

                })
                .setNegativeButton(cancelButtonText, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .show();
    }


    public static  long getCurrentTimeInMillis(){
        long currentTimeMillis ;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        currentTimeMillis = calendar.getTimeInMillis();
        return  currentTimeMillis ;
    }

    public static long getHoursDifferent(long startTime , long currentTime){
        long  hours = 0 ;
        long diff = currentTime - startTime ;
        hours = diff/(60*60*1000);
        return  hours ;
    }


    public static boolean isObjectNull(Object object){
     return (object == null );
    }


}
