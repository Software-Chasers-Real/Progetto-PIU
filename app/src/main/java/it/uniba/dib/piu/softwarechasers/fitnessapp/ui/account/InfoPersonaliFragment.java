package it.uniba.dib.piu.softwarechasers.fitnessapp.ui.account;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import it.uniba.dib.piu.softwarechasers.fitnessapp.R;
import it.uniba.dib.piu.softwarechasers.fitnessapp.databinding.FragmentInformazioniPersonaliBinding;

public class InfoPersonaliFragment extends Fragment {

    private FragmentInformazioniPersonaliBinding binding;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
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


        binding = FragmentInformazioniPersonaliBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Verifica se l'utente è autenticato
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            // retrieve utente
            db.collection("utenti")
                    .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            // DocumentSnapshot contiene i dati del documento
                            DocumentSnapshot document = task.getResult();
                            if (document != null && document.exists()) {
                                String email = document.getString("email");
                                int eta = document.getLong("eta").intValue();
                                int peso = document.getLong("peso").intValue();
                                String genere = document.getString("genere");

                                updateUI(email, eta, peso, genere);
                            } else {
                                handleDocumentNotExist();
                            }
                        } else {
                            handleDataRetrievalError(task.getException());
                        }
                    });
        }

        return root;
    }

    private void updateUI(String email, int eta, int peso, String genere) {
        getActivity().runOnUiThread(() -> {
            binding.email.setText(email);
            binding.eta.setText(String.valueOf(eta)); // Converti 'eta' in una stringa prima di impostarlo
            binding.peso.setText(String.valueOf(peso)); // Converti 'peso' in una stringa prima di impostarlo
            binding.genere.setText(genere);
        });
    }

    private void handleDocumentNotExist() {
        Log.d("InfoPersonaliFragment", "Document does not exist!");
    }

    private void handleDataRetrievalError(@Nullable Exception exception) {
        if (exception != null) {
            Log.d("InfoPersonaliFragment", "Document does not");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
