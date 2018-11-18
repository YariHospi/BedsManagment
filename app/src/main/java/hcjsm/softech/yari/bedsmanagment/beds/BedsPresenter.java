package hcjsm.softech.yari.bedsmanagment.beds;

import android.annotation.SuppressLint;

import java.util.List;

import hcjsm.softech.yari.bedsmanagment.beds.domain.model.Bed;
import hcjsm.softech.yari.bedsmanagment.data.beds.BedsRepository;

import static android.support.v4.util.Preconditions.checkNotNull;

public class BedsPresenter implements BedsMvp.Presenter {

    private final BedsRepository    mBedsRepository;
    private final BedsMvp.View      mBedsView;

    public static final int PRODUCTS_LIMIT = 20;

    private boolean isFirstLoad = true;
    private int mCurrentPage = 1;

    @SuppressLint("RestrictedApi")
    public BedsPresenter (BedsRepository bedsRepository, BedsMvp.View bedsView){
        mBedsRepository = checkNotNull(bedsRepository);
        mBedsView = checkNotNull(bedsView);
    }

    @Override
    public void loadBeds(boolean reload) {
        final boolean reallyReload = reload || isFirstLoad;

        if(reallyReload){
            mBedsView.showLoadingState(true);
            mBedsRepository.refreshBeds();
            mCurrentPage = 1;
        }else{
            mBedsView.showLoadMoreIndicator(true);
            mCurrentPage++;
        }
        mBedsRepository.getBeds(
                new BedsRepository.GetBedsCallback() {
                    @Override
                    public void onBedsLoaded(List<Bed> beds) {
                        mBedsView.showLoadingState(false);
                        processBeds(beds, reallyReload);

                        isFirstLoad = false;
                    }

                    @Override
                    public void onDataNotAvailable(String error) {
                        mBedsView.showLoadingState(false);
                        mBedsView.showLoadMoreIndicator(false);
                        mBedsView.showBedsError(error);
                    }
                });
    }

    private void processBeds(List<Bed> beds, boolean reload) {
        if(beds.isEmpty()){
            if(reload){
                mBedsView.showEmptyState();
            }else{
                mBedsView.showLoadMoreIndicator(false);
            }
            mBedsView.allowMoreData(false);
        }else {
            if(reload){
                mBedsView.showBeds(beds);
            }else {
                mBedsView.showLoadMoreIndicator(false);
                mBedsView.showBedsPage(beds);
            }
            mBedsView.allowMoreData(true);
        }
    }
}
