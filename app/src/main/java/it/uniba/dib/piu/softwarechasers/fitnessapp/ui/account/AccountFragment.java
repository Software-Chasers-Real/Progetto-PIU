package it.uniba.dib.piu.softwarechasers.fitnessapp.ui.account;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.google.firebase.auth.FirebaseAuth;

import it.uniba.dib.piu.softwarechasers.fitnessapp.R;
import it.uniba.dib.piu.softwarechasers.fitnessapp.access.LoginFragment;
import it.uniba.dib.piu.softwarechasers.fitnessapp.access.LoginSignupActivity;
import it.uniba.dib.piu.softwarechasers.fitnessapp.databinding.FragmentAccountBinding;

public class AccountFragment extends Fragment {

    private FragmentAccountBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AccountViewModel homeViewModel =
                new ViewModelProvider(this).get(AccountViewModel.class);

        binding = FragmentAccountBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button logout = root.findViewById(R.id.sign_out);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getActivity(), LoginSignupActivity.class));
                getActivity().finish();
            }
        });

        // Aggiunta del listener all'immagine per le informazioni personali
        TextView informazioni_personali = root.findViewById(R.id.informazioni_personali);
        informazioni_personali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Esegui il logout e passa a un nuovo fragment utilizzando il Navigation Component
                FirebaseAuth.getInstance();
                Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main)
                        .navigate(R.id.navigation_informazioni_personali);
            }
        });

        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}