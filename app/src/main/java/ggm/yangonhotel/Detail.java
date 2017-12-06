package ggm.yangonhotel;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.wang.avi.AVLoadingIndicatorView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import ggm.yangonhotel.Adapter.Adapter_Horilistview;
import ggm.yangonhotel.Adapter.Commentadapter;
import ggm.yangonhotel.MyHttpclient.MyRequest;
import ggm.yangonhotel.Object.Constant;
import ggm.yangonhotel.Object.Jsonparser;
import ggm.yangonhotel.Object.get;
import ggm.yangonhotel.api.HorizontalListView;
import ggm.yangonhotel.api.NestedListView;
import ggm.yangonhotel.api.Zawgyitextview;

/**
 * Created by Go Goal on 12/3/2017.
 */

public class Detail extends AppCompatActivity implements OnMapReadyCallback {


    static String id;
    static ScrollView sv;
    static AVLoadingIndicatorView pg,impg;
    static RelativeLayout network;
    static Zawgyitextview name, detail,address,rateinfo,submit;
    static TextView view, viewvalue, rating, price, pricevalue,defaultrating,ratepersontv;
    static RatingBar defaultratebar,ratebar;
    static HorizontalListView hlv;
    static Adapter_Horilistview hlvadapter;
    static AppCompatActivity ac;
    static int imgcount=3;
    static LinearLayout raterl,reviewlayout,allreviewlinear,viewrl;
    static NestedListView nlv;
    static Commentadapter nlvadapter;
    String [] ratetype=new String[]{"Hated it","Disliked it","it's OK","Liked it","Loved it"};

