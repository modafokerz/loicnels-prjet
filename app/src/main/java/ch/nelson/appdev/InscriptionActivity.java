package ch.nelson.appdev;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
         * Va chercher les boutons de la fênetre pour le choix Escort ou Client
         */
        Button escortChoice = findViewById(R.id.inscription_escort_btn);
        Button clientChoice = findViewById(R.id.inscription_client_btn);

        /**
         * Ajoute l'action au bouton escort et le renvoie vers l'activité
         */
        escortChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        /**
         * Ajoute l'action au bouton client et le renvoie vers l'activité correspondante.
         */

    }
}
