package it.uniba.dib.piu.softwarechasers.fitnessapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.Map;

import it.uniba.dib.piu.softwarechasers.fitnessapp.informazioniUtente.InfromazioniUtenteActivity;

import it.uniba.dib.piu.softwarechasers.fitnessapp.access.LoginSignupActivity;
import it.uniba.dib.piu.softwarechasers.fitnessapp.model.Esercizio;
import it.uniba.dib.piu.softwarechasers.fitnessapp.model.EsercizioSchede;
import it.uniba.dib.piu.softwarechasers.fitnessapp.model.Scheda;
import it.uniba.dib.piu.softwarechasers.fitnessapp.model.Utente;

public class SplashActivity extends AppCompatActivity {
    private ArrayList<Scheda> schede;
    private Utente utente;
    private boolean loggato = false;
    private boolean profiloCompletato = false;
    private boolean fecthCompletato = false;
    private static final long MIN_WAIT_INTERVAL = 1500L;
    private static final long MAX_WAIT_INTERVAL = 3000L;
    private static final int GO_AHEAD_WHAT = 1;
    private static final int FECTH_TERMINATO = 2;
    private long mStartTime;
    private boolean mIsDone;
    private boolean mIsDoneSchede;
    private FirebaseUser currentUser;
    private FirebaseFirestore db;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GO_AHEAD_WHAT:
                    long elapsedTime = SystemClock.uptimeMillis() - mStartTime;
                    if ((elapsedTime >= MIN_WAIT_INTERVAL && !mIsDone) && fecthCompletato) {
                        mIsDone = true;
                        startMainActivity();
                    }
                    break;
                case FECTH_TERMINATO:
                    if (fecthCompletato) {
                        mIsDone = true;
                        startMainActivity();
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        Log.d("SplashActivity", "OnCreate");
        decorView.setSystemUiVisibility(uiOptions);
        db = FirebaseFirestore.getInstance();
        utente = new Utente();
        schede = new ArrayList<>();
        fetchDataFromDataBase();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mStartTime = SystemClock.uptimeMillis();
        final Message goAheadMessage = mHandler.obtainMessage(GO_AHEAD_WHAT);
        mHandler.sendMessageAtTime(goAheadMessage, mStartTime + MAX_WAIT_INTERVAL);
    }

    private void fetchDataFromDataBase() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();
        if (currentUser != null) {
            verificaCompletamentoProfilo(currentUser.getUid());
        } else {
            // Utente non loggato
            loggato = false;
            profiloCompletato = false;
            fecthCompletato = true;
            startMainActivity(); // Chiamare startMainActivity anche se non è loggato
        }
    }

    //metodo che verifica se l'utente ha completato il profilo con le informazioni necessarie
    //sesso, peso, altezza, anni
    private void verificaCompletamentoProfilo(String uid) {
        //verifica che nel database di firestore esiste il documento con uid nella raccolta utenti
        //se esiste allora l'utente ha completato il profilo
        //altrimenti l'utente deve completare il profilo
        db.collection("utenti")
                .document(uid)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        if (task.getResult().exists()) {
                            //l'utente ha completato il profilo
                            loggato = true;
                            profiloCompletato = true;
                            popolaOggettoUtente(task.getResult());
                            fetchSchede();
                        } else {
                            //l'utente non ha completato il profilo
                            loggato = true;
                            profiloCompletato = false;
                            fetchSchede();
                        }
                    } else {
                        //stampa nel log un messaggio di errore
                        Log.d("SplashActivity", "Task fallito");
                        loggato = false;
                        profiloCompletato = false;
                        startMainActivity();
                    }
                });
    }

    //metodo che recupera le informazioni dell'utente dal database
    private void popolaOggettoUtente(DocumentSnapshot document) {
        Map<String, Object> nuovoUtente = document.getData();
        utente.setEmail(nuovoUtente.get("email").toString());
        utente.setAltezza(Float.valueOf(nuovoUtente.get("altezza").toString()));
        utente.setPeso(Float.valueOf(nuovoUtente.get("peso").toString()));
        utente.setGenere(nuovoUtente.get("genere").toString());
        utente.setEta(Integer.valueOf(nuovoUtente.get("eta").toString()));
        if(((ArrayList<String>) nuovoUtente.get("schede")).get(0) == ""){
            utente.setIdSchede(new ArrayList<String>());
        }else{
            utente.setIdSchede((ArrayList<String>) nuovoUtente.get("schede"));
        }
    }

    private void fetchSchede() {
        db.collection("schede")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Map<String, Object> scheda = document.getData();

                            ArrayList<Map<String, String>> esercizi = (ArrayList<Map<String, String>>) scheda.get("Esercizi");
                            ArrayList<EsercizioSchede> eserciziSchede = new ArrayList<>();
                            Log.d("SplashActivity", "Esercizi: " + esercizi.size());
                            for(Map<String, String> esercizio: esercizi){
                                eserciziSchede.add(new EsercizioSchede(esercizio.get("nome"),
                                        esercizio.get("descrizione"),
                                        esercizio.get("serie_ripetizioni"),
                                        esercizio.get("riposo"),
                                        esercizio.get("immagine"),
                                        esercizio.get("video").toString()));

                            }

                            schede.add(new Scheda(
                                    scheda.get("nome").toString(),
                                    Integer.valueOf(scheda.get("tempo").toString()),
                                    Integer.valueOf(scheda.get("calorie").toString()),
                                    scheda.get("descrizione").toString(),
                                    scheda.get("image_sfondo").toString(),
                                    eserciziSchede,
                                    document.getId().toString()
                            ));
                        }
                        fecthCompletato = true;
                        mHandler.sendEmptyMessage(FECTH_TERMINATO);
                    } else {
                        //stampa nel log un messaggio di errore
                        Log.d("SplashActivity", "Task fallito");
                        fecthCompletato = true;
                        mHandler.sendEmptyMessage(FECTH_TERMINATO);
                    }
                });
    }

    private void startMainActivity() {
        if(loggato == false && profiloCompletato == false) {
            Log.d("SplashActivity", "Login lanciata");
            startActivity(new Intent(this, LoginSignupActivity.class));
            finish();
        }else if(loggato == true && profiloCompletato == false){
            Log.d("SplashActivity", "Profilo lanciata");
            startActivity(new Intent(this, InfromazioniUtenteActivity.class));
            finish();
        }else if(loggato == true && profiloCompletato == true){
            Bundle bundle = new Bundle();
            bundle.putParcelable("utente", utente);
            bundle.putParcelableArrayList("schede", schede);
            Log.d("SplashActivity", "Grandezza schede "+schede.size());
            Log.d("SplashActivity", "Main lanciata");
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        }
    }
}
