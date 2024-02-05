package it.uniba.dib.piu.softwarechasers.fitnessapp.ui.home;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import it.uniba.dib.piu.softwarechasers.fitnessapp.MainActivity;
import it.uniba.dib.piu.softwarechasers.fitnessapp.R;
import it.uniba.dib.piu.softwarechasers.fitnessapp.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private MainActivity mActivity;
    private FragmentHomeBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();

        // Assicurati che l'activity sia MainActivity
        if (getActivity() instanceof MainActivity) {
            mActivity = (MainActivity) getActivity();

            // Modifica il colore del testo del titolo nella barra dell'app
            ActionBar actionBar = mActivity.getSupportActionBar();
            if (actionBar != null) {
                Spannable text = new SpannableString(actionBar.getTitle());
                text.setSpan(new ForegroundColorSpan(Color.parseColor("#ffffff")), 0, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                actionBar.setTitle(text);
            }

            // Aggiorna la data quando la schermata è in primo piano
            updateCurrentDate();
        }
    }

    private void updateCurrentDate() {
        if (mActivity != null && isAdded()) {
            TextView textData = requireView().findViewById(R.id.txt_data);

            // Ottenere la data attuale utilizzando Calendar
            Calendar calendar = Calendar.getInstance();
            Date currentDate = calendar.getTime();

            // Formattare la data
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String formattedDate = dateFormat.format(currentDate);

            // Visualizzare la data in un TextView (assumendo che tu abbia un TextView nel tuo layout)
            textData.setText(formattedDate);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}