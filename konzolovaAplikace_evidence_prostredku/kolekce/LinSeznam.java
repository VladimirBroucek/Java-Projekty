package kolekce;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 *
 * @author Admin
 */
public class LinSeznam<E> implements Seznam<E> {

    private class Prvek {

        final E data;
        Prvek dalsi;

        public Prvek(E data, Prvek dalsi) {
            this.data = data;
            this.dalsi = dalsi;
        }
    }

    private Prvek prvni;
    private Prvek aktualni;
    private Prvek posledni;
    private int pocet;

    public LinSeznam() {
        _zrus();
    }

    @Override
    public void nastavPrvni() throws KolekceException {
        kontrolaPrazdnehoSeznamu();
        aktualni = prvni;
    }

    @Override
    public void nastavPosledni() throws KolekceException {
        kontrolaPrazdnehoSeznamu();
        aktualni = posledni;
    }

    @Override
    public boolean dalsi() throws KolekceException {
        kontrolaNastaveniAktualnihoPrvku();
        if(aktualni == null){
            throw new KolekceException();
        }
        if (aktualni.dalsi != null) {
            aktualni = aktualni.dalsi;
            return true;
        }
        return false;
    }

    @Override
    public void vlozPrvni(E data) {
        Objects.requireNonNull(data);
        if (prvni == null) {
            prvni = new Prvek(data, null);
            posledni = prvni;
        } else {
            prvni = new Prvek(data, prvni);
        }
        pocet++;
    }

    @Override
    public void vlozNaKonec(E data) {
        Objects.requireNonNull(data);
        if (prvni == null) {
            posledni = new Prvek(data, null);
            prvni = posledni;
        } else {
            posledni.dalsi = new Prvek(data, null);
            posledni = posledni.dalsi;
        }
        pocet++;
    }

    @Override
    public void vlozZaAktualni(E data) throws KolekceException {
        Objects.requireNonNull(data);
        kontrolaNastaveniAktualnihoPrvku();
        if (aktualni == posledni) {
            aktualni.dalsi = new Prvek(data, null);
            posledni = aktualni.dalsi;
        } else {
            Prvek prvek = aktualni;
            aktualni.dalsi = new Prvek(data, prvek.dalsi);
        }
        pocet++;
    }

    @Override
    public boolean jePrazdny() {
        return prvni == null;
    }

    @Override
    public E dejPrvni() throws KolekceException {
        kontrolaPrazdnehoSeznamu();
        return prvni.data;
    }

    @Override
    public E dejPosledni() throws KolekceException {
        kontrolaPrazdnehoSeznamu();
        return posledni.data;
    }

    @Override
    public E dejAktualni() throws KolekceException {
        kontrolaNastaveniAktualnihoPrvku();
        return aktualni.data;
    }

    @Override
    public E dejZaAktualnim() throws KolekceException {
        if(aktualni == null || aktualni.dalsi == null){
            throw new KolekceException();
        }
        return aktualni.dalsi.data;
    }

    @Override
    public E odeberAktualni() throws KolekceException {
        kontrolaNastaveniAktualnihoPrvku();
        if (aktualni == prvni) {
            prvni = null;
        }
        Prvek prvek = aktualni;
        aktualni = aktualni.dalsi;
        prvni = aktualni;

        pocet--;
        return prvek.data;
    }

    @Override
    public E odeberZaAktualnim() throws KolekceException {
        kontrolaNastaveniAktualnihoPrvku();
        if (aktualni == posledni) {
            throw new KolekceException();
        }
        E data = aktualni.dalsi.data;
        aktualni.dalsi = aktualni.dalsi.dalsi;
        pocet--;
        return data;
    }

    @Override
    public int size() {
        return pocet;
    }

    @Override
    public void zrus() {
        _zrus();
    }

    private void _zrus() {
        prvni = null;
        posledni = null;
        aktualni = null;
        pocet = 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator() {
            Prvek ukazatel = prvni;

            @Override
            public boolean hasNext() {
                return ukazatel != null;
            }

            @Override
            public Object next() {
                if (hasNext()) {
                    E data = ukazatel.data;
                    ukazatel = ukazatel.dalsi;
                    return data;
                }
                throw new NoSuchElementException();
            }
        };
    }

    private void kontrolaPrazdnehoSeznamu() throws KolekceException {
        if (prvni == null) {
            throw new KolekceException();
        }
    }

    private void kontrolaNastaveniAktualnihoPrvku() throws KolekceException {
        if (aktualni == null) {
            throw new KolekceException();
        }
    }

    public int getPocet() {
        return pocet;
    }
    
    

}
