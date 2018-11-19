package hcjsm.softech.yari.bedsmanagment.data.beds.datasource.memory;

import java.util.List;

import hcjsm.softech.yari.bedsmanagment.beds.domain.criteria.IBedCriteria;
import hcjsm.softech.yari.bedsmanagment.beds.domain.model.Bed;

public interface IMemoryBedsDataSource {
    List<Bed> find(IBedCriteria criteria);

    void save(Bed bed);

    void deleteAll();

    boolean mapIsNull();
}
