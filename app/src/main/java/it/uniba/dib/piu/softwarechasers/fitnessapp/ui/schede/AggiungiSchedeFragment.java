package it.uniba.dib.piu.softwarechasers.fitnessapp.ui.schede;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import it.uniba.dib.piu.softwarechasers.fitnessapp.MainActivity;
import it.uniba.dib.piu.softwarechasers.fitnessapp.R;
import it.uniba.dib.piu.softwarechasers.fitnessapp.model.Esercizio;
import it.uniba.dib.piu.softwarechasers.fitnessapp.model.EsercizioSchede;
import it.uniba.dib.piu.softwarechasers.fitnessapp.model.Scheda;

public class AggiungiSchedeFragment extends Fragment implements SchedeListener {
    private MainActivity mActvity;
    static View VIEW;
    static final int FECTH_TERMINATO = 1;
    static int NUMERO_SCHEDE;
    static int NUMERO_IMMGINI_SCARICATE = 0;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case FECTH_TERMINATO:
                    visualizzaSchede();
                    break;
            }
        }
    };

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActvity = (MainActivity) getActivity();
        NUMERO_SCHEDE = mActvity.schede.size();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_aggiungi_schede_utente, container, false);
        VIEW = view;
        FirebaseStorage storage = FirebaseStorage.getInstance();
        NUMERO_IMMGINI_SCARICATE = 0;
        for (Scheda scheda : mActvity.schede) {
            if (scheda.getImmagineScheda() != null) {
                NUMERO_IMMGINI_SCARICATE++;
                if (NUMERO_IMMGINI_SCARICATE == NUMERO_SCHEDE) {
                    mHandler.sendEmptyMessage(FECTH_TERMINATO);
                }
            } else {
                StorageReference storageRef = storage.getReference(scheda.getRiferimentoImmagineScheda());

                File localFile = null;
                try {
                    localFile = File.createTempFile("images", ".jpg");

                    File finalLocalFile = localFile;
                    storageRef.getFile(localFile).addOnSuccessListener(taskSnapshot -> {
                        // Immagine scaricata
                        Log.d("SchedeAdapter", "Immagine scaricata par o pesc");
                        Bitmap myBitmap = BitmapFactory.decodeFile(finalLocalFile.getAbsolutePath());
                        Drawable myDrawable = new BitmapDrawable(getResources(), myBitmap);
                        scheda.setImmagineScheda(myDrawable);
                        NUMERO_IMMGINI_SCARICATE++;
                        if (NUMERO_IMMGINI_SCARICATE == NUMERO_SCHEDE) {
                            mHandler.sendEmptyMessage(FECTH_TERMINATO);
                        }
                    }).addOnFailureListener(exception -> {
                        // Immagine non scaricata
                        Log.d("SchedeAdapter", "Immagine non scaricata par a uallr");
                        NUMERO_IMMGINI_SCARICATE++;
                    });
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

            return view;
        }

        private void visualizzaSchede () {
            RecyclerView recyclerView = VIEW.findViewById(R.id.schede_disponibili_recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(mActvity.getApplicationContext()));
            recyclerView.setAdapter(new SchedeAdapter(mActvity.getApplicationContext(), mActvity.schede, AggiungiSchedeFragment.this));
        }

        // Metodo chiamato quando un elemento della RecyclerView viene cliccato
        @Override
        public void onItemClick ( int position){
            Bundle bundle = new Bundle();
            bundle.putParcelable("scheda", mActvity.schede.get(position));
            Log.d("HomeFragmentLogopedista", "Paziente passato: " + mActvity.schede.get(position).getNome());
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
            navController.navigate(R.id.navigation_dettaglio_schede, bundle);
        }
    }
