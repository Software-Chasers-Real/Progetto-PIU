package it.uniba.dib.piu.softwarechasers.fitnessapp.ui.schede;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import it.uniba.dib.piu.softwarechasers.fitnessapp.R;
import it.uniba.dib.piu.softwarechasers.fitnessapp.databinding.FragmentSchedeBinding;

public class SchedeFragment extends Fragment {

    private FragmentSchedeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SchedeViewModel homeViewModel =
                new ViewModelProvider(this).get(SchedeViewModel.class);

        binding = FragmentSchedeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

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
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}