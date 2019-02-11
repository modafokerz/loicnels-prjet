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

        switch(langPref){
            case "en":
                topText.setText(R.string.inscr_choix_en_texte);
                hommeText.setText(R.string.inscr_choix_en_homme);
                escortText.setText(R.string.inscr_choix_en_escort);
                salonText.setText(R.string.inscr_choix_en_salon);
                break;
            case "de":
                topText.setText(R.string.inscr_choix_de_texte);
                hommeText.setText(R.string.inscr_choix_de_homme);
                escortText.setText(R.string.inscr_choix_de_escort);
                salonText.setText(R.string.inscr_choix_de_salon);
                break;
            case "ita":
                topText.setText(R.string.inscr_choix_ita_texte);
                hommeText.setText(R.string.inscr_choix_ita_homme);
                escortText.setText(R.string.inscr_choix_ita_escort);
                salonText.setText(R.string.inscr_choix_ita_salon);
                break;
            default:
                topText.setText(R.string.inscr_choix_fr_texte);
                hommeText.setText(R.string.inscr_choix_fr_homme);
                escortText.setText(R.string.inscr_choix_fr_escort);
                salonText.setText(R.string.inscr_choix_fr_salon);
                break;
        }
    }

    private void initializeClickListeners(){


        hommeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent defaultInscription = new Intent(getApplicationContext(), InscriptionActivity.class);
                startActivity(defaultInscription);
            }
        });
    }
}
