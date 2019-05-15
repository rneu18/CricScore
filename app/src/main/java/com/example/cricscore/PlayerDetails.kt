package com.example.cricscore

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class EnterPlayer : AppCompatActivity() {

    lateinit var recyclerView1: RecyclerView
    lateinit var mAdapter1: RecyclerView.Adapter<*>
    lateinit var layoutManager1: RecyclerView.LayoutManager
    lateinit var myDB: DataBaseHelper

    lateinit var recyclerView2: RecyclerView
    lateinit var mAdapter2: RecyclerView.Adapter<*>
    lateinit var layoutManager2: RecyclerView.LayoutManager
    lateinit var submitBtn: Button
    internal var totalPlayer: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myDB = DataBaseHelper(this)
        totalPlayer = intent.getIntExtra("total", 0)
        setContentView(R.layout.activity_enter_player)
        val preferences = PreferenceManager.getDefaultSharedPreferences(this)
        val team1 = preferences.getString("TeamOne", null)
        val team2 = preferences.getString("TeamTwo", null)
        val firstTeam:TextView = findViewById(R.id.ryc_team1)
        val secondTeam:TextView = findViewById(R.id.ryc_team2)
        firstTeam.text = team1
        secondTeam.text = team2

        recyclerView1 = findViewById(R.id.team1_players)
        recyclerView1.setHasFixedSize(true)
        layoutManager1 = LinearLayoutManager(this)
        recyclerView1.layoutManager = layoutManager1
        mAdapter1 = MyAdapter1(totalPlayer)
        recyclerView1.adapter = mAdapter1

        recyclerView2 = findViewById(R.id.team2_players)
        recyclerView2.setHasFixedSize(true)
        layoutManager2 = LinearLayoutManager(this)
        recyclerView2.layoutManager = layoutManager2
        mAdapter2 = MyAdapter1(totalPlayer)
        recyclerView2.adapter = mAdapter2

        submitBtn = findViewById(R.id.teamSubmit)

        submitBtn.setOnClickListener {
            val player:TextView = findViewById(R.id.players_X)
            val checkPlayer = validatePlayerEntry()
            if (checkPlayer == true) {
                myDB.clearTables()
                for (k in 0 until totalPlayer) {
                    val playerTeam1 = (recyclerView1.layoutManager!!.findViewByPosition(k)!!.findViewById(R.id.players_X) as EditText).text.toString()
                    val playerTeam2 = (recyclerView2.layoutManager!!.findViewByPosition(k)!!.findViewById(R.id.players_X) as EditText).text.toString()
                    myDB.insertBatting1(playerTeam1)
                    myDB.insertBatting2(playerTeam2)

                }
                startActivity(Intent(this@EnterPlayer, ScoreDetails::class.java))
            } else {
                Toast.makeText(this@EnterPlayer, "Enter all player name and unique", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun validatePlayerEntry(): Boolean? {
        var i = 0

        var check = true

        while (i < totalPlayer - 1) {

            for (j in i + 1 until totalPlayer) {
                val check1 = (recyclerView1.layoutManager!!.findViewByPosition(j)!!.findViewById(R.id.players_X) as EditText).text.toString()
                val check2 = (recyclerView1.layoutManager!!.findViewByPosition(i)!!.findViewById(R.id.players_X) as EditText).text.toString()

                val check3 = (recyclerView2.layoutManager!!.findViewByPosition(j)!!.findViewById(R.id.players_X) as EditText).text.toString()
                val check4 = (recyclerView2.layoutManager!!.findViewByPosition(i)!!.findViewById(R.id.players_X) as EditText).text.toString()



                if (check1 == check2
                        || check3 == check4
                        || check1 == check3
                        || check1 == check4
                        || check2 == check3
                        || check2 == check4) {
                    check = false
                }

            }
            i++

        }

        return check
    }
}
