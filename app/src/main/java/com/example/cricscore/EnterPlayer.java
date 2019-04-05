package com.example.cricscore;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EnterPlayer extends AppCompatActivity {

    public RecyclerView recyclerView1;
    public RecyclerView.Adapter mAdapter1;
    public RecyclerView.LayoutManager layoutManager1;
    DataBaseHelper myDB;

    public RecyclerView recyclerView2;
    public RecyclerView.Adapter mAdapter2;
    public RecyclerView.LayoutManager layoutManager2;
    Button submitBtn;
    int totalPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myDB = new DataBaseHelper(this);
        totalPlayer = getIntent().getIntExtra("total", 0);
        setContentView(R.layout.activity_enter_player);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String team1 = preferences.getString("TeamOne", null);
        String team2 = preferences.getString("TeamTwo", null);
        TextView firstTeam = (TextView) findViewById(R.id.ryc_team1);
        TextView secondTeam = (TextView) findViewById(R.id.ryc_team2);
        firstTeam.setText(team1);
        secondTeam.setText(team2);

        recyclerView1 = (RecyclerView) findViewById(R.id.team1_players);
        recyclerView1.setHasFixedSize(true);
        layoutManager1 = new LinearLayoutManager(this);
        recyclerView1.setLayoutManager(layoutManager1);
        mAdapter1 = new MyAdapter1(totalPlayer);
        recyclerView1.setAdapter(mAdapter1);

        recyclerView2 = (RecyclerView) findViewById(R.id.team2_players);
        recyclerView2.setHasFixedSize(true);
        layoutManager2 = new LinearLayoutManager(this);
        recyclerView2.setLayoutManager(layoutManager2);
        mAdapter2 = new MyAdapter1(totalPlayer);
        recyclerView2.setAdapter(mAdapter2);

        submitBtn = (Button) findViewById(R.id.teamSubmit);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText player = (EditText) findViewById(R.id.players_X);
                Boolean checkPlayer = validatePlayerEntry();
                if(checkPlayer == true){
                       myDB.clearTables();
                    for(int k= 0; k<totalPlayer; k++){
                        String player_team1 = ((EditText) recyclerView1.getLayoutManager().findViewByPosition(k).findViewById(R.id.players_X)).getText().toString();
                        String player_team2 = ((EditText) recyclerView2.getLayoutManager().findViewByPosition(k).findViewById(R.id.players_X)).getText().toString();
                        myDB.insertBatting1(player_team1);
                        myDB.insertBatting2(player_team2);

                    }
                    startActivity(new Intent(EnterPlayer.this, ScoreDetails.class));
                }
                else{
                    Toast.makeText(EnterPlayer.this, "Enter all player name and unique", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private Boolean validatePlayerEntry() {
        int i;

        boolean check = true;

            for(i=0; i<totalPlayer-1; i++){

                for(int j=i+1; j<totalPlayer; j++){
                    String check1 = ((EditText) recyclerView1.getLayoutManager().findViewByPosition(j).findViewById(R.id.players_X)).getText().toString();
                    String check2 = ((EditText) recyclerView1.getLayoutManager().findViewByPosition(i).findViewById(R.id.players_X)).getText().toString();

                    String check3 = ((EditText) recyclerView2.getLayoutManager().findViewByPosition(j).findViewById(R.id.players_X)).getText().toString();
                    String check4 = ((EditText) recyclerView2.getLayoutManager().findViewByPosition(i).findViewById(R.id.players_X)).getText().toString();



                   if (check1.equals(check2)
                           || check3.equals(check4)
                           || check1.equals(check3)
                           || check1.equals(check4)
                           || check2.equals(check3)
                           || check2.equals(check4)){
                       check = false;
                   }

                }

            }

        return check;
    }
}
