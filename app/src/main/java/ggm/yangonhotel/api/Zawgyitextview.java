package ggm.yangonhotel.api;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Go Goal on 3/20/2017.
 */

public class Zawgyitextview extends TextView {

    public Zawgyitextview(Context context) {
        super(context);
        Typeface face=Typeface.createFromAsset(context.getAssets(), "zawgyi.ttf");
        this.setTypeface(face);
    }

    public Zawgyitextview(Context context, AttributeSet attrs) {
        super(context, attrs);
        Typeface face=Typeface.createFromAsset(context.getAssets(), "zawgyi.ttf");
        this.setTypeface(face);
    }

    public Zawgyitextview(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        Typeface face=Typeface.createFromAsset(context.getAssets(), "zawgyi.ttf");
        this.setTypeface(face);
    }
}
