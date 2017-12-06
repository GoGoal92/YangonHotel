package ggm.yangonhotel;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.List;

import ggm.yangonhotel.MyHttpclient.MyRequest;
import ggm.yangonhotel.Object.Constant;
import ggm.yangonhotel.Object.Jsonparser;
import ggm.yangonhotel.Object.phoneid;
import ggm.yangonhotel.Utils.Myalertdialog;
import ggm.yangonhotel.api.Zawgyitextview;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by Go Goal on 12/5/2017.
 */

public class Splash extends AppCompatActivity implements EasyPermissions.PermissionCallbacks{

    static AppCompatActivity ac;
    TextView loading;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        ac = this;

        loading = (TextView) findViewById(R.id.loading);

        if (!checkIfAlreadyhavePermission()){
            checkStoragePermission();
        }


        //usernmae

        Constant.setusername(Build.BRAND+Build.MODEL);

        //username

    }


    private boolean checkIfAlreadyhavePermission() {
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int result1= ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);
        if (result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED ) {
            return true;
        } else {
            return false;
        }
    }


    private void Foldercreate() {

        String [] str=new String[]{"GUIDHM","GUIDHM/cover","GUIDHM/detail"};
        for (int i=0;i<str.length;i++){
            File fi = new File( Environment.getExternalStorageDirectory() + "/"+str[i]);
            if (!fi.exists())
                fi.mkdir();
        }



    }


    private void checkStoragePermission() {
        String[] per = new String[]{android.Manifest.permission.READ_PHONE_STATE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(ac, per)) {
            // Already have permission, do the thing

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    MyRequest.checkversion();
                    loading.setText("Checking App Version (1.0)");
                    phoneid pid=new phoneid(ac);
                    Constant.generateapi(ac);
                    Foldercreate();

                }
            },2000);
        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(this, "All Permission Must Grant", 200, per);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode==200){
            MyRequest.checkversion();
            loading.setText("Checking App Version (1.0)");
            phoneid pid=new phoneid(ac);
            Constant.generateapi(ac);
            Foldercreate();
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                MyRequest.checkversion();
                loading.setText("Checking App Version (1.0)");
            }
        },2000);
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {

        Toast.makeText(ac,"Permission Needed",Toast.LENGTH_SHORT).show();
       ac.finish();

    }



    public static void Feedback_Error() {

        AlertDialog.Builder ab = new AlertDialog.Builder(ac);
        ab.setTitle("Connection Problem");
        View v = ac.getLayoutInflater().inflate(R.layout.ontextzawwgyirow, null);
        ab.setView(v);
        Zawgyitextview tv = (Zawgyitextview) v.findViewById(R.id.ttv);
        tv.setText("Network Fail ! Please Check Your Network");

        ab.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                MyRequest.checkversion();

            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                ac.finish();
            }
        });

        ab.show();

    }

    public static void Feedback(String s) {






        String status = Jsonparser.getonestring(s, "status");
        if (status.equals("0")) {

            Intent it = new Intent(ac, MainActivity.class);
            ac.startActivity(it);
            ac.finish();

        } else if (status.equals("1")) {
            String msg = Jsonparser.getonestring(s, "msg");
            Myalertdialog.show_exit(msg, ac, "Come back Later", "Server Matainance");

        } else if (status.equals("2")) {

            String msg = Jsonparser.getonestring(s, "msg");
            String url = Jsonparser.getonestring(s, "url");
            Myalertdialog.show_updateremind(msg, ac, "Update", "Later", "Update Remind", url);

        } else if (status.equals("3")) {

            String msg = Jsonparser.getonestring(s, "msg");
            String url = Jsonparser.getonestring(s, "url");

            Myalertdialog.show_update(msg, ac, "Update", "Cancel", "Need To Update", url);

        }



    }


}
