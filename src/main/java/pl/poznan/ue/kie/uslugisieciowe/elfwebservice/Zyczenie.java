/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.poznan.ue.kie.uslugisieciowe.elfwebservice;

/**
 * Klasa przechowuje dane opisujące nadsyłane życzenia.
 * Wykorzystywana jest w klasie ListaZyczeń. Jeśli najpierw otworzyłeś/aś ten plik,
 * na razie może lepiej najpierw obejrzyj plik ListaZyczen.
 * Tutaj po prostu przechowujemy dane, które zostały nadesłane przez klientów naszej usługi.
 */
public class Zyczenie {
    
    // atrybuty klasy, przechowujące poszczególne dane dot. pojedynczego życzenia
    private String dlaKogo;
    private String nazwaPrezentu;
    private String opis;
    private byte[] obrazek;

    /**
     * konstruktor, wywoływany w momencie tworzenia obiektu 
     */
    public Zyczenie(String dlaKogo, String nazwaPrezentu, String opis, byte[] obrazek) {
        this.dlaKogo = dlaKogo;
        this.nazwaPrezentu = nazwaPrezentu;
        this.opis = opis;
        this.obrazek = obrazek;
    }

    public byte[] getObrazek() {
        return obrazek;
    }

    public void setObrazek(byte[] obrazek) {
        this.obrazek = obrazek;
    }

    /**
     * Poniżej mamy gettery i settery. W momencie, gdy chcemy przesłać obiekt...
     * ... za pośrednictwem SOAP, biblioteki, z których korzystamy,  szukają ...
     * ... właśnie metod zaczynających się od get i na tej podstawie określają ...
     * ... jakie dane mają być umieszczone w wygenerowanym XMLu.
     * 
     */

    public String getDlaKogo() {
        return dlaKogo;
    }

    public void setDlaKogo(String dlaKogo) {
        this.dlaKogo = dlaKogo;
    }

    public String getNazwaPrezentu() {
        return nazwaPrezentu;
    }

    public void setNazwaPrezentu(String nazwaPrezentu) {
        this.nazwaPrezentu = nazwaPrezentu;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    } 
    
    
    
}
