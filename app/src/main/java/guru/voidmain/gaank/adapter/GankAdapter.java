package guru.voidmain.gaank.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import guru.voidmain.gaank.R;
import guru.voidmain.gaank.model.GankItem;
import guru.voidmain.gaank.utils.GankType;

/**
 * 支持各类干货的adapter
 * Created by voidmain on 8/30/15.
 */
public class GankAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    protected List<GankItem> mDataset;

    // 包含一张图片的ViewHolder
    public static class ImageViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mDescTextView;
        public ImageViewHolder(View rootView) {
            super(rootView);
            mImageView = (ImageView)rootView.findViewById(R.id.hero_image);
            mDescTextView = (TextView) rootView.findViewById(R.id.item_desc);
        }
    }

    // 文字排版的ViewHolder
    public static class TextViewHolder extends RecyclerView.ViewHolder {
        public TextView mDescTextView;
        public TextViewHolder(View rootView) {
            super(rootView);

            mDescTextView = (TextView)rootView.findViewById(R.id.item_desc);
        }
    }

    public GankAdapter() {
        mDataset = new ArrayList<>();
    }

    public void appendGankItems(List<GankItem> items) {
        mDataset.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        GankItem item = mDataset.get(position);
        return GankType.getGankTypeWithString(item.type);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        switch (viewType) {
            case 1: {
                View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_item_image, viewGroup, false);
                return new ImageViewHolder(v);
            }
            default: {
                View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_item_text, viewGroup, false);
                return new TextViewHolder(v);
            }
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        GankItem item = mDataset.get(position);
        if (viewHolder instanceof ImageViewHolder) {
            ImageViewHolder holder = (ImageViewHolder)viewHolder;
            ImageLoader imageLoader = ImageLoader.getInstance();
            imageLoader.displayImage(item.url, holder.mImageView);
            holder.mDescTextView.setText(item.desc);
        } else if (viewHolder instanceof TextViewHolder) {
            TextViewHolder holder = (TextViewHolder)viewHolder;
            holder.mDescTextView.setText(item.desc);
        }
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
