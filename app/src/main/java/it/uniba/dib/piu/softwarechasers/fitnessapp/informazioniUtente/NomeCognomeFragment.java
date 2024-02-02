package it.uniba.dib.piu.softwarechasers.fitnessapp.informazioniUtente;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import it.uniba.dib.piu.softwarechasers.fitnessapp.R;
import it.uniba.dib.piu.softwarechasers.fitnessapp.model.Scheda;
import it.uniba.dib.piu.softwarechasers.fitnessapp.model.Utente;

public class NomeCognomeFragment extends Fragment {
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

        // Recupera l'oggetto dal Bundle
        Bundle bundle = getArguments();
        if (bundle != null) {
            newUtente = (Utente) bundle.getParcelable("utente");
            Log.d("GenereFragment", "Utente con email: " + newUtente.getEmail());
            if(bundle.containsKey("schede")) {
                Log.d("MainActivity", "Bundle ricevuto");
                schede = bundle.getParcelableArrayList("schede");
            }
        }else{
            Log.d("GenereFragment", "Bundle null");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nome_cognome, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        EditText nome = view.findViewById(R.id.edit_signup_nome);
        TextView txtNome = view.findViewById(R.id.label_signup_nome);
        nome.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                // L'EditText ha ottenuto il focus
                nome.setHint("");
                txtNome.setTextColor(getResources().getColor(R.color.lime_A200));
                txtNome.setText(R.string.lbl_nome);
                txtNome.setVisibility(View.VISIBLE);
            } else {
                // L'EditText ha perso il focus
                if(nome.getText().toString().isEmpty()) {
                    nome.setHint(R.string.lbl_nome);
                    txtNome.setVisibility(View.INVISIBLE);
                }
            }
        });

        EditText cognome = view.findViewById(R.id.edit_signup_cognome);
        TextView txtCognome = view.findViewById(R.id.label_signup_cognome);
        cognome.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                // L'EditText ha ottenuto il focus
                cognome.setHint("");
                txtCognome.setTextColor(getResources().getColor(R.color.lime_A200));
                txtCognome.setText(R.string.lbl_cognome);
                txtCognome.setVisibility(View.VISIBLE);
            } else {
                // L'EditText ha perso il focus
                if(cognome.getText().toString().isEmpty()) {
                    txtCognome.setHint(R.string.lbl_cognome);
                    txtCognome.setVisibility(View.INVISIBLE);
                }
            }
        });

        Button btnSuccessivo = view.findViewById(R.id.btn_successivo_nome_cognome);

        btnSuccessivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean error = false;
                String nomeStr = nome.getText().toString();
                String cognomeStr = cognome.getText().toString();

                if (nomeStr.isEmpty()) {
                    txtNome.setText(R.string.inserisci_il_nome);
                    txtNome.setTextColor(getResources().getColor(R.color.red));
                    txtNome.setVisibility(View.VISIBLE);
                    error = true;
                }

                if (cognomeStr.isEmpty()) {
                    txtCognome.setText("Inserisci il cognome");
                    txtCognome.setTextColor(getResources().getColor(R.color.red));
                    txtCognome.setVisibility(View.VISIBLE);
                    error = true;
                }
                if (error == true) {
                    return;
                } else {
                    newUtente.setNome(nomeStr);
                    newUtente.setCognome(cognomeStr);

                    Bundle bundle = new Bundle();
                    bundle.putParcelable("utente", newUtente);
                    bundle.putParcelableArrayList("schede", schede);
                    GenereFragment genereFragment = new GenereFragment();
                    genereFragment.setArguments(bundle);

                    mActivity.getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.fragment_informativa_utente, genereFragment, null)
                            .addToBackStack(null)
                            .commit();
                }
            }
        });
    }
}
