package it.uniba.dib.piu.softwarechasers.fitnessapp.ui.schede;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import it.uniba.dib.piu.softwarechasers.fitnessapp.R;
import it.uniba.dib.piu.softwarechasers.fitnessapp.model.EsercizioSchede;
import it.uniba.dib.piu.softwarechasers.fitnessapp.model.Scheda;

public class EserciziAdapter extends RecyclerView.Adapter<EserciziHolderView>{

    Context context;

    List<EsercizioSchede> items;

    private EserciziListener clickEserciziListener;

    // Costruttore che riceve la lista di dati e un'istanza di ClickListener
    public EserciziAdapter(Context context, List<EsercizioSchede> items, EserciziListener listener) {
        this.context = context;
        this.items = items;
        this.clickEserciziListener = listener;
    }

    @NonNull
    @Override
    public EserciziHolderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EserciziHolderView(LayoutInflater.from(context).inflate(R.layout.item_esercizi_scheda, parent, false), clickEserciziListener);
    }

    @Override
    public void onBindViewHolder(@NonNull EserciziHolderView holder, int position) {
        if(items.get(position).getImmagine()!=null){
            holder.immagineEsercizio.setImageDrawable(items.get(position).getImmagine());
        }else{
            holder.immagineEsercizio.setImageResource(R.drawable.immagine_sfondo_signup);
        }
        holder.nomeEsercizio.setText(items.get(position).getNome());
        holder.serie_ripetizioni_esercizio.setText(items.get(position).getSerie_ripetizioni()+" (riposo: "+items.get(position).getRiposo()+")");
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
