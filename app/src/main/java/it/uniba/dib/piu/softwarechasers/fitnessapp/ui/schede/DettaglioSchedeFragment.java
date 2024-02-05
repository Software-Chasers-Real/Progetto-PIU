package it.uniba.dib.piu.softwarechasers.fitnessapp.ui.schede;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

import it.uniba.dib.piu.softwarechasers.fitnessapp.MainActivity;
import it.uniba.dib.piu.softwarechasers.fitnessapp.R;
import it.uniba.dib.piu.softwarechasers.fitnessapp.model.EsercizioSchede;
import it.uniba.dib.piu.softwarechasers.fitnessapp.model.Scheda;

public class DettaglioSchedeFragment extends Fragment implements EserciziListener {
    private MainActivity mActvity;
    private Scheda schedaSelezionata;
    private static int NUMERO_ESERCIZI_SCHEDA = 0;

    private static int NUMERO_IMMAGINI_SCARICATE = 0;
    private  static final int FECTH_TERMINATO_IMMAGINI = 1;
    private boolean bottoneVisibile = true;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case FECTH_TERMINATO_IMMAGINI:
                    visualizzaEsercizi();
                    break;
            }
        }
    };

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActvity = (MainActivity) getActivity();

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            schedaSelezionata = bundle.getParcelable("scheda");
            Log.d("SCHEDA", schedaSelezionata.getNome());
            NUMERO_ESERCIZI_SCHEDA = schedaSelezionata.getEsercizi().size();
            if(bundle.containsKey("bottone")){
                if(bundle.getBoolean("bottone") == false){
                    bottoneVisibile = false;
                }
            }
        }
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
        View view = inflater.inflate(R.layout.fragment_dettaglio_scheda, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView sfondo = view.findViewById(R.id.sfondo_dettaglio_scheda);
        TextView txtNomeScheda = view.findViewById(R.id.nome_scheda_dettaglio);
        Button btnTempo = view.findViewById(R.id.tempo_dettaglio_bottone);
        Button btnCalorie = view.findViewById(R.id.calorie_dettaglio_bottone);
        TextView txtDescrizione = view.findViewById(R.id.descr_dettaglio_scheda);
        ExtendedFloatingActionButton fabAggiungiScheda = view.findViewById(R.id.fab_aggiungi_scheda);
        ExtendedFloatingActionButton fabEliminaScheda = view.findViewById(R.id.fab_rimuovi_scheda);
        if(bottoneVisibile == false){
            fabAggiungiScheda.setVisibility(View.GONE);
            fabEliminaScheda.setVisibility(View.VISIBLE);
        }else{
            fabAggiungiScheda.setOnClickListener(v -> {
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                if(mActvity.utente.getIdSchede().isEmpty()){
                    mActvity.utente.getIdSchede().add(schedaSelezionata.getIdDatabase());
                }else if(mActvity.utente.getIdSchede().contains(schedaSelezionata.getIdDatabase()) == false){
                    mActvity.utente.getIdSchede().add(schedaSelezionata.getIdDatabase());
                }else{
                    Toast.makeText(mActvity.getApplicationContext(), "Scheda già presente", Toast.LENGTH_SHORT).show();
                    return;
                }
                db.collection("utenti")
                        .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .update("schede", mActvity.utente.getIdSchede()).addOnSuccessListener(aVoid -> {
                                    Log.d("DettaglioSchedaFragment", "Scheda aggiunta");
                                    Toast.makeText(mActvity.getApplicationContext(), "Scheda aggiunta con successo", Toast.LENGTH_SHORT).show();
                                    NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
                                    navController.navigate(R.id.navigation_schede);
                                }
                        ).addOnFailureListener(e -> {
                            Log.d("DettaglioSchedaFragment", "Errore aggiunta scheda");
                            Toast.makeText(mActvity.getApplicationContext(), "Errore aggiunta scheda", Toast.LENGTH_SHORT).show();
                        });
            });
        }

        fabEliminaScheda.setOnClickListener(v -> {
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            if(mActvity.utente.getIdSchede().size() == 1){
                mActvity.utente.getIdSchede().clear();
            }else{
                mActvity.utente.getIdSchede().remove(schedaSelezionata.getIdDatabase());
            }
            db.collection("utenti")
                    .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .update("schede", mActvity.utente.getIdSchede()).addOnSuccessListener(aVoid -> {
                        Log.d("DettaglioSchedaFragment", "Scheda rimossa");
                        Toast.makeText(mActvity.getApplicationContext(), "Scheda rimossa con successo", Toast.LENGTH_SHORT).show();
                        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
                        navController.navigate(R.id.navigation_schede);
                    }
            ).addOnFailureListener(e -> {
                Log.d("DettaglioSchedaFragment", "Errore rimozione scheda");
                Toast.makeText(mActvity.getApplicationContext(), "Errore rimozione scheda", Toast.LENGTH_SHORT).show();
            });
        });


        sfondo.setImageDrawable(schedaSelezionata.getImmagineScheda());
        txtNomeScheda.setText(schedaSelezionata.getNome());
        btnTempo.setText(String.valueOf(schedaSelezionata.getTempo())+" min");
        btnCalorie.setText(String.valueOf(schedaSelezionata.getCalorie())+" kcal");
        txtDescrizione.setText(schedaSelezionata.getDescrizione());

        recuperaImmaginiEsercizi();
    }

    private void recuperaImmaginiEsercizi(){
        NUMERO_IMMAGINI_SCARICATE = 0;
        for(EsercizioSchede esercizio : schedaSelezionata.getEsercizi()){
            if(esercizio.getImmagine() == null && esercizio.getRiferimentoImmagine() != null){
                Log.d("DettaglioSchedaFragment", "Scarico immagine");
                StorageReference storageRef = FirebaseStorage.getInstance().getReference();
                StorageReference pathReference = storageRef.child(esercizio.getRiferimentoImmagine());
                try {
                    final File localFile = File.createTempFile("images", ".jpg");
                    pathReference.getFile(localFile).addOnSuccessListener(taskSnapshot -> {
                        Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                        Drawable d = new BitmapDrawable(getResources(), bitmap);
                        esercizio.setImmagine(d);
                        NUMERO_IMMAGINI_SCARICATE++;
                        if(NUMERO_IMMAGINI_SCARICATE == NUMERO_ESERCIZI_SCHEDA){
                            mHandler.sendEmptyMessage(FECTH_TERMINATO_IMMAGINI);
                        }
                    }).addOnFailureListener(exception -> {
                        // Handle any errors
                        NUMERO_IMMAGINI_SCARICATE++;
                        if(NUMERO_IMMAGINI_SCARICATE == NUMERO_ESERCIZI_SCHEDA){
                            mHandler.sendEmptyMessage(FECTH_TERMINATO_IMMAGINI);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                Log.d("DettaglioSchedaFragment", "Immagine già scaricata");
                NUMERO_IMMAGINI_SCARICATE++;
                if(NUMERO_IMMAGINI_SCARICATE == NUMERO_ESERCIZI_SCHEDA){
                    mHandler.sendEmptyMessage(FECTH_TERMINATO_IMMAGINI);
                }
            }
        }
    }

    private void visualizzaEsercizi() {
        //ottieni il riferimento alla recycler view
        RecyclerView recyclerView = getView().findViewById(R.id.recycler_dettaglio_scheda_esercizi);
        recyclerView.setLayoutManager(new LinearLayoutManager(mActvity.getApplicationContext()));
        recyclerView.setAdapter(new EserciziAdapter(mActvity.getApplicationContext(), schedaSelezionata.getEsercizi(), DettaglioSchedeFragment.this));
        Log.d("DettaglioSchedaFragment", "EserciziVisualizzati: "+schedaSelezionata.getEsercizi().size());
    }

    @Override
    public void onItemClick(int position) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("esercizio", schedaSelezionata.getEsercizi().get(position));
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        navController.navigate(R.id.navigation_dettaglio_esercizio, bundle);
    }
}
