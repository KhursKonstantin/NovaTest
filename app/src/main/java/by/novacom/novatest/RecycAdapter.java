package by.novacom.novatest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by al-ev on 19.01.2017.
 */
public class RecycAdapter extends RecyclerView.Adapter<RecycAdapter.Holder> {

    private List<String> mList;
    private OnLayoutClickListener mListener;
    private Context mContext;

    public RecycAdapter(Context context, List<String> list, OnLayoutClickListener listener)
    {
        mList = list;
        mListener = listener;
        mContext = context;


    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.id_card, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {

        final String text = mList.get(position);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) mListener.onClick(position, text);
            }
        });
        Picasso.with(mContext)
                .load(text)
                .placeholder(R.drawable.ic_place)
                .into(holder.mImageView);

    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }


    protected static class Holder extends RecyclerView.ViewHolder
    {

        private ImageView mImageView;

        public Holder(View itemView)
        {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.img_android);
        }
    }

    public interface OnLayoutClickListener
    {
        void onClick(int position, String url);
    }

}
