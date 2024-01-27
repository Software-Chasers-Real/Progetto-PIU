package it.uniba.dib.piu.softwarechasers.fitnessapp.ui.schede;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

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
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}