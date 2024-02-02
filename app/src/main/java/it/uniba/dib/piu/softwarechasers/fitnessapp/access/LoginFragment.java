package it.uniba.dib.piu.softwarechasers.fitnessapp.access;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Map;

import it.uniba.dib.piu.softwarechasers.fitnessapp.MainActivity;
import it.uniba.dib.piu.softwarechasers.fitnessapp.R;
import it.uniba.dib.piu.softwarechasers.fitnessapp.informazioniUtente.InfromazioniUtenteActivity;
import it.uniba.dib.piu.softwarechasers.fitnessapp.model.Scheda;
import it.uniba.dib.piu.softwarechasers.fitnessapp.model.Utente;

public class LoginFragment extends Fragment {
    private LoginSignupActivity mActivity;
    private ArrayList<Scheda> schede;

    private FirebaseAuth auth;

    private Utente utente;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (LoginSignupActivity) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();
        utente = new Utente();

        Bundle bundle = getArguments();
        if (bundle != null) {
            if(bundle.containsKey("schede")) {
                schede = bundle.getParcelableArrayList("schede");
            }
        }else{
            schede = new ArrayList<>();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        EditText loginEmail = view.findViewById(R.id.edit_login_email);
        TextView txtLoginEmail = view.findViewById(R.id.label_login_email);
        loginEmail.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                // L'EditText ha ottenuto il focus
                loginEmail.setHint("");
                txtLoginEmail.setTextColor(getResources().getColor(R.color.lime_A200));
                txtLoginEmail.setText(R.string.lbl_email);
                txtLoginEmail.setVisibility(View.VISIBLE);
            } else {
                // L'EditText ha perso il focus
                if(loginEmail.getText().toString().isEmpty()) {
                    loginEmail.setHint(R.string.lbl_email);
                    txtLoginEmail.setVisibility(View.INVISIBLE);
                }
            }
        });

        EditText loginPassword = view.findViewById(R.id.edit_login_password);
        TextView txtLoginPassword = view.findViewById(R.id.label_login_password);
        loginPassword.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                // L'EditText ha ottenuto il focus
                loginPassword.setHint("");
                txtLoginPassword.setTextColor(getResources().getColor(R.color.lime_A200));
                txtLoginPassword.setText(R.string.lbl_password);
                txtLoginPassword.setVisibility(View.VISIBLE);
            } else {
                // L'EditText ha perso il focus
                if(loginPassword.getText().toString().isEmpty()) {
                    loginPassword.setHint(R.string.lbl_password);
                    txtLoginPassword.setVisibility(View.INVISIBLE);
                }
            }
        });

        Button loginButton = view.findViewById(R.id.btn_login);
        loginButton.setOnClickListener(v -> {
            Boolean error = false;
            String email = loginEmail.getText().toString();
            String password = loginPassword.getText().toString();

            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                txtLoginEmail.setText(R.string.lbl_email_non_valida);
                txtLoginEmail.setTextColor(getResources().getColor(R.color.red));
                txtLoginEmail.setVisibility(View.VISIBLE);
                error = true;
            }else if (email.isEmpty()) {
                txtLoginEmail.setText(R.string.lbl_inserisci_email);
                txtLoginEmail.setTextColor(getResources().getColor(R.color.red));
                txtLoginEmail.setVisibility(View.VISIBLE);
                error = true;
            }

            if(password.isEmpty()){
                txtLoginPassword.setText(R.string.lbl_inserisci_password);
                txtLoginPassword.setTextColor(getResources().getColor(R.color.red));
                txtLoginPassword.setVisibility(View.VISIBLE);
                error = true;
            }

            if(error == true){
                return;
            }else{
                auth.signInWithEmailAndPassword(email, password)
                        .addOnSuccessListener(mActivity, authResult -> {
                            FirebaseFirestore db = FirebaseFirestore.getInstance();
                            db.collection("utenti")
                                    .document(auth.getCurrentUser().getUid())
                                    .get()
                                    .addOnCompleteListener(task -> {
                                        if (task.isSuccessful()) {
                                            if (task.getResult().exists()) {
                                                //l'utente ha completato il profilo
                                                //eseguo il retrieve dei suoi dati
                                                Map<String, Object> nuovoUtente = task.getResult().getData();
                                                utente.setNome(nuovoUtente.get("nome").toString());
                                                utente.setCognome(nuovoUtente.get("cognome").toString());
                                                utente.setEmail(nuovoUtente.get("email").toString());
                                                utente.setAltezza(Float.valueOf(nuovoUtente.get("altezza").toString()));
                                                utente.setPeso(Float.valueOf(nuovoUtente.get("peso").toString()));
                                                utente.setGenere(nuovoUtente.get("genere").toString());
                                                utente.setEta(Integer.valueOf(nuovoUtente.get("eta").toString()));
                                                if (((ArrayList<String>) nuovoUtente.get("schede")) == null) {
                                                    utente.setIdSchede(new ArrayList<String>());
                                                } else {
                                                    utente.setIdSchede((ArrayList<String>) nuovoUtente.get("schede"));
                                                }

                                                Log.d("AltezzaFragment", "Utente aggiunto al db");
                                                Bundle bundle = new Bundle();
                                                bundle.putParcelable("utente", utente);
                                                bundle.putParcelableArrayList("schede", schede);
                                                Intent intent = new Intent(mActivity, MainActivity.class);
                                                intent.putExtras(bundle);
                                                startActivity(intent);
                                                mActivity.finish();

                                            } else {
                                                //l'utente non ha completato il profilo
                                                Bundle bundle = new Bundle();
                                                bundle.putParcelableArrayList("schede", schede);
                                                Intent intent = new Intent(mActivity, InfromazioniUtenteActivity.class);
                                                intent.putExtras(bundle);
                                                startActivity(intent);
                                                mActivity.finish();
                                            }
                                        } else {
                                            //stampa nel log un messaggio di errore
                                            if (task.getException() instanceof FirebaseAuthException) {
                                                // Errore durante il login, potrebbe essere una password errata
                                                FirebaseAuthException e = (FirebaseAuthException) task.getException();
                                                Log.e("LoginFragment", "Errore durante il login: " + e.getMessage());
                                                // Controlla il codice dell'errore per determinare la causa specifica
                                                String errorCode = e.getErrorCode();
                                                if (errorCode.equals("ERROR_WRONG_PASSWORD")) {
                                                    // Password errata
                                                    Log.w("LoginFragment", "Password errata per l'email fornita.");
                                                    // Aggiungi qui la tua logica per gestire il caso di password errata
                                                } else {
                                                    // Altri tipi di errore durante il login
                                                    Log.w("LoginFragment", "Errore durante il login", e);
                                                }
                                            }
                                        }
                                    });
                        })
                        .addOnFailureListener(mActivity, e -> {
                            Log.e("LoginFragment", "Errore durante il login "+e);
                            if(e instanceof  FirebaseAuthInvalidCredentialsException){
                                txtLoginPassword.setText("Password errata");
                                txtLoginPassword.setTextColor(getResources().getColor(R.color.red));
                                txtLoginPassword.setVisibility(View.VISIBLE);
                            } else if (e instanceof FirebaseTooManyRequestsException) {
                                Toast.makeText(mActivity, "Hai effettuato troppe richieste, attendi qualche minuto.", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(mActivity, "Login fallito.", Toast.LENGTH_SHORT).show();
                            }

                        });
            }
        });
    }
}
