Beskrivelse av app:
Appen er et Flappybird-spill. Spillet går ut på styre en fugl gjennom ulike søyler og få så høyest poengsum som mulig. Brukeren kan hoppe med mellomrom-tasten og gravitasjonen vil samtidig dra fuglen ned. Søylene kommer inn fra høyre side og har et randomisert gap mellom dem.

Diagram:
![alt text](image.png)

Kunne også lagt til ScoreManager og scoreInterface, men ble litt trangt om plassen så forklarer det her i stedet.
flappybirdController ville hatt en åpen pil mot ScoreManager og ScoreManager ville hatt en stiplet åpen pil mot scoreInterface.

Spørsmål:

1. Hvilke deler av pensum i emnet dekkes i prosjektet, og på hvilken måte? (For
eksempel bruk av arv, interface, delegering osv.)

Jeg bruker interface ved å ha en scoreInterface klasse som scoreManager arver fra. Dette gjør at jeg kan definere spesifikke metoder som skal implementeres i scoreManager, og sikrer at disse metodene alltid er tilgjengelige uansett hvordan scoreManager senere utvides eller endres. Det inngår også en del delegering i koden min. Kontroller-klassen bruker metodene fra Bird, Pillars og ScoreManager for å kjøre programmet, noe som viser hvordan ansvaret blir delegert til de riktige klassene. Dette gjør koden mer modulær og lettere å vedlikeholde, siden logikken for hvert element er samlet i sine respektive klasser. Bruker også innkapsling i alle klassene mine for å hindre uautorisert tilgang til feltene mine. Felter er deklarert som private, og jeg bruker gettere og settere for å kontrollere tilgangen til dem. Dette beskytter integriteten til dataene og gjør det mulig å validere eller manipulere data på en kontrollert måte. I tillegg benytter jeg meg av objekter og klasser som er sentrale konsepter i pensum, der jeg bruker JavaFX-klasser som ImageView til å representere både fuglen og pilarene grafisk. 

2. Dersom deler av pensum ikke er dekket i prosjektet deres, hvordan kunne dere brukt
disse delene av pensum i appen?

Brukte ikke streams i koden min, men kunne enkelt ha byttet ut flere av for-løkkene mine med det. For eksempel kunne jeg ha brukt streams til å iterere over pilarer i stedet for for-løkker, noe som ville gjort koden mer deklarativ og ryddig. Brukte heller ingen funksjonelle grensesnitt, men kunne for eksempel brukt binaryoperator og math.max for å sjekke om currentscore > highscore (eller motsatt). Jeg kunne også ha benyttet meg av Optional for å unngå nullverdier når jeg leser inn highscore fra fil, noe som ville gjort koden mer robust og mindre utsatt for nullpointer exceptions. Videre kunne jeg ha brukt mer av Collections API-et, som Set eller Map, for å holde styr på unike objekter eller lagre flere spilleres poengsummer med navn som nøkler. Interfaces og arv kunne også vært brukt mer konsekvent for å sikre bedre struktur og gjenbrukbarhet i koden. Bruken av lambda-uttrykk kunne ha gjort koden mer kompakt og lesbar, spesielt når det gjelder eventhåndtering eller enkle operasjoner som å oppdatere poengsummen. I tillegg kunne parallelle streams blitt brukt for å forbedre ytelsen dersom jeg ønsket å gjøre flere operasjoner samtidig, som for eksempel å oppdatere flere visuelle elementer uavhengig av hverandre. Ved å implementere flere av disse delene fra pensum, kunne jeg ha gjort koden mer moderne, effektiv og robust.

3. Hvordan forholder koden deres seg til Model-View-Controller-prinsippet? (Merk: det
er ikke nødvendig at koden er helt perfekt i forhold til Model-View-Controller
standarder. Det er mulig (og bra) å reflektere rundt svakheter i egen kode)

Jeg har prøvd mitt beste å få all logikk ut fra kontrolleren og inn i de andre klassene. Syntes jeg har klart det ganske bra ettersom all funksjonalitet og logikk med tanke på fuglen, søylene, bevegelse, kollisjon og poeng ligger utenfor kontrolleren.
Model: Bird, Pillars, ScoreManager representerer data og logikk
View: JavaFX og ImageView-instansene representerer det visuelle (brukergrensesnittet)
Controller: styrer samspillet mellom modellene og det visuelle

4. Hvordan har dere gått frem når dere skulle teste appen deres, og hvorfor har dere
valgt de testene dere har? Har dere testet alle deler av koden? Hvis ikke, hvordan
har dere prioritert hvilke deler som testes og ikke? (Her er tanken at dere skal
reflektere rundt egen bruk av tester)

Jeg fokuserte på å teste de mest kritiske delene av applikasjonen:
Bevegelse av objekter:
	•	Bird.java og Pillars.java har ansvar for kontinuerlig bevegelse i spillet.
	•	Jeg testet at fuglen beveger seg korrekt basert på tyngdekraft og hopp (testBirdMovement()).
	•	Jeg testet at søylene beveger seg med riktig hastighet og kan tilbakestilles (testPillarsMove() og testPillarsReset()).
Poengsystemet:
	•	ScoreManager.java er viktig for å spore fremgang og motivere spilleren.
	•	Jeg testet at poeng kan legges til, tilbakestilles, og at høyeste poengsum blir lagret og hentet korrekt (testWriteAndReadHighScore() og testResetScore()).

Hvorfor jeg valgte disse testene:
De delene jeg testet er grunnleggende for spillets funksjonalitet:
	•	Hvis fuglen ikke beveger seg riktig, blir spillet uspillbart.
	•	Hvis pilarene ikke beveger seg, blir det ingen utfordring.
	•	Hvis poengsystemet ikke fungerer, mister spillet sin målsetting.
Jeg ønsket å sikre at hovedfunksjonene fungerer før vi fokuserer på mindre viktige aspekter.

Jeg testet ikke alle delene av koden ettersom det ble veldig vanskelig å håndtere javafx-instansene. Bestemte meg derfor at de testene jeg hadde lagd var nok og fikk testet noe av det viktigste. Skulle gjerne ha testet birdCollides i Pillars-klassen, men fikk det rett og slett ikke til å fungere med JUnit-testene. Utenom akkurat den metoden så mener jeg derimot at jeg har fått testet de viktigste aspektene (som forklart i forrige avsnitt).
