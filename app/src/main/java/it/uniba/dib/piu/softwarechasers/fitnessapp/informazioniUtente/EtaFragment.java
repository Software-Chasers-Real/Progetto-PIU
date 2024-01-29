package it.uniba.dib.piu.softwarechasers.fitnessapp.informazioniUtente;

import android.annotation.SuppressLint;
import android.content.Context;
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
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.lang.reflect.Field;

import it.uniba.dib.piu.softwarechasers.fitnessapp.R;
import it.uniba.dib.piu.softwarechasers.fitnessapp.model.Utente;

public class EtaFragment extends Fragment {
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
        View view = inflater.inflate(R.layout.fragment_eta, container, false);

        // Recupera l'oggetto dal Bundle
        Bundle bundle = getArguments();
        if (bundle != null) {
            newUtente = (Utente) bundle.getParcelable("utente");
            Log.d("EtaFragment", "Utente con email: " + newUtente.getEmail() + " e genere: " + newUtente.getGenere());
        }

        return view;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        NumberPicker etaPicker = view.findViewById(R.id.eta_numberPicker);
        Button btnSuccessivo = view.findViewById(R.id.btn_successivo_eta);
        Button btnIndietro = view.findViewById(R.id.btn_indietro_eta);

        etaPicker.setMinValue(16);
        etaPicker.setMaxValue(90);
        etaPicker.setValue(16);

        // Imposta il colore del testo
        changeNumberPickerTextColor(etaPicker, Color.WHITE);
        // Imposta il colore del divider
        changeDividerColor(etaPicker, getResources().getColor(R.color.lime_A200));

        btnSuccessivo.setOnClickListener(v -> {
            newUtente.setEta(etaPicker.getValue());

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

        btnIndietro.setOnClickListener(v -> {

            Bundle bundle = new Bundle();
            bundle.putParcelable("utente", newUtente);
            GenereFragment genereFragment = new GenereFragment();
            genereFragment.setArguments(bundle);

            mActivity.getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragment_informativa_utente, genereFragment, null)
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
