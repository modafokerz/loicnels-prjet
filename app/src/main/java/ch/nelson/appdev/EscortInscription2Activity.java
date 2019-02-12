package ch.nelson.appdev;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class EscortInscription2Activity extends AppCompatActivity {

    private Spinner spinner;
    private Spinner spinnerTaille;
    private Spinner spinnerCheveux;

    private Escort escort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escort_inscription2);

        escort = (Escort) getIntent().getSerializableExtra("escort");

        // Remplissage du premier spinner : origine.
        spinner = (Spinner) findViewById(R.id.inscr_escort2_spinner_categorie);
        spinnerTaille = (Spinner) findViewById(R.id.inscr_escort2_spinner_taille);
        spinnerCheveux = (Spinner) findViewById(R.id.inscr_escort2_spinner_cheveux);

        List exempleList = new ArrayList();
        exempleList.add("Européenne");
        exempleList.add("Latine");
        exempleList.add("Asiatique");
        exempleList.add("Africaine");
        exempleList.add("Europe de l'Est");
        exempleList.add("Suisse");
        exempleList.add("Métisse");
        exempleList.add("Orientale");

		/*Le Spinner a besoin d'un adapter pour sa presentation alors on lui passe le context(this) et
                un fichier de presentation par défaut( android.R.layout.simple_spinner_item)
		Avec la liste des elements (exemple) */
        ArrayAdapter adapter = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                exempleList
        );

        List exempleList2 = new ArrayList();
        exempleList2.add("Naine");
        exempleList2.add("150-155");
        exempleList2.add("156-160");
        exempleList2.add("161-165");
        exempleList2.add("166-170");
        exempleList2.add("171-175");
        exempleList2.add("176-180");
        exempleList2.add("181 et +");

        ArrayAdapter adapter2 = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                exempleList2
        );


        List exempleList3 = new ArrayList();
        exempleList3.add("Blonde");
        exempleList3.add("Châtain");
        exempleList3.add("Brune");
        exempleList3.add("Gris");
        exempleList3.add("Rouge/Rousse");

        ArrayAdapter adapter3 = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                exempleList3
        );

        /* On definit une présentation du spinner quand il est déroulé         (android.R.layout.simple_spinner_dropdown_item) */
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Enfin on passe l'adapter au Spinner et c'est tout
        spinner.setAdapter(adapter);
        spinner.setPrompt("Choisissez votre origine");
        spinnerTaille.setAdapter(adapter2);
        spinnerTaille.setPrompt("Choisissez votre taille");
        spinnerCheveux.setAdapter(adapter3);
        spinnerCheveux.setPrompt("Votre couleur de cheveux");





    }
}
