package ch.nelson.appdev;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class InscriptionActivity extends AppCompatActivity {


    private TextView topText;
    private String errEmpty;
    private String errEmail;
    private String errPassword;
    private String errPassword2;
    private Button nextBtn;
    /**
     * class qui gère le choix de type d'inscription escort ou client
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        /**
         * Va chercher le boutons et les input(Email et passwd)
         */
        nextBtn = findViewById(R.id.inscription_nextBtn);
        topText = findViewById(R.id.inscription_topText);

        final EditText emailInput = findViewById(R.id.inscription_emailInput);
        final EditText passwdInput = findViewById(R.id.inscription_passwdInput);
        final EditText passwdInput2 = findViewById(R.id.inscription_passwdInput2);

        SharedPreferences mPreferences  = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor mEditor = mPreferences.edit();

        String langPref = mPreferences.getString(getString(R.string.SP_langPref_Key),"");

        // Language par défaut fr
        if(langPref.equals(""))
            mEditor.putString(getString(R.string.SP_langPref_Key),getString(R.string.SP_langPref_frValue));

        /**
         * Traduction des éléments
         */
        emailInput.setHint("E-mail");


        topText.setText(getStringFromResourcesByName("inscription_"+langPref+"_str"));
        nextBtn.setText(getStringFromResourcesByName("inscription_"+langPref+"_str"));
        passwdInput.setHint(getStringFromResourcesByName("inscription_"+langPref+"_passwd"));
        passwdInput2.setHint(getStringFromResourcesByName("inscription_"+langPref+"_passwd2"));
        errEmpty = getStringFromResourcesByName("inscription_"+langPref+"_err_empty");
        errEmail = getStringFromResourcesByName("inscription_"+langPref+"_err_email");
        errPassword = getStringFromResourcesByName("inscription_"+langPref+"_err_passwd");
        errPassword2 = getStringFromResourcesByName("inscription_"+langPref+"_err_passwd2");

        /**
         * Ajoute l'action au bouton next et test les valeurs entrées :
         * Email doit avoir un "@" et un "."
         * Password doit avoir au moins 6 caractères.
         *
         */

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailInput.getText().toString();
                String passwd = passwdInput.getText().toString();
                String passwd2 = passwdInput2.getText().toString();



                String type = "register";

                if(email.length()==0 || passwd.length()==0)
                    Toast.makeText(getApplicationContext(),errEmpty, Toast.LENGTH_SHORT).show();
                else {
                    if(!isValidEmail(email)){
                        Toast.makeText(getApplicationContext(),errEmail, Toast.LENGTH_SHORT).show();
                    } else if (passwd.length()<6){
                        Toast.makeText(getApplicationContext(),errPassword, Toast.LENGTH_SHORT).show();
                    } else {
                        if (passwd.equals(passwd2))
                        {
                            BackgroundWorker bgW = new BackgroundWorker(InscriptionActivity.this);
                            bgW.execute(type,email,passwd);
                            //Si l'inscription a fonctionné on ferme l'activité
                            finish();
                        }
                        else
                            Toast.makeText(getApplicationContext(),errPassword2, Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });


    }

    /**
     * Utilise Patterns d'android pour vérifier si l'email est valide.
     * @param target
     * @return
     */
    public final static boolean isValidEmail(CharSequence target) {
        if (target == null)
            return false;

        return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    private String getStringFromResourcesByName(String resourceName){

        // Get the application package name
        String packageName = getPackageName();


        // Get the string resource id by name
        int resourceId = getResources().getIdentifier(resourceName,"string",packageName);

        // Return the string value
        return getString(resourceId);
    }
}
