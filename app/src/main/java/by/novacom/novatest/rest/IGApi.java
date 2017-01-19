package by.novacom.novatest.rest;

import by.novacom.novatest.model.Root;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by al-ev on 19.01.2017.
 */
public interface IGApi {
    @GET("v1/stickers/trending")
    Call<Root> getGifs(@Query("api_key") String string);

    @GET("v1/stickers/search")
    Call<Root> getQuery(@Query("q") String str, @Query("api_key") String string);

    @GET("v1/stickers/search")
    Call<Root> getRating(@Query("q") String str,@Query("rating") String rate, @Query("api_key") String string);
}
