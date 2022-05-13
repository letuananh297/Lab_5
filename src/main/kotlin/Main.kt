fun main() {
    val red = Color(255.0, 0.0, 0.0)
    val green = Color(0.0, 255.0, 0.0)
    val blue = Color(0.0, 0.0, 255.0)

    val shapeCollector = ShapeCollector<ColoredShape2d>()
    shapeCollector.addShape(Circle(2.0, red, green))
    shapeCollector.addShape(Circle(9.0, green, blue))
    shapeCollector.addShape(Square(7.0, blue, red))
    shapeCollector.addShape(Rectangle(6.0, 4.0, red, blue))
    shapeCollector.addShape(Rectangle(5.0, 2.0, blue, green))
    shapeCollector.addShape(Triangle(3.0, 4.0, 5.0, green, red))

    println("All shapes in collection: \n${shapeCollector.allShapes()}")

    println("Shape with min area:\n${shapeCollector.minArea()}")

    println("Shape with max area:\n${shapeCollector.maxArea()}")

    println("Sum area of all shape in collection: ${shapeCollector.sumArea()}")

    println("All shapes with red border color: \n${shapeCollector.shapesWithBorderColor(red)}")

    println("All shapes with blue fill color: \n${shapeCollector.shapesWithFillColor(blue)}")

    println("Number shapes in collection: ${shapeCollector.numberShapes()}")

    println("Group by borderColor: ${shapeCollector.shapesGroupedByBorderColor()}\n")

    println("Group by fillColor: ${shapeCollector.shapesGroupedByFillColor()}\n")

    println("Group by type: ${shapeCollector.shapesWithType<Rectangle>()}")

    val shapeCollector2 = ShapeCollector<ColoredShape2d>()
    shapeCollector2.addShape(Circle(6.0, red, green))
    shapeCollector2.addShape(Square(4.0, blue, red))
    shapeCollector.addAll(shapeCollector2)
    println("All shapes in collection after adding: \n${shapeCollector.allShapes()}")

    println("All shapes sorted by area in collection: \n${shapeCollector.getSorted(compareBy { it.calcArea() })}")
}