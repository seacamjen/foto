package com.seacam.fotofind.adapters;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.seacam.fotofind.FirebaseFotoViewHolder;
import com.seacam.fotofind.models.Fotos;
import com.seacam.fotofind.util.ItemTouchHelperAdapter;
import com.seacam.fotofind.util.OnStartDragListener;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by jensensc on 8/22/17.
 */

public class FirebaseFotoListAdapter extends FirebaseRecyclerAdapter<Fotos, FirebaseFotoViewHolder> implements ItemTouchHelperAdapter {
    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;
    private ChildEventListener mChildEventListener;
    private ArrayList<Fotos> mFotos = new ArrayList<>();

    public FirebaseFotoListAdapter(Class<Fotos> modelClass, int modelLayout, Class<FirebaseFotoViewHolder> viewHolderClass, Query ref, OnStartDragListener onStartDragListener, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        mRef = ref.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;

//        mChildEventListener = mRef.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                mFotos.add(dataSnapshot.getValue(Fotos.class));
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
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
//        Collections.swap(mFotos, fromPosition, toPoisition);
        notifyItemMoved(fromPosition, toPoisition);
        return false;
    }

    @Override
    public void onItemDismiss(int position) {
//        mFotos.remove(position);
        getRef(position).removeValue();
    }

//    private void setIndexInFirebase() {
//        for (Fotos foto : mFotos) {
//            int index = mFotos.indexOf(foto);
//            DatabaseReference ref = getRef(index);
//            foto.setIndex(Integer.toString(index));
//            ref.setValue(foto);
//        }
//    }
//
//    @Override
//    public void cleanup() {
//        super.cleanup();
//        setIndexInFirebase();
//        mRef.removeEventListener(mChildEventListener);
//    }
}
