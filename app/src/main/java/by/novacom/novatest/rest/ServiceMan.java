package by.novacom.novatest.rest;

import android.util.Log;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import by.novacom.novatest.Constants;
import by.novacom.novatest.model.Root;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by al-ev on 19.01.2017.
 */
public class ServiceMan {
    private static ServiceMan ourInstance = new ServiceMan();
    private Retrofit mRetrofit;
    private static Root root;


    public static ServiceMan getInstance() {
        return ourInstance;
    }

    private ServiceMan() {
    }


    public Retrofit getRetrofit() {
        if (mRetrofit == null) {

            OkHttpClient client = new OkHttpClient.Builder()

                    .addNetworkInterceptor(new StethoInterceptor())
                    .addInterceptor(new Interceptor() {
                        @Override
                        public okhttp3.Response intercept(Chain chain) throws IOException {
                            Request request = chain.request();
                            Headers.Builder builder = request.headers().newBuilder();
                            request = request.newBuilder().headers(builder.build()).build();

                            return chain.proceed(request);
                        }
                    })
                    .build();

            GsonBuilder gsonBuilder = new GsonBuilder();


            Gson gson = gsonBuilder.create();

            mRetrofit = new Retrofit.Builder()
                    .baseUrl(Constants.API_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .build();
        }
        return mRetrofit;
    }

    public static Root getRoot() {
        return root;
    }

    public void getGifs(final ICallBack callBack) {
        IGApi iowApi = getRetrofit().create(IGApi.class);
        //Log.d("Moi", "create r ");

        Call<Root> call = iowApi.getGifs(Constants.API_KEY);
        call.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                root = response.body();

                if (response.isSuccessful() && root != null) {
                    //ok
                    //Log.d("Moi", "all ok, ");
                    callBack.response(false);
                } else {
                    //bad
                    try {
                        Log.d("Moi", "error response body = " + response.errorBody().string());
                    } catch (IOException e) {

                    }
                    callBack.response(true);
                }
            }



            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                Log.d("Moi", "error f " + t.getMessage());
                callBack.response(true);
            }
        });
    }

    public void getGifSearch(String qq, final ICallBack callBack) {
        IGApi iowApi = getRetrofit().create(IGApi.class);

        Call<Root> call = iowApi.getQuery(qq, Constants.API_KEY);
        call.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                root = response.body();

                if (response.isSuccessful() && root != null) {

                    callBack.response(false);
                } else {

                    try {
                        Log.d("Moi", "error response body = " + response.errorBody().string());
                    } catch (IOException e) {

                    }
                    callBack.response(true);
                }
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                Log.d("Moi", "error f " + t.getMessage());
                callBack.response(true);
            }
        });
    }

    public void getRating(String qq, String rate, final ICallBack callBack) {
        IGApi iowApi = getRetrofit().create(IGApi.class);

        Call<Root> call = iowApi.getRating(qq, rate, Constants.API_KEY);
        call.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                root = response.body();

                if (response.isSuccessful() && root != null) {
                    //ok
                    //Log.d("Moi", "all ok, ");
                    callBack.response(false);
                } else {
                    //bad
                    try {
                        Log.d("Moi", "error response body = " + response.errorBody().string());
                    } catch (IOException e) {

                    }
                    callBack.response(true);
                }
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                Log.d("Moi", "error f " + t.getMessage());
                callBack.response(true);
            }
        });
    }
}
