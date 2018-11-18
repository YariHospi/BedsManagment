package hcjsm.softech.yari.bedsmanagment.data.beds.datasource.memory;

import java.util.List;

import hcjsm.softech.yari.bedsmanagment.beds.domain.model.Bed;

public interface IMemoryBedsDataSource {
    List<Bed> find(BedCriteria criteria);

    void save(Bed bed);

    void deleteAll();

    boolean mapIsNull();
}
