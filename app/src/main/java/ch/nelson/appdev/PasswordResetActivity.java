package ch.nelson.appdev;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PasswordResetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_reset);

        /**
         * prend le bouton et le texte de l'email de l'activité
         */

        Button resetBtn = findViewById(R.id.passwdReset_btn);
        final EditText emailInput = findViewById(R.id.passwdReset_EmailInput);


        /**
         * Ajoute l'action au bouton
         */
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailStr = emailInput.getText().toString();

                if(emailStr.equals("test1")){
                    /**
                     * TODO send email to the user and request the database
                     */
                    Toast.makeText(getApplicationContext(),"L'email vous a bien été envoyé", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),"Cet email n'existe pas", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
