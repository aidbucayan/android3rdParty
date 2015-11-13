package bb.aid.com.android3rdparty.volley.fragment.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import bb.aid.com.android3rdparty.R;
import bb.aid.com.android3rdparty.utils.Constants;
import bb.aid.com.android3rdparty.volley.fragment.model.VolleyFlower;

public class VolleyFlowersAdapter extends RecyclerView.Adapter<VolleyFlowersAdapter.Holder> {

    public static String TAG = VolleyFlowersAdapter.class.getSimpleName();

    private List<VolleyFlower> mFlowers;

    public VolleyFlowersAdapter(List<VolleyFlower> flowers) {
        mFlowers = flowers;
    }

    public void addFlower(VolleyFlower flower) {
        mFlowers.add(flower);
        notifyDataSetChanged();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);

        return new Holder(row);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

        VolleyFlower currentFlower = mFlowers.get(position);
        holder.mName.setText(currentFlower.mName);
        holder.mCategory.setText(currentFlower.mCategory);
        holder.mPrice.setText(Double.toString(currentFlower.mPrice));
        holder.mInstructions.setText(currentFlower.mInstructions);

        Glide.with(holder.itemView.getContext())
                .load(Constants.PHOTO_URL + currentFlower.mPhoto)
                .centerCrop()
                .crossFade()
                .into(holder.mImage);
    }

    @Override
    public int getItemCount() {
        return mFlowers.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        public TextView mName, mCategory, mPrice, mInstructions;
        public ImageView mImage;

        public Holder(View itemView) {
            super(itemView);
            mImage = (ImageView) itemView.findViewById(R.id.flowerImage);
            mName = (TextView) itemView.findViewById(R.id.flowerName);
            mCategory = (TextView) itemView.findViewById(R.id.flowerCategory);
            mPrice = (TextView) itemView.findViewById(R.id.flowerPrice);
            mInstructions = (TextView) itemView.findViewById(R.id.flowerInstruction);
        }
    }
}
