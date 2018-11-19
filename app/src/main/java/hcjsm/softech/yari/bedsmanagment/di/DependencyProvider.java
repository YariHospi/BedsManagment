package hcjsm.softech.yari.bedsmanagment.di;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;

import hcjsm.softech.yari.bedsmanagment.data.beds.BedsRepository;
import hcjsm.softech.yari.bedsmanagment.data.beds.datasource.cloud.CloudBedsDataSource;
import hcjsm.softech.yari.bedsmanagment.data.beds.datasource.memory.MemoryBedsDataSource;

import static android.support.v4.util.Preconditions.checkNotNull;

public final class DependencyProvider {
    
    private static Context mContext;
    private static MemoryBedsDataSource memorySource = null;
    private static CloudBedsDataSource cloudSource = null;
    private static BedsRepository   mBedsRepository = null;
    
    private DependencyProvider(){
        
    }
    
    @SuppressLint("RestrictedApi")
    public static BedsRepository provideBedsRepository(@NonNull Context context){
        mContext = checkNotNull(context);
        if(mBedsRepository == null){
            mBedsRepository = new BedsRepository(getMemorySource(),getCloudSource(),context);
        }
        return mBedsRepository;
    }

    private static CloudBedsDataSource getCloudSource() {
        if(cloudSource == null){
            cloudSource = new CloudBedsDataSource();
        }
        return cloudSource;
    }

    private static MemoryBedsDataSource getMemorySource() {
        if(memorySource == null){
            memorySource = new MemoryBedsDataSource();
        }
        return memorySource;
    }
}
