package hcjsm.softech.yari.bedsmanagment.beds;


import android.content.Context;

import static com.google.common.base.Preconditions.checkNotNull;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import hcjsm.softech.yari.bedsmanagment.R;
import hcjsm.softech.yari.bedsmanagment.beds.domain.model.Bed;



public class BedsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements IDataLoading {

    private List <Bed>          mBeds;
    private BedItemListener     mItemListener;

    private boolean mLoading = false;
    private boolean mMoreData = false;

    private final static int TYPE_BED = 1;
    private final static int TYPE_LOADING_MORE = 2;


    public BedsAdapter(List<Bed> beds, BedItemListener itemListener){
        setList(beds);
        mItemListener = itemListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view;

        if(viewType == TYPE_LOADING_MORE) {
            view = inflater.inflate(R.layout.item_loading_footer, parent, false);
            return new LoadingMoreHolder(view);
        }

        view = inflater.inflate(R.layout.item_bed,parent,false);
        return new BedsHolder(view, mItemListener);
    }

    @Override
    public int getItemViewType(int position){
        if(position < getDataItemCount() && getDataItemCount() > 0 ){
            return TYPE_BED;
        }
        return TYPE_LOADING_MORE;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        switch (getItemViewType(position)){
            case TYPE_BED:
                Bed bed = mBeds.get(position);
                BedsHolder bedsHolder = (BedsHolder) viewHolder;
                bedsHolder.code.setText(bed.getmCode());
                bedsHolder.floor.setText(bed.getFormatedfloor());
                bedsHolder.room.setText(bed.getFormatedRoom());
                bedsHolder.status.setText(bed.getmStatus());
                break;
            case TYPE_LOADING_MORE:
                bindLoadingViewHolder((LoadingMoreHolder) viewHolder, position);
                break;
        }
    }

    private void bindLoadingViewHolder(LoadingMoreHolder viewHolder, int position) {
        viewHolder.progress.setVisibility((position>0 && mLoading && mMoreData)
                ? View.VISIBLE: View.INVISIBLE);
    }

    public void dataStartedLoading() {
        if (mLoading) return;
        mLoading = true;
       notifyItemInserted(getLoadingMoreItemPosition());

    }

    public void dataFinishedLoading() {
        if (!mLoading) return;
        mLoading = false;
        notifyItemRemoved(getLoadingMoreItemPosition());

    }

    public void setMoreData(boolean more) {
        mMoreData = more;
    }
    private int getLoadingMoreItemPosition(){
        return mLoading ? getItemCount() - 1 : RecyclerView.NO_POSITION;
    }

    public void replaceData(List<Bed> notes){
        setList(notes);
        notifyDataSetChanged();
    }


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

    @Override
    public boolean isLoadingData() {
        return mLoading;
    }

    @Override
    public boolean isThereMoreData() {
        return mMoreData;
    }

    class BedsHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

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

    private class LoadingMoreHolder extends RecyclerView.ViewHolder {
        public ProgressBar progress;

        public LoadingMoreHolder(View view) {
            super(view);
            progress = (ProgressBar) view.findViewById(R.id.progressBar);
        }
    }

    public interface BedItemListener{
        void onBedClick(Bed clickedNote);
    }
}
