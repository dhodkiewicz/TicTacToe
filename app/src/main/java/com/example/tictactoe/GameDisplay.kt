package com.example.tictactoe

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

 class GameDisplay : AppCompatActivity() {

     ///Sorry for wonky formatting- I'm going to try and find out why that's happening
    private lateinit var buttonHome: Button //field for home button
     private lateinit var buttonPlayAgain: Button //field to hold the play again button
    private lateinit var tvPlayer: TextView // holds our Textview for turn's and winner

    //field for initial players turn
    private var playerTurn: Boolean = false
     //field for if the game is over
     private var isGameOver: Boolean = false
    //field for counting number of moves
    private var moves : Int = 0

    //fields for gameplay buttons
    private lateinit var btn1: Button
    private lateinit var btn2: Button
    private lateinit var btn3: Button
    private lateinit var btn4: Button
    private lateinit var btn5: Button
    private lateinit var btn6: Button
    private lateinit var btn7: Button
    private lateinit var btn8: Button
    private lateinit var btn9: Button

    // fields to hold player names
     var playerOne: String? = null
     var playerTwo: String? = null

     //map for our values to be stored from the game
     val mapPositions = mutableMapOf(1 to 0, 2 to 0 , 3 to 0, 4 to 0, 5 to 0, 6 to 0 , 7 to 0, 8 to 0, 9 to 0)

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_display)

            //setting the button fields and bundle extras from previous activity
            val bundle = intent.extras
            buttonPlayAgain = findViewById(R.id.button)
            buttonHome = findViewById(R.id.button2)

            //set variables for GAME Buttons
            btn1 = findViewById(R.id.btn1)
            btn2 = findViewById(R.id.btn2)
            btn3 = findViewById(R.id.btn3)
            btn4 = findViewById(R.id.btn4)
            btn5 = findViewById(R.id.btn5)
            btn6 = findViewById(R.id.btn6)
            btn7 = findViewById(R.id.btn7)
            btn8 = findViewById(R.id.btn8)
            btn9 = findViewById(R.id.btn9)

            //this is confusing to me, I can't get rid of the name part, this was a head scratcher for me
          val name: String = bundle!!.getString("PLAYER_ONE", "Default").toString()
            playerOne = bundle.get("PLAYER_ONE").toString() // setting player names
            playerTwo = bundle.get("PLAYER_TWO").toString()

            tvPlayer = findViewById(R.id.txtPlayer) // getting the Textview by id
            tvPlayer.text = playerOne.toString() // setting the TextViews text by first player's name

            // setting the home buttons onClick listener and activity
        buttonHome.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }
            // restart this activity on play again button click
        buttonPlayAgain.setOnClickListener{
            startActivity(intent)
        }

    // setting all of our game button event listeners
            btn1.setOnClickListener{
                btn1.tag = 1
            gameClick(this.btn1)
            }

            btn2.setOnClickListener{
                btn2.tag = 2
                gameClick(this.btn2)
            }
            btn3.setOnClickListener{
                btn3.tag = 3
                gameClick(this.btn3)
            }
            btn4.setOnClickListener{
                btn4.tag = 4
                gameClick(this.btn4)
            }
            btn5.setOnClickListener{
                btn5.tag = 5
                gameClick(this.btn5)
            }
            btn6.setOnClickListener{
                btn6.tag = 6
                gameClick(this.btn6)
            }
            btn7.setOnClickListener{
                btn7.tag = 7
                gameClick(this.btn7)
            }
            btn8.setOnClickListener{
                btn8.tag = 8
                gameClick(this.btn8)
            }
            btn9.setOnClickListener{
                btn9.tag = 9
                gameClick(this.btn9)
            }
    }

    //TODO for resetting the game
