package hcjsm.softech.yari.bedsmanagment.beds.domain.model;

import com.google.gson.annotations.SerializedName;

public class Bed {

    @SerializedName("code")
    private String mCode;

    @SerializedName("floor")
    private int mFloor;

    @SerializedName("room")
    private int mRoom;

    @SerializedName("description")
    private String mDescription;

    @SerializedName("patient")
    private String mPatientName;

    @SerializedName("status")
    private String mStatus;

    public Bed (String code, int floor, int room ){
        mCode = code;
        mFloor = floor;
        mRoom = room;
        mStatus = "Vacia";
    }


    public String getmCode() {
        return mCode;
    }

    public void setmCode(String mCode) {
        this.mCode = mCode;
    }

    public int getmFloor() {
        return mFloor;
    }

    public void setmFloor(int mFloor) {
        this.mFloor = mFloor;
    }

    public int getmRoom() {
        return mRoom;
    }

    public void setmRoom(int mRoom) {
        this.mRoom = mRoom;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmPatientName() {
        return mPatientName;
    }

    public void setmPatientName(String mPatientName) {
        this.mPatientName = mPatientName;
    }

    public String getmStatus() {
        return mStatus;
    }

    public void setmStatus(String mStatus) {
        this.mStatus = mStatus;
    }
    public String getFormatedfloor() {
        return String.format("Piso: ", mFloor);
    }
    public String getFormatedRoom(){
        return String.format("Sala: ",mRoom);
    }
}
