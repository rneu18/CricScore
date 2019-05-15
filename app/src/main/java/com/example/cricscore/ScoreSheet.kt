package com.example.cricscore

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView

class ScoreSheet : AppCompatActivity() {

    private var scoreA = 0
    private var wicketA = 0
    private var scoreB = 0
    private var wicketB = 0
    private var teamOne: String? = null
    private var teamTwo: String? = null
    private var tOvers: Int = 0
    private var tPlayers: Int = 0
    private var balls1 = 0
    private var balls2 = 0
    lateinit var scoreCardA: LinearLayout
    lateinit var scoreCardB: LinearLayout
    lateinit var finalResult: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score_sheet)
        val preferences = PreferenceManager.getDefaultSharedPreferences(this)
        teamOne = preferences.getString("TeamOne", null)
        teamTwo = preferences.getString("TeamTwo", null)
        tOvers = preferences.getInt("Overs", 0)
        tPlayers = preferences.getInt("Players", 0)

        val team1:TextView = findViewById(R.id.team_a)
        val team2:TextView = findViewById(R.id.team_b)
        team1.text = teamOne
        team2.text = teamTwo
        displayForTeamA(scoreA, wicketA, balls1)

        scoreCardA = findViewById<LinearLayout>(R.id.score_team_a)
        scoreCardB = findViewById<LinearLayout>(R.id.score_team_b)
        scoreCardB.visibility = LinearLayout.GONE
        finalResult = findViewById<TextView>(R.id.result)

    }

    fun add1ToA(view: View) {
        if (wicketA < tPlayers && balls1 < tOvers * 6) {
            scoreA += 1
            balls1 += 1
            displayForTeamA(scoreA, wicketA, balls1)
        } else
            displayForTeamASummary(scoreA, wicketA)
    }

    fun add2ToA(view: View) {
        if (wicketA < tPlayers && balls1 < tOvers * 6) {

            scoreA += 2
            balls1 += 1
            displayForTeamA(scoreA, wicketA, balls1)
        } else
            displayForTeamASummary(scoreA, wicketA)
    }

    fun add3ToA(view: View) {
        if (wicketA < tPlayers && balls1 < tOvers * 6) {

            scoreA += 3
            balls1 += 1
            displayForTeamA(scoreA, wicketA, balls1)
        } else
            displayForTeamASummary(scoreA, wicketA)
    }


    fun add4ToA(view: View) {
        if (wicketA < tPlayers && balls1 < tOvers * 6) {

            scoreA += 4
            balls1 += 1
            displayForTeamA(scoreA, wicketA, balls1)
        } else
            displayForTeamASummary(scoreA, wicketA)
    }

    fun add5ToA(view: View) {
        if (wicketA < tPlayers && balls1 < tOvers * 6) {

            scoreA += 5
            balls1 += 1
            displayForTeamA(scoreA, wicketA, balls1)
        } else
            displayForTeamASummary(scoreA, wicketA)
    }

    fun add6ToA(view: View) {
        if (wicketA < tPlayers && balls1 < tOvers * 6) {

            scoreA += 6
            balls1 += 1
            displayForTeamA(scoreA, wicketA, balls1)
        } else
            displayForTeamASummary(scoreA, wicketA)
    }

    fun addDotBallToA(view: View) {

        if (wicketA < tPlayers && balls1 < tOvers * 6) {

            balls1 += 1
            displayForTeamA(scoreA, wicketA, balls1)
        } else
            displayForTeamASummary(scoreA, wicketA)
    }

    fun addNoBallToA(view: View) {
        if (wicketA < tPlayers && balls1 < tOvers * 6) {
            scoreA += 1


            displayForTeamA(scoreA, wicketA, balls1)
        } else
            displayForTeamASummary(scoreA, wicketA)
    }

    fun addWideToA(view: View) {
        if (wicketA < tPlayers && balls1 < tOvers * 6) {

            scoreA += 1

            displayForTeamA(scoreA, wicketA, balls1)
        } else
            displayForTeamASummary(scoreA, wicketA)


    }

    fun add1WicketA(view: View) {
        wicketA += 1

        if (wicketA < tPlayers - 1 && balls1 < tOvers * 6) {


            displayForTeamA(scoreA, wicketA, balls1)

        } else
            displayForTeamASummary(scoreA, wicketA)

    }

    fun displayForTeamA(scoreA: Int, wicketA: Int, balls1: Int) {
        val scoreView = findViewById(R.id.scoreForA) as TextView
        scoreView.text = scoreA.toString() + "/" + wicketA + " (" + balls1 / 6 + "." + balls1 % 6 + "/" + tOvers + ")"
    }

    @SuppressLint("SetTextI18n")
    fun displayForTeamASummary(scoreA: Int, wicketA: Int) {
        scoreCardA.visibility = LinearLayout.GONE
        scoreCardB.visibility = LinearLayout.VISIBLE
        val target = scoreA + 1
        val scoreView:TextView = findViewById(R.id.scoreForA)
        scoreView.setText("Innings Ended: $scoreA/$wicketA\n Target for $teamTwo: $target")
    }

    fun add1ToB(view: View) {
        if (wicketB < tPlayers && scoreB < scoreA + 1 && balls2 < tOvers * 6) {
            scoreB += 1
            balls2 += 1
            displayForTeamB(scoreB, wicketB, balls2)
        } else

            displayForTeamBSummary(scoreB, wicketB)
    }

    fun add2ToB(view: View) {
        if (wicketB < tPlayers && scoreB < scoreA + 1 && balls2 < tOvers * 6) {

            scoreB += 2
            balls2 += 1
            displayForTeamB(scoreB, wicketB, balls2)
        } else

            displayForTeamBSummary(scoreB, wicketB)
    }

    fun add3ToB(view: View) {
        if (wicketB < tPlayers && scoreB < scoreA + 1 && balls2 < tOvers * 6) {

            scoreB += 3
            balls2 += 1
            displayForTeamB(scoreB, wicketB, balls2)
        } else

            displayForTeamBSummary(scoreB, wicketB)
    }

    fun add4ToB(view: View) {
        if (wicketB < tPlayers && scoreB < scoreA + 1 && balls2 < tOvers * 6) {

            scoreB += 4
            balls2 += 1
            displayForTeamB(scoreB, wicketB, balls2)
        } else

            displayForTeamBSummary(scoreB, wicketB)
    }

    fun add5ToB(view: View) {
        if (wicketB < tPlayers && scoreB < scoreA + 1 && balls2 < tOvers * 6) {

            scoreB += 5
            balls2 += 1
            displayForTeamB(scoreB, wicketB, balls2)
        } else

            displayForTeamBSummary(scoreB, wicketB)
    }

    fun add6ToB(view: View) {
        if (wicketB < tPlayers && scoreB < scoreA + 1 && balls2 < tOvers * 6) {

            scoreB += 6
            balls2 += 1
            displayForTeamB(scoreB, wicketB, balls2)
        } else

            displayForTeamBSummary(scoreB, wicketB)
    }

    fun addDotToB(view: View) {
        if (wicketB < tPlayers && scoreB < scoreA + 1 && balls2 < tOvers * 6) {

            balls2 += 1
            displayForTeamB(scoreB, wicketB, balls2)
        } else

            displayForTeamBSummary(scoreB, wicketB)
    }

    fun addNoBallToB(view: View) {
        if (wicketB < tPlayers && scoreB < scoreA + 1 && balls2 < tOvers * 6) {
            scoreB += 1

            displayForTeamB(scoreB, wicketB, balls2)
        } else

            displayForTeamBSummary(scoreB, wicketB)
    }

    fun addWideTob(view: View) {
        if (wicketB < tPlayers && scoreB < scoreA + 1 && balls2 < tOvers * 6) {
            scoreB += 1
            displayForTeamB(scoreB, wicketB, balls2)
        } else

            displayForTeamBSummary(scoreB, wicketB)
    }

    fun add1WicketB(view: View) {
        wicketB += 1
        if (wicketB < tPlayers - 1 && scoreB < scoreA + 1 && balls2 < tOvers * 6) {


            displayForTeamB(scoreB, wicketB, balls2)
        } else

            displayForTeamBSummary(scoreB, wicketB)
    }

    fun displayForTeamB(scoreB: Int, wicketB: Int, balls2: Int) {
        val scoreView:TextView = findViewById(R.id.scoreForB)
        scoreView.setText(scoreB.toString() + "/" + wicketB + " (" + balls2 / 6 + "." + balls2 % 6 + "/" + tOvers + ")")
    }

    fun displayForTeamBSummary(scoreB: Int, wicketB: Int) {
        result()

        scoreCardB.visibility = LinearLayout.GONE
        val scoreView:TextView = findViewById(R.id.scoreForB)
        scoreView.setText("Innings Ended : $scoreB/$wicketB")
    }

    fun reset(view: View) {
        val myIntent = Intent(this, ScoreDetails::class.java)
        //  myIntent.putExtra("total", tPlayers);
        startActivity(myIntent)
    }

    fun result() {
        val ch: String?
        if (scoreA > scoreB) {
            ch = teamOne
            displayResult(ch)
        } else if (scoreB > scoreA) {
            ch = teamTwo
            displayResult(ch)
        } else {
            ch = "T"
            displayResult(ch)
        }
    }

    fun displayResult(ch: String?) {

        if (ch === teamOne || ch === teamTwo) {
            finalResult.text = ch!! + " won the game!"

        } else {
            finalResult.text = "The game was a Tie! Well Played!"

        }

    }

    fun removeRunToA(view: View) {
        scoreA -= 1

        displayForTeamA(scoreA, wicketA, balls1)
    }

    fun removeDotToA(view: View) {

        balls1 -= 1
        displayForTeamA(scoreA, wicketA, balls1)
    }

    fun remove1WicketA(view: View) {

        wicketA -= 1
        displayForTeamA(scoreA, wicketA, balls1)
    }

    fun removeRunToB(view: View) {
        scoreB -= 1

        displayForTeamB(scoreB, wicketB, balls2)
    }

    fun removeDotToB(view: View) {
        balls2 -= 1

        displayForTeamB(scoreB, wicketB, balls2)
    }

    fun remove1WicketB(view: View) {
        wicketB -= 1

        displayForTeamB(scoreB, wicketB, balls2)
    }
}
