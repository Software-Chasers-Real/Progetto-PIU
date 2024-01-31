package it.uniba.dib.piu.softwarechasers.fitnessapp.ui.schede;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

import it.uniba.dib.piu.softwarechasers.fitnessapp.MainActivity;
import it.uniba.dib.piu.softwarechasers.fitnessapp.R;
import it.uniba.dib.piu.softwarechasers.fitnessapp.model.Scheda;

public class DettaglioSchedeFragment extends Fragment {
    private MainActivity mActvity;
    private Scheda schedaSelezionata;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActvity = (MainActivity) getActivity();

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            schedaSelezionata = bundle.getParcelable("scheda");
            Log.d("SCHEDA", schedaSelezionata.getNome());
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dettaglio_scheda, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView sfondo = view.findViewById(R.id.sfondo_dettaglio_scheda);
        TextView txtNomeScheda = view.findViewById(R.id.nome_scheda_dettaglio);
        Button btnTempo = view.findViewById(R.id.tempo_dettaglio_bottone);
        Button btnCalorie = view.findViewById(R.id.calorie_dettaglio_bottone);
        TextView txtDescrizione = view.findViewById(R.id.descr_dettaglio_scheda);

        sfondo.setImageDrawable(schedaSelezionata.getImmagineScheda());
        txtNomeScheda.setText(schedaSelezionata.getNome());
        btnTempo.setText(String.valueOf(schedaSelezionata.getTempo())+" min");
        btnCalorie.setText(String.valueOf(schedaSelezionata.getCalorie())+" kcal");
        txtDescrizione.setText(schedaSelezionata.getDescrizione());

    }
}
