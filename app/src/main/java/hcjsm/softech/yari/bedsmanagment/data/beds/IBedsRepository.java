package hcjsm.softech.yari.bedsmanagment.data.beds;

import java.util.List;

import hcjsm.softech.yari.bedsmanagment.beds.domain.model.Bed;

public interface IBedsRepository {
    interface GetBedsCallback{
        void onBedsLoaded(List<Bed> beds);
        void onDataNotAvailable(String error);
    }
    void getBeds(GetBedsCallback callback);
    void refreshBeds();
}
