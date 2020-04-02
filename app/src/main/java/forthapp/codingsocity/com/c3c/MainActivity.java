package forthapp.codingsocity.com.c3c;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int[] gamestate = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningpositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    int activeplayer = 0;
    boolean gameactive = true;

    public void Dropin(View view) {
        // 0:yellow 1:red


        ImageView counter = (ImageView) view;


        int Tappedcounter = Integer.parseInt(counter.getTag().toString());

        if (gamestate[Tappedcounter] == 2 && gameactive) {

            gamestate[Tappedcounter] = activeplayer;

            counter.setTranslationY(-1500);


            if (activeplayer == 0) {

                counter.setImageResource(R.drawable.yellow);
                activeplayer = 1;


            } else {
                counter.setImageResource(R.drawable.red);
                activeplayer = 0;

            }
            counter.animate().translationYBy(1500).rotation(3000).setDuration(400);

            for (int[] winningposition : winningpositions) {


                String winner = "";
                if (gamestate[winningposition[0]] == gamestate[winningposition[1]] && gamestate[winningposition[1]] == gamestate[winningposition[2]] && gamestate[winningposition[0]] != 2) {
                    gameactive = false;
                    if (activeplayer == 1) {
                        winner = "Yellow";
                    } else {
                        winner = "Red";
                    }


                    Button playagainbutton = (Button) findViewById(R.id.playagainbutton);
                    TextView winnertextview = (TextView) findViewById(R.id.winnertextview);
                    winnertextview.setText(winner + " has won");
                    playagainbutton.setVisibility(View.VISIBLE);
                    winnertextview.setVisibility(View.VISIBLE);

                }

            }
        }

    }


    public void playAgain(View view) {
        Button playagainbutton = (Button) findViewById(R.id.playagainbutton);
        TextView winnertextview = (TextView) findViewById(R.id.winnertextview);

        playagainbutton.setVisibility(View.INVISIBLE);
        winnertextview.setVisibility(View.INVISIBLE);
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }

        for (int i = 0; i < gamestate.length; i++) {
            gamestate[i] = 2;

            activeplayer = 0;
            gameactive = true;


        }
    }

        @Override
        protected void onCreate (Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
        }
    }
