data class Color(val red: Double, val green: Double, val blue: Double) {
    override fun toString(): String {
        return "ColorRGB($red, $green, $blue)"
    }
}

interface Shape2d {
    fun calcArea(): Double
}

interface ColoredShape2d : Shape2d {
    val borderColor: Color
    val fillColor: Color
}
