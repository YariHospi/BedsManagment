package hcjsm.softech.yari.bedsmanagment.beds.domain.criteria;

import java.util.ArrayList;
import java.util.List;

import hcjsm.softech.yari.bedsmanagment.beds.domain.model.Bed;

public class PagingBedCriteria implements IBedCriteria {
    private final int mPage;
    private final int mLimit;

    public PagingBedCriteria(int page, int limit){
        mPage = page;
        mLimit = limit;
    }

    @Override
    public List<Bed> match(List<Bed> beds) {
        List<Bed> criteriaBeds = new ArrayList<>();

        if(mLimit <=0 || mPage <= 0){
            return criteriaBeds;
        }
        int size = beds.size();
        int numPages = size / mLimit;
        int a,b;

        if(mPage > numPages){
            return criteriaBeds;
        }
        a = (mPage - 1) * mLimit;

        if(a == size) {
            return criteriaBeds;
        }

        b = a + mLimit;

        criteriaBeds = beds.subList(a,b);
        return  criteriaBeds;
    }
}
