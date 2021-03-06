/*
 * Copyright 2013 wada811<at.wada811@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package at.wada811.utils;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.os.Build;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.Toast;

public class AndroidUtils {

    // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    // Android Build Version 判定
    // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    /**
     * Android OS バージョンが指定のバージョン以上の場合は true
     * 
     * @param versionCode
     * @return
     */
    public static boolean isMoreThanBuildVersion(int versionCode){
        return Build.VERSION.SDK_INT >= versionCode;
    }

    /**
     * Android OS バージョンが指定のバージョン未満の場合は true
     * 
     * @param versionCode
     * @return
     */
    public static boolean isLessThanBuildVersion(int version_code){
        return Build.VERSION.SDK_INT < version_code;
    }

    // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    // Application Meta Info
    // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    /**
     * バージョンコードを取得する
     * 
     * @param context
     * @return
     */
    public static int getVersionCode(Context context){
        PackageManager pm = context.getPackageManager();
        int versionCode = 0;
        try{
            PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), 0);
            versionCode = packageInfo.versionCode;
        }catch(NameNotFoundException e){
            e.printStackTrace();
        }
        return versionCode;
    }

    /**
     * バージョン名を取得する
     * 
     * @param context
     * @return
     */
    public static String getVersionName(Context context){
        PackageManager pm = context.getPackageManager();
        String versionName = "";
        try{
            PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = packageInfo.versionName;
        }catch(NameNotFoundException e){
            e.printStackTrace();
        }
        return versionName;
    }

    public static boolean isDebug(Context context){
        PackageManager pm = context.getPackageManager();
        try{
            ApplicationInfo applicationInfo = pm.getApplicationInfo(context.getPackageName(), 0);
            return (applicationInfo.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        }catch(NameNotFoundException e){
            e.printStackTrace();
        }
        return false;
    }

    // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    // Toast 表示
    // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    /**
     * Toastを表示する
     * 
     * @param context
     * @param message
     */
    public static void showToast(final Context context, final String message){
        if(ThreadUtils.isMainThread()){
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        }else{
            if(context instanceof Service){
                // Handler で表示する
                // 
                // public class HandlerService extends Service {
                // 
                //     Handler handler;
                // 
                //     @Override
                //     public void onCreate() {
                //         super.onCreate();
                //         handler = new Handler();
                //     }
                // 
                //     private void runOnUiThread(Runnable runnable) {
                //         handler.post(runnable);
                //     }
                // 
                // }
                throw new RuntimeException("Cannot show Toast in Service.");
            }else if(context instanceof Activity){
                ((Activity)context).runOnUiThread(new Runnable(){
                    @Override
                    public void run(){
                        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                    }
                });
            }
        }
    }

    /**
     * Toastを表示する
     * 
     * @param context
     * @param resId
     */
    public static void showToast(final Context context, final int resId){
        if(ThreadUtils.isMainThread()){
            Toast.makeText(context, resId, Toast.LENGTH_LONG).show();
        }else{
            if(context instanceof Service){
                // Handler で表示する
                // 
                // public class HandlerService extends Service {
                // 
                //     Handler handler;
                // 
                //     @Override
                //     public void onCreate() {
                //         super.onCreate();
                //         handler = new Handler();
                //     }
                // 
                //     private void runOnUiThread(Runnable runnable) {
                //         handler.post(runnable);
                //     }
                // 
                // }
                throw new RuntimeException("Cannot show Toast in Service.");
            }else if(context instanceof Activity){
                ((Activity)context).runOnUiThread(new Runnable(){
                    @Override
                    public void run(){
                        Toast.makeText(context, resId, Toast.LENGTH_LONG).show();
                    }
                });
            }
        }
    }

    /**
     * Toastを表示する
     * 
     * @param context
     * @param bitmap
     */
    public static void showToast(final Context context, final Bitmap bitmap){
        if(ThreadUtils.isMainThread()){
            Toast toast = new Toast(context);
            ImageView image = new ImageView(context);
            image.setImageBitmap(bitmap);
            image.setLayoutParams(new LayoutParams(bitmap.getWidth(), bitmap.getHeight()));
            toast.setView(image);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.show();
        }else{
            if(context instanceof Service){
                // Handler で表示する
                // 
                // public class HandlerService extends Service {
                // 
                //     Handler handler;
                // 
                //     @Override
                //     public void onCreate() {
                //         super.onCreate();
                //         handler = new Handler();
                //     }
                // 
                //     private void runOnUiThread(Runnable runnable) {
                //         handler.post(runnable);
                //     }
                // 
                // }
                throw new RuntimeException("Cannot show Toast in Service.");
            }else if(context instanceof Activity){
                ((Activity)context).runOnUiThread(new Runnable(){
                    @Override
                    public void run(){
                        Toast toast = new Toast(context);
                        ImageView image = new ImageView(context);
                        image.setImageBitmap(bitmap);
                        image.setLayoutParams(new LayoutParams(bitmap.getWidth(), bitmap.getHeight()));
                        toast.setView(image);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.setDuration(Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });
            }
        }
    }

}
