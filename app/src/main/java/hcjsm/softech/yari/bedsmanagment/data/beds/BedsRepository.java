package hcjsm.softech.yari.bedsmanagment.data.beds;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.List;

import hcjsm.softech.yari.bedsmanagment.beds.domain.model.Bed;
import hcjsm.softech.yari.bedsmanagment.data.beds.datasource.cloud.ICloudBedsDataSource;
import hcjsm.softech.yari.bedsmanagment.data.beds.datasource.memory.IMemoryBedsDataSource;

import static android.support.v4.util.Preconditions.checkNotNull;

public class BedsRepository implements IBedsRepository {

    private final IMemoryBedsDataSource mMemoryBedsDataSource;
    private final ICloudBedsDataSource mCloudBedsDataSource;
    private final Context               mContext;

    private boolean mReload;

    @SuppressLint("RestrictedApi")
    public BedsRepository(IMemoryBedsDataSource memoryDataSource, ICloudBedsDataSource
                          cloudDataSource, Context context){
        mMemoryBedsDataSource = checkNotNull(memoryDataSource);
        mCloudBedsDataSource = checkNotNull(cloudDataSource);
        mContext = checkNotNull(context);
    }

    @Override
    public void getBeds(GetBedsCallback callback) {
        if(!mMemoryBedsDataSource.mapIsNull() && !mReload){
            getBedsFromMemory(callback);
            return;
        }
        if(mReload){
            getBedsFromServer(callback, criteria);
        }else {
            List<Bed> beds = mMemoryBedsDataSource.find();
            if(beds.size()>0){
                callback.onBedsLoaded(beds);
            }else {
                getBedsFromServer(callback);
            }
        }
    }

    private void getBedsFromServer(final GetBedsCallback callback) {
        if(!isOnline()){
            callback.onDataNotAvailable("No hay conexion de red");
            return;
        }
        mCloudBedsDataSource.getBeds(
                new ICloudBedsDataSource.BedServiceCallback() {
                    @Override
                    public void onLoaded(List<Bed> beds) {
                        refreshMemoryDataSource(beds);
                        getBedsFromMemory(callback);
                    }

                    @Override
                    public void onError(String error) {
                        callback.onDataNotAvailable(error);

                    }
                }
        );
    }

    private boolean isOnline(){
        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return info != null && info.isConnected();
    }

    private void refreshMemoryDataSource(List<Bed> beds){
        mMemoryBedsDataSource.deleteAll();
        for (Bed bed: beds){
            mMemoryBedsDataSource.save(bed);
        }
        mReload = false;
    }
    private void getBedsFromMemory(GetBedsCallback callback) {
        callback.onBedsLoaded(mMemoryBedsDataSource.find());
    }



    @Override
    public void refreshBeds() {
        mReload = true;
    }
}
