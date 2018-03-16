package com.example.trafalgar.martimage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by fdelgado on 15/2/18.
 */

public class NasaRecyclerViewAdapter extends RecyclerView.Adapter<NasaViewHolder> {

    private static final String LOG_TAG = NasaRecyclerViewAdapter.class.getSimpleName();

    private List<Photo> mPhotoList;
    private Context     mContext;

    public NasaRecyclerViewAdapter(Context context, List<Photo> photoList) {
        mPhotoList = photoList;
        mContext = context;
    }

    @Override
    public NasaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.browse, null, false);

        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        view.setLayoutParams(lp);

        NasaViewHolder nasaViewHolder =
                new NasaViewHolder(view);

        return nasaViewHolder;
    }

    @Override
    public int getItemCount() {
        return (mPhotoList != null ? mPhotoList.size() : 0 );
    }

    @Override
    public void onBindViewHolder(NasaViewHolder holder, int position) {
        // Obtenemos el elemento que va a estar en la posición pedida
        Photo photoItem = mPhotoList.get(position);

        Log.d(LOG_TAG, "Processing: " +photoItem.getTitle() + " -> " + Integer.toString(position));

        // Pintamos el thumbnail en la pantalla
        Picasso.with(mContext).load(photoItem.getImage())
                .error(R.drawable.placeholder)      // En caso de error
                .placeholder(R.drawable.placeholder)// Mientras descarga
                .into(holder.getThumbnail());

        holder.mTitle.setText(photoItem.getTitle());
    }

    public void loadNewData(List<Photo> photos){
        mPhotoList = photos;

        notifyDataSetChanged();
    }

    public Photo getPhoto(int position) {
        return (mPhotoList != null ? mPhotoList.get(position) : null );
    }
}





















