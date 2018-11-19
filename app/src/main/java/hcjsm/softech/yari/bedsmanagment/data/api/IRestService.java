package hcjsm.softech.yari.bedsmanagment.data.api;

import java.util.List;

import hcjsm.softech.yari.bedsmanagment.beds.domain.model.Bed;
import retrofit2.Call;
import retrofit2.http.GET;

public interface IRestService {
    @GET("beds")
    Call<List<Bed>> getBeds();
}
