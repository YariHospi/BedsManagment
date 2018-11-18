package hcjsm.softech.yari.bedsmanagment.beds;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import hcjsm.softech.yari.bedsmanagment.R;
import hcjsm.softech.yari.bedsmanagment.beds.domain.model.Bed;


public class BedsFragment extends Fragment {

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_beds,container,false);

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

    private void setUpBedsList() {
        mBedsList.setAdapter(mBedsAdapter);
        mBedsList.setHasFixedSize(true);
    }

}
