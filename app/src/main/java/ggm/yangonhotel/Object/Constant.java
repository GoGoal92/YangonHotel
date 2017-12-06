package ggm.yangonhotel.Object;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * Created by Go Goal on 12/3/2017.
 */

public class Constant {

    public static String Coverpath = Environment.getExternalStorageDirectory() + "/GUIDHM/cover/";
    public static String detailpath = Environment.getExternalStorageDirectory() + "/GUIDHM/detail/";

    public static String host = "http://18.217.71.66/hotel/api/v1/";
    public static String imgaepath_cover = "http://18.217.71.66/hotel/api/hotelcover/";
    public static String imgaepath_detail = "http://18.217.71.66/hotel/api/detailimage/";

    public static String apikey = "aa";
    public static String username = "bb";

    public static void generateapi(AppCompatActivity ac) {
        try {

            PackageInfo info = ac.getPackageManager().getPackageInfo("market.goldandgo.videonew", PackageManager.GET_SIGNATURES);
            for (android.content.pm.Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String sign = Base64.encodeToString(md.digest(), Base64.DEFAULT);
                apikey = sign;

            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }

        apikey = "aa";
    }


    public static String getcommannmuber(String s){

        String ss= NumberFormat.getNumberInstance(Locale.US).format(s);
        return ss;

    }


    private static final NavigableMap<Long, String> suffixes = new TreeMap<>();
    static {
        suffixes.put(1_000L, "THOUSAND");
        suffixes.put(1_000_000L, "MILLION");
        suffixes.put(1_000_000_000L, "BILLION");
        suffixes.put(1_000_000_000_000L, "TRILLION");
    }

    public static String format(long value,int i) {

        String suffix="0";
        //Long.MIN_VALUE == -Long.MIN_VALUE so we need an adjustment here
        if (value == Long.MIN_VALUE) return format(Long.MIN_VALUE + 1,i);
        if (value < 0) return "-" + format(-value,i);
        if (value < 1000) return Long.toString(value); //deal with easy case

        Map.Entry<Long, String> e = suffixes.floorEntry(value);
        Long divideBy = e.getKey();
        suffix = e.getValue();

        long truncated = value / (divideBy / 10); //the number part of the output times 10
        boolean hasDecimal = truncated < 100 && (truncated / 10d) != (truncated / 10);


        String [] str=new String[]{ (truncated / 10) +"",suffix};

       // return hasDecimal ? (truncated / 10d) + suffix : (truncated / 10) + suffix;

        return  str[i];
    }


    public static void setusername(String s) {
        username=s;
    }
}
