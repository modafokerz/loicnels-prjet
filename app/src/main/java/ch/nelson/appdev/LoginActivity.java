package ch.nelson.appdev;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class LoginActivity extends AppCompatActivity {
    /**
     * Acitivité gérant le login de l'application
     * Test le mot de passe et login correspondant dans la base de données.
     */

    private LoginActivity activity;
    private static final String langNameFile = "lang_pref.txt";
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;

    private EditText emailInput, passwdInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        /**
         * Va chercher la préférence de langue du client.
         * met FR par défaut.
         */
        if(!verifyLangFile())
        writeLangPref("fr",getApplicationContext());


        String langPref = readFromFile(getApplicationContext());



        /**
         * Les ressources dont on a besoin, btns, texte, etc.
         */
        Button loginBtn = findViewById(R.id.login_btn);
        emailInput = findViewById(R.id.login_email_input);
        passwdInput = findViewById(R.id.login_passwd_input);
        this.activity = this;
        TextView resetPasswd = findViewById(R.id.login_passwd_reset);
        TextView inscription = findViewById(R.id.login_inscription_btn);
        ImageView flag = findViewById(R.id.login_language_flag);

        switch(langPref){
            case "en":
                inscription.setText(R.string.login_en_inscription);
                resetPasswd.setText(R.string.login_en_passwd_reset);
                flag.setImageResource(R.drawable.eng_flag);
                break;
            default:
                inscription.setText(R.string.login_fr_inscription);
                resetPasswd.setText(R.string.login_fr_passwd_reset);
                flag.setImageResource(R.drawable.fr_flag);
                break;
        }

        /**
         * Affiche le drapeau selon le choix de langue qui a été fait.
         */
        ;
        switch (langPref){
            case "fr":
                flag.setImageResource(R.drawable.fr_flag);
                break;
            case "ger":
                flag.setImageResource(R.drawable.ger_flag);
                break;
            case "ita":
                flag.setImageResource(R.drawable.ita_flag);
                break;
            case "eng":
                flag.setImageResource(R.drawable.eng_flag);
                break;
        }


        /**
         * Action du bouton connect, test les valeurs dans les EditText puis connecte ou affiche un Toast
         * si le pass ou login est faux.
         */
      /**  loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String loginEmail = emailInput.getText().toString();
                String loginPwd = passwdInput.getText().toString();

                if(loginEmail.equals("test1") && loginPwd.equals("test1")) {
                        Toast.makeText(getApplicationContext(), "Vous êtes loggé correctement", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Login incorrect", Toast.LENGTH_LONG).show();
                }

            }
        });*/

        /**
         * Gére le bouton mot de passe oublié et l'envoie vers l'activité en question.
         */
        resetPasswd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent passwdReset = new Intent(getApplicationContext(), PasswordResetActivity.class);
                startActivity(passwdReset);
                finish();
            }
        });

        /**
         * Gère le bouton d'inscription et l'envoie vers l'activité InscriptionActivity
         */
        inscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inscriptionActivity = new Intent(getApplicationContext(), InscriptionActivity.class);
                startActivity(inscriptionActivity);
                finish();
            }
        });


        /**
         * Action du drapeau de la langue
         */
        flag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent languageChoice = new Intent(getApplicationContext(), LanguageChoiceActivity.class);
                startActivity(languageChoice);
                finish();
            }
        });



    }

    /**
     * Méthode pour se connecter a la bdd et savoir si les infos données sont justes
     *
     */
    public void OnLogin (View view)
    {

        String loginEmail = emailInput.getText().toString();
        String loginPwd = passwdInput.getText().toString();
        String type = "login";

        BackgroundWorker bgW = new BackgroundWorker(this);
        bgW.execute(type,loginEmail,loginPwd);

        Session userSession = new Session(this);

        //Si le pwd a été tappé faut au début il faudra 2 click pour que ca marche putain de merde


        if (userSession.getIsConnected()==1)
        {
            Intent goToNavActivity = new Intent(getApplicationContext(), NavigationActivity.class);
            startActivity(goToNavActivity);
            finish();
        }
    }


    /**
     * Méthode pour écrire dans le fichier lang_pref.txt
     * @param data valeur
     * @param context AppContext
     */
    public static void writeLangPref(String data,Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(langNameFile, Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    public static String readFromFile(Context context) {

        String ret = "";

        try {
            InputStream inputStream = context.openFileInput(langNameFile);

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }

    private boolean verifyLangFile(){
        File file = getApplicationContext().getFileStreamPath(langNameFile);
        if(file == null || !file.exists()) {
            return false;
        }
        return true;
    }
}
