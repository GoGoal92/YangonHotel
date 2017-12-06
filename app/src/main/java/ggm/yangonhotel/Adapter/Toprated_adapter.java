package ggm.yangonhotel.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import ggm.yangonhotel.Object.Constant;
import ggm.yangonhotel.Object.get;
import ggm.yangonhotel.R;


public class Toprated_adapter extends PagerAdapter {
    LayoutInflater mLayoutInflater;
    Context con;
    Activity ac;
    int[] color = new int[]{Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK};
    ArrayList<get> list;

    public Toprated_adapter(Activity ac, ArrayList<get> list1) {
        // TODO Auto-generated constructor stub

        list = list1;
        con = ac;
        this.ac = ac;
        mLayoutInflater = (LayoutInflater) con
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return 360;
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        View itemView = mLayoutInflater.inflate(R.layout.home_pager_layout,
                container, false);
        final LinearLayout back = (LinearLayout) itemView.findViewById(R.id.back);
        TextView mtitle= (TextView) itemView.findViewById(R.id.mtitle);
        TextView minfo= (TextView) itemView.findViewById(R.id.mdetail);


        Bitmap myBitmap = BitmapFactory.decodeFile(Constant.Coverpath +  "1.ghm");
        Drawable myDrawable = new BitmapDrawable(ac.getResources(), myBitmap);
        back.setBackground(myDrawable);



      /*  mtitle.setText(list.get(position%5).getTitle());
        minfo.setText("Like "+ calculate_st.format(Long.parseLong(list.get(position%5).getLike()))+"\nView "+calculate_st.format(Long.parseLong(list.get(position%5).getView())) );


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.get(position%5).getMid().contains("s")){

                    String mid=list.get(position%5).getMid();
                    mid=mid.replace("s","");
                    Intent it=new Intent(ac,Series.class);
                    it.putExtra("mid",mid);
                    it.putExtra("mname",list.get(position%5).getTitle());
                    it.putExtra("image",list.get(position%5).getImage());
                    it.putExtra("detail",list.get(position%5).getTitle());
                    ac.startActivity(it);



                }else{

                    Intent it=new Intent(ac,Detail.class);
                    it.putExtra("mid",list.get(position%5).getMid());
                    it.putExtra("type","movie");
                    it.putExtra("mname",list.get(position%5).getTitle());
                    it.putExtra("image",list.get(position%5).getImage());
                    ac.startActivity(it);

                }
            }
        });
*/

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }


    public void refresh(ArrayList<get> list1) {
        list=list1;
        notifyDataSetChanged();
    }
}
