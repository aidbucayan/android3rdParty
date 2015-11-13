package bb.aid.com.android3rdparty.retrofit.fragment.api;

import com.google.gson.GsonBuilder;

import bb.aid.com.android3rdparty.utils.Constants;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by Filippo-TheAppExpert on 9/2/2015.
 */
public class RestApiManager {

    private FlowerApi mFlowerApi;

    public FlowerApi getFlowerApi() {

        if(mFlowerApi == null) {
            GsonBuilder gson = new GsonBuilder();
            gson.registerTypeAdapter(String.class, new StringDesirializer());

            mFlowerApi = new RestAdapter.Builder()
                    .setEndpoint(Constants.BASE_URL)
                    .setConverter(new GsonConverter(gson.create()))
                    .build()
                    .create(FlowerApi.class);
        }
        return mFlowerApi;
    }

}
