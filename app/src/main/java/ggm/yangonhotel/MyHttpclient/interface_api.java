package ggm.yangonhotel.MyHttpclient;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Go Goal on 6/29/2017.
 */

public interface interface_api {

    @FormUrlEncoded
    @POST("latest")
    Call<String> latest(@Field("api") String name);


    @FormUrlEncoded
    @POST("gethoteldetail")
    Call<String> gethoteldetail(@Field("api") String name,@Field("id") String nameid);


    @FormUrlEncoded
    @POST("checkversion")
    Call<String> checkversion(@Field("api") String name,@Field("pid") String nameid,@Field("name") String username);

}
