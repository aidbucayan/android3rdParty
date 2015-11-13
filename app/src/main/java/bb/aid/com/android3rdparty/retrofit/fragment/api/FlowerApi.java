package bb.aid.com.android3rdparty.retrofit.fragment.api;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Filippo-TheAppExpert on 9/2/2015.
 */
public interface FlowerApi {

    @GET("/feeds/flowers.json")
    void getFlowers(Callback<String> flowers);
}
