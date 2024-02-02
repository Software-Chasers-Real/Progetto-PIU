package it.uniba.dib.piu.softwarechasers.fitnessapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
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

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        mActivity = (MainActivity) getActivity();
        return root;
    }
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView textBentornato= view.findViewById(R.id.txt_bentornato);
        if(mActivity.utente.getGenere().equals("uomo")){
            textBentornato.setText("Bentornato "+mActivity.utente.getNome());
        }else{
            textBentornato.setText("Bentornata "+mActivity.utente.getNome());
        }

        // Ottenere la data attuale utilizzando Calendar
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();

        // Formattare la data
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = dateFormat.format(currentDate);

        // Visualizzare la data in un TextView (assumendo che tu abbia un TextView nel tuo layout)
        TextView textData = view.findViewById(R.id.txt_data);
        textData.setText(formattedDate);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}