// Wir fangen extrem simpel an und arbeiten uns langsam nach oben.
// - Das allerwichtigste zuerst: wir wollen Programme _immer_ nach
//   dem selben Muster schreiben.
// - Diese Muster nennen wir "Konstruktionsanleitung"

/**
 * | Tarif              | Grundgebühr pro Monat | Verbrauchspreis pro kWh |
 * |--------------------+-----------------------+-------------------------|
 * | "Billig-Strom"     | € 4.90                | € 0.19                  |
 * | "Watt fuer Wenig"  | € 8.20                | € 0.16                  |
 */
//1. Kurzbeschreibung
//2. Datenanalyse
//3. Signatur-Deklaration
//4. Tests
//5. Gerüst
//6. Schablonen
//7. Rumpf
/**
 * Gesamtkosten für billig-strom zu berechen pro verbrauch
 */
fun billigStrom(kWh: Int): Double =
    stromtarifRechnungsbetrag(4.90, 0.19, kWh)
    //4.90 + kWh * 0.19

/**
 * Gesamtkosten für watt für wenig berechnen pro verbrauch
 */
fun wattFuerWenig(kWh: Int): Double =
    stromtarifRechnungsbetrag(8.20, 0.16, kWh)
    //8.20 + kWh * 0.16

/**
 * Gesamtkosten für einen bestimmten Vertrag berechnen.
 */
fun stromtarifRechnungsbetrag(grundgebuehr: Double,
                              preisProKWh: Double,
                              kWh: Int): Double =
    grundgebuehr + kWh * preisProKWh