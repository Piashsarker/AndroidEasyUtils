# AndroidEasyUtils
Android Easy Utils is a simple android library that contains some utils method which is much more needed when working of any project. Such as - Network Info, Dialogs, GPS info , Device Info Etc.

[![](https://jitpack.io/v/Piashsarker/AndroidEasyUtils.svg)](https://jitpack.io/#Piashsarker/AndroidEasyUtils)


## Usage 

### Step 1. Add the JitPack repository & dependencies to your build file 

- Add below code in your  project level `build.gradle` file,  At the end of repositories: </br> 

```java
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
 ``` 
- Add the dependency to your app level `build.gradle` file. 
```java
	dependencies {
	     implementation 'com.github.Piashsarker:AndroidEasyUtils:1.0.1'
	}
 ```
  
 ## Usages Methods 

**VALIDATORS**

- ``` Utils.isValidURL(String url)``` - return `true` if url is valid otherwise `false`.

- ``` Utils.isValidEmail(String email) ``` - return `true` if email is valid otherwise `false`. 

- ``` Utils.isValidIP(String ipAddress) ``` - return `true` if ip is valid otherwise `false`. 

**DIALOGS**

- ```Utils.showExitDialog(Context context , String  title, String  message)``` - Show a exits dialog with title and message also with Exit & Cancel button.   By clicking Exit button user will prompt out from app. 
    	
- ``` Utils.showExitDialog(Context context, String title ,String message ,String exitButtonText, String noButtonText)```- Same as exit dialog with exitButtonText & noButtonText. 

- ```Utils.showDialog(Context context,String title, String message)``` - Show dialog with with title & message.

- ```Utils.showMessageDialog(Context context ,String title,String message, int dialogLogo)``` - Show dialog with with title & message & logo.

- ```Utils.showGPSEnableDialog(Context context)``` - Show GPS Enable dialog with default message and GPS enable button.

- ```Utils.showGPSEnableDialog(Context context, String messge , String positiveButtonText, String negativeButtonText)``` - Show GPS Enable dialog with  message, positiveButtonText , negativeButtonText.

     
       
        
      
**PROGRESS DIALOG** 

- ```Utils.showProgressDialog(Context context)``` - Show progress dialog with default title & message. 

- ```Utils.showProgressDialog(Context context, String title , String message)``` - Show progress dialog with title & message. 

- ```Utils.hideProgressDialog()``` - Don't forget to hide the progress dialog. 

**CONNECTIVITY**

- ```Utils.isNetworkAvailable(Context context)``` - return `true` if network/internet  is enable otherwise `false`. Require permission `<uses-permission android:name="android.permission.INTERNET" />` &  `<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />` in your manifest file.

- ```Utils.isGPSEnable(Context context)```  - return `true` if gps is enable otherwise `false`. 

- ```Utils.getWifiIpAddress(Context context)``` - return ip address. 

- ```Utils.getDeviceIMEI(Context context)``` = return device IMEI. Require runtime permission  `<uses-permission android:name="android.permission.READ_PHONE_STATE"/>`


**DATE TIME**

See Supported Date Time Formats  - https://developer.android.com/reference/java/text/SimpleDateFormat

- ```Utils.getCurrentDateTime()``` - Return current date time.  Default format - dd-MMM-yyyy hh:mm:ss

- ```Utils.getCurrentTime()``` - Return  currrent time. Default format - hh:mm 

- ```Utils.getCurrentTime(String format)```- Return  current time with format. 

- ```Utils.getCurrentDateTime(String format )```- Return current date time with format.

- ```Utils.getCurrentTimeInMillis()``` - Return current time in milliseconds.

- ```Utils.getLocalTimeFromUTCTime(String utcTime)``` - Return local time from UTC Time. 

- ```Utils.getLocalDateFromUTCTime(String utcTime)``` - Return local date from UTC Time. 

- ```Utils.getLocalFromUTC(String utcTime)``` - Return local date time from UTC 
	
**BITMAP**

- ```Utils.getBitmapFromFile("/path/to/storage")``` - Return `Bitmap` object from a file.

- ```Utils.getBitmapFromURI(Uri uri, Context context)```- Return `Bitmap` object from `Uri`. 

- ```Utils.getByteFromBitmap(Bitmap bitmap)```  - Return `byte[]` array from a Bitmap. 
       
- ```Utils.getBitmapFromByte(byte[] bytes)``` - Return `Bitmap` from a byte[]. 

**LOG & TOAST** 

- ```Utils.log(String TAG, String message)``` - Print a log in debug console.

- ```Utils.shortToast(Context context, String message)``` - Show short toast. 

- ```Utils.longToast(Context context, String message)``` - Show long toast.
        

**HASHMAP & ARRAYLIST**

- ```Utils.removeFromArrayListByName(ArrayList<String> list, String name)``` - Delete a specifiy string value from  Arraylist.

- ```Utils.getValuesFromHashMap(HashMap<String, String> map)``` - Return a `ArrayList` of `HashMap` values. 

- ```Utils.getKeyFromValue(HashMap<String,String> map ,String value)``` - Return key of that value of `HashMap`. 

       


**Amount Format**

- ```Utils.getFormatFromDecimalValue(Double amount)``` - Return String of Amount with Format . Example Utils.getFormatFromDecimalValue(33333.05) will return  33,333.05.

- ```Utils.getFormatFromIntegerValue(int amount )``` -  Return String of Amount with Format . Example Utils.getFormatFromIntegerlValue(33333) will return  33,333.
	
**OTHERS**
- ```Utils.isObjectNull(Object object)``` - Return `true` if a object is `null` otherwise `false`; 


### Supports 

For any kinds of support  mail sarkerpt@gmail.com 
Follow me on Twitter - https://twitter.com/PiashSarker

MIT License

Copyright (c) 2018 Piash Sarker
