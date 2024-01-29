package it.uniba.dib.piu.softwarechasers.fitnessapp.informazioniUtente;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import it.uniba.dib.piu.softwarechasers.fitnessapp.R;
import it.uniba.dib.piu.softwarechasers.fitnessapp.model.Utente;

public class InfromazioniUtenteActivity extends AppCompatActivity {
    private Utente newUtente;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informazioni_utente);
        auth = FirebaseAuth.getInstance();
        newUtente = new Utente();
        newUtente.setEmail(auth.getCurrentUser().getEmail());

        Bundle bundle = new Bundle();
        bundle.putParcelable("utente", newUtente);
        GenereFragment genereFragment = new GenereFragment();
        genereFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.fragment_informativa_utente, genereFragment, null)
                .commit();
    }
}
