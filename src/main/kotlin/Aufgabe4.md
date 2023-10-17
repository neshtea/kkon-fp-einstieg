Ein Supermarkt möchte seine Waren in einem Programm verwalten. Es gibt drei
Warenklassen:

- *Essen* beschrieben durch einen Namen, den Stückpreis, das
  Mindesthaltbarkeitsdatum und den aktuellen Bestand im Supermarkt
- *Getränke* beschrieben durch einen Namen, den Stückpreis, das
  Mindesthaltbarkeitsdatum und den Bestand. Zusätzlich muss hier noch
  festgehalten werden, ob Pfand verlangt wird.
- *Sonstige* beschrieben durch einen Namen, den Stückpreis und den Bestand.

Aufgabe:

1. Führe eine Datenanalyse durch und erstelle Daten- und Record-Definitionen.
2. Schreibe eine Funktion =stückpreis=, die eine Warenklasse akzeptiert und den
   Stückpreis zurückgibt.
3. Schreibe eine Funktion =buchen=, die eine Warenklasse und die Anzahl der
   abzubuchenden Exemplare akzeptiert, den Bestand der Warenklasse reduziert und
   die Warenklasse zurückgibt. Falls mehr Exemplare gefordert werden, als in der
   Warenklasse vorhanden sind, soll der Bestand auf 0 gesetzt werden.
4. Schreibe eine Funktion =istHaltbar=, die eine Warenklasse und ein Datum
   akzeptiert und =true= zurückgibt, falls das MHD nicht überschritten wurde.
   Falls kein MHD bekannt ist, soll =true= zurück gegeben werden.