package hcjsm.softech.yari.bedsmanagment.data.beds.datasource.cloud;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import android.os.Handler;
import com.google.api.client.util.Lists;
import com.google.gson.Gson;

import android.view.View;


import hcjsm.softech.yari.bedsmanagment.beds.domain.criteria.IBedCriteria;
import hcjsm.softech.yari.bedsmanagment.beds.domain.model.Bed;
import hcjsm.softech.yari.bedsmanagment.data.api.ErrorResponse;
import hcjsm.softech.yari.bedsmanagment.data.api.IRestService;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CloudBedsDataSource implements ICloudBedsDataSource {

    //public static final String BASE_URL = "https://google.com";
    public static final String BASE_URL = "http://yariromero.tech/AppBedsAPI/v1/";
    private final Retrofit mRetrofit;
    private final IRestService mRestService;

    public CloudBedsDataSource(){
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mRestService = mRetrofit.create(IRestService.class);
    }

    @Override
    public void getBeds(final BedServiceCallback callback, IBedCriteria criteria) {
        Call<List<Bed>> call = mRestService.getBeds();

        call.enqueue(new Callback<List<Bed>>() {
            @Override
            public void onResponse(Call<List<Bed>> call, Response<List<Bed>> response) {
                // procesa posibles casos
                processGetBedsResponse(response,callback);
            }

            @Override
            public void onFailure(Call<List<Bed>> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }

    private void processGetBedsResponse(Response<List<Bed>> response,
                                        BedServiceCallback callback){
        String error = "Ha ocurrido un error";
        ResponseBody errorBody = response.errorBody();

        // was a error?
        if (errorBody != null){
            //api error?
            if(errorBody.contentType().subtype().equals("json")){
                try{
                    //parse object
                    ErrorResponse er = new Gson()
                            .fromJson(errorBody.string(), ErrorResponse.class);
                    error = er.getmMessage();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            callback.onError(error);
        }

        // all ok?
        if(response.isSuccessful()){
            callback.onLoaded(response.body());
        }
    }
}
