package it.uniba.dib.piu.softwarechasers.fitnessapp.access;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;

import it.uniba.dib.piu.softwarechasers.fitnessapp.MainActivity;
import it.uniba.dib.piu.softwarechasers.fitnessapp.R;

public class LoginFragment extends Fragment {
    private LoginSignupActivity mActivity;

    private FirebaseAuth auth;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (LoginSignupActivity) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();
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
                            Toast.makeText(mActivity, "Login esguito", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(mActivity, MainActivity.class));
                            mActivity.finish();
                        })
                        .addOnFailureListener(mActivity, e -> {
                            Toast.makeText(mActivity, "Login fallito", Toast.LENGTH_SHORT).show();
                        });
            }
        });
    }
}
