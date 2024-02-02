package it.uniba.dib.piu.softwarechasers.fitnessapp.access;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import it.uniba.dib.piu.softwarechasers.fitnessapp.R;
import it.uniba.dib.piu.softwarechasers.fitnessapp.informazioniUtente.NomeCognomeFragment;
import it.uniba.dib.piu.softwarechasers.fitnessapp.model.Scheda;

public class LoginSignupActivity extends AppCompatActivity {
    private ArrayList<Scheda> schede;
    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signup);
        Bundle ricevutoBundle = getIntent().getExtras();
        if (ricevutoBundle != null) {
            if(ricevutoBundle.containsKey("schede")) {
                schede = ricevutoBundle.getParcelableArrayList("schede");
            }
        }
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("schede", schede);
        LoginFragment loginFragment = new LoginFragment();
        loginFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.fragment_login_signup, loginFragment, null)
                .commit();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("LoginSignupActivity", "OnResume");

        TextView accediText = findViewById(R.id.txtAccedi);
        TextView registratiText = findViewById(R.id.txtRegistrati);

        accediText.setOnClickListener(v -> {
            registratiText.setBackground(null);
            accediText.setBackgroundResource(R.drawable.bordo_bottom_giallo);
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("schede", schede);
            LoginFragment loginFragment = new LoginFragment();
            loginFragment.setArguments(bundle);

            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragment_login_signup, loginFragment, null)
                    .commit();
        });

        registratiText.setOnClickListener(v -> {
            accediText.setBackground(null);
            registratiText.setBackgroundResource(R.drawable.bordo_bottom_giallo);

            SignupFragment signupFragment = new SignupFragment();
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragment_login_signup, signupFragment, null)
                    .commit();
        });
    }
}
