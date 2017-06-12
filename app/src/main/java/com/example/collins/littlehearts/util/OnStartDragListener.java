package com.example.collins.littlehearts.util;

import android.support.v7.widget.RecyclerView;

/**
 * Created by collins on 6/12/17.
 */

public interface OnStartDragListener {
//    onStartDrag() will be called when the user begins a 'drag-and-drop' interaction with the touchscreen. viewHolder represents the RecyclerView holder corresponding to the object being dragged.
    void onStartDrag(RecyclerView.ViewHolder viewHolder);
}
