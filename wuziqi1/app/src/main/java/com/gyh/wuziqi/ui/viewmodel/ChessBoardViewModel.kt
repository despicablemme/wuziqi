package com.gyh.wuziqi.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ChessBoardViewModel : ViewModel() {

    /*
    flag that controls black first or white
    true: means black first, transparently white first
    used when user want to set this manually  or
    decided by the result of the last game
     */
    val roundFlag = MutableLiveData<Boolean> ()

    /*
    flag that check and save the turn around
    do not need concern whether true or false
    verse every time turn around
     */
    private var turnAroundFlag = false

    /*
    matrix that record the chess board
    where white placed and black placed
     */
    private var chessBoard = ArrayList<ArrayList<Int>> (400)

    fun turnAround() {
        turnAroundFlag = !turnAroundFlag
    }

    fun getTurn() : Boolean {
        return turnAroundFlag
    }

}