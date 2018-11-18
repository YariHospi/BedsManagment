package hcjsm.softech.yari.bedsmanagment.beds;

import java.util.List;

import hcjsm.softech.yari.bedsmanagment.beds.domain.model.Bed;

public interface BedsMvp {
    interface View{
        void showBeds(List<Bed> beds);
        void showLoadingState(boolean show);
        void showEmptyState();
        void showBedsError(String msg);
        void showBedsPage (List<Bed> beds);
        void showLoadMoreIndicator(boolean show);
        void allowMoreData(boolean show);


    }

    interface Presenter{
        void loadBeds(boolean reload);
    }
}
