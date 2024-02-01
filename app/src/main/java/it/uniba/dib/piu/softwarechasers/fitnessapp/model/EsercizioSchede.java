package it.uniba.dib.piu.softwarechasers.fitnessapp.model;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.ByteArrayOutputStream;

public class EsercizioSchede extends Esercizio implements Parcelable {
    private String serie_ripetizioni;
    private String riposo;
    private String riferimentoImmagine;
    private Drawable immagine;
    private String idYouTubeVideo;

    public EsercizioSchede(String nome, String descrizione, String serie_ripetizioni, String riposo,String riferimentoImmagine,String idYouTubeVideo) {
        super(nome, descrizione);
        this.serie_ripetizioni = serie_ripetizioni;
        this.riposo = riposo;
        this.riferimentoImmagine = riferimentoImmagine;
        this.idYouTubeVideo = idYouTubeVideo;
    }

    public EsercizioSchede(String nome, String descrizione, String serie_ripetizioni, String riposo,Drawable immagine, String idYouTubeVideo) {
        super(nome, descrizione);
        this.serie_ripetizioni = serie_ripetizioni;
        this.riposo = riposo;
        this.immagine = immagine;
        this.idYouTubeVideo = idYouTubeVideo;
    }


    protected EsercizioSchede(Parcel in) {
        super(in);
        serie_ripetizioni = in.readString();
        riposo = in.readString();
        riferimentoImmagine = in.readString();
        // Leggi la lunghezza del byte array dell'immagine
        int byteArrayLength = in.readInt();

        // Controlla se l'immagine è stata scritta come byte array
        if (byteArrayLength > 0) {
            // Leggi il byte array e convertilo in un Bitmap
            byte[] byteArray = new byte[byteArrayLength];
            in.readByteArray(byteArray);
            Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArrayLength);

            // Crea un Drawable da Bitmap e assegna a immagineScheda
            immagine = new BitmapDrawable(Resources.getSystem(), bitmap);
        } else {
            // Se l'immagine è nulla, assegna null a immagineScheda
            immagine = null;
        }

        idYouTubeVideo = in.readString();
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

    public String getRiferimentoImmagine() {
        return riferimentoImmagine;
    }

    public void setImmagine(Drawable immagine) {
        this.immagine = immagine;
    }

    public Drawable getImmagine() {
        return immagine;
    }

    public String getIdYouTubeVideo() {
        return idYouTubeVideo;
    }

    public void setIdYouTubeVideo(String idYouTubeVideo) {
        this.idYouTubeVideo = idYouTubeVideo;
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
        parcel.writeString(riferimentoImmagine);

        // Controlla se immagineScheda è diverso da null prima di scriverlo nel parcel
        if (immagine != null) {
            // Converti l'immagine in un byte array utilizzando un Bitmap
            Bitmap bitmap = ((BitmapDrawable) immagine).getBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();

            // Scrivi la lunghezza del byte array e il byte array stesso nel parcel
            parcel.writeInt(byteArray.length);
            parcel.writeByteArray(byteArray);
        } else {
            // Se immagineScheda è null, scrivi -1 per indicare che l'immagine è nulla
            parcel.writeInt(-1);
        }

        parcel.writeString(idYouTubeVideo);
    }
}
