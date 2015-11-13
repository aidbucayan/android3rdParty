package bb.aid.com.android3rdparty.retrofit.fragment;


import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import bb.aid.com.android3rdparty.R;
import bb.aid.com.android3rdparty.base.BaseFragment;
import bb.aid.com.android3rdparty.retrofit.fragment.adapter.RetrofitFlowerAdapter;
import bb.aid.com.android3rdparty.retrofit.fragment.controller.Controller;
import bb.aid.com.android3rdparty.retrofit.fragment.model.RetrofitFlower;

public class RetrofitFragment extends BaseFragment implements Controller.FlowerCallbackListener{

    private static final String TAG = RetrofitFragment.class.getSimpleName();
    private View view;

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private List<RetrofitFlower> mFlowerList = new ArrayList<>();
    private RetrofitFlowerAdapter mRetrofitFlowersAdapter;
    private Controller mController;

    public RetrofitFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mController = new Controller(this);
        mController.startFetching();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_retrofit, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.retrofit_list);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.retrofit_swipe);
        configViews();
    }

    private void configViews() {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());

        mRetrofitFlowersAdapter = new RetrofitFlowerAdapter(mFlowerList);
        mRecyclerView.setAdapter(mRetrofitFlowersAdapter);

        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent),
                getResources().getColor(R.color.colorPrimary),
                getResources().getColor(R.color.colorPrimaryDark));

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mController.startFetching();
            }
        });
    }

    @Override
    public void onFetchStart() {

    }

    @Override
    public void onFetchProgress(RetrofitFlower flower) {
        mRetrofitFlowersAdapter.addFlower(flower);
    }

    @Override
    public void onFetchProgress(List<RetrofitFlower> flowerList) {

    }

    @Override
    public void onFetchComplete() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onFetchFailed() {

    }
}