//    public fun playAgainButtonClick(View view){
//
//    }

    //nav back to the home screen
    public fun homeButtonClick(){
        startActivity(Intent(this, GameDisplay::class.java))
    }

    //method for game click
    public fun gameClick(btn: Button){
        if(!playerTurn){
            btn.text= "X"
        }
        else{
            btn.text= "O"
        }
        setPosition(btn)
        changeTurn()
        btn.isEnabled = false
    }

     //method for changing turns
     public fun changeTurn(){
         var message = "'s Turn!"
         if(isGameOver){
             return
         }
         if(!this.playerTurn) {
             playerTurn = true
             var name: String = playerTwo.toString()
             var turnAlert = name + message
             tvPlayer.text = turnAlert
             return
         }
        else
        {
            this.playerTurn  = false
            var name: String = playerOne.toString()
            var turnAlert = name + message
            tvPlayer.text = turnAlert
        }
     }
    //method for setting the buttons positions to the map - also increments moves and checks for a winner
     public fun setPosition(btn: Button){
         val tagToString = btn.tag.toString()
         val parsedInt = tagToString.toInt()
         if(!this.playerTurn){
             mapPositions.put(parsedInt,1)
         }
         else{
             mapPositions.put(parsedInt,10)
         }
         moves++
         checkForWinner()
     }

     //method to check for our winner, and if there is call the gameOver method
     public fun checkForWinner(){

         val firstHorizontalRow = mapPositions.filterKeys{it == 1 || it == 2 || it == 3}
         val secondHorizontalRow = mapPositions.filterKeys{it == 4 || it == 5 || it == 6}
         val thirdHorizontalRow = mapPositions.filterKeys{it == 7 || it == 8 || it == 9}
         val firstVerticalRow = mapPositions.filterKeys{it == 1 || it == 4 || it == 7}
         val secondVerticalRow = mapPositions.filterKeys{it == 2 || it == 5 || it == 8}
         val thirdVerticalRow = mapPositions.filterKeys{it == 3 || it == 6 || it == 9}
         val firstDiagonalRow = mapPositions.filterKeys{it == 1 || it == 5 || it == 9}
         val secondDiagonalRow = mapPositions.filterKeys{it == 3 || it == 5 || it == 7}

         if(firstHorizontalRow.filterValues { it == 1}.size == 3 || firstHorizontalRow.filterValues { it == 10}.size == 3)
         {
             gameOver()
             return
         }
         if(secondHorizontalRow.filterValues { it == 1}.size == 3 || secondHorizontalRow.filterValues { it == 10}.size == 3)
         {
             gameOver()
             return
         }
         if(thirdHorizontalRow.filterValues { it == 1}.size == 3 || thirdHorizontalRow.filterValues { it == 10}.size == 3)
         {
             gameOver()
             return
         }
         if(firstVerticalRow.filterValues { it == 1}.size == 3 || firstVerticalRow.filterValues { it == 10}.size == 3)
         {
             gameOver()
             return
         }
         if(secondVerticalRow.filterValues { it == 1}.size == 3 || secondVerticalRow.filterValues { it == 10}.size == 3)
         {
             gameOver()
             return
         }
         if(thirdVerticalRow.filterValues { it == 1}.size == 3 || thirdVerticalRow.filterValues { it == 10}.size == 3)
         {
             gameOver()
             return
         }
         if(firstDiagonalRow.filterValues { it == 1}.size == 3 || firstDiagonalRow.filterValues { it == 10}.size == 3)
         {
             gameOver()
             return
         }
         if(secondDiagonalRow.filterValues { it == 1}.size == 3 || secondDiagonalRow.filterValues { it == 10}.size == 3)
         {
             gameOver()
             return
         }
         // if it's a draw and there's no winner
        if(moves == 9){
            isGameOver = true
            tvPlayer.text = "It's a Draw!"
        }

     }

     //method for establishing that the game is over
     public fun gameOver(){
         isGameOver = true
         var congrats: String = " Wins!"
         if(!playerTurn){
             var name: String = playerOne.toString()
             var totalCongrats = name + congrats
             tvPlayer.text = totalCongrats
         }
         else{
             var name: String = playerTwo.toString()
             var totalCongrats = name + congrats
             tvPlayer.text = totalCongrats
         }

         // disable all of the game buttons
         btn1.isEnabled = false
         btn2.isEnabled = false
         btn3.isEnabled = false
         btn4.isEnabled = false
         btn5.isEnabled = false
         btn6.isEnabled = false
         btn7.isEnabled = false
         btn8.isEnabled = false
         btn9.isEnabled = false
     }
}