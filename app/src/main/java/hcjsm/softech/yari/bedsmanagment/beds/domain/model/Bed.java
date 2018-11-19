package hcjsm.softech.yari.bedsmanagment.beds.domain.model;

public class Bed {

    private String mCode;
    private int mFloor;
    private int mRoom;
    private String mDescription;
    private String mPatientName;
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
