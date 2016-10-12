Klassen PageReader har som oppgave å liste ut alle ord som er tilstede på en webside. SearchEngine har to metoder, en for å indeksere en side og en for å søke etter ord på alle websidene som er indeksert.

* Endre SearchEngine slik at den bruker Dependency Injection. PageReader skal injiseres istedetfor å opprettes i konstruktøren.
* I prosjektet finner dere også SearchEngineTest. Lag en metode for å teste funksjonaliteten i SearchEngine.java
* I denne test-metoden, bruk Mockito til å lage en mock av PageReader som returnerer en hardkodet liste med ord som den "har funnet" på en url. Varier responsen i forhold til gitt url. Importer mockito statisk: 

_import static org.mockito.Mockito.*;_

* Lag en ny instans av SearchEngine med PageReader-mock'en som parameter. Indekser to eller flere sider og sjekk med assert() at søkemotoren returnerer url'ene i riktig rekkefølge når man kjører søk. Den siden som har flest instanser av søkeordet skal komme først.
* Sjekk med assert() om søkemotoren tåler at man søker på ord som ikke er indeksert. Fiks SearchEngine hvis det ikke er tilfelle.
 