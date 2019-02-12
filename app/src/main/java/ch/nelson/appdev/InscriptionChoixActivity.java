package ch.nelson.appdev;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class InscriptionChoixActivity extends AppCompatActivity {

    private SharedPreferences mPreferences;
    private TextView topText;
    private TextView hommeText;
    private TextView escortText;
    private TextView salonText;

    private ImageView hommeImage;
    private ImageView escortImage;
    private ImageView salonImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription_choix);
        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        hommeImage = findViewById(R.id.inscr_choix_homme_img);
        escortImage = findViewById(R.id.inscr_choix_escort_img);
        salonImage = findViewById(R.id.inscr_choix_salon_img);

        topText = findViewById(R.id.inscr_choix_txt);
        hommeText = findViewById(R.id.inscr_choix_client);
        escortText = findViewById(R.id.inscr_choix_escort);
        salonText = findViewById(R.id.inscr_choix_salon);

        translatePage();
        initializeClickListeners();
    }

    private void translatePage() {

        String langPrefKey = getString(R.string.SP_langPref_Key);
        String langPref = mPreferences.getString(langPrefKey,"");


        topText.setText(getStringFromResourcesByName("inscr_choix_"+langPref+"_texte"));
        hommeText.setText(getStringFromResourcesByName("inscr_choix_"+langPref+"_homme"));
        escortText.setText(getStringFromResourcesByName("inscr_choix_"+langPref+"_escort"));
        salonText.setText(getStringFromResourcesByName("inscr_choix_"+langPref+"_salon"));

    }

    private String getStringFromResourcesByName(String resourceName){

        // Get the application package name
        String packageName = getPackageName();

        // Get the string resource id by name
        int resourceId = getResources().getIdentifier(resourceName,"string",packageName);

        // Return the string value
        return getString(resourceId);
    }

    private void initializeClickListeners(){


        hommeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent defaultInscription = new Intent(getApplicationContext(), InscriptionActivity.class);
                startActivity(defaultInscription);
            }
        });

        escortImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent defaultInscription = new Intent(getApplicationContext(), EscortInscriptionActivity.class);
                startActivity(defaultInscription);
            }
        });

        salonImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent defaultInscription = new Intent(getApplicationContext(), EscortInscriptionActivity.class);
                startActivity(defaultInscription);
            }
        });
    }
}
