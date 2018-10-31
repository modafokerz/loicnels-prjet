package ch.nelson.appdev;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.preference.PreferenceManager;
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
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;


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

        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mPreferences.edit();



        frFlag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.putString(getString(R.string.SP_langPref_Key),getString(R.string.SP_langPref_frValue));
                mEditor.commit();
                String langPref = mPreferences.getString(getString(R.string.SP_langPref_Key),"");
                System.out.println(langPref);
                returnToLogin();
            }
        });

        engFlag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.putString(getString(R.string.SP_langPref_Key),getString(R.string.SP_langPref_enValue));
                mEditor.commit();
                String langPref = mPreferences.getString(getString(R.string.SP_langPref_Key),"");
                System.out.println(langPref);
                returnToLogin();
            }
        });

        gerFlag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.putString(getString(R.string.SP_langPref_Key),getString(R.string.SP_langPref_deValue));
                mEditor.commit();
                String langPref = mPreferences.getString(getString(R.string.SP_langPref_Key),"");
                System.out.println(langPref);
                returnToLogin();
            }
        });

        itaFlag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.putString(getString(R.string.SP_langPref_Key),getString(R.string.SP_langPref_itaValue));
                mEditor.commit();
                String langPref = mPreferences.getString(getString(R.string.SP_langPref_Key),"");
                System.out.println(langPref);
                returnToLogin();
            }
        });

    }

    private void returnToLogin(){
        Intent login = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(login);
        finish();
    }
}
