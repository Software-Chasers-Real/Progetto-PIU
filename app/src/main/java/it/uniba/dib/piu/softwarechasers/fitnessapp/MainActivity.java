package it.uniba.dib.piu.softwarechasers.fitnessapp;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.ArrayList;

import it.uniba.dib.piu.softwarechasers.fitnessapp.databinding.ActivityMainBinding;
import it.uniba.dib.piu.softwarechasers.fitnessapp.model.Scheda;
import it.uniba.dib.piu.softwarechasers.fitnessapp.model.Utente;

public class MainActivity extends AppCompatActivity {
    public Utente utente;
    public ArrayList<Scheda> schede;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle ricevutoBundle = getIntent().getExtras();

        if (ricevutoBundle != null) {
            if(ricevutoBundle.containsKey("utente")){
                Log.d("MainActivity", "Bundle ricevuto");
                utente = ricevutoBundle.getParcelable("utente");
            }else{
                Log.d("MainActivity", "Bundle ricevuto ma utente non presente");
            }

            if(ricevutoBundle.containsKey("schede")) {
                Log.d("MainActivity", "Bundle ricevuto");
                schede = ricevutoBundle.getParcelableArrayList("schede");
            }else{
                Log.d("MainActivity", "Utente: " + utente.getEmail());
            }
        }else{
            Log.d("MainActivity", "Bundle non ricevuto");
        }

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_sale, R.id.navigation_coach,
                R.id.navigation_schede, R.id.navigation_account)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    //metodo che permette di tronare al fragment precedente
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        return navController.navigateUp();
    }
}