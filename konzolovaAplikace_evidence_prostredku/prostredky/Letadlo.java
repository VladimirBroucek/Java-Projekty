/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prostredky;

/**
 *
 * @author Admin
 */
public class Letadlo extends DopravniProstredek{
    
    private int pocetSedadel;
    private int dolet;
    private int pocetMotoru;
    
    public Letadlo(float hmotnost, String spz){
        super(ProstredekTyp.LETADLO, spz, hmotnost);
    }
    public Letadlo(String spz,float hmotnost, int pocetSedadel, int dolet, int pocetMotoru){
        this(hmotnost, spz);
        this.dolet = dolet;
        this.pocetMotoru = pocetMotoru;
        this.pocetSedadel = pocetSedadel;
    }

    public int getPocetSedadel() {
        return pocetSedadel;
    }

    public void setPocetSedadel(int pocetSedadel) {
        this.pocetSedadel = pocetSedadel;
    }

    public int getDolet() {
        return dolet;
    }

    public void setDolet(int dolet) {
        this.dolet = dolet;
    }

    public int getPocetMotoru() {
        return pocetMotoru;
    }

    public void setPocetMotoru(int pocetMotoru) {
        this.pocetMotoru = pocetMotoru;
    }
    @Override
    public String toString(){
        return super.toString() + ", pocetSedadel=" + pocetSedadel + ", pocetMotoru=" + pocetMotoru + ", dolet=" + dolet;
    }
    
    
}
