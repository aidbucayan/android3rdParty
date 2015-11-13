package bb.aid.com.android3rdparty.retrofit.fragment.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import bb.aid.com.android3rdparty.R;
import bb.aid.com.android3rdparty.retrofit.fragment.model.RetrofitFlower;
import bb.aid.com.android3rdparty.utils.Constants;

public class RetrofitFlowerAdapter extends RecyclerView.Adapter<RetrofitFlowerAdapter.Holder> {

    public static String TAG = RetrofitFlowerAdapter.class.getSimpleName();

    private List<RetrofitFlower> mFlowers;

    public RetrofitFlowerAdapter(List<RetrofitFlower> flowers) {
        mFlowers = flowers;
    }

    public void addFlower(RetrofitFlower flower) {
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

        RetrofitFlower currentFlower = mFlowers.get(position);
        holder.mName.setText(currentFlower.mName);
        holder.mCategory.setText(currentFlower.mCategory);
        holder.mPrice.setText(Double.toString(currentFlower.mPrice));
        holder.mInstructions.setText(currentFlower.mInstructions);

        Picasso.with(holder.itemView.getContext()).
                load(Constants.PHOTO_URL + currentFlower.mPhoto).fit().
                into(holder.mImage);
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
