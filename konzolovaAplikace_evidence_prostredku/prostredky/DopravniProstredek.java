/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prostredky;

import java.io.Serializable;
import java.util.Locale;
import java.util.Objects;

/**
 *
 * @author Admin
 */
public abstract class DopravniProstredek implements Serializable{
    
    private static int counter = 0;
    private final int id;
    private final ProstredekTyp type;
    private String spz;
    private float hmotnost;

    protected DopravniProstredek(ProstredekTyp type, int id, String spz) {
        this.id = id;
        this.type = type;
        this.spz = spz;
    }
    public DopravniProstredek(ProstredekTyp type){
        id = counter++;
        this.type = type;
    }
    public DopravniProstredek(ProstredekTyp type, String spz, float hmotnost){
        this(type);
        this.spz = spz;
        this.hmotnost = hmotnost;
    }

    public int getId() {
        return id;
    }

    public ProstredekTyp getType() {
        return type;
    }

    public String getSpz() {
        return spz;
    }
    public void setSpz(String spz){
        Objects.requireNonNull(spz);
        this.spz = spz;
    }

    public float getHmotnost() {
        return hmotnost;
    }
    public void setHmotnost(float hmotnost){
        this.hmotnost = hmotnost;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.type);
        hash = 23 * hash + Objects.hashCode(this.spz);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DopravniProstredek other = (DopravniProstredek) obj;
        if (!Objects.equals(this.spz, other.spz)) {
            return false;
        }
        
        return this.type == other.type;
    }
    
    @Override
    public String toString(){
        return String.format(Locale.UK, "id=%4d %-18s spz=%6s hmotnost=%6.0f",
                id, type.getNazev(),spz,hmotnost);
    }
    
    
}
