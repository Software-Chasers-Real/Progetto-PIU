package it.uniba.dib.piu.softwarechasers.fitnessapp.model;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Scheda implements Parcelable {
    private String nome;
    private int Tempo;
    private int Calorie;
    private String descrizione;
    private String riferimentoImmagineScheda;
    private Drawable immagineScheda;
    private ArrayList<EsercizioSchede> esercizi;

    public Scheda(String nome, int tempo, int calorie, String descrizione, String riferimentoImmagineScheda, ArrayList<EsercizioSchede> esercizi) {
        this.nome = nome;
        Tempo = tempo;
        Calorie = calorie;
        this.descrizione = descrizione;
        this.riferimentoImmagineScheda = riferimentoImmagineScheda;
        this.esercizi = esercizi;
    }

    public Scheda(String nome, int tempo, int calorie, String descrizione, ArrayList<EsercizioSchede> esercizi, Drawable immagineScheda) {
        this.nome = nome;
        Tempo = tempo;
        Calorie = calorie;
        this.descrizione = descrizione;
        this.esercizi = esercizi;
        this.immagineScheda = immagineScheda;
    }

    protected Scheda(Parcel in) {
        nome = in.readString();
        Tempo = in.readInt();
        Calorie = in.readInt();
        descrizione = in.readString();
        riferimentoImmagineScheda = in.readString();

        // Leggi la lunghezza del byte array dell'immagine
        int byteArrayLength = in.readInt();

        // Controlla se l'immagine è stata scritta come byte array
        if (byteArrayLength > 0) {
            // Leggi il byte array e convertilo in un Bitmap

        } else {
            // Se l'immagine è nulla, assegna null a immagineScheda
            immagineScheda = null;
        }

        // Leggi l'ArrayList di EsercizioSchede utilizzando readTypedList
        esercizi = new ArrayList<>();
        in.readTypedList(esercizi, EsercizioSchede.CREATOR);
    }


    public static final Creator<Scheda> CREATOR = new Creator<Scheda>() {
        @Override
        public Scheda createFromParcel(Parcel in) {
            return new Scheda(in);
        }

        @Override
        public Scheda[] newArray(int size) {
            return new Scheda[size];
        }
    };

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTempo() {
        return Tempo;
    }

    public void setTempo(int tempo) {
        Tempo = tempo;
    }

    public int getCalorie() {
        return Calorie;
    }

    public void setCalorie(int calorie) {
        Calorie = calorie;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
    public Drawable getImmagineScheda() {
        return immagineScheda;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(nome);
        parcel.writeInt(Tempo);
        parcel.writeInt(Calorie);
        parcel.writeString(descrizione);
        parcel.writeString(riferimentoImmagineScheda);

        // Controlla se immagineScheda è diverso da null prima di scriverlo nel parcel
        if (immagineScheda != null) {
            // Converti l'immagine in un byte array utilizzando un Bitmap

        } else {
            // Se immagineScheda è null, scrivi -1 per indicare che l'immagine è nulla
            parcel.writeInt(-1);
        }

        // Usa writeTypedList per scrivere l'ArrayList di EsercizioSchede nel parcel
        parcel.writeTypedList(esercizi);
    }

}
