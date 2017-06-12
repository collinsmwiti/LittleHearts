package com.example.collins.littlehearts.util;

/**
 * Created by collins on 6/12/17.
 */

public interface ItemTouchHelperAdapter {
// onItemMove() will be called each time the user moves an item by dragging it across the touch screen. The argument fromPosition represents the location the item originally resided at. toPosition represents the location the user moved the item to.
    boolean onItemMove(int fromPosition, int toPosition);
// onItemDismiss() is called when an item has been dismissed with a swipe motion. The parameter position represents the location of the dismissed item.
    void onItemDismiss(int position);
}
