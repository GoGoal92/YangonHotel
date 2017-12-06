package ggm.yangonhotel.Object;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Go Goal on 6/29/2017.
 */

public class Jsonparser {



    public static String getonestring(String str, String status) {
        JSONObject jo = null;
        String result = "";

        try {
            jo = new JSONObject(str);
            result = jo.getString(status);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return result;
    }

    public static ArrayList<get> getlatestlist(String s) {
        ArrayList<get> list = new ArrayList<>();
        try {

            JSONArray id = new JSONArray(s);
            for (int i = 0; i < id.length(); i++) {
                JSONObject c1 = id.getJSONObject(i);
                get eg = new get();
                eg.setHotelid(c1.getString("ID"));
                eg.setHotelname(c1.getString("name"));
                eg.setHoteladdress(c1.getString("address"));
                eg.setHotelprice(c1.getString("price"));
                eg.setHotelrating(c1.getString("rating"));
                eg.setHotelview(c1.getString("view"));
                list.add(eg);
            }


        } catch (Exception e) {

        }
        return list;
    }


}
