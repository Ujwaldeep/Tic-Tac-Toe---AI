package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.GridLayout
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.example.tictactoe.Board.Companion.COMPUTER
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private val boardCells = Array(3){ arrayOfNulls<ImageView>(3)}
    var board = Board()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadBoard()

        restart.setOnClickListener{
            board= Board()
            textView_result.text =""
            restart.isEnabled = false
            restart.setBackgroundColor(ContextCompat.getColor(this@MainActivity,R.color.background))

            mapBoardToUi()
        }
    }
    private fun mapBoardToUi(){
        for(i in board.board.indices){
            for(j in board.board.indices){
                when(board.board[i][j]){
                    Board.PLAYER->{
                        boardCells[i][j]?.setImageResource(R.drawable.circle)
                        boardCells[i][j]?.isEnabled=false
                    }
                    Board.COMPUTER->{
                        boardCells[i][j]?.setImageResource(R.drawable.cross)
                        boardCells[i][j]?.isEnabled=false
                    }
                    else->{
                        boardCells[i][j]?.setImageResource(0)
                        boardCells[i][j]?.isEnabled=true
                    }
                }
            }
        }
    }

    private fun loadBoard(){
        for(i in boardCells.indices){
            for(j in boardCells.indices){
                boardCells[i][j] = ImageView(this)
                boardCells[i][j]?.layoutParams = GridLayout.LayoutParams().apply {
                    rowSpec = GridLayout.spec(i)
                    columnSpec = GridLayout.spec(j)
                    width=250
                    height=230
                    bottomMargin=5
                    topMargin=5
                    leftMargin=5
                    rightMargin=5
                }

                boardCells[i][j]?.setBackgroundColor(ContextCompat.getColor(this,R.color.colorPrimary))
                boardCells[i][j]?.setOnClickListener(CellClickListener(i,j))
                layout_board.addView(boardCells[i][j])
            }

        }
        restart.isEnabled = false
    }

    inner class CellClickListener(private val i:Int,private  val j:Int): View.OnClickListener{
        override fun onClick(v: View?) {
            if(!board.isGameOver){
                val cell = Cell(i,j)
                board.placeMove(cell,Board.PLAYER)
                board.minimax(0,Board.COMPUTER)
                board.computersMove?.let {
                    board.placeMove(it,Board.COMPUTER)
                }


                mapBoardToUi()
            }

            when{
                board.hasComputerWon()-> {
                    textView_result.text = "Computer Won!"
                    restart.isEnabled=true
                    restart.setBackgroundColor(ContextCompat.getColor(this@MainActivity,R.color.colorPrimary))

                }
                board.hasPlayerWon()-> {
                    textView_result.text = "Player Won!"
                    restart.isEnabled=true
                    restart.setBackgroundColor(ContextCompat.getColor(this@MainActivity,R.color.colorPrimary))

                }
                board.isGameOver-> {
                    textView_result.text = "Draw!"
                    restart.isEnabled=true
                    restart.setBackgroundColor(ContextCompat.getColor(this@MainActivity,R.color.colorPrimary))
                }

            }

        }

    }
}