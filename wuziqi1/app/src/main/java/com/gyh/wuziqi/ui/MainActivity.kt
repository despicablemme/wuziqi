package com.gyh.wuziqi.ui

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gyh.wuziqi.R

import com.gyh.wuziqi.databinding.ActivityMainBinding
import com.gyh.wuziqi.databinding.CheeseBoardItemViewBinding
import com.gyh.wuziqi.ui.adapters.CheeseBoardAdapter
import com.gyh.wuziqi.ui.listener.ChessBoardClickListener
import com.gyh.wuziqi.ui.viewmodel.ChessBoardViewModel

class MainActivity : AppCompatActivity() ,ChessBoardClickListener {

    lateinit var binding : ActivityMainBinding
    lateinit var chessBoardViewModel: ChessBoardViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        chessBoardViewModel = ViewModelProvider(this)[ChessBoardViewModel::class.java]


        binding = ActivityMainBinding.inflate(layoutInflater)
        // todo: init view
        val adapter = CheeseBoardAdapter(400)
        adapter.setOnItemClickListener(this)
        binding.cheeseBoard.adapter = adapter

        // todo end: init view

        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onChessBoardItemClick(view: View) {
        view.isClickable = false
        val binding = CheeseBoardItemViewBinding.bind(view)
        if (chessBoardViewModel.getTurn()) {
            binding.blackPiece.visibility = View.VISIBLE
            binding.whitePiece.visibility = View.GONE  // actual no need

        } else {
            binding.blackPiece.visibility = View.GONE  // actual no need
            binding.whitePiece.visibility = View.VISIBLE
        }

        chessBoardViewModel.turnAround()
    }

    private val observer = Observer<Boolean> {

    }

}