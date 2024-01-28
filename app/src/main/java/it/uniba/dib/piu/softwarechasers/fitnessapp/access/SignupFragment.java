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

public class SignupFragment extends Fragment {
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
        return inflater.inflate(R.layout.fragment_signup, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        EditText signUpEmail = view.findViewById(R.id.edit_signup_email);
        TextView txtSignupEmail = view.findViewById(R.id.label_signup_email);
        signUpEmail.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                // L'EditText ha ottenuto il focus
                signUpEmail.setHint("");
                txtSignupEmail.setTextColor(getResources().getColor(R.color.lime_A200));
                txtSignupEmail.setText(R.string.lbl_email);
                txtSignupEmail.setVisibility(View.VISIBLE);
            } else {
                // L'EditText ha perso il focus
                if(signUpEmail.getText().toString().isEmpty()) {
                    signUpEmail.setHint(R.string.lbl_email);
                    txtSignupEmail.setVisibility(View.INVISIBLE);
                }
            }
        });

        EditText signupPassword = view.findViewById(R.id.edit_signup_password);
        TextView txtSignupPassword = view.findViewById(R.id.label_signup_password);
        signupPassword.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                // L'EditText ha ottenuto il focus
                signupPassword.setHint("");
                txtSignupPassword.setTextColor(getResources().getColor(R.color.lime_A200));
                txtSignupPassword.setText(R.string.lbl_password);
                txtSignupPassword.setVisibility(View.VISIBLE);
            } else {
                // L'EditText ha perso il focus
                if(signupPassword.getText().toString().isEmpty()){
                    signupPassword.setHint(R.string.lbl_password);
                    txtSignupPassword.setVisibility(View.INVISIBLE);
                }
            }
        });

        EditText signupPasswordConfirm = view.findViewById(R.id.edit_signup_ripeti_password);
        TextView txtSignupPasswordConfirm = view.findViewById(R.id.label_signup_confirm_password);
        signupPasswordConfirm.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                // L'EditText ha ottenuto il focus
                signupPasswordConfirm.setHint("");
                txtSignupPasswordConfirm.setTextColor(getResources().getColor(R.color.lime_A200));
                txtSignupPasswordConfirm.setText(R.string.lbl_conferma_password);
                txtSignupPasswordConfirm.setVisibility(View.VISIBLE);
            } else {
                // L'EditText ha perso il focus
                if(signupPasswordConfirm.getText().toString().isEmpty()){
                    signupPasswordConfirm.setHint(R.string.lbl_conferma_password);
                    txtSignupPasswordConfirm.setVisibility(View.INVISIBLE);
                }

            }
        });

        Button signUpButton = view.findViewById(R.id.btn_registrati);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean error = false;
                String email = signUpEmail.getText().toString();
                String password = signupPassword.getText().toString();
                String passwordConfirm = signupPasswordConfirm.getText().toString();

                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    txtSignupEmail.setText(R.string.lbl_email_non_valida);
                    txtSignupEmail.setTextColor(getResources().getColor(R.color.red));
                    txtSignupEmail.setVisibility(View.VISIBLE);
                    error = true;
                }else if (email.isEmpty()) {
                    txtSignupEmail.setText(R.string.lbl_inserisci_email);
                    txtSignupEmail.setTextColor(getResources().getColor(R.color.red));
                    txtSignupEmail.setVisibility(View.VISIBLE);
                    error = true;
                }

                if(password.isEmpty()){
                    txtSignupPassword.setText(R.string.lbl_inserisci_password);
                    txtSignupPassword.setTextColor(getResources().getColor(R.color.red));
                    txtSignupPassword.setVisibility(View.VISIBLE);
                    error = true;
                }

                if(passwordConfirm.isEmpty()){
                    txtSignupPasswordConfirm.setText(R.string.lbl_inserisci_password);
                    txtSignupPasswordConfirm.setTextColor(getResources().getColor(R.color.red));
                    txtSignupPasswordConfirm.setVisibility(View.VISIBLE);
                    error = true;
                }

                if(password.equals(passwordConfirm) == false){
                    txtSignupPasswordConfirm.setText(R.string.lbl_password_non_corrispondenti);
                    txtSignupPasswordConfirm.setTextColor(getResources().getColor(R.color.red));
                    txtSignupPasswordConfirm.setVisibility(View.VISIBLE);
                    error = true;
                }

                if(error == true){
                    return;
                }else{
                    auth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(mActivity, task -> {
                                if (task.isSuccessful()) {
                                    //Registrazione avvenuta con successo
                                    Toast.makeText(mActivity, "Registrazione avvenuta con successo", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(mActivity, MainActivity.class));
                                    mActivity.finish();
                                } else {
                                    //Registrazione fallita
                                    Toast.makeText(mActivity, "Registrazione fallita", Toast.LENGTH_SHORT).show();
                                    // If sign in fails, display a message to the user.
                                    //Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    //Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",
                                    //        Toast.LENGTH_SHORT).show();
                                    //updateUI(null);
                                }
                            });
                }


            }
        });

    }

}
