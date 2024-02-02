package it.uniba.dib.piu.softwarechasers.fitnessapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Utente implements Parcelable {
    private String nome;
    private String cognome;
    private String email;
    private String genere;
    private int eta;
    private float peso;
    private float altezza;
    private List<String> idSchede;

    public Utente() {
    }

    public Utente(String nome,String cognome,String email, String genere, int eta, float peso, float altezza, List<String> idSchede){
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.genere = genere;
        this.eta = eta;
        this.peso = peso;
        this.altezza = altezza;
        this.idSchede = idSchede;
    }

    protected Utente(Parcel in) {
        nome = in.readString();
        cognome = in.readString();
        email = in.readString();
        genere = in.readString();
        eta = in.readInt();
        peso = in.readFloat();
        altezza = in.readFloat();

        byte schedaPresente = in.readByte();
        idSchede = new ArrayList<>();
        if(schedaPresente == (byte) 1){
            in.readStringList(idSchede);
        }
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

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

    public List<String> getIdSchede() {
        return idSchede;
    }

    public void setIdSchede(List<String> idSchede) {
        this.idSchede = idSchede;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(nome);
        parcel.writeString(cognome);
        parcel.writeString(email);
        parcel.writeString(genere);
        parcel.writeInt(eta);
        parcel.writeFloat(peso);
        parcel.writeFloat(altezza);

        if(idSchede != null) {
            parcel.writeByte((byte) 1);
            parcel.writeStringList(idSchede);
        }else{
            parcel.writeByte((byte) 0);
        }
    }
}
