## Konstruktionsanleitung 0

Gehe bei der Konstruktion einer Funktion in folgender Reihenfolge vor:

1. Kurzbeschreibung
2. Datenanalyse
3. Signatur-Deklaration
4. Tests
5. Gerüst
6. Schablonen
7. Rumpf

# Konstruktionsanleitung 1 (Kurzbeschreibung)

Schreibe für die Funktion zunächst einen Kommentar, der ihren Zweck beschreibt.
Ein Satz, der auf eine Zeile passen sollte, reicht.  Beispiel:

## Konstruktionsanleitung 2 (Signatur-Deklaration)

Schreibe für die Funktion direkt unter die Kurzbeschreibung eine
Signatur-Deklaration. Da- zu denke Dir zunächst einen möglichst aussagekräftigen
Namen aus. Überlege dann, welche Sorten die Ein- und Ausgaben haben und schreibe
dann eine Signatur, welche die Ein- und Ausgaben der Funktion möglichst präzise
beschreiben. Beispiel:

```kotlin
fun billigStrom(kWh: Int): Double = TODO()
```

Achte bei den Zahlen-Signaturen besonders auf eine möglichst präzise
Signatur. Bei der Funktion `billigStrom` wäre auch die Signatur

```kotlin
fun billigStrom(kWh: Double): Double = TODO()
```

korrekt, aber nicht so genau.


## Konstruktionsanleitung 3 (Tests)

Schreibe unter die Signatur Tests für die Funktion. Denke Dir dafür möglichst
einfache, aber auch möglichst interessante Beispiele für Aufrufe der Funktion
aus und lege fest, was dabei herauskommen soll. Mache aus den Beispielen Tests
mit `assertEquals`. Beispiel:

```kotlin
fun billigStrom() {
        assertEquals(4.9, billigStrom(0), 0.01)
        assertEquals(6.8, billigStrom(10), 0.01)
        assertEquals(8.7, billigStrom(20), 0.01)
        assertEquals(10.6, billigStrom(30), 0.01)
    }
```

Achte darauf, dass die Tests dafür sorgen, dass der Code Deiner Funktion durch
die Tests vollständig abgedeckt wird.


## Konstruktionsanleitung 5 (Rumpf)

Als letzten Schritt fülle mit Hilfe des Wissens über das Problem den Rumpf der
Funktion aus.

```kotlin
fun billigStrom(kWh: Int): Double = 4.90 + kWh * 0.19
```
## Konstruktionsanleitung 7 (Fallunterscheidung: Datenanalyse)

Versuche, für die Datendefinition eine Formulierung "..ist eins der folgenden"
zu finden. Wenn das möglich ist, beschreibt Deine Datendefinition eine
Fallunterscheidung. Schreibe dann eine Auflistung aller Fälle, jeder Fall auf
eine separate Zeile:

```kotlin
// Ein X ist eines der folgenden:
// - Fall 1
// - Fall 2
// - ...
// - Fall n
```

## Konstruktionsanleitung 8 (Aufzählung: Datenanalyse)

Falls Deine Datendefinition eine Fallunterscheidung beschreibt und jeder der
Fälle nur aus einem einzelnen Wert besteht, handelt es sich um eine /Aufzählung/.

Schreibe für jede Aufzählung eine Signatur-Defnition der Form:

```kotlin
enum class Direction {
    ...
}
```

Achte darauf, dass die Anzahl der Fälle der Signatur-Definition der Anzahl der
Fälle der Datendefinition entspricht.

## Konstruktionsanleitung 9 (Schablone)

Wenn Du das Gerüst fertiggestellt hast, benutze die Signatur und die
dazugehörigen Datendefitionen, um Konstruktionsanleitungen mit ein oder
mehreren Schablonen auszuwählen und übertrage die Elemente der Schablonen in den
Rumpf der Funktion.

## Konstruktionsanleitung 10 (Fallunterscheidung: Schablone)

Wenn Du eine Funktion schreibst, die eine Fallunterscheidung als Eingabe
verarbeitet, schreibe als Schablone in den Rumpf eine Verzweigung mit so
vielen Zweigen, wie es in der Fallunterscheidung Fälle gibt, nach folgendem
Muster:

```kotlin
fun f(val: <A>): <B> =
  when (val) {
    ... -> ...
	...
	... -> ...
  }
```

Schreibe danach Bedingungen in die Zweige, welche die einzelnen Fälle
voneinander unterscheiden.