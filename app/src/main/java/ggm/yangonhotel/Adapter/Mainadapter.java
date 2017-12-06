package ggm.yangonhotel.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;

import java.util.ArrayList;

import ggm.yangonhotel.Detail;
import ggm.yangonhotel.Object.Constant;
import ggm.yangonhotel.Object.get;
import ggm.yangonhotel.R;
import ggm.yangonhotel.Utils.AutoScrollViewPager;
import ggm.yangonhotel.Utils.HFRecyclerView;
import ggm.yangonhotel.api.Zawgyitextview;
import me.relex.circleindicator.CircleIndicator;


/**
 * Created by Go Goal on 6/29/2017.
 */

public class Mainadapter extends HFRecyclerView<get> {


    ArrayList<get> list;
    Activity ac;


    public Mainadapter(Activity getactivity, ArrayList<get> asklist1) {
        super(asklist1, true, true);
        list = asklist1;
        ac = getactivity;

    }

    @Override
    protected RecyclerView.ViewHolder getItemView(LayoutInflater inflater, ViewGroup parent, int viewType) {
        return new ItemViewHolder(inflater.inflate(R.layout.hotel_row, parent, false));
    }

    @Override
    protected RecyclerView.ViewHolder getHeaderView(LayoutInflater inflater, ViewGroup parent) {
        return new HeaderViewHolder(inflater.inflate(R.layout.nothing, parent, false));
    }

    @Override
    protected RecyclerView.ViewHolder getFooterView(LayoutInflater inflater, ViewGroup parent) {
        return new FooterViewHolder(inflater.inflate(R.layout.footer_pg, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int i) {
        if (holder instanceof HeaderViewHolder) {
            final HeaderViewHolder holder1 = (HeaderViewHolder) holder;
            Toprated_adapter adapter = new Toprated_adapter(ac, new ArrayList<get>());
            holder1.vpager.setAdapter(adapter);
            holder1.vpager.setCurrentItem(180);
            holder1.indicator.setViewPager(holder1.vpager);
            holder1.vpager.startAutoScroll(3000);

        } else if (holder instanceof ItemViewHolder) {
            final ItemViewHolder holder1 = (ItemViewHolder) holder;
            holder1.seeallclick.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it=new Intent(ac,Detail.class);
                    it.putExtra("id",list.get(i-1).getHotelid());
                    ac.startActivity(it);
                }
            });

            holder1.hname.setText(list.get(i-1).getHotelname());
            try{
                holder1.hprice.setText(Constant.getcommannmuber(list.get(i-1).getHotelprice())+"  KYAT/DAY");
            }catch (Exception e){
                holder1.hprice.setText(list.get(i-1).getHotelprice());
            }

            holder1.hrating.setText(list.get(i-1).getHotelrating());
            holder1.hview.setText(list.get(i-1).getHotelview()+" Views");
            holder1.haddress.setText(list.get(i-1).getHoteladdress());
            holder1.rb.setRating(Float.parseFloat(list.get(i-1).getHotelrating()));


            Bitmap myBitmap = BitmapFactory.decodeFile(Constant.Coverpath + list.get(i - 1).getHotelid() + ".ghm");
            Drawable myDrawable = new BitmapDrawable(ac.getResources(), myBitmap);
            holder1.iv.setBackground(myDrawable);


        } else if (holder instanceof FooterViewHolder) {

            FooterViewHolder footholder = (FooterViewHolder) holder;
            if (showpg) {
                footholder.progressBar.setVisibility(View.VISIBLE);

            } else {
                footholder.progressBar.setVisibility(View.GONE);

            }


        }
    }

    static boolean showpg = false;

    @Override
    public int getItemCount() {
        return list.size() + 2;
    }

    public void load_pg(boolean b) {
        showpg = b;
        notifyDataSetChanged();
    }

    public void refresh(ArrayList<get> list1) {
        list = list1;
        notifyDataSetChanged();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout seeallclick;
        public Zawgyitextview hname,hprice,hrating,hview,haddress;
        public ImageView iv;
        public RatingBar rb;




        public ItemViewHolder(View v) {

            super(v);
            seeallclick= (LinearLayout) v.findViewById(R.id.seeallclick);
            hname= (Zawgyitextview) v.findViewById(R.id.hname);
            hprice= (Zawgyitextview) v.findViewById(R.id.hprice);
            hrating= (Zawgyitextview) v.findViewById(R.id.hrating);
            hview= (Zawgyitextview) v.findViewById(R.id.hview);
            haddress= (Zawgyitextview) v.findViewById(R.id.haddress);
            iv= (ImageView) v.findViewById(R.id.iv);
            rb= (RatingBar) v.findViewById(R.id.hotelrate);

        }
    }


    public class HeaderViewHolder extends RecyclerView.ViewHolder {

        public AutoScrollViewPager vpager;
        public CircleIndicator indicator;


        public HeaderViewHolder(View itemView) {
            super(itemView);
            vpager = (AutoScrollViewPager) itemView.findViewById(R.id.viewpager);
            indicator= (CircleIndicator) itemView.findViewById(R.id.indicator);
        }
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public FooterViewHolder(View v) {
            super(v);

            progressBar = (ProgressBar) v.findViewById(R.id.pg);
        }
    }
}
