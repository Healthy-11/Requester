package com.example.requester;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "condo")
public class Condo implements Serializable {

    @ColumnInfo(name = "server_id")
    String serverId = "";
    @ColumnInfo(name = "societe")
    String societe = "";
    @ColumnInfo(name = "cleunik")
    String cleunik = "";
    @ColumnInfo(name = "copro")
    String copro = "";
    @ColumnInfo(name = "nom")
    String nom = "";
    @ColumnInfo(name = "cp")
    String cp = "";
    @ColumnInfo(name = "ville")
    String ville = "";

    @PrimaryKey(autoGenerate = true)
    private int ID;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Condo() {
    }

    public Condo(String serverId, String societe, String cleunik, String copro, String nom, String cp, String ville) {
        this.serverId = serverId;
        this.societe = societe;
        this.cleunik = cleunik;
        this.copro = copro;
        this.nom = nom;
        this.cp = cp;
        this.ville = ville;
    }

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public String getSociete() {
        return societe;
    }

    public void setSociete(String societe) {
        this.societe = societe;
    }

    public String getCleunik() {
        return cleunik;
    }

    public void setCleunik(String cleunik) {
        this.cleunik = cleunik;
    }

    public String getCopro() {
        return copro;
    }

    public void setCopro(String copro) {
        this.copro = copro;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }
}


