package com.seacam.fotofind.util;

/**
 * Created by jensensc on 8/22/17.
 */

public interface ItemTouchHelperAdapter {
    boolean onItemMove (int fromPosition, int toPosition);
    void onItemDismiss (int position);
}
