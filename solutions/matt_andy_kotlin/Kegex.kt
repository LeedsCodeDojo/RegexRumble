package co.abowes.regex

typealias RegexElement = (Char) -> Boolean

fun String.isMatch(s: String): Boolean {
    val regex: List<RegexElement> = compile(this)
    return (0 until s.length).any{
        regex.checkFragment(s.substring(it))
    }
}

fun compile(pattern: String): List<RegexElement> {
    val regex : MutableList<RegexElement> = mutableListOf()
    var ptr = 0
    while (ptr < pattern.length){
        val currentChar = pattern.get(ptr)
        val element = when (currentChar){
                '.' -> {c: Char -> true}
                '\\' -> {
                    ptr++
                    when (pattern.get(ptr)) {
                        'w' -> { c: Char -> c == '_' || c.isLetterOrDigit() }
                        'W' -> { c: Char -> c != '_' && !c.isLetterOrDigit() }
                        'd' -> { c: Char -> c.isDigit() }
                        'D' -> { c: Char -> !c.isDigit() }
                        's' -> { c: Char -> c.isWhitespace() }
                        else -> { c: Char -> false }
                    }
                }
                else -> {c: Char -> c == currentChar}
            }
        regex.add(element)
        ptr++
    }
    return regex.toList()
}

private fun List<RegexElement>.checkFragment( fragment: String) : Boolean{
    return (this.size <= fragment.length)
            && this.zip(fragment.asIterable()).all { it.first.invoke(it.second) }
}
