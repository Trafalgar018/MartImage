package com.example.trafalgar.martimage;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by fdelgado on 15/2/18.
 */

public class NasaViewHolder extends RecyclerView.ViewHolder {

    private ImageView mThumbnail;
    public TextView  mTitle;

    public NasaViewHolder(View itemView) {
        super(itemView);
        mThumbnail = itemView.findViewById(R.id.imageViewThumbnail);
        mTitle = itemView.findViewById(R.id.textViewTitle);
    }

    public ImageView getThumbnail() {
        return mThumbnail;
    }
}
