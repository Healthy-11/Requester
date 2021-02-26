package com.example.requester;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "partowner")

public class Partowner implements Serializable {

    @ColumnInfo(name = "petitre")
    String petitre = "";
    @ColumnInfo(name = "penom")
    String penom = "";
    @ColumnInfo(name = "peprenom")
    String peprenom = "";
    @ColumnInfo(name = "pevoirienom")
    String pevoirienom = "";
    @ColumnInfo(name = "peadresse")
    String peadresse = "";
    @ColumnInfo(name = "peville")
    String peville = "";
    @ColumnInfo(name = "peteldom")
    String peteldom = "";
    @ColumnInfo(name = "peemail")
    String peemail = "";

    @PrimaryKey(autoGenerate = true)
    private int ID;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Partowner(){}

    public Partowner(String petitre, String penom, String peprenom, String pevoirienom, String peadresse, String peville, String peteldom, String peemail) {
        this.petitre = petitre;
        this.penom = penom;
        this.peprenom = peprenom;
        this.pevoirienom = pevoirienom;
        this.peadresse = peadresse;
        this.peville = peville;
        this.peteldom = peteldom;
        this.peemail = peemail;
    }

    public String getPetitre() {
        return petitre;
    }

    public void setPetitre(String petitre) {
        this.petitre = petitre;
    }

    public String getPenom() {
        return penom;
    }

    public void setPenom(String penom) {
        this.penom = penom;
    }

    public String getPeprenom() {
        return peprenom;
    }

    public void setPeprenom(String peprenom) {
        this.peprenom = peprenom;
    }

    public String getPevoirienom() {
        return pevoirienom;
    }

    public void setPevoirienom(String pevoirienom) {
        this.pevoirienom = pevoirienom;
    }

    public String getPeadresse() {
        return peadresse;
    }

    public void setPeadresse(String peadresse) {
        this.peadresse = peadresse;
    }

    public String getPeville() {
        return peville;
    }

    public void setPeville(String peville) {
        this.peville = peville;
    }

    public String getPeteldom() {
        return peteldom;
    }

    public void setPeteldom(String peteldom) {
        this.peteldom = peteldom;
    }

    public String getPeemail() {
        return peemail;
    }

    public void setPeemail(String peemail) {
        this.peemail = peemail;
    }
}
