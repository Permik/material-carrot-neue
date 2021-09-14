package xyz.permik.carrotneue

/**
 * Increase readability of a hexcode by inserting spaces every 4 characters
 *
 * @param unreadable [String] that needs formatting
 * @return [String] that is more readable
 */
fun formatAddHexReadability(unreadable: String): String {
    var formattedString = ""
    var i = 0
    while (i < unreadable.length) {
        formattedString += unreadable.substring(i, i + 4) + " "
        i += 4
    }
    return formattedString
}