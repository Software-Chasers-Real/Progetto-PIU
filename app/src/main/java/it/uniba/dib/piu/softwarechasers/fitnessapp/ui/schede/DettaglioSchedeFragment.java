package it.uniba.dib.piu.softwarechasers.fitnessapp.ui.schede;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
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

    }
}
