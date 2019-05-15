package com.example.cricscore

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.*
import java.util.ArrayList

class ScoreDetails : AppCompatActivity() {

     lateinit var teamOne: String
     lateinit var teamTwo: String
     lateinit var newMatch: ImageView
     lateinit var submitTeamDetails: Button
     var tOvers: Int = 0
     var tPlayers: Int = 0
     var wickets1: Int = 0
     var wickets2: Int = 0
     var totalRuns1: Int = 0
     var totalRuns2: Int = 0
     var over1: Int = 0
     var over2: Int = 0
    lateinit var battingTeam1: TextView
    lateinit var currentScore1: TextView
    lateinit var oversDone1: TextView
    lateinit var battingTeam2: TextView
    lateinit var currentScore2: TextView
    lateinit var oversDone2: TextView
    lateinit var target: TextView
    lateinit var fTeam1: TextView
    lateinit var fTeam2: TextView
    lateinit var myDB: DataBaseHelper
    lateinit var batting1: LinearLayout
    lateinit var batting2: LinearLayout
    lateinit var fielding1: LinearLayout
    lateinit var fielding2: LinearLayout
    lateinit var players:ArrayList<String>
    lateinit var recyclerViewBatting1: RecyclerView
    lateinit var mAdapterBatting11: RecyclerView.Adapter<*>
    lateinit var layoutManagerBatting1: RecyclerView.LayoutManager



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_socre_details)


        val dialog = Dialog(this)
        dialog.setContentView(R.layout.team_details)

        battingTeam1 = findViewById(R.id.battingTeam1)
        currentScore1 = findViewById(R.id.currentScore1)
        oversDone1 = findViewById(R.id.oversDone1)
        fTeam1 = findViewById(R.id.team_1)
        fTeam2 = findViewById(R.id.team_2)

        fielding1 = findViewById(R.id.linear_fielding1)
        fielding2 = findViewById(R.id.linear_fielding2)
        fielding1.visibility = LinearLayout.GONE
        fielding2.visibility = LinearLayout.GONE

        battingTeam2 = findViewById(R.id.battingTeam2)
        currentScore2 = findViewById(R.id.currentScore2)
        oversDone2 = findViewById(R.id.oversDone2)
        target = findViewById(R.id.target)
        updateScore()



        newMatch = findViewById(R.id.refresh)
        newMatch.setOnClickListener {
            val preferences = PreferenceManager.getDefaultSharedPreferences(baseContext)
            val editor = preferences.edit()
            editor.putString("TeamOne", null)
            editor.putString("TeamTwo", null)
            editor.putInt("Overs", 0)
            editor.putInt("Players", 0)
            editor.apply()
            dialog.show()
        }

        if (teamOne == null) {
            dialog.show()
        }

        submitTeamDetails = dialog.findViewById(R.id.submitTeams)




        submitTeamDetails.setOnClickListener {
                try {
                    val input1 = dialog.findViewById(R.id.totalPlayer) as EditText
                    val input2 = dialog.findViewById(R.id.totalOvers) as EditText
                    val team1 = (dialog.findViewById(R.id.teamOne) as EditText).text.toString()
                    val team2 = (dialog.findViewById(R.id.teamTwo) as EditText).text.toString()
                    val totalOvers:Int = input2.text.toString().toInt()
                    val totalPlayers:Int = input1.text.toString().toInt()

                    if (team2 != null && team1 != null && totalPlayers > 0 && totalOvers > 0) {
                        val preferences = PreferenceManager.getDefaultSharedPreferences(baseContext)
                        val editor = preferences.edit()
                        editor.putString("TeamOne", team1)
                        editor.putString("TeamTwo", team2)
                        editor.putInt("Overs", totalOvers)
                        editor.putInt("Players", totalPlayers)
                        editor.apply()
                        updateScore()
                        dialog.dismiss()
                        nextActivity1()
                    }

                } catch (e: Exception) {

                }
            }


        }



    private fun nextActivity() {
        val myIntent = Intent(this, ScoreSheet::class.java)
        myIntent.putExtra("total", tPlayers)
        startActivity(myIntent)

    }

    private fun nextActivity1() {
        val myIntent = Intent(this, EnterPlayer::class.java)
        myIntent.putExtra("total", tPlayers)
        startActivity(myIntent)

    }

    private fun updateScore() {
        val preferences = PreferenceManager.getDefaultSharedPreferences(this)
        teamOne = preferences.getString("TeamOne", "N/A")
        teamTwo = preferences.getString("TeamTwo", "N/A")
        tOvers = preferences.getInt("Overs", 0)
        tPlayers = preferences.getInt("Players", 0)
        battingTeam1.text = teamOne
        currentScore1.text = "$totalRuns1/$wickets1"
        oversDone1.text = "$over1($tOvers)"
        fTeam1.text = teamOne!! + " Bowling Details"
        fTeam2.text = teamTwo!! + " Bowling Details"

        battingTeam2.text = teamTwo
        currentScore2.text = "$totalRuns2/$wickets2"
        oversDone2.text = "$over2($tOvers)"
        target.text = (totalRuns1 + 1).toString()


    }

    fun viewScore() {
        println("33333333333333333333333333333333333333 " + players[0])
        try {
            val res = myDB!!.allData
            if (res.count > 0) {
                println("4444444444444444444444444444444444 " + players[0])
                res.moveToFirst()

            }

            do {
                val player_name = res.getString(1)
                println("222222222222222222222222player name $player_name")

            } while (res.moveToNext())

            println("2222222222222222222222222 " + players[0])
            println("2222222222222222222222222 $players")

            recyclerViewBatting1 = findViewById(R.id.batting1)
            recyclerViewBatting1.setHasFixedSize(true)
            layoutManagerBatting1 = LinearLayoutManager(this)
            recyclerViewBatting1.layoutManager = layoutManagerBatting1
            mAdapterBatting11 = MyAdapterTeam1(players)
            recyclerViewBatting1.adapter = mAdapterBatting11

        } catch (e: Exception) {

        }

    }
}