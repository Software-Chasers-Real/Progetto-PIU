package it.uniba.dib.piu.softwarechasers.fitnessapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Utente implements Parcelable {
    private String email;
    private String genere;
    private int eta;
    private float peso;
    private float altezza;

    public Utente() {
    }

    public Utente(String email, String genere, int eta, float peso, float altezza) {
        this.email = email;
        this.genere = genere;
        this.eta = eta;
        this.peso = peso;
        this.altezza = altezza;
    }

    protected Utente(Parcel in) {
        email = in.readString();
        genere = in.readString();
        eta = in.readInt();
        peso = in.readFloat();
        altezza = in.readFloat();
    }

    public static final Creator<Utente> CREATOR = new Creator<Utente>() {
        @Override
        public Utente createFromParcel(Parcel in) {
            return new Utente(in);
        }

        @Override
        public Utente[] newArray(int size) {
            return new Utente[size];
        }
    };

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public int getEta() {
        return eta;
    }

    public void setEta(int eta) {
        this.eta = eta;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getAltezza() {
        return altezza;
    }

    public void setAltezza(float altezza) {
        this.altezza = altezza;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(email);
        parcel.writeString(genere);
        parcel.writeInt(eta);
        parcel.writeFloat(peso);
        parcel.writeFloat(altezza);
    }
}
