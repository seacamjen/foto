package com.seacam.fotofind.adapters;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.seacam.fotofind.FirebaseFotoViewHolder;
import com.seacam.fotofind.models.Fotos;
import com.seacam.fotofind.util.ItemTouchHelperAdapter;
import com.seacam.fotofind.util.OnStartDragListener;

/**
 * Created by jensensc on 8/22/17.
 */

public class FirebaseFotoListAdapter extends FirebaseRecyclerAdapter<Fotos, FirebaseFotoViewHolder> implements ItemTouchHelperAdapter {
    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;

    public FirebaseFotoListAdapter(Class<Fotos> modelClass, int modelLayout, Class<FirebaseFotoViewHolder> viewHolderClass, Query ref, OnStartDragListener onStartDragListener, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        mRef = ref.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;
    }

    @Override
    protected void populateViewHolder(final FirebaseFotoViewHolder viewHolder, Fotos model, int position) {
        viewHolder.bindFoto(model);
        viewHolder.mFotosImageView.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mOnStartDragListener.onStartDrag(viewHolder);
                }
                return false;
            }
        });
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPoisition) {
        notifyItemMoved(fromPosition, toPoisition);
        return false;
    }

    @Override
    public void onItemDismiss(int position) {
        getRef(position).removeValue();
    }
}
