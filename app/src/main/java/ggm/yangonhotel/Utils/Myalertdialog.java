package ggm.yangonhotel.Utils;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import ggm.yangonhotel.MainActivity;
import ggm.yangonhotel.R;
import ggm.yangonhotel.api.Zawgyitextview;


/**
 * Created by Go Goal on 6/30/2017.
 */

public class Myalertdialog {


    public static void show_exit(String msg, final AppCompatActivity ac, String positive, String title) {

        AlertDialog.Builder ab = new AlertDialog.Builder(ac);
        ab.setTitle(title);
        View v = ac.getLayoutInflater().inflate(R.layout.ontextzawwgyirow, null);
        ab.setView(v);
        Zawgyitextview tv = (Zawgyitextview) v.findViewById(R.id.ttv);
        tv.setText(msg);

        ab.setPositiveButton(positive, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                ac.finish();
            }
        });
        ab.setCancelable(false);
        ab.show();

    }

    public static void show_updateremind(String msg, final AppCompatActivity ac, String positive, String cancel, String title, final String url) {

        AlertDialog.Builder ab = new AlertDialog.Builder(ac);
        ab.setTitle(title);
        View v = ac.getLayoutInflater().inflate(R.layout.ontextzawwgyirow, null);
        ab.setView(v);
        Zawgyitextview tv = (Zawgyitextview) v.findViewById(R.id.ttv);
        tv.setText(msg);

        ab.setPositiveButton(positive, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri
                        .parse(url));
                ac.startActivity(intent);

            }
        }).setNegativeButton(cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent it = new Intent(ac, MainActivity.class);
                ac.startActivity(it);
            }
        });
        ab.setCancelable(false);
        ab.show();

    }

    public static void show_update(String msg, final AppCompatActivity ac, String positive, String cancel, String title, final String url) {

        AlertDialog.Builder ab = new AlertDialog.Builder(ac);
        ab.setTitle(title);
        View v = ac.getLayoutInflater().inflate(R.layout.ontextzawwgyirow, null);
        ab.setView(v);
        Zawgyitextview tv = (Zawgyitextview) v.findViewById(R.id.ttv);
        tv.setText(msg);

        ab.setPositiveButton(positive, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri
                        .parse(url));
                ac.startActivity(intent);

            }
        }).setNegativeButton(cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                ac.finish();
            }
        });
        ab.setCancelable(false);
        ab.show();
    }


    public static void WILLBEDELETE(final AppCompatActivity ac) {
        AlertDialog.Builder ab = new AlertDialog.Builder(ac);
        ab.setTitle("Alert");
        View v = ac.getLayoutInflater().inflate(R.layout.ontextzawwgyirow, null);
        ab.setView(v);
        Zawgyitextview tv = (Zawgyitextview) v.findViewById(R.id.ttv);
        tv.setText("ဤေနရာမွ ထြက္ခြာသြားလွ်င္ ယခု ျပဳလုပ္ထားသမွ် အရာအားလံုး ေပ်ာက္သြားမယ္ျဖစ္သည္။ ထြက္ခြာမည္မွာ ေသခ်ာပါသလား ?\n");

        ab.setPositiveButton("DISCARD POST", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                ac.finish();

            }
        }).setNegativeButton("CANCEL", null);
        ab.setCancelable(false);
        ab.show();
    }

    public static void showresultupload(final AppCompatActivity ac, String msg) {
        AlertDialog.Builder ab = new AlertDialog.Builder(ac);
        ab.setTitle("Information");
        View v = ac.getLayoutInflater().inflate(R.layout.ontextzawwgyirow, null);
        ab.setView(v);
        Zawgyitextview tv = (Zawgyitextview) v.findViewById(R.id.ttv);
        tv.setText(msg);

        ab.setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                ac.finish();

            }
        });
        ab.setCancelable(false);
        ab.show();

    }
}
