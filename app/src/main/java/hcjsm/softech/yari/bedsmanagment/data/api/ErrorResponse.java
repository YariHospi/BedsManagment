package hcjsm.softech.yari.bedsmanagment.data.api;

import com.google.gson.annotations.SerializedName;

public class ErrorResponse {

    @SerializedName("message")
    String mMessage;

    public String getmMessage(){
        return mMessage;
    }
}
