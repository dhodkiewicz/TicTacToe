package com.example.tictactoe


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import com.example.tictactoe.databinding.GameSetupBinding


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class GameSetUp : Fragment() {

    private var _binding: GameSetupBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    //variables for holding the editable text field values
    private lateinit var player1Text: EditText
    private lateinit var player2Text: EditText
    private lateinit var btnSend : Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = GameSetupBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnSend = binding.buttonSecond

        //sends the intent to the GameDisplay activity (our array of names)
        btnSend.setOnClickListener(){
            // variables to be assigned via binding
            player1Text = binding.editTextTextPersonName
            player2Text = binding.editTextTextPersonName2
            var playerOne = player1Text.text
            var playerTwo = player2Text.text
//            startActivity(Intent(context, GameDisplay::class.java)
//                .putExtra("PLAYER_ONE", playerOne)
//                .putExtra("PLAYER_TWO", playerTwo))
            val intent= Intent(context, GameDisplay::class.java)
            intent.putExtra("PLAYER_ONE", playerOne)
            intent.putExtra("PLAYER_TWO", playerTwo)
            startActivity(intent)

        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}