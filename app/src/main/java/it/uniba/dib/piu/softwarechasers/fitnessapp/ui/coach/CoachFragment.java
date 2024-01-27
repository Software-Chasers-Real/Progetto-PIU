package it.uniba.dib.piu.softwarechasers.fitnessapp.ui.coach;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import it.uniba.dib.piu.softwarechasers.fitnessapp.databinding.FragmentCoachBinding;

public class CoachFragment extends Fragment {

    private FragmentCoachBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CoachViewModel homeViewModel =
                new ViewModelProvider(this).get(CoachViewModel.class);

        binding = FragmentCoachBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}