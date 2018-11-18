package hcjsm.softech.yari.bedsmanagment.beds.domain.criteria;

import java.util.List;

import hcjsm.softech.yari.bedsmanagment.beds.domain.model.Bed;

public interface IBedCriteria {
    List<Bed> match(List<Bed> beds);
}
