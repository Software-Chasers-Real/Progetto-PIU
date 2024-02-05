package it.uniba.dib.piu.softwarechasers.fitnessapp.ui.schede;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import it.uniba.dib.piu.softwarechasers.fitnessapp.MainActivity;
import it.uniba.dib.piu.softwarechasers.fitnessapp.R;
import it.uniba.dib.piu.softwarechasers.fitnessapp.model.EsercizioSchede;

public class DettaglioEsercizioFragment extends Fragment {
    private MainActivity mActvity;
    private EsercizioSchede esercizioSelezionato;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActvity = (MainActivity) getActivity();

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            esercizioSelezionato = (EsercizioSchede) bundle.getParcelable("esercizio");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dettaglio_esercizio, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView nomeEsercizio = view.findViewById(R.id.dettaglio_nome_esercizio);
        TextView descrizioneEsercizio = view.findViewById(R.id.dettaglio_esercizio_descrizione);
        WebView videoEsercizio = getView().findViewById(R.id.video_esercizio);

        nomeEsercizio.setText(esercizioSelezionato.getNome());
        descrizioneEsercizio.setText(esercizioSelezionato.getDescrizione());

        if(esercizioSelezionato.getIdYouTubeVideo() != null && !esercizioSelezionato.getIdYouTubeVideo().equals("")){
            String youtubeVideoId = esercizioSelezionato.getIdYouTubeVideo(); // Sostituisci con l'ID del video di YouTube desiderato
            String embedUrl = "https://www.youtube.com/embed/" + youtubeVideoId;
            videoEsercizio.getSettings().setJavaScriptEnabled(true);
            videoEsercizio.setWebChromeClient(new WebChromeClient());
            videoEsercizio.loadUrl(embedUrl);
        }
        /*
        String video = " <iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/aG7_Y9eR5OI?si=YaMAArM5JX88T-Tz\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe> ";
        videoEsercizio.loadData(video, "text/html","utf-8");
        videoEsercizio.getSettings().setJavaScriptEnabled(true);
        videoEsercizio.setWebChromeClient(new WebChromeClient());
        */
    }
}
