package guru.voidmain.gaank.fragment;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import guru.voidmain.gaank.MainActivity;
import guru.voidmain.gaank.R;
import guru.voidmain.gaank.adapter.GankAdapter;
import guru.voidmain.gaank.api.GankApiFactory;
import guru.voidmain.gaank.api.service.GankDataService;
import guru.voidmain.gaank.exception.GankTypeNotFoundException;
import guru.voidmain.gaank.model.response.GankCategoryResponse;
import guru.voidmain.gaank.utils.GankType;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by voidmain on 8/30/15.
 */
public class GankFragment extends Fragment {
    /**
     * 代表当前fragment对应的类型，比如福利，iOS等等
     */
    private static final String ARG_SECTION_TYPE = "section_type";

    public static GankFragment newInstance(int sectionType) {
        GankFragment fragment = new GankFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_TYPE, sectionType);
        fragment.setArguments(args);
        return fragment;
    }

    protected SwipeRefreshLayout mSwipeRefreshLayout;
    protected RecyclerView mRecyclerView;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected GankAdapter mAdapter;


    public GankFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_gank, container, false);
        mSwipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh_layout);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.main_recycler_view);

        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new GankAdapter();
        mRecyclerView.setAdapter(mAdapter);

        GankDataService service = GankApiFactory.getInstance().createDataService();
        service.getGankData("all", 20, 1).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GankCategoryResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(GankCategoryResponse gankCategoryResponse) {
                        if (!gankCategoryResponse.error) {
                            mAdapter.appendGankItems(gankCategoryResponse.results);
                        }
                    }
                });
        return rootView;
    }

    @Override
    public void onAttach(Context context) {

        super.onAttach(context);

        if (context instanceof MainActivity) {
            int sectionType = getArguments().getInt(ARG_SECTION_TYPE);
            try {
                ((MainActivity) context).onSectionAttached(GankType.getGankType(getContext(), sectionType));
            } catch (GankTypeNotFoundException e) {
                Toast.makeText(getContext(), R.string.exception_gank_type_not_found, Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
    }
}
