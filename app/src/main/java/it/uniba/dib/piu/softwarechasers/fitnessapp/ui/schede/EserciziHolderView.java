package it.uniba.dib.piu.softwarechasers.fitnessapp.ui.schede;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class EserciziHolderView  extends RecyclerView.ViewHolder implements View.OnClickListener{
    TextView nomeEsercizio, serie_ripetizioni_esercizio;
    ImageView immagineEsercizio;

    private EserciziListener clickEserciziListener;

    // Costruttore che riceve un'istanza di ClickListener
    public EserciziHolderView(View itemView, EserciziListener listener) {
        super(itemView);
        // Imposta questo ViewHolder come gestore di clic
        itemView.setOnClickListener(this);
        // Salva il riferimento all'istanza di ClickListener
        this.clickEserciziListener = listener;

        nomeEsercizio = itemView.findViewById(it.uniba.dib.piu.softwarechasers.fitnessapp.R.id.item_nome_esercizio);
        serie_ripetizioni_esercizio = itemView.findViewById(it.uniba.dib.piu.softwarechasers.fitnessapp.R.id.item_serie_ripetizione_esercizio);
        immagineEsercizio = itemView.findViewById(it.uniba.dib.piu.softwarechasers.fitnessapp.R.id.item_image_esercizio);
    }

    // Metodo chiamato quando un elemento della RecyclerView viene cliccato
    @Override
    public void onClick(View view) {
        // Verifica se il ClickListener è stato assegnato
        if(clickEserciziListener != null){
            // Passa la posizione dell'elemento cliccato al ClickListener
            clickEserciziListener.onItemClick(getAdapterPosition());
        }
    }
}
