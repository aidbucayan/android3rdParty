package bb.aid.com.android3rdparty.volley.fragment;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import bb.aid.com.android3rdparty.R;
import bb.aid.com.android3rdparty.base.BaseFragment;
import bb.aid.com.android3rdparty.utils.Constants;
import bb.aid.com.android3rdparty.utils.LogMe;
import bb.aid.com.android3rdparty.volley.fragment.adapter.VolleyFlowersAdapter;
import bb.aid.com.android3rdparty.volley.fragment.model.VolleyFlower;

public class VolleyFragment extends BaseFragment {

    private static final String TAG = VolleyFragment.class.getSimpleName();
    private View view;
    private JsonObjectRequest jsonRequest;
    private RequestQueue mRequestQueue;

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private List<VolleyFlower> mFlowerList = new ArrayList<>();
    private VolleyFlowersAdapter mVolleyFlowersAdapter;

    public VolleyFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        callAPI();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_volley, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.volley_list);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.volley_swipe);
        configViews();
    }

    private void configViews() {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());

        mVolleyFlowersAdapter = new VolleyFlowersAdapter(mFlowerList);
        mRecyclerView.setAdapter(mVolleyFlowersAdapter);

        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent),
                getResources().getColor(R.color.colorPrimary),
                getResources().getColor(R.color.colorPrimaryDark));

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                callAPI();
            }
        });
    }

    private void callAPI() {
        String url = Constants.BASE_URL + Constants.FLOWERS;
        LogMe.d(TAG, url);

        JsonArrayRequest jArReq = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray responseArray) {
                        try {
                            for(int i = 0; i < responseArray.length(); i++) {
                                JSONObject object = responseArray.getJSONObject(i);

                                VolleyFlower flower = new VolleyFlower.Builder()
                                        .setCategory(object.getString("category"))
                                        .setPrice(object.getDouble("price"))
                                        .setInstructions(object.getString("instructions"))
                                        .setPhoto(object.getString("photo"))
                                        .setName(object.getString("name"))
                                        .setProductId(object.getInt("productId"))
                                        .build();

                                LogMe.d(TAG, "Id- " + flower.mProductId + "\n Cat- " + flower.mCategory
                                        + "\n Price- " + flower.mPrice + "\n Ins- " + flower.mInstructions
                                        + "\n Photo- " + flower.mPhoto + "\n Name- " + flower.mName);

                                mVolleyFlowersAdapter.addFlower(flower);
                                mSwipeRefreshLayout.setRefreshing(false);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                });

        Volley.newRequestQueue(getActivity()).add(jArReq);

    }

    private  void sampleObjectRequest() {
        String url = "http://httpbin.org/get?site=code&network=tutsplus";

        JsonObjectRequest jsonRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // the response is already constructed as a JSONObject!
                        try {
                            response = response.getJSONObject("args");
                            String site = response.getString("site"),
                                    network = response.getString("network");
                            System.out.println("Site: "+site+"\nNetwork: "+network);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        Volley.newRequestQueue(getActivity()).add(jsonRequest);
    }

    private void callImageAPI() {
        String url = "http://i.imgur.com/Nwk25LA.jpg";

        ImageRequest imgRequest = new ImageRequest(url,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        //img.setImageBitmap(response);
                    }
                }, 0, 0, ImageView.ScaleType.FIT_XY, Bitmap.Config.ARGB_8888, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //img.setBackgroundColor(Color.parseColor("#ff0000"));
                error.printStackTrace();
            }
        });

        Volley.newRequestQueue(getActivity()).add(imgRequest);
    }

    @Override
    public void onStop() {
        super.onStop();
        // Cancelling Request
        Volley.newRequestQueue(getActivity()).cancelAll(new RequestQueue.RequestFilter() {
            @Override
            public boolean apply(Request<?> request) {
                return true;
            }
        });
        // Stopping Request
        Volley.newRequestQueue(getActivity()).stop();
    }

}
