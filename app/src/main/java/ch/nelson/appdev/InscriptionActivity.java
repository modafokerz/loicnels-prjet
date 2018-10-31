package ch.nelson.appdev;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InscriptionActivity extends AppCompatActivity {

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
        Button nextBtn = findViewById(R.id.inscription_nextBtn);
        final EditText emailInput = findViewById(R.id.inscription_emailInput);
        final EditText passwdInput = findViewById(R.id.inscription_passwdInput);

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

                String text = getString(R.string.inscription_fr_str);

                if(email.length()==0 || passwd.length()==0)
                    Toast.makeText(getApplicationContext(),text, Toast.LENGTH_SHORT).show();
                else {
                    if(!isValidEmail(email)){
                        Toast.makeText(getApplicationContext(),getString(R.string.inscription_fr_err_email), Toast.LENGTH_SHORT).show();
                    } else if (passwd.length()<6){
                        Toast.makeText(getApplicationContext(),getString(R.string.inscription_fr_err_passwd), Toast.LENGTH_SHORT).show();
                    } else {
                        // SI TOUT EST OK, ALORS ON ENREGISTRE LE BORDEL DANS SHAREDPREFERENCES


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
}
