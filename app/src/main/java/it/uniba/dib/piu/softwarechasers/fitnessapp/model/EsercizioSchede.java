package it.uniba.dib.piu.softwarechasers.fitnessapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class EsercizioSchede extends Esercizio implements Parcelable {
    private String serie_ripetizioni;
    private String riposo;
    public EsercizioSchede(String nome, String descrizione, String serie_ripetizioni, String riposo) {
        super(nome, descrizione);
        this.serie_ripetizioni = serie_ripetizioni;
        this.riposo = riposo;
    }

    protected EsercizioSchede(Parcel in) {
        super(in);
        serie_ripetizioni = in.readString();
        riposo = in.readString();
    }

    public static final Creator<EsercizioSchede> CREATOR = new Creator<EsercizioSchede>() {
        @Override
        public EsercizioSchede createFromParcel(Parcel in) {
            return new EsercizioSchede(in);
        }

        @Override
        public EsercizioSchede[] newArray(int size) {
            return new EsercizioSchede[size];
        }
    };

    public String getSerie_ripetizioni() {
        return serie_ripetizioni;
    }

    public void setSerie_ripetizioni(String serie_ripetizioni) {
        this.serie_ripetizioni = serie_ripetizioni;
    }

    public String getRiposo() {
        return riposo;
    }

    public void setRiposo(String riposo) {
        this.riposo = riposo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(getNome());
        parcel.writeString(getDescrizione());
        parcel.writeString(serie_ripetizioni);
        parcel.writeString(riposo);
    }
}