    SupportMapFragment mapFragment;
    private static GoogleMap mMap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);
        ac=this;
        getSupportActionBar().setTitle("");
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        id = getIntent().getExtras().getString("id");


        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
       mapFragment.getMapAsync(this);


        sv = (ScrollView) findViewById(R.id.sv);
        name = (Zawgyitextview) findViewById(R.id.name);
        detail = (Zawgyitextview) findViewById(R.id.detail);
        address = (Zawgyitextview) findViewById(R.id.address);
        rateinfo = (Zawgyitextview) findViewById(R.id.rateinfo);
        submit = (Zawgyitextview) findViewById(R.id.submit);

        ratebar= (RatingBar) findViewById(R.id.ratebar);
        view = (TextView) findViewById(R.id.viewcount);
        viewvalue = (TextView) findViewById(R.id.viewvalue);
        rating = (TextView) findViewById(R.id.rating);
        price = (TextView) findViewById(R.id.price);
        pricevalue = (TextView) findViewById(R.id.pricevalue);
        defaultrating= (TextView) findViewById(R.id.defaultrate);
        defaultratebar= (RatingBar) findViewById(R.id.defaltratebar);
        hlv= (HorizontalListView) findViewById(R.id.hlv);
        raterl= (LinearLayout) findViewById(R.id.raterl);
        viewrl= (LinearLayout) findViewById(R.id.viewrl);

        reviewlayout= (LinearLayout) findViewById(R.id.reviewlayout);
        allreviewlinear= (LinearLayout) findViewById(R.id.allreviewlinear);
        ratepersontv= (TextView) findViewById(R.id.rateperson);
        nlv= (NestedListView) findViewById(R.id.nlv);


        impg = (AVLoadingIndicatorView) findViewById(R.id.imgpg);
        impg.show();

        pg = (AVLoadingIndicatorView) findViewById(R.id.avi);
        network = (RelativeLayout) findViewById(R.id.networkerro);
        pg.show();

        Button reload = (Button) findViewById(R.id.reload);
        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyRequest.gethoteldetail(id);
                network.setVisibility(View.GONE);
                pg.setVisibility(View.VISIBLE);
                pg.show();
            }
        });

        MyRequest.gethoteldetail(id);
        
        ratebar.setOnTouchListener(new View.OnTouchListener()
        {
            private float downXValue;

            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    downXValue = event.getX();
                    return false;
                }

                if(event.getAction() == MotionEvent.ACTION_MOVE)
                {
                    // When true is returned, view will not handle this event.
                    return true;
                }

                if(event.getAction() == MotionEvent.ACTION_UP)
                {
                    float currentX = event.getX();
                    float difference = 0;
                    // Swipe on left side
                    if(currentX < downXValue)
                        difference = downXValue - currentX;
                        // Swipe on right side
                    else if(currentX > downXValue)
                        difference = currentX - downXValue;

                    if(difference < 10 )
                        return false;

                    return true;
                }
                return false;
            }
        });

        ratebar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                rateinfo.setVisibility(View.VISIBLE);
                submit.setEnabled(true);
                submit.setTextColor(Color.BLACK);

                switch ((int) ratingBar.getRating()){
                    case 1:
                        rateinfo.setText(ratetype[0]);
                        break;
                    case 2:
                        rateinfo.setText(ratetype[1]);
                        break;
                    case 3:
                        rateinfo.setText(ratetype[2]);
                        break;
                    case 4:
                        rateinfo.setText(ratetype[3]);
                        break;
                    case 5:
                        rateinfo.setText(ratetype[4]);
                        break;

                }
            }
        });

    }

    public static void Feedback_Error() {
        sv.setVisibility(View.GONE);
        pg.setVisibility(View.GONE);
        network.setVisibility(View.VISIBLE);
        pg.hide();

    }

    public static void Feedback(String s) {

        sv.setVisibility(View.VISIBLE);
        pg.setVisibility(View.GONE);
        network.setVisibility(View.GONE);
        pg.hide();



        name.setText(Jsonparser.getonestring(s, "name"));
        long jsonprice = Long.parseLong(Jsonparser.getonestring(s, "price"));
        String jsonpricevalue = Constant.format(jsonprice, 0);
        String jsonpriceunit = Constant.format(jsonprice, 1);

        price.setText(jsonpricevalue);
        pricevalue.setText(jsonpriceunit);

        imgcount=Integer.parseInt(Jsonparser.getonestring(s, "imgcount"));
        hlvadapter=new Adapter_Horilistview(ac,id,imgcount);
        hlv.setAdapter(hlvadapter);


        long jsonview = Long.parseLong(Jsonparser.getonestring(s, "view"));
        String jsonviewvalue = Constant.format(jsonview, 0);
        String jsonviewunit = Constant.format(jsonview, 1);

        view.setText(jsonviewvalue);
        int viewcountcon=Integer.parseInt(jsonviewunit);
        if (viewcountcon<1000) {

            viewrl.setVisibility(View.GONE);

        } else {
            viewvalue.setText(jsonviewunit);
        }

        rating.setText(Jsonparser.getonestring(s, "rating"));

        String detailstr=Jsonparser.getonestring(s, "detail");
        String[] lines = detailstr.split(System.getProperty("line.separator"));

        detail.setText(lines[0]+lines[1]);

        address.setText(Jsonparser.getonestring(s,"address"));

        float jsondefaultrate=Float.parseFloat(Jsonparser.getonestring(s,"defaultrating"));
        defaultratebar.setRating(jsondefaultrate);
        defaultrating.setText(jsondefaultrate+"");


        File file = new File(Constant.detailpath + id+"/");
        if (!file.exists()) {
            file.mkdir();
        }
        Imagecondition();

      int  rateperson=Integer.parseInt(Jsonparser.getonestring(s, "rateperson"));
        if (rateperson<1){
            raterl.setVisibility(View.GONE);

        }else{
            reviewlayout.setVisibility(View.VISIBLE);
            ArrayList<get> clist=new ArrayList<>();
            for (int k=0;k<rateperson;k++){
                get eg=new get();
                clist.add(eg);
            }

            nlvadapter=new Commentadapter(ac,clist);
            nlv.setAdapter(nlvadapter);
            nlv.setDividerHeight(0);

            if (rateperson>5){

                allreviewlinear.setVisibility(View.VISIBLE);
            }
        }

        ratepersontv.setText(rateperson+"");

    }

    static String progressimage = "0";

    private static void Imagecondition() {
        for (int i = 0; i < imgcount; i++) {

            int j=i+1;
            File fi = new File(Constant.detailpath + id+"/"+j+ ".ghm");
            if (!fi.exists()) {

                if (progressimage.equals("0")) {
                    progressimage = j+"";
                    new Downloadhotel(Constant.imgaepath_detail, j+"").execute();
                    break;
                }

            }else{
                impg.setVisibility(View.GONE);
                impg.hide();
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;



        mMap.setMinZoomPreference(11f);





   /*     *//*LatLng northeast = new LatLng(17.176531269184856, 96.33444227278233);
        LatLng southwest = new LatLng(16.613043381434426, 95.9897157177329);
        LatLngBounds bounds = new LatLngBounds(southwest, northeast);
*//*
        mMap.setLatLngBoundsForCameraTarget(bounds);*/





        LatLng red = new LatLng(16.80592, 96.15545);
        mMap.addMarker(new MarkerOptions().position(red));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(red, 16));

    }

    static class Downloadhotel extends AsyncTask<Void, Void, Void> {

        String imgurl, imagename;

        public Downloadhotel(String link, String imagename1) {

            imgurl = link;
            imagename = imagename1;
        }

        @Override
        protected Void doInBackground(Void... params) {

            Bitmap bmp = null;
            URL url = null;
            try {
                url = new URL(imgurl+id+"/"+imagename+".png");
                bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                File file = new File(Constant.detailpath +id+"/"+ imagename + ".ghm");

                FileOutputStream stream = null;
                try {
                    stream = new FileOutputStream(file);
                    ByteArrayOutputStream outstream = new ByteArrayOutputStream();
                    bmp.compress(Bitmap.CompressFormat.PNG, 100, outstream);
                    byte[] byteArray = outstream.toByteArray();

                    stream.write(byteArray);
                    stream.close();


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            new Processforadapter().execute();


        }
    }

    static class Processforadapter extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {

            try {

                hlvadapter=new Adapter_Horilistview(ac,id,imgcount);


            } catch (Exception e) {

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            hlv.setAdapter(hlvadapter);
            progressimage = "0";
            Imagecondition();
        }
    }







    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            case R.id.bookmark:
                menuItem.setChecked(!menuItem.isChecked());
                menuItem.setIcon(menuItem.isChecked() ? R.drawable.ic_bookicon_select : R.drawable.ic_bookicon_noselect);
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bookmark, menu);
        return true;
    }
}
