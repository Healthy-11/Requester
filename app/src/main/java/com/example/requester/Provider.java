package com.example.requester;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "provider")

public class Provider implements Serializable {

    @ColumnInfo(name = "foref")
    String foref = "";
    @ColumnInfo(name = "fonom")
    String fonom = "";
    @ColumnInfo(name = "foad")
    String foad = "";
    @ColumnInfo(name = "focp")
    String focp = "";
    @ColumnInfo(name = "foville")
    String foville = "";
    @ColumnInfo(name = "foemail")
    String foemail = "";

    @PrimaryKey(autoGenerate = true)
    private int ID;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Provider(){}

    public Provider(String foref, String fonom, String foad, String focp, String foville, String foemail) {
        this.foref = foref;
        this.fonom = fonom;
        this.foad = foad;
        this.focp = focp;
        this.foville = foville;
        this.foemail = foemail;
    }

    public String getForef() {
        return foref;
    }

    public void setForef(String foref) {
        this.foref = foref;
    }

    public String getFonom() {
        return fonom;
    }

    public void setFonom(String fonom) {
        this.fonom = fonom;
    }

    public String getFoad() {
        return foad;
    }

    public void setFoad(String foad) {
        this.foad = foad;
    }

    public String getFocp() {
        return focp;
    }

    public void setFocp(String focp) {
        this.focp = focp;
    }

    public String getFoville() {
        return foville;
    }

    public void setFoville(String foville) {
        this.foville = foville;
    }

    public String getFoemail() {
        return foemail;
    }

    public void setFoemail(String foemail) {
        this.foemail = foemail;
    }
}
