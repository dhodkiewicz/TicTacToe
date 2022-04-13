package com.example.tictactoe

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat

class TicTacToe @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val a= arrayOf(context.theme.obtainStyledAttributes(attrs,
        R.styleable.TicTacToe,0,0))
    private var boardColor: Int = a[R.styleable.TicTacToe_boardColor].getIndex(0)
    private var xColor: Int = a[R.styleable.TicTacToe_XColor].getIndex(0)
    private var yColor: Int = a[R.styleable.TicTacToe_OColor].getIndex(0)
    private var winningLineColor: Int = a[R.styleable.TicTacToe_boardColor].getIndex(0)

}

//@Override
//fun onMeasure(width: Int, height: Int){
//    setMeasuredDimension(width,height)
//}