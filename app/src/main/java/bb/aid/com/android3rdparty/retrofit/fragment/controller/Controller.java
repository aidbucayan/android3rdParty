package bb.aid.com.android3rdparty.retrofit.fragment.controller;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import bb.aid.com.android3rdparty.retrofit.fragment.api.RestApiManager;
import bb.aid.com.android3rdparty.retrofit.fragment.model.RetrofitFlower;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Filippo-TheAppExpert on 9/2/2015.
 */
public class Controller {

    private static final String TAG = Controller.class.getSimpleName();
    private FlowerCallbackListener mListener;
    private RestApiManager mApiManager;

    public Controller(FlowerCallbackListener listener) {
        mListener = listener;
        mApiManager = new RestApiManager();
    }

    public void startFetching() {
        mApiManager.getFlowerApi().getFlowers(new Callback<String>() {
            @Override
            public void success(String s, Response response) {
                Log.d(TAG, "JSON :: " + s);

                try {
                    JSONArray array = new JSONArray(s);

                    for(int i = 0; i < array.length(); i++) {
                        JSONObject object = array.getJSONObject(i);

                        RetrofitFlower flower = new RetrofitFlower.Builder()
                                .setCategory(object.getString("category"))
                                .setPrice(object.getDouble("price"))
                                .setInstructions(object.getString("instructions"))
                                .setPhoto(object.getString("photo"))
                                .setName(object.getString("name"))
                                .setProductId(object.getInt("productId"))
                                .build();

                        mListener.onFetchProgress(flower);

                    }

                } catch (JSONException e) {
                    mListener.onFetchFailed();
                }


                mListener.onFetchComplete();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d(TAG, "Error :: " + error.getMessage());
                mListener.onFetchComplete();
            }
        });
    }

    public interface FlowerCallbackListener {

        void onFetchStart();
        void onFetchProgress(RetrofitFlower flower);
        void onFetchProgress(List<RetrofitFlower> flowerList);
        void onFetchComplete();
        void onFetchFailed();
    }
}
