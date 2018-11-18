package hcjsm.softech.yari.bedsmanagment.beds;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import hcjsm.softech.yari.bedsmanagment.R;
import hcjsm.softech.yari.bedsmanagment.beds.domain.model.Bed;


public class BedsFragment extends Fragment implements BedsMvp.View {

    private RecyclerView    mBedsList;
    private BedsAdapter     mBedsAdapter;

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private View mEmptyView;
    private BedsAdapter.BedItemListener mItemListener = new BedsAdapter.BedItemListener(){

        @Override
        public void onBedClick(Bed clickedNote) {

        }
    };

    public BedsFragment() {

    }

    public static BedsFragment newInstance() {
        return new BedsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBedsAdapter = new BedsAdapter(new ArrayList<Bed>(0),mItemListener);
    }

    @Override
    public android.view.View onCreateView(LayoutInflater inflater, ViewGroup container,
                                          Bundle savedInstanceState) {

        android.view.View root = (android.view.View) inflater.inflate(R.layout.fragment_beds,container,false);

        // UI references
        mBedsList = (RecyclerView) root.findViewById(R.id.beds_list);
        mEmptyView = root.findViewById(R.id.noBeds);
        mSwipeRefreshLayout = (SwipeRefreshLayout) root.findViewById(R.id.refresh_layout);

        //Setup
        setUpBedsList();
        setUpRefreshLayout();

        return root;
    }

    private void setUpRefreshLayout() {
        mSwipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark));

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });
    }

    @Override
    public void showBeds(List<Bed> beds){
        mBedsAdapter.replaceData(beds);

        mBedsList.setVisibility(View.VISIBLE);
        mEmptyView.setVisibility(View.GONE);
    }

    @Override
    public void showLoadingState(final boolean show) {
        if(getView() == null){
            return;
        }
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(show);
            }
        });
    }

    @Override
    public void showEmptyState() {
        mBedsList.setVisibility(View.GONE);
        mEmptyView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showBedsError(String msg) {
        Toast.makeText(getActivity(),msg,Toast.LENGTH_LONG).show();
    }

    @Override
    public void showBedsPage(List<Bed> beds) {
        mBedsAdapter.addData(beds);
    }

    @Override
    public void showLoadMoreIndicator(boolean show) {

    }

    @Override
    public void allowMoreData(boolean show) {

    }

    private void setUpBedsList() {
        mBedsList.setAdapter(mBedsAdapter);
        mBedsList.setHasFixedSize(true);
    }

}
