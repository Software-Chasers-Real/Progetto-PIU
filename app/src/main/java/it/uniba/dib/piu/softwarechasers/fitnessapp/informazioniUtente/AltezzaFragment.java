package it.uniba.dib.piu.softwarechasers.fitnessapp.informazioniUtente;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import it.uniba.dib.piu.softwarechasers.fitnessapp.MainActivity;
import it.uniba.dib.piu.softwarechasers.fitnessapp.R;
import it.uniba.dib.piu.softwarechasers.fitnessapp.model.Utente;

public class AltezzaFragment extends Fragment {
    private InfromazioniUtenteActivity mActivity;
    private Utente newUtente;

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
        View view = inflater.inflate(R.layout.fragment_altezza, container, false);

        // Recupera l'oggetto dal Bundle
        Bundle bundle = getArguments();
        if (bundle != null) {
            newUtente = (Utente) bundle.getParcelable("utente");
            Log.d("AltezzaFragment", "Utente con email: " + newUtente.getEmail() + " e genere: " + newUtente.getGenere()
                    + " e età: " + newUtente.getEta()+ " e peso: " + newUtente.getPeso());
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
        // Imposta il colore del divider
        changeDividerColor(altezzaPicker, getResources().getColor(R.color.lime_A200));

        btnSuccessivo.setOnClickListener(v -> {
            newUtente.setAltezza(altezzaPicker.getValue());

            //aggiungi utente al db
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            Map<String , Object> nuovoUtente = new HashMap<>();
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

                            //partel'activity principale
                            new Intent(mActivity, MainActivity.class);
                            startActivity(new Intent(mActivity, MainActivity.class));
                            mActivity.finish();
                        }else{
                            Log.d("AltezzaFragment", "Errore nell'aggiunta dell'utente al db");
                        }
                    });
        });

        btnIndietro.setOnClickListener(v -> {

            Bundle bundle = new Bundle();
            bundle.putParcelable("utente", newUtente);
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

    private void changeDividerColor(NumberPicker numberPicker, int colorResource) {
        try {
            // Ottieni la risorsa dei divider
            Field[] fields = NumberPicker.class.getDeclaredFields();
            for (Field field : fields) {
                if (field.getName().equals("mSelectionDivider")) {
                    field.setAccessible(true);
                    // Modifica il colore del divider
                    field.set(numberPicker, getResources().getDrawable(colorResource));
                    break;
                }
            }
        } catch (IllegalAccessException | IllegalArgumentException e) {
            return;
        }
    }
}
