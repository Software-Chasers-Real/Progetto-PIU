package it.uniba.dib.piu.softwarechasers.fitnessapp.ui.schede;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.List;

import it.uniba.dib.piu.softwarechasers.fitnessapp.MainActivity;
import it.uniba.dib.piu.softwarechasers.fitnessapp.R;
import it.uniba.dib.piu.softwarechasers.fitnessapp.model.Scheda;

public class SchedeAdapter extends RecyclerView.Adapter<SchedeHolderView>{

    Context context;

    List<Scheda> items;

    //private ClickFigliListener clickFigliListener;

    // Costruttore che riceve la lista di dati e un'istanza di ClickListener
    /*
    public SchedeAdapter(Context context, List<Scheda> items, ClickFigliListener listener) {
        this.context = context;
        this.items = items;
        this.clickFigliListener = listener;
    }
    */
    public SchedeAdapter(Context context, List<Scheda> items) {
        this.context = context;
        this.items = items;
    }
    @NonNull
    @Override
    public SchedeHolderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SchedeHolderView(LayoutInflater.from(context).inflate(R.layout.item_schede, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SchedeHolderView holder, int position) {
        holder.nomeScheda.setText(items.get(position).getNome());// Create a storage reference from our app
        holder.calorieScheda.setText(String.valueOf(items.get(position).getCalorie()));
        holder.durataScheda.setText(String.valueOf(items.get(position).getTempo()));
        holder.imageView.setImageDrawable(items.get(position).getImmagineScheda());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
