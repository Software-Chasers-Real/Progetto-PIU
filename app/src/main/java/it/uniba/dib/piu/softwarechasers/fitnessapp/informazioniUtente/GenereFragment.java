package it.uniba.dib.piu.softwarechasers.fitnessapp.informazioniUtente;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import it.uniba.dib.piu.softwarechasers.fitnessapp.R;
import it.uniba.dib.piu.softwarechasers.fitnessapp.model.Scheda;
import it.uniba.dib.piu.softwarechasers.fitnessapp.model.Utente;

public class GenereFragment extends Fragment {

    private InfromazioniUtenteActivity mActivity;
    private Utente newUtente;
    private ArrayList<Scheda> schede;
    private Boolean uomo = false;
    private Boolean donna = false;

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
        View view = inflater.inflate(R.layout.fragment_genere, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Button btnUomo = view.findViewById(R.id.btn_uomo);
        Button btnDonna = view.findViewById(R.id.btn_donna);
        TextView txtErrGenere = view.findViewById(R.id.txt_err_genere);
        Button btnSuccessivo = view.findViewById(R.id.btn_successivo_genere);

        btnUomo.setOnClickListener(v -> {
            if(!uomo) {
                btnUomo.setTextColor(getResources().getColor(R.color.black));
                btnUomo.setCompoundDrawableTintList(getResources().getColorStateList(R.color.black));
                btnUomo.setBackgroundTintList(getResources().getColorStateList(R.color.lime_A200));
                uomo = true;
                donna = false;
                btnDonna.setTextColor(getResources().getColor(R.color.white));
                btnDonna.setCompoundDrawableTintList(getResources().getColorStateList(R.color.white));
                btnDonna.setBackgroundTintList(getResources().getColorStateList(R.color.colorBorderBottomNav));
                txtErrGenere.setVisibility(View.INVISIBLE);
            }
        });

        btnDonna.setOnClickListener(v -> {
            if(!donna) {
                btnDonna.setTextColor(getResources().getColor(R.color.black));
                btnDonna.setCompoundDrawableTintList(getResources().getColorStateList(R.color.black));
                btnDonna.setBackgroundTintList(getResources().getColorStateList(R.color.lime_A200));
                donna = true;
                uomo = false;
                btnUomo.setTextColor(getResources().getColor(R.color.white));
                btnUomo.setCompoundDrawableTintList(getResources().getColorStateList(R.color.white));
                btnUomo.setBackgroundTintList(getResources().getColorStateList(R.color.colorBorderBottomNav));
                txtErrGenere.setVisibility(View.INVISIBLE);
            }
        });

        btnSuccessivo.setOnClickListener(v -> {
            if(uomo || donna) {
                if (uomo) {
                    newUtente.setGenere("uomo");
                } else {
                    newUtente.setGenere("donna");
                }
                Log.d("GenereFragment", "Utente email: " + newUtente.getEmail() + " genere: " + newUtente.getGenere());
                Bundle bundle = new Bundle();
                bundle.putParcelable("utente", newUtente);
                bundle.putParcelableArrayList("schede", schede);
                EtaFragment etaFragment = new EtaFragment();
                etaFragment.setArguments(bundle);

                mActivity.getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragment_informativa_utente, etaFragment, null)
                        .addToBackStack(null)
                        .commit();
            }else{
                txtErrGenere.setVisibility(View.VISIBLE);
            }
        });

        Button btnIndietro = view.findViewById(R.id.btn_indietro_genere);
        btnIndietro.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putParcelable("utente", newUtente);
            bundle.putParcelableArrayList("schede", schede);
            NomeCognomeFragment nomeCognomeFragment = new NomeCognomeFragment();
            nomeCognomeFragment.setArguments(bundle);

            mActivity.getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragment_informativa_utente, nomeCognomeFragment, null)
                    .addToBackStack(null)
                    .commit();
        });
    }
}
