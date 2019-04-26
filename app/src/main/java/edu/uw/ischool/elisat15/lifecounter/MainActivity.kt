package edu.uw.ischool.elisat15.lifecounter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    var player1Health: Int = 100
        set(value: Int) {
            if(value >= 0) field = value
        }
    var player2Health: Int = 100
        set(value: Int) {
            if(value >= 0) field = value
        }
    var player3Health: Int = 100
        set(value: Int) {
            if(value >= 0) field = value
        }
    var player4Health: Int = 100
        set(value: Int) {
            if(value >= 0) field = value
        }

    private fun setHealth (newHealth: Int, playerInfo: View) {
        val playerHealth = playerInfo.findViewById<TextView>(R.id.HPtext)
        playerHealth.text = "HP: ${newHealth}"

    }

    private fun subtractHealth (subtractBy: Int, playerInfo: View, currentHealth: Int): Int {
        val playerID = playerInfo.findViewById<TextView>(R.id.header).text.toString()
        var newHealth = currentHealth.minus(subtractBy)
        if (newHealth <= 0) {
            newHealth = 0
            val gameUpdate = findViewById<TextView>(R.id.gameUpdate)
            gameUpdate.text = "Update: ${playerID} Lost!"

            // val duration = Toast.LENGTH_LONG
            // val toast = Toast.makeText(this, "${playerID} Lost!", duration)
            // toast.show()
        }
        setHealth(newHealth, playerInfo)
        return newHealth
    }

    private fun addHealth (addBy: Int, playerInfo: View, currentHealth: Int): Int {
        var newHealth = currentHealth.plus(addBy)
        setHealth(newHealth, playerInfo)
        return newHealth
    }

    private fun setButtonListener (playerInfo: View) {

        val playerID = playerInfo.findViewById<TextView>(R.id.header).text.toString()
        val playerAdd1 = playerInfo.findViewById<Button>(R.id.add1)
        val playerAdd5 = playerInfo.findViewById<Button>(R.id.add5)
        val playerSub1 = playerInfo.findViewById<Button>(R.id.sub1)
        val playerSub5 = playerInfo.findViewById<Button>(R.id.sub5)

        playerSub1.setOnClickListener {
            when(playerID) {
                "Player 1" -> player1Health = subtractHealth (1, playerInfo, player1Health)
                "Player 2" -> player2Health = subtractHealth (1, playerInfo, player2Health)
                "Player 3" -> player3Health = subtractHealth (1, playerInfo, player3Health)
                "Player 4" -> player4Health = subtractHealth (1, playerInfo, player4Health)
            }
        }

        playerSub5.setOnClickListener {
            when(playerID) {
                "Player 1" -> player1Health = subtractHealth (5, playerInfo, player1Health)
                "Player 2" -> player2Health = subtractHealth (5, playerInfo, player2Health)
                "Player 3" -> player3Health = subtractHealth (5, playerInfo, player3Health)
                "Player 4" -> player4Health = subtractHealth (5, playerInfo, player4Health)
            }
        }

        playerAdd1.setOnClickListener {
            when(playerID) {
                "Player 1" -> player1Health = addHealth(1, playerInfo, player1Health)
                "Player 2" -> player2Health = addHealth(1, playerInfo, player2Health)
                "Player 3" -> player3Health = addHealth(1, playerInfo, player3Health)
                "Player 4" -> player4Health = addHealth(1, playerInfo, player4Health)
            }
        }

        playerAdd5.setOnClickListener {
            when(playerID) {
                "Player 1" -> player1Health = addHealth(5, playerInfo, player1Health)
                "Player 2" -> player2Health = addHealth(5, playerInfo, player2Health)
                "Player 3" -> player3Health = addHealth(5, playerInfo, player3Health)
                "Player 4" -> player4Health = addHealth(5, playerInfo, player4Health)
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val player1Info = findViewById<ConstraintLayout>(R.id.player1)
        val player2Info = findViewById<ConstraintLayout>(R.id.player2)
        val player3Info = findViewById<ConstraintLayout>(R.id.player3)
        val player4Info = findViewById<ConstraintLayout>(R.id.player4)

        if (savedInstanceState != null) {
            with(savedInstanceState) {
                // Restore value of members from saved state
                player1Health = getInt(STATE_PLAYER1)
                player2Health = getInt(STATE_PLAYER2)
                player3Health = getInt(STATE_PLAYER3)
                player4Health = getInt(STATE_PLAYER4)
            }
            setHealth(player1Health, player1Info)
            setHealth(player2Health, player2Info)
            setHealth(player3Health, player3Info)
            setHealth(player4Health, player4Info)
        }

        setButtonListener(player1Info)
        setButtonListener(player2Info)
        setButtonListener(player3Info)
        setButtonListener(player4Info)

    }

    override fun onSaveInstanceState(outState: Bundle?) {
        // Save the user's current game state
        outState?.run {
            putInt(STATE_PLAYER1, player1Health)
            putInt(STATE_PLAYER2, player2Health)
            putInt(STATE_PLAYER3, player3Health)
            putInt(STATE_PLAYER4, player4Health)
        }

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(outState)
    }

    companion object {
        val STATE_PLAYER1 = "player1Health"
        val STATE_PLAYER2 = "player2Health"
        val STATE_PLAYER3 = "player3Health"
        val STATE_PLAYER4 = "player4Health"
    }

}
