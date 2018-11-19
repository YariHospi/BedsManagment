package hcjsm.softech.yari.bedsmanagment.data.beds.datasource.cloud;

import java.util.List;

import hcjsm.softech.yari.bedsmanagment.beds.domain.criteria.IBedCriteria;
import hcjsm.softech.yari.bedsmanagment.beds.domain.model.Bed;

public interface ICloudBedsDataSource {
    interface BedServiceCallback {
        void onLoaded(List<Bed> beds);

        void onError(String error);
    }
    void getBeds(BedServiceCallback callback, IBedCriteria criteria);
}

