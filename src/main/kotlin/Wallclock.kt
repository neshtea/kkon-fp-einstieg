// Eine Wallclock besteht aus
// - stunden
// - minuten
data class Wallclock(val hour: Int, val minutes: Int)

/**
 * Minuten nach Mitternacht berechnen.
 */
fun minutenNachMitternacht(wallclock: Wallclock): Int =
    60 * wallclock.hour + wallclock.minutes

/**
 * Aus Minuten seit mitternacht die uhrzeit berechnen
 */
fun minutesSinceMidnightToWallclock(minutesSinceMidnight: Int): Wallclock =
    Wallclock(minutesSinceMidnight / 60,
        minutesSinceMidnight % 60
        )