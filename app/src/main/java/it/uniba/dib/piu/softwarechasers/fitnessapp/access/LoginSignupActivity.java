package it.uniba.dib.piu.softwarechasers.fitnessapp.access;

import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import it.uniba.dib.piu.softwarechasers.fitnessapp.R;

public class LoginSignupActivity extends AppCompatActivity {
    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signup);
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.fragment_login_signup, LoginFragment.class, null)
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

            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragment_login_signup, LoginFragment.class, null)
                    .commit();
        });

        registratiText.setOnClickListener(v -> {
            accediText.setBackground(null);
            registratiText.setBackgroundResource(R.drawable.bordo_bottom_giallo);

            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragment_login_signup, SignupFragment.class, null)
                    .commit();
        });
    }
}
