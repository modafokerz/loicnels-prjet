package ch.nelson.appdev;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    /**
     * Acitivité gérant le login de l'application
     * Test le mot de passe et login correspondant dans la base de données.
     */

    private LoginActivity activity;

    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;

    private EditText emailInput, passwdInput;
    private TextView resetPasswd, inscription;
    private ImageView flag;
    private Button loginBtn;
    private CheckBox checkBox;
    public static Activity fa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        fa = this;
        /**
         * Les ressources dont on a besoin, btns, texte, etc.
         */
        loginBtn = findViewById(R.id.login_btn);
        emailInput = findViewById(R.id.login_email_input);
        passwdInput = findViewById(R.id.login_passwd_input);
        this.activity = this;
        resetPasswd = findViewById(R.id.login_passwd_reset);
        inscription = findViewById(R.id.login_inscription_btn);
        flag = findViewById(R.id.login_language_flag);
        checkBox = findViewById(R.id.login_checkBox);


        /**
         * Va chercher la préférence de langue du user.
         * met FR par défaut.
         */
        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mPreferences.edit();

        checkSharedPreferences();


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
            }
        });

        /**
         * Gère le bouton d'inscription et l'envoie vers l'activité InscriptionActivity
         */
        inscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inscriptionActivity = new Intent(getApplicationContext(), InscriptionChoixActivity.class);
                startActivity(inscriptionActivity);
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


        String loginKey = getString(R.string.SP_login_Key);
        String passwdKey = getString(R.string.SP_passwd_Key);
        String langPref = getString(R.string.SP_langPref_Key);
        String checkBoxKey = getString(R.string.SP_checkBoxSave_Key);

        String langPrefValue = mPreferences.getString(langPref,"");
        String checkBoy_trueValue = getString(R.string.SP_checkBox_true);
        String checkBox_falseValue = getString(R.string.SP_checkBox_false);

        if(checkBox.isChecked()){
            mEditor.putString(loginKey,loginEmail);
            mEditor.commit();

            mEditor.putString(passwdKey,loginPwd);
            mEditor.commit();

            mEditor.putString(langPref,langPrefValue);
            mEditor.commit();

            mEditor.putString(checkBoxKey,checkBoy_trueValue);
            mEditor.commit();
        } else {
            mEditor.putString(loginKey,"");
            mEditor.commit();

            mEditor.putString(passwdKey,"");
            mEditor.commit();

            mEditor.putString(langPref,"fr");
            mEditor.commit();

            mEditor.putString(checkBoxKey,checkBox_falseValue);
            mEditor.commit();
        }
    }

    private void checkSharedPreferences(){
        String langPrefKey = getString(R.string.SP_langPref_Key);
        String checkBoxKey = getString(R.string.SP_checkBoxSave_Key);
        String loginKey = getString(R.string.SP_login_Key);
        String pwdKey = getString(R.string.SP_passwd_Key);

        String langPref = mPreferences.getString(langPrefKey,"");
        String checkBoxPref = mPreferences.getString(checkBoxKey,"false");

        String login = mPreferences.getString(loginKey,"");
        String pwd = mPreferences.getString(pwdKey,"");

        // Language par défaut fr
        if(langPref.equals(""))
            mEditor.putString(langPrefKey,getString(R.string.SP_langPref_frValue));

        if(checkBoxPref.equals("true")){
            checkBox.setChecked(true);
            emailInput.setText(login);
            passwdInput.setText(pwd);
        } else {
            checkBox.setChecked(false);
            emailInput.setText("");
            passwdInput.setText("");
        }



        inscription.setText(getStringFromResourcesByName("login_"+langPref+"_inscription"));
        resetPasswd.setText(getStringFromResourcesByName("login_"+langPref+"_passwd_reset"));
        checkBox.setText(getStringFromResourcesByName("login_"+langPref+"_checkBox"));
        flag.setImageResource(getResources().getIdentifier(langPref+"_flag",
                "drawable", getPackageName()));

        }

        /**
         * Affiche le drapeau selon le choix de langue qui a été fait.
         */

        private String getStringFromResourcesByName(String resourceName){

            // Get the application package name
            String packageName = getPackageName();


            // Get the string resource id by name
            int resourceId = getResources().getIdentifier(resourceName,"string",packageName);

            // Return the string value
            return getString(resourceId);
        }



    }





