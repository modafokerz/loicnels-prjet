package ch.nelson.appdev;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EscortInscriptionActivity extends AppCompatActivity {

    private Escort escort;

    private Button btn;
    private EditText emailInput;
    private EditText pseudo;
    private EditText passwd;
    private EditText passwd2;

    private String errEmpty;
    private String errPassword;
    private String errPassword2;
    private String errEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escort_inscription);


        btn = findViewById(R.id.inscr_esc_btn);
        emailInput = findViewById(R.id.inscr_esc_emailInput);
        pseudo = findViewById(R.id.inscr_esc_pseudoInput);
        passwd = findViewById(R.id.inscr_esc_passwdInput);
        passwd2 = findViewById(R.id.inscr_esc_passwdInput2);
        translatePage();


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkInputs()){

                    /*
                        Building escort, mtn que les param ont été checkés.
                     */




                    /*
                    Passing data thru the activity
                     */
                    Intent defaultInscription = new Intent(getApplicationContext(), EscortInscription2Activity.class);
                    defaultInscription.putExtra("escort", escort);
                    startActivity(defaultInscription);

                }
            }
        });


    }

    private boolean checkInputs(){
        String email = emailInput.getText().toString();
        String pass = passwd.getText().toString();
        String pass2 = passwd2.getText().toString();




        String type = "register";

        if(email.length()==0 || pass.length()==0 || pass2.length()==0){
            Toast.makeText(getApplicationContext(),errEmpty, Toast.LENGTH_SHORT).show();
            return false;
        } else {
            if(!InscriptionActivity.isValidEmail(email)){
                Toast.makeText(getApplicationContext(),errEmail, Toast.LENGTH_SHORT).show();
                return false;
            } else if (passwd.length()<6){
                Toast.makeText(getApplicationContext(),errPassword, Toast.LENGTH_SHORT).show();
                return false;
            } else {
                if (passwd.equals(passwd2))
                {
                   return true;
                }
                else {
                    Toast.makeText(getApplicationContext(), passwd + " : " + passwd2, Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
        }
    }

    private void translatePage(){
        String langPrefKey = getString(R.string.SP_langPref_Key);

        SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        String langPref = mPreferences.getString(langPrefKey,"");

        TextView pb_info = findViewById(R.id.inscr_esc_progressInfo);
        TextView pb_contact = findViewById(R.id.inscr_esc_progressContact);
        TextView pb_photos = findViewById(R.id.inscr_esc_progressPhotos);

        EditText pseudo = findViewById(R.id.inscr_esc_pseudoInput);
        EditText passwd = findViewById(R.id.inscr_esc_passwdInput);
        EditText passwd2 = findViewById(R.id.inscr_esc_passwdInput2);

        pb_info.setText(getStringFromResourcesByName("inscr_esc_pb_"+langPref+"_info"));
        pb_contact.setText(getStringFromResourcesByName("inscr_esc_pb_"+langPref+"_contact"));
        pb_photos.setText(getStringFromResourcesByName("inscr_esc_pb_"+langPref+"_photos"));

        // -----

        pseudo.setHint(getStringFromResourcesByName("inscr_esc_"+langPref+"_pseudo"));
        passwd.setHint(getStringFromResourcesByName("inscription_"+langPref+"_passwd"));
        passwd2.setHint(getStringFromResourcesByName("inscription_"+langPref+"_passwd2"));

        btn.setText(getStringFromResourcesByName("inscr_esc_"+langPref+"_suivant"));

        errEmpty = getStringFromResourcesByName("inscription_"+langPref+"_err_empty");
        errEmail = getStringFromResourcesByName("inscription_"+langPref+"_err_email");
        errPassword = getStringFromResourcesByName("inscription_"+langPref+"_err_passwd");
        errPassword2 = getStringFromResourcesByName("inscription_"+langPref+"_err_passwd2");
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
