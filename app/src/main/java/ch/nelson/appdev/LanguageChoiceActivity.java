package ch.nelson.appdev;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class LanguageChoiceActivity extends AppCompatActivity {
    /**
     * Activité qui gère le choix de la langue
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_choice);

        /** Récuper les imageView et le texte du haut
         *
         */
        TextView topText = findViewById(R.id.languageChoice_topText);
        ImageView frFlag = findViewById(R.id.languageChoice_fr_flag);
        ImageView engFlag = findViewById(R.id.languageChoice_eng_flag);
        ImageView gerFlag = findViewById(R.id.languageChoice_ger_flag);
        ImageView itaFlag = findViewById(R.id.languageChoice_ita_flag);


        frFlag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.writeLangPref("fr",getApplicationContext());
                returnToLogin();
            }
        });

        engFlag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.writeLangPref("en",getApplicationContext());
                returnToLogin();
            }
        });

    }

    private void returnToLogin(){
        Intent loginActivity = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(loginActivity);
        finish();
    }
}
