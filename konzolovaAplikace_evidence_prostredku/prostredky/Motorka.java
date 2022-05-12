/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prostredky;

import util.Barva;

/**
 *
 * @author Admin
 */
public class Motorka extends DopravniProstredek{
    
    private Barva barva;
    private int pocetKW;
    private float objem;
    
    public Motorka(float hmotnost, String spz){
        super(ProstredekTyp.MOTORKA, spz, hmotnost);
    }
    
    public Motorka(String spz, float hmotnost, Barva barva, int pocetKW,float objem){
        this(hmotnost, spz);
        this.barva = barva;
        this.pocetKW = pocetKW;
        this.objem = objem;
    }

    public Barva getBarva() {
        return barva;
    }

    public void setBarva(Barva barva) {
        this.barva = barva;
    }

    public int getPocetKW() {
        return pocetKW;
    }

    public void setPocetKW(int pocetKW) {
        this.pocetKW = pocetKW;
    }

    public float getObjem() {
        return objem;
    }

    public void setObjem(float objem) {
        this.objem = objem;
    }
    
    @Override
    public String toString(){
        return super.toString() + ", barva=" + barva + ", počet KW=" + pocetKW + ", Objem=" + objem;
    }
    
    
    
    
    
}
