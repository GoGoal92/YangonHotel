package ggm.yangonhotel.MyHttpclient;


import android.os.Build;

import ggm.yangonhotel.Detail;
import ggm.yangonhotel.MainActivity;
import ggm.yangonhotel.Object.Constant;
import ggm.yangonhotel.Object.phoneid;
import ggm.yangonhotel.Splash;
import ggm.yangonhotel.Utils.Stringconverter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Go Goal on 6/29/2017.
 */

public class MyRequest {




    public static void getlatest() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.host).addConverterFactory(new Stringconverter()).build();


        interface_api service = retrofit.create(interface_api.class);
        Call<String> result = service.latest(Constant.apikey);
        result.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                try {

                    MainActivity.Feedback(response.body().toString());
                } catch (Exception e) {
                    MainActivity.Feedback_Error();
                    e.printStackTrace();

                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                MainActivity.Feedback_Error();
                t.printStackTrace();
            }
        });
    }


    public static void gethoteldetail(String id) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.host).addConverterFactory(new Stringconverter()).build();


        interface_api service = retrofit.create(interface_api.class);
        Call<String> result = service.gethoteldetail(Constant.apikey,id);
        result.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                try {

                    Detail.Feedback(response.body().toString());
                } catch (Exception e) {
                    Detail.Feedback_Error();
                    e.printStackTrace();

                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Detail.Feedback_Error();
                t.printStackTrace();
            }
        });

    }

    public static void checkversion() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.host).addConverterFactory(new Stringconverter()).build();


        interface_api service = retrofit.create(interface_api.class);
        Call<String> result = service.checkversion(Constant.apikey, phoneid.getid(),Constant.username);
        result.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                try {

                    Splash.Feedback(response.body().toString());
                } catch (Exception e) {
                    Splash.Feedback_Error();
                    e.printStackTrace();

                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Splash.Feedback_Error();
                t.printStackTrace();
            }
        });
    }
}
