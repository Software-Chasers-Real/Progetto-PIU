package it.uniba.dib.piu.softwarechasers.fitnessapp.informazioniUtente;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import it.uniba.dib.piu.softwarechasers.fitnessapp.R;
import it.uniba.dib.piu.softwarechasers.fitnessapp.model.Scheda;
import it.uniba.dib.piu.softwarechasers.fitnessapp.model.Utente;

public class InfromazioniUtenteActivity extends AppCompatActivity {
    private Utente newUtente;
    private FirebaseAuth auth;
    private ArrayList<Scheda> schede;
    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informazioni_utente);
        auth = FirebaseAuth.getInstance();
        newUtente = new Utente();
        newUtente.setEmail(auth.getCurrentUser().getEmail());

        Bundle ricevutoBundle = getIntent().getExtras();
        if (ricevutoBundle != null) {
            if(ricevutoBundle.containsKey("schede")) {
                schede = ricevutoBundle.getParcelableArrayList("schede");
                Log.d("InformazioniUtenteActivity", "schede: " + schede.size() );
            }
        }else{
            Log.d("MainActivity", "Bundle non ricevuto");
        }

        Bundle bundle = new Bundle();
        bundle.putParcelable("utente", newUtente);
        bundle.putParcelableArrayList("schede", schede);
        NomeCognomeFragment nomeCognomeFragment = new NomeCognomeFragment();
        nomeCognomeFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.fragment_informativa_utente, nomeCognomeFragment, null)
                .commit();
    }
}
