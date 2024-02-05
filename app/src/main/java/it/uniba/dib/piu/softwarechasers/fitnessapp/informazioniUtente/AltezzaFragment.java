package it.uniba.dib.piu.softwarechasers.fitnessapp.informazioniUtente;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import it.uniba.dib.piu.softwarechasers.fitnessapp.MainActivity;
import it.uniba.dib.piu.softwarechasers.fitnessapp.R;
import it.uniba.dib.piu.softwarechasers.fitnessapp.model.Scheda;
import it.uniba.dib.piu.softwarechasers.fitnessapp.model.Utente;

public class AltezzaFragment extends Fragment {
    private InfromazioniUtenteActivity mActivity;
    private Utente newUtente;
    private ArrayList<Scheda> schede;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (InfromazioniUtenteActivity) context;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Modifica il colore del testo del titolo nella barra dell'app
        if (getActivity() != null) {
            ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
            if (actionBar != null) {
                // Modifica il colore del testo del titolo
                Spannable text = new SpannableString(actionBar.getTitle());
                text.setSpan(new ForegroundColorSpan(Color.parseColor("#ffffff")), 0, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                actionBar.setTitle(text);

                // Modifica il colore della freccia di navigazione
                actionBar.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24); // Sostituisci con la tua icona personalizzata
                actionBar.setHomeActionContentDescription("Back");
                actionBar.setDisplayHomeAsUpEnabled(true);
            }
        }

        View view = inflater.inflate(R.layout.fragment_altezza, container, false);

        // Recupera l'oggetto dal Bundle
        Bundle bundle = getArguments();
        if (bundle != null) {
            newUtente = (Utente) bundle.getParcelable("utente");
            Log.d("AltezzaFragment", "Utente con email: " + newUtente.getEmail() + " e genere: " + newUtente.getGenere()
                    + " e età: " + newUtente.getEta()+ " e peso: " + newUtente.getPeso());
            if(bundle.containsKey("schede")) {
                Log.d("MainActivity", "Bundle ricevuto");
                schede = bundle.getParcelableArrayList("schede");
            }
        }

        return view;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        NumberPicker altezzaPicker = view.findViewById(R.id.altezza_numberPicker);
        Button btnSuccessivo = view.findViewById(R.id.btn_successivo_altezza);
        Button btnIndietro = view.findViewById(R.id.btn_indietro_altezza);

        altezzaPicker.setMinValue(120);
        altezzaPicker.setMaxValue(220);
        altezzaPicker.setValue(120);

        // Imposta il colore del testo
        changeNumberPickerTextColor(altezzaPicker, Color.WHITE);

        btnSuccessivo.setOnClickListener(v -> {
            newUtente.setAltezza(altezzaPicker.getValue());

            //aggiungi utente al db
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            Map<String , Object> nuovoUtente = new HashMap<>();
            nuovoUtente.put("nome", newUtente.getNome());
            nuovoUtente.put("cognome", newUtente.getCognome());
            nuovoUtente.put("email", newUtente.getEmail());
            nuovoUtente.put("genere", newUtente.getGenere());
            nuovoUtente.put("eta", newUtente.getEta());
            nuovoUtente.put("peso", newUtente.getPeso());
            nuovoUtente.put("altezza", newUtente.getAltezza());
            db.collection("utenti")
                    .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .set(nuovoUtente)
                    .addOnCompleteListener(task -> {
                        if(task.isSuccessful()){
                            Log.d("AltezzaFragment", "Utente aggiunto al db");
                            Bundle bundle = new Bundle();
                            bundle.putParcelable("utente", newUtente);
                            bundle.putParcelableArrayList("schede", schede);
                            Intent intent = new Intent(mActivity, MainActivity.class);
                            intent.putExtras(bundle);
                            startActivity(intent);
                            mActivity.finish();
                        }else{
                            Log.d("AltezzaFragment", "Errore nell'aggiunta dell'utente al db");
                        }
                    });
        });

        btnIndietro.setOnClickListener(v -> {

            Bundle bundle = new Bundle();
            bundle.putParcelable("utente", newUtente);
            bundle.putParcelableArrayList("schede", schede);
            PesoFragment pesoFragment = new PesoFragment();
            pesoFragment.setArguments(bundle);

            mActivity.getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragment_informativa_utente, pesoFragment, null)
                    .addToBackStack(null)
                    .commit();
        });

    }

    private void changeNumberPickerTextColor(NumberPicker numberPicker, int color) {
        final int count = numberPicker.getChildCount();
        for (int i = 0; i < count; i++) {
            View child = numberPicker.getChildAt(i);
            if (child instanceof EditText) {
                try {
                    @SuppressLint("SoonBlockedPrivateApi") Field selectorWheelPaintField = numberPicker.getClass().getDeclaredField("mSelectorWheelPaint");
                    selectorWheelPaintField.setAccessible(true);
                    ((Paint) selectorWheelPaintField.get(numberPicker)).setColor(color);
                    ((EditText) child).setTextColor(color);
                    numberPicker.invalidate();
                } catch (NoSuchFieldException | IllegalAccessException | IllegalArgumentException e) {
                    return;
                }
            }
        }
    }
}
