package hcjsm.softech.yari.bedsmanagment.data.beds.datasource.cloud;

import java.util.HashMap;
import java.util.LinkedHashMap;
import android.os.Handler;
import android.view.View;


import hcjsm.softech.yari.bedsmanagment.beds.domain.model.Bed;

public class CloudBedsDataSource implements ICloudBedsDataSource {

    private static HashMap<String, Bed> API_DATA;
    private static final long LATENCY = 2000;

    static {
        API_DATA = new LinkedHashMap<>();
        for(int i = 0; i<100; i++){
            addBed("Cama 0905",9,5);
        }
    }

    private static void addBed(String code, int floor, int room){
        Bed newBed = new Bed(code,floor,room);
        API_DATA.put(newBed.getmCode(),newBed);
    }

    @Override
    public void getBeds(final BedServiceCallback callback) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.onLoaded(Lists.newArrayList(API_DATA.values()));
            }
        },LATENCY);

    }
}
