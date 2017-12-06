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
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import ggm.yangonhotel.Object.Constant;
import ggm.yangonhotel.Object.get;
import ggm.yangonhotel.R;


/**
 * Created by Go Goal on 11/25/2015.
 */
public class Adapter_Horilistview extends BaseAdapter {

    Activity ac;
    String hotelid;
    int size;

    public Adapter_Horilistview(Activity applicationContext,String hotelid1,int size1) {

        this.ac = applicationContext;
        hotelid= hotelid1;
        size=size1;


    }

    @Override
    public int getCount() {
        return size;
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

        View v = in.inflate(R.layout.imagerow, null);
        ImageView iv = (ImageView) v.findViewById(R.id.iv);

        int po=i+1;
        Bitmap myBitmap = BitmapFactory.decodeFile(Constant.detailpath +hotelid+"/"+po+".ghm");
        Drawable myDrawable = new BitmapDrawable(ac.getResources(), myBitmap);
        iv.setBackground(myDrawable);



        return v;
    }


    public void refresh(int imgcount) {
        size=imgcount;
        notifyDataSetChanged();
    }
}
