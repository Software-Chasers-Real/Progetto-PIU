package it.uniba.dib.piu.softwarechasers.fitnessapp.ui.schede;

import static android.os.Build.VERSION_CODES.R;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import it.uniba.dib.piu.softwarechasers.fitnessapp.R;

public class SchedeHolderView extends RecyclerView.ViewHolder implements View.OnClickListener{
    TextView nomeScheda, calorieScheda, durataScheda;
    ImageView imageView;

    private SchedeListener clickSchedeListener;

    // Costruttore che riceve un'istanza di ClickListener
    public SchedeHolderView(View itemView, SchedeListener listener) {
        super(itemView);
        // Imposta questo ViewHolder come gestore di clic
        itemView.setOnClickListener(this);
        // Salva il riferimento all'istanza di ClickListener
        this.clickSchedeListener = listener;

        nomeScheda = itemView.findViewById(it.uniba.dib.piu.softwarechasers.fitnessapp.R.id.titolo_scheda);
        calorieScheda = itemView.findViewById(it.uniba.dib.piu.softwarechasers.fitnessapp.R.id.calorie_scheda);
        durataScheda = itemView.findViewById(it.uniba.dib.piu.softwarechasers.fitnessapp.R.id.tempo_scheda);
        imageView = itemView.findViewById(it.uniba.dib.piu.softwarechasers.fitnessapp.R.id.sfondo_scheda);
    }


    // Metodo chiamato quando un elemento della RecyclerView viene cliccato
    @Override
    public void onClick(View view) {
        // Verifica se il ClickListener è stato assegnato
        if(clickSchedeListener != null){
            // Passa la posizione dell'elemento cliccato al ClickListener
            clickSchedeListener.onItemClick(getAdapterPosition());
        }
    }
}
