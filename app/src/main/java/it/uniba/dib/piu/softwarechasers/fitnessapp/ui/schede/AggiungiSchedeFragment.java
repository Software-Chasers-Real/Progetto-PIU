package it.uniba.dib.piu.softwarechasers.fitnessapp.ui.schede;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
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
import it.uniba.dib.piu.softwarechasers.fitnessapp.model.Scheda;

public class AggiungiSchedeFragment extends Fragment {
    private MainActivity mainActivityGenitore;
    private List<Scheda> schede;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainActivityGenitore = (MainActivity) getActivity();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        schede = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_aggiungi_schede_utente, container, false);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("schede")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for(QueryDocumentSnapshot document: task.getResult()){
                            Map<String, Object> scheda = document.getData();
                            FirebaseStorage storage = FirebaseStorage.getInstance();
                            StorageReference storageRef = storage.getReference(scheda.get("image_sfondo").toString());
                            Log.d("SchedeAdapter", "URL immagine: " + storageRef.getDownloadUrl().toString());
                            File localFile = null;
                            try {
                                localFile = File.createTempFile("images", ".jpg");

                                File finalLocalFile = localFile;
                                storageRef.getFile(localFile).addOnSuccessListener(taskSnapshot -> {
                                    // Immagine scaricata
                                    Log.d("SchedeAdapter", "Immagine scaricata");
                                    Bitmap myBitmap = BitmapFactory.decodeFile(finalLocalFile.getAbsolutePath());
                                    Drawable myDrawable = new BitmapDrawable(getResources(), myBitmap);
                                    /*schede.add(new Scheda(
                                            scheda.get("nome").toString(),
                                            Integer.valueOf(scheda.get("tempo").toString()),
                                            Integer.valueOf(scheda.get("calorie").toString()),
                                            scheda.get("descrizione").toString(),
                                            (List<Map<Esercizio, String>>) scheda.get("esercizi"),
                                            myDrawable
                                    ));*/

                                    RecyclerView recyclerView = view.findViewById(R.id.schede_disponibili_recyclerView);
                                    recyclerView.setLayoutManager(new LinearLayoutManager(mainActivityGenitore.getApplicationContext()));
                                    recyclerView.setAdapter(new SchedeAdapter(mainActivityGenitore.getApplicationContext(), schede));
                                }).addOnFailureListener(exception -> {
                                    // Immagine non scaricata
                                    Log.d("SchedeAdapter", "Immagine non scaricata");
                                });
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }

                    } else {
                        Log.d("AggiungiSchedeFragment", "Error getting documents: ", task.getException());
                    }
                });

        return view;
    }
}
