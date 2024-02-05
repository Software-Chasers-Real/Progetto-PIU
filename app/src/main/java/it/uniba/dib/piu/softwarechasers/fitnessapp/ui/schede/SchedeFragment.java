package it.uniba.dib.piu.softwarechasers.fitnessapp.ui.schede;

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

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import it.uniba.dib.piu.softwarechasers.fitnessapp.MainActivity;
import it.uniba.dib.piu.softwarechasers.fitnessapp.R;
import it.uniba.dib.piu.softwarechasers.fitnessapp.databinding.FragmentSchedeBinding;
import it.uniba.dib.piu.softwarechasers.fitnessapp.model.EsercizioSchede;
import it.uniba.dib.piu.softwarechasers.fitnessapp.model.Scheda;

public class SchedeFragment extends Fragment implements SchedeListener {

    private MainActivity mActvity;
    private FragmentSchedeBinding binding;
    private ArrayList<Scheda> schedeUtente;
    private static int NUMERO_SCHEDE = 0;
    private static int NUMERO_IMMAGINI_SCARICATE = 0;
    private  static final int FECTH_TERMINATO = 1;
    private boolean bottoneVisibile = true;

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

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

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
        SchedeViewModel homeViewModel =
                new ViewModelProvider(this).get(SchedeViewModel.class);

        binding = FragmentSchedeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        mActvity = (MainActivity) getActivity();
        NUMERO_SCHEDE = mActvity.utente.getIdSchede().size();

        return root;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button btnAggiungi = view.findViewById(R.id.btn_aggiungi_schede);

        btnAggiungi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
                navController.navigate(R.id.navigation_aggiungi_schede);
            }
        });

        recuperaImmaginiSchede();
    }

    private void recuperaImmaginiSchede(){
        FirebaseStorage storage = FirebaseStorage.getInstance();
        NUMERO_IMMAGINI_SCARICATE = 0;
        schedeUtente = new ArrayList<>();
        for (Scheda scheda : mActvity.schede) {
            if(mActvity.utente.getIdSchede().contains(scheda.getIdDatabase())){
                schedeUtente.add(scheda);
                if (scheda.getImmagineScheda() != null) {
                    NUMERO_IMMAGINI_SCARICATE++;
                    if (NUMERO_IMMAGINI_SCARICATE == NUMERO_SCHEDE) {
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
                            NUMERO_IMMAGINI_SCARICATE++;
                            if (NUMERO_IMMAGINI_SCARICATE == NUMERO_SCHEDE) {
                                mHandler.sendEmptyMessage(FECTH_TERMINATO);
                            }
                        }).addOnFailureListener(exception -> {
                            // Immagine non scaricata
                            Log.d("SchedeAdapter", "Immagine non scaricata par a uallr");
                            NUMERO_IMMAGINI_SCARICATE++;
                            if (NUMERO_IMMAGINI_SCARICATE == NUMERO_SCHEDE) {
                                mHandler.sendEmptyMessage(FECTH_TERMINATO);
                            }
                        });

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            }
    }

    private void visualizzaSchede() {
        //ottieni il riferimento alla recycler view
        RecyclerView recyclerView = getView().findViewById(R.id.schede_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(mActvity.getApplicationContext()));
        recyclerView.setAdapter(new SchedeAdapter(mActvity.getApplicationContext(), schedeUtente, SchedeFragment.this));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onItemClick(int position) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("bottone", false);
        bundle.putParcelable("scheda", schedeUtente.get(position));
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        navController.navigate(R.id.navigation_dettaglio_schede, bundle);
    }
}