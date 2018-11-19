package hcjsm.softech.yari.bedsmanagment.beds;



import java.util.List;

import hcjsm.softech.yari.bedsmanagment.beds.domain.criteria.IBedCriteria;
import hcjsm.softech.yari.bedsmanagment.beds.domain.criteria.PagingBedCriteria;
import hcjsm.softech.yari.bedsmanagment.beds.domain.model.Bed;
import hcjsm.softech.yari.bedsmanagment.data.beds.BedsRepository;

import static com.google.common.base.Preconditions.checkNotNull;

public class BedsPresenter implements BedsMvp.Presenter {

    private final BedsRepository    mBedsRepository;
    private final BedsMvp.View      mBedsView;

    public static final int PRODUCTS_LIMIT = 5;

    private boolean isFirstLoad = true;
    private int mCurrentPage = 1;


    public BedsPresenter (BedsRepository bedsRepository, BedsMvp.View bedsView){
        mBedsRepository = checkNotNull(bedsRepository);
        mBedsView = checkNotNull(bedsView);
    }

    @Override
    public void loadBeds(final boolean reload) {
        final boolean reallyReload = reload || isFirstLoad;

        if(reallyReload){
            mBedsView.showLoadingState(true);
            mBedsRepository.refreshBeds();
            mCurrentPage = 1;
        }else{
            mBedsView.showLoadMoreIndicator(true);
            mCurrentPage++;
        }

        IBedCriteria criteria = new PagingBedCriteria(mCurrentPage, PRODUCTS_LIMIT );

        mBedsRepository.getBeds(
                new BedsRepository.GetBedsCallback() {
                    @Override
                    public void onBedsLoaded(List<Bed> beds) {
                        mBedsView.showLoadingState(false);
                        processBeds(beds, reallyReload);
                        isFirstLoad=false;
                    }

                    @Override
                    public void onDataNotAvailable(String error) {
                        mBedsView.showLoadingState(false);
                        mBedsView.showLoadMoreIndicator(false);
                        mBedsView.showBedsError(error);
                    }
                },criteria);
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
