package hcjsm.softech.yari.bedsmanagment.beds;

import android.annotation.SuppressLint;
import android.content.Context;

import static android.support.v4.util.Preconditions.checkNotNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import hcjsm.softech.yari.bedsmanagment.R;
import hcjsm.softech.yari.bedsmanagment.beds.domain.model.Bed;



public class BedsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List <Bed>          mBeds;
    private BedItemListener     mItemListener;


    public BedsAdapter(List<Bed> beds, BedItemListener itemListener){
        setList(beds);
        mItemListener = itemListener;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view;

        view = inflater.inflate(R.layout.item_bed,parent,false);
        return new BedsHolder(view, mItemListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof BedsHolder){
            Bed bed = mBeds.get(position);
            BedsHolder bedsHolder = (BedsHolder) viewHolder;
            bedsHolder.code.setText(bed.getmCode());
            bedsHolder.floor.setText(bed.getmFloor());
            bedsHolder.room.setText(bed.getmRoom());
            bedsHolder.status.setText(bed.getmStatus());
        }
    }

    public void replaceData(List<Bed> notes){
        setList(notes);
        notifyDataSetChanged();
    }

    @SuppressLint("RestrictedApi")
    public void setList(List<Bed> notes){
        mBeds = checkNotNull(notes);
    }

    public void addData(List<Bed> beds){
        mBeds.addAll(beds);
    }

    @Override
    public int getItemCount() {
        return getDataItemCount();
    }

    public Bed getItem(int position){
        return mBeds.get(position);
    }

    public int getDataItemCount(){
        return mBeds.size();
    }

    public class BedsHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView     code;
        public TextView     floor;
        public TextView     room;
        public TextView     status;

        private BedItemListener mItemListener;

        public BedsHolder (View itemView, BedItemListener listener){
            super(itemView);
            mItemListener = listener;
            code = (TextView) itemView.findViewById(R.id.bed_code);
            floor = (TextView) itemView.findViewById(R.id.bed_floor);
            room = (TextView) itemView.findViewById(R.id.bed_room);
            status = (TextView) itemView.findViewById(R.id.bed_status);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Bed bed = getItem(position);
            mItemListener.onBedClick(bed);
        }
    }

    public interface BedItemListener{
        void onBedClick(Bed clickedNote);
    }
}
