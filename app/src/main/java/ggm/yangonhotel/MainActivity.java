package ggm.yangonhotel;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wang.avi.AVLoadingIndicatorView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import ggm.yangonhotel.Adapter.Mainadapter;
import ggm.yangonhotel.MyHttpclient.MyRequest;
import ggm.yangonhotel.Object.Constant;
import ggm.yangonhotel.Object.Jsonparser;
import ggm.yangonhotel.Object.get;
import ggm.yangonhotel.api.Zawgyitextview;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    static ArrayList<get> list;
    static RecyclerView rv;
    LinearLayoutManager llm;
    static AppCompatActivity ac;
    static Mainadapter adapter;
    static AVLoadingIndicatorView pg;
    static RelativeLayout network;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ac = this;


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        View headerView = navigationView.getHeaderView(0);
        Zawgyitextview username = (Zawgyitextview) headerView.findViewById(R.id.username);
        username.setText(Constant.username);




        list = new ArrayList<>();



        rv = (RecyclerView) findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);

        adapter = new Mainadapter(ac, list);
        rv.setAdapter(adapter);


        pg = (AVLoadingIndicatorView) findViewById(R.id.avi);
        network = (RelativeLayout) findViewById(R.id.networkerro);
        pg.show();

        Button reload = (Button) findViewById(R.id.reload);
        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyRequest.getlatest();
                network.setVisibility(View.GONE);
                pg.setVisibility(View.VISIBLE);
                pg.show();
            }
        });

        MyRequest.getlatest();

    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_share) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public static void Feedback(String s) {

        rv.setVisibility(View.VISIBLE);
        pg.setVisibility(View.GONE);
        network.setVisibility(View.GONE);
        pg.hide();


        list= new ArrayList<>();
        list = Jsonparser.getlatestlist(s);
        adapter.refresh(list);
        Imagecondition();

    }

    public static void Feedback_Error() {
        rv.setVisibility(View.GONE);
        pg.setVisibility(View.GONE);
        network.setVisibility(View.VISIBLE);
        pg.hide();
    }

    static String progressimage = "0";

    private static void Imagecondition() {
        for (int i = 0; i < list.size(); i++) {


            File fi = new File(Constant.Coverpath + list.get(i).getHotelid() + ".ghm");
            if (!fi.exists()) {

                if (progressimage.equals("0")) {
                    progressimage = list.get(i).getHotelid();
                    new Downloadhotel(Constant.imgaepath_cover, list.get(i).getHotelid()).execute();
                    break;
                }

            }
        }
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
                url = new URL(imgurl+imagename+".png");
                bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                File file = new File(Constant.Coverpath + imagename + ".ghm");

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

                adapter.refresh(list);

            } catch (Exception e) {

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            adapter.load_pg(false);
            progressimage = "0";
            Imagecondition();
        }
    }



}
