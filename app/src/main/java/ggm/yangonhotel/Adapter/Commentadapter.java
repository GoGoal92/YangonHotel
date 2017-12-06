package ggm.yangonhotel.Adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

import ggm.yangonhotel.Object.Constant;
import ggm.yangonhotel.Object.get;
import ggm.yangonhotel.R;


/**
 * Created by Go Goal on 11/25/2015.
 */
public class Commentadapter extends BaseAdapter {

    Activity ac;
    ArrayList<get> list;

    public Commentadapter(Activity applicationContext, ArrayList<get> list1) {

        this.ac = applicationContext;
        list = list1;


    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater in = (LayoutInflater) ac.getSystemService(ac.LAYOUT_INFLATER_SERVICE);

        View v = in.inflate(R.layout.comment, null);


        return v;
    }


}
