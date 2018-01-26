/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.poznan.ue.kie.uslugisieciowe.elfwebservice;

import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 * Usługa sieciowa z okazji Mikołajek - umożliwia nadsyłanie życzeń prezentowych.
 * Usługa udostępnia interfejs SOAP, w którym są metodo do nadsyłania życzenia  ...
 * ... oraz pobierania listy życzeń.
 * 
 * @author student
 */

// Adnotacja serviceName znajdzie się później w adresie URL do usługi sieciowej.
// Jedna klasa odpowiada niejako jednej usłudze siecowej (jednemu plikowi WSDL)...
// ... ale wewnątrz usługi może być kilka operacji, które z perspektywy kodu...
// ... javowego oprogramowywane są przez poszczególne funkcje klasy.
// To, która funkcja w klasie odpowiada za konkretną metodę SOAP, oznaczane ...
// ... jest również za pomocą adnotacji, tym razem @WebMethod(operationName)
// Zwróć uwagę, że oprócz adnotacji, ta klasa tak naprawdę niczym się ...
// ... nie różni od standardowej klasy javowej i można z niej normalnie korzystać
// Odpowiednie biblioteki, z których korzystamy, nadawane adnotacje ...
// ... i odpowiedni sposób uruchomienia (wdrożenia) projektu pozwalają...
// ... po prostu na zdalne wywoływanie naszych javowych metod.
@WebService(serviceName = "ListaZyczen")
public class ListaZyczen {
    
    
    // kolekcja, w której przechowujemy obiekty skonstruowane na podstawie...
    // ... nadchodzących komunikatów na operację zglosPrezent
    // Klasa "Zyczenie" jest zdefiniowana w tym projekcie, w pliku Zyczenie.java
    List<Zyczenie> zyczenia = new ArrayList<>();

    
    /**
     * Metoda zglosPrezent, która przyjmuje życzenia świąteczne i je zapisuje w kolekcji.
     * Metoda ta przyjmuje cztery parametry, z których trzy są tekstowe...
     * ... a czwarta to tablica bajtów. Metoda ta dostępna jest w interfejsie...
     * ... SOAP pod nazwą zglosPrezent (wartość adnotacji @WebMethod(operationName) ...
     * ... a poszczególne dane nadsyłane mają być wewnątrz tagów z nazwiami...
     * ... odpowiadającymi wartościom adnotacji @WebParam(name).
     * Z perspektywy programisty, parametry są dostępne jako normalne...
     * ... parametry wywołania funkcji javowej.
     * Np. linijka  --- @WebParam(name = "dlaKogo") String dlaKogo --- w liście...
     * ... parametrów oznacza, że wartośc nadesłana w komunikacie SOAP wewnątrz tagu dlaKogo...
     * ... ma być dostępna pod zmienną String o nazwie dlaKogo.
     */
    @WebMethod(operationName = "zglosPrezent")
    public String zglosPrezent(
            @WebParam(name = "dlaKogo") String dlaKogo,
            @WebParam(name = "nazwaPrezentu") String nazwaPrezentu,
            @WebParam(name = "opis") String opis,
            @WebParam(name = "obrazek") byte[] obrazek
    ) {
        // Tworzymy instancję klasy Zyczenie, do konstruktora przesyłamy odpowiednie parametry
        Zyczenie z = new Zyczenie(dlaKogo, nazwaPrezentu, opis, obrazek);
        // utworzony obiekt dodaję do listy zyczenia
        // ta lista jest atrybutem obiektu, patrz linijka 40
        // dzięki temu, że dodaję ten obiekt do kolekcji, która jest atrybutem obiektu
        // obiekt nie zniknie po wykonaniu ostatniej linijki w metodzie, ale będzie...
        // ... przechowywany w tej kolekcji tak, długo jak obiekt istnieje
        // (chyba żebyśmy ten obiekt z listy jakoś usuwali, ale tego nie robimy)
        // U nas, obiekt istnieje tak długo, jak długo działa program
        zyczenia.add(z);
        // odsyłamy krótki komunikat zwrotny
        return "OK, w tym momencie w liscie jest zyczen: " + zyczenia.size();
        
    }
    
    /**
     * Operacja SOAP nie musi przyjmować żadnych parametrów.
     * Tak jak tutaj; po wywołaniu operacji o nazwie pobierzWszystkieZyczenia ...
     * ... bez żadnych parametrów, metoda zwróci listę przechowującą obiekty życzeń.
     * Uwaga: zwróć uwagę, że nie musimy w żaden sposób przejmować się tutaj przygotowaniem ...
     * ... odpowiedzi SOAP. Dzięki odpowiedniej konstrukcji klasy Zyczenie ...
     * ... i bibliotekom, z których korzystamy, dla obiektu this.zyczenia zostanie ...
     * ... automatycznie, "w tle" wygenerowana reprezentacja w odpowiednim formacie, ...
     * ... którą można osadzić w wiadomości SOAP.
     * @return 
     */
    @WebMethod(operationName = "pobierzWszystkieZyczenia")
    public List<Zyczenie> pobierzWszystkieZyczenia() {
        return this.zyczenia;
    }
    
    /**
     * Drobna modyfikacja metody pobierzWszystkieZyczenia.
     * Tutaj dodatkowo otrzymujemy parametr zawężający życzenia tylko do tych, ...
     * ... dla których wartość atrybutu "dlaKogo"  była taka, jak nadesłana w żądaniu.
     * @param dlaKogo
     * @return 
     */
    @WebMethod(operationName = "pobierzZyczeniaDlaOsoby")
    public List<Zyczenie> pobierzZyczeniaDlaOsoby(@WebParam(name = "dlaKogo") String dlaKogo) {
        // tworzymy sobie pomocniczą listę, którą zwrócimy w odpowiedzi
        List<Zyczenie> zyczeniaOsoby = new ArrayList<>();
        // iterujemy po wszystkich życzeniach
        for(Zyczenie z: zyczenia)
        {
            // jeśli zgadza się wartość dlaKogo...
            if(dlaKogo.equalsIgnoreCase(z.getDlaKogo()))
                // ... dodajemy obiekt do listy pomocniczej
                zyczeniaOsoby.add(z);
        }
        // zwracamy listę pomocniczą - są tam tylko życzenia z odpowiednią wartością dlaKogo
        return zyczeniaOsoby;
    }
}
