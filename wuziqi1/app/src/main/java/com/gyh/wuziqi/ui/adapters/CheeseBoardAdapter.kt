package com.gyh.wuziqi.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ResourceCursorAdapter
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.gyh.wuziqi.R

import com.gyh.wuziqi.databinding.CheeseBoardItemViewBinding
import com.gyh.wuziqi.ui.listener.ChessBoardClickListener

class CheeseBoardAdapter(boardItemCount : Int) : Adapter<CheeseBoardAdapter.CheeseBoardItemHolder>() {

    private val ITEM_TYPE_INNER = 0
    private val ITEM_TYPE_BORDER_TOP = 1
    private val ITEM_TYPE_BORDER_BOTTOM = 2
    private val ITEM_TYPE_BORDER_START = 3
    private val ITEM_TYPE_BORDER_END = 4
    private val ITEM_TYPE_CORNER_NW = 11
    private val ITEM_TYPE_CORNER_NE = 12
    private val ITEM_TYPE_CORNER_SW = 13
    private val ITEM_TYPE_CORNER_SE = 14

    private val CHEESE_SIZE = 20

    private val count = boardItemCount

    lateinit var mListener : ChessBoardClickListener

    class CheeseBoardItemHolder(itemView: View) : ViewHolder(itemView) {

    }

    override fun getItemViewType(position: Int): Int {

         position.let {
            if (it == 0) {
                return ITEM_TYPE_CORNER_NW
            } else if (it < CHEESE_SIZE - 1) {
                return ITEM_TYPE_BORDER_TOP
            } else if (it == CHEESE_SIZE - 1) {
                return ITEM_TYPE_CORNER_NE
            } else if (it == CHEESE_SIZE * (CHEESE_SIZE - 1)) {
                return ITEM_TYPE_CORNER_SW
            } else if (it % CHEESE_SIZE == 0) {
                return ITEM_TYPE_BORDER_START
            } else if (it == CHEESE_SIZE * CHEESE_SIZE -1) {
                return ITEM_TYPE_CORNER_SE
            } else if (it % CHEESE_SIZE == CHEESE_SIZE - 1) {
                return ITEM_TYPE_BORDER_END
            } else if (CHEESE_SIZE * (CHEESE_SIZE - 1) < it
                && it < CHEESE_SIZE * CHEESE_SIZE - 1) {
                return ITEM_TYPE_BORDER_BOTTOM
            }else {
                return ITEM_TYPE_INNER
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheeseBoardItemHolder {

        val binding = CheeseBoardItemViewBinding.inflate(LayoutInflater.from(parent.context))
        when (viewType) {
            ITEM_TYPE_BORDER_TOP ->
                binding.boardItem.setImageResource(R.drawable.cheese_board_item_top_border)
            ITEM_TYPE_BORDER_END ->
                binding.boardItem.setImageResource(R.drawable.cheese_board_item_end_border)
            ITEM_TYPE_BORDER_BOTTOM ->
                binding.boardItem.setImageResource(R.drawable.cheese_board_item_bottom_border)
            ITEM_TYPE_BORDER_START ->
                binding.boardItem.setImageResource(R.drawable.cheese_board_item_start_border)
            ITEM_TYPE_CORNER_NE ->
                binding.boardItem.setImageResource(R.drawable.cheese_board_item_ne_corner)
            ITEM_TYPE_CORNER_SW ->
                binding.boardItem.setImageResource(R.drawable.cheese_board_item_sw_corner)
            ITEM_TYPE_CORNER_SE ->
                binding.boardItem.setImageResource(R.drawable.cheese_board_item_se_corner)
            ITEM_TYPE_CORNER_NW ->
                binding.boardItem.setImageResource(R.drawable.cheese_board_item_nw_corner)
            ITEM_TYPE_INNER ->
                binding.boardItem.setImageResource(R.drawable.cheese_board_item_inner)

        }

        binding.root.setOnClickListener {
            mListener.onChessBoardItemClick(it)
        }

        return CheeseBoardItemHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return count
    }

    override fun onBindViewHolder(holder: CheeseBoardItemHolder, position: Int) {

    }

    fun setOnItemClickListener(listener: ChessBoardClickListener) {
        mListener = listener
    }
}