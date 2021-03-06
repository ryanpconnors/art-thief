package com.ryanpconnors.art_thief.rate;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ryanpconnors.art_thief.R;
import com.ryanpconnors.art_thief.artgallery.ArtWork;
import com.ryanpconnors.art_thief.rate.ArtWorkListFragment.OnArtWorkListFragmentInteractionListener;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link ArtWork} and makes a call to the
 * specified {@link OnArtWorkListFragmentInteractionListener}.
 */
public class ArtWorkRecyclerViewAdapter extends RecyclerView.Adapter<ArtWorkListViewHolder> {

    private List<ArtWork> mArtWorks;
    private final OnArtWorkListFragmentInteractionListener mListener;

    private Context mContext;

    public ArtWorkRecyclerViewAdapter(List<ArtWork> items, OnArtWorkListFragmentInteractionListener listener) {
        mArtWorks = items;
        mListener = listener;
    }

    @Override
    public ArtWorkListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_artwork_list_item, parent, false);
        mContext = parent.getContext();
        return new ArtWorkListViewHolder(view);
    }

    /**
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ArtWorkListViewHolder holder, int position) {

        ArtWork artWork = mArtWorks.get(position);
        holder.bindArtWork(artWork, mListener, mContext);
    }

    public void updateArtWorks(List<ArtWork> artWorks) {
        mArtWorks = artWorks;
    }

    @Override
    public int getItemCount() {
        return mArtWorks.size();
    }
}
