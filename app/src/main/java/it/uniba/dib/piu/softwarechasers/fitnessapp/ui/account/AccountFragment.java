package it.uniba.dib.piu.softwarechasers.fitnessapp.ui.account;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.google.firebase.auth.FirebaseAuth;

import it.uniba.dib.piu.softwarechasers.fitnessapp.MainActivity;
import it.uniba.dib.piu.softwarechasers.fitnessapp.R;
import it.uniba.dib.piu.softwarechasers.fitnessapp.access.LoginFragment;
import it.uniba.dib.piu.softwarechasers.fitnessapp.access.LoginSignupActivity;
import it.uniba.dib.piu.softwarechasers.fitnessapp.databinding.FragmentAccountBinding;

public class AccountFragment extends Fragment {

    private FragmentAccountBinding binding;
    private MainActivity mActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mActivity = (MainActivity) getActivity();
    }

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

        AccountViewModel homeViewModel =
                new ViewModelProvider(this).get(AccountViewModel.class);

        binding = FragmentAccountBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button logout = root.findViewById(R.id.sign_out);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("schede", mActivity.schede);
                Intent intent = new Intent(getActivity(), LoginSignupActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
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