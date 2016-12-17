package com.example.bimurto.konamoneyappinfolauncher;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

//    private static final String packageName = "com.example.bimurto.konamoneyappinfolauncher";
    private static final String packageName = "com.konai.konamoney";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(startInstalledAppDetailsActivity(this)) {
            finish();
        }
        else{
            setContentView(R.layout.activity_main);
        }
    }

    public static boolean startInstalledAppDetailsActivity(final Activity context) {
        if (context == null ) {
            return false;
        }
        if(!isPackageInstalled(packageName, context.getPackageManager())){
            return false;
        }
        final Intent i = new Intent();
        i.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        i.addCategory(Intent.CATEGORY_DEFAULT);
        i.setData(Uri.parse("package:" + packageName));
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        context.startActivity(i);
        return true;
    }

    private static boolean isPackageInstalled(String packagename, PackageManager packageManager) {
        try {
            packageManager.getPackageInfo(packagename, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
}
