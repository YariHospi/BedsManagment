package hcjsm.softech.yari.bedsmanagment.data.beds.datasource.memory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import android.support.test.espresso.core.internal.deps.guava.collect.Lists;

import hcjsm.softech.yari.bedsmanagment.beds.domain.criteria.IBedCriteria;
import hcjsm.softech.yari.bedsmanagment.beds.domain.model.Bed;

public class MemoryBedsDataSource implements IMemoryBedsDataSource {

    private static HashMap<String, Bed> mCachedBeds;

    @Override
    public List<Bed> find(IBedCriteria criteria) {
        ArrayList<Bed> beds = Lists.newArrayList(mCachedBeds.values());
        return criteria.match(beds);
    }

    @Override
    public void save(Bed bed) {
        if(mCachedBeds == null){
            mCachedBeds = new LinkedHashMap<>();
        }
        mCachedBeds.put(bed.getmCode(), bed);
    }

    @Override
    public void deleteAll() {
        if(mCachedBeds == null){
            mCachedBeds = new LinkedHashMap<>();
        }
        mCachedBeds.clear();
    }

    @Override
    public boolean mapIsNull() {
        return mCachedBeds == null;
    }
}
