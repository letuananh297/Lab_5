import kotlin.math.sqrt

data class Circle(
    val r: Double,
    override val borderColor: Color,
    override val fillColor: Color
) : ColoredShape2d {
    init {
        require(r > 0)
    }

    override fun calcArea(): Double {
        return Math.PI * r * r
    }

    override fun toString(): String {
        return "Circle($r, $borderColor, $fillColor)"
    }
}

data class Square(
    val a: Double,
    override val borderColor: Color,
    override val fillColor: Color
) : ColoredShape2d {
    init {
        require(a > 0)
    }

    override fun calcArea(): Double {
        return a * a
    }

    override fun toString(): String {
        return "Square($a, $borderColor, $fillColor)"
    }
}

data class Rectangle(
    val a: Double,
    val b: Double,
    override val borderColor: Color,
    override val fillColor: Color
) : ColoredShape2d {
    init {
        require(a > 0 && b > 0)
    }

    override fun calcArea(): Double {
        return a * b
    }

    override fun toString(): String {
        return "Rectangle($a, $b, $borderColor, $fillColor)"
    }
}

data class Triangle(
    val a: Double,
    val b: Double,
    val c: Double,
    override val borderColor: Color,
    override val fillColor: Color
) : ColoredShape2d {
    init {
        require(a > 0 && b > 0 && c > 0)
        require(a + b > c && b + c > a && c + a > b)
    }

    private val p: Double = (a + b + c) / 2
    override fun calcArea(): Double {
        return sqrt(p * (p - a) * (p - b) * (p - c))
    }

    override fun toString(): String {
        return "Triangle($a, $b, $c, $borderColor, $fillColor)"
    }
}