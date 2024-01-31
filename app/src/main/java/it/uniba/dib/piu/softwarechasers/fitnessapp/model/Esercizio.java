package it.uniba.dib.piu.softwarechasers.fitnessapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Esercizio implements Parcelable {
    private String nome;
    private String descrizione;

    public Esercizio(String nome, String descrizione) {
        this.nome = nome;
        this.descrizione = descrizione;
    }

    protected Esercizio(Parcel in) {
        nome = in.readString();
        descrizione = in.readString();
    }

    public static final Creator<Esercizio> CREATOR = new Creator<Esercizio>() {
        @Override
        public Esercizio createFromParcel(Parcel in) {
            return new Esercizio(in);
        }

        @Override
        public Esercizio[] newArray(int size) {
            return new Esercizio[size];
        }
    };

    public String getNome() {
        return nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(nome);
        parcel.writeString(descrizione);
    }
}
