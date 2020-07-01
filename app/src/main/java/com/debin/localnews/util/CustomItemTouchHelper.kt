package com.debin.localnews.util

import android.util.Log
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.debin.localnews.ui.adapter.NewsAdapter

class CustomItemTouchHelper(val newsAdapter: NewsAdapter) :
    ItemTouchHelper.SimpleCallback(0, (ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT)) {
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        Log.d("TAG_X", "onMove...")
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        Log.e("TAG_X", "Direction :: "+direction)
        if (direction == (ItemTouchHelper.LEFT) || direction == (ItemTouchHelper.RIGHT)) {
            val itemPosition = viewHolder.adapterPosition

            newsAdapter.items.removeAt(itemPosition)
            newsAdapter.notifyItemRemoved(itemPosition)
        }
    }
}