package com.kzhou.spotifystreamer.ui.element;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kzhou.spotifystreamer.R;
import com.kzhou.spotifystreamer.model.view.ListItem;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * User: Kevin
 * Date: 14/06/15
 * Time: 10:18 PM
 */
public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ArtistViewHolder> {
    private final LayoutInflater layoutInflater;
    private final Context context;
    private List<ListItem> listItems;
    private ListItemClickedListener itemClickedListener;
    private boolean isShowSubName;

    public ItemListAdapter(Context context,
                           List<ListItem> listItems,
                           ListItemClickedListener itemClickedListener,
                           boolean isShowSubName) {
        this.context = context;
        this.listItems = listItems;
        layoutInflater = LayoutInflater.from(context);
        this.itemClickedListener = itemClickedListener;
        this.isShowSubName = isShowSubName;
    }

    public ListItem getItem(int position) {
        return listItems.get(position);
    }

    public void setArtistItems(List<ListItem> listItems) {
        this.listItems = listItems;
    }

    public void refreshListByData(List<ListItem> listItems) {
        setArtistItems(listItems);
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    @Override
    public ArtistViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ArtistViewHolder(layoutInflater.inflate(R.layout.list_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(ArtistViewHolder viewHolder, final int position) {
        String imageUrl = getItem(position).getImageUrl();
        if (imageUrl == null) {
            viewHolder.image.setImageResource(R.mipmap.people_image_defaultprofile);
        } else {
            Picasso.with(context).load(imageUrl).into(viewHolder.image);
        }

        viewHolder.name.setText(getItem(position).getName());
        String subName = getItem(position).getSubName();
        if (isShowSubName && !TextUtils.isEmpty(subName)) {
            viewHolder.subName.setVisibility(View.VISIBLE);
            viewHolder.subName.setText(subName);
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickedListener.onItemClicked(position);
            }
        });
    }

    public static class ArtistViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;
        TextView subName;

        ArtistViewHolder(View view) {
            super(view);
            image = (ImageView) view.findViewById(R.id.image);
            name = (TextView) view.findViewById(R.id.name);
            subName = (TextView) view.findViewById(R.id.subName);
        }
    }
}
