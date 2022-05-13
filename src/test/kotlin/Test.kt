import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

val red = Color(255.0, 0.0, 0.0)
val green = Color(0.0, 255.0, 0.0)
val blue = Color(0.0, 0.0, 255.0)

val circle1: Circle = Circle(2.0, red, green)
val circle2: Circle = Circle(9.0, green, blue)
val square: Square = Square(7.0, blue, red)
val rectangle1: Rectangle = Rectangle(6.0, 4.0, red, blue)
val rectangle2: Rectangle = Rectangle(5.0, 2.0, blue, green)
val triangle: Triangle = Triangle(3.0, 4.0, 5.0, green, red)

internal class ShapeCollectorTest {
    @Test
    fun testAddShape() {
        val collector = ShapeCollector<ColoredShape2d>()
        assertSame(collector.listShape.toList(), emptyList<ColoredShape2d>())

        collector.addShape(circle1)
        assertNotSame(collector.listShape.toList(), emptyList<ColoredShape2d>())
        assertSame(collector.listShape[0], circle1)
    }

    @Test
    fun testMinArea() {
        val collector1 = ShapeCollector<ColoredShape2d>()
        assertNull(collector1.minArea())

        val collector2 = ShapeCollector<ColoredShape2d>()
        collector2.addShape(circle1)
        collector2.addShape(square)
        collector2.addShape(rectangle1)
        collector2.addShape(triangle)

        assertNotNull(collector2.minArea())
        assertEquals(collector2.minArea(), collector2.listShape.minByOrNull { it.calcArea() })
    }

    @Test
    fun testMaxArea() {
        val collector1 = ShapeCollector<ColoredShape2d>()
        assertNull(collector1.maxArea())

        val collector2 = ShapeCollector<ColoredShape2d>()
        collector2.addShape(circle1)
        collector2.addShape(square)
        collector2.addShape(rectangle1)
        collector2.addShape(triangle)

        assertNotNull(collector2.maxArea())
        assertEquals(collector2.maxArea(), collector2.listShape.maxByOrNull { it.calcArea() })
    }

    @Test
    fun testSumArea() {
        val collector = ShapeCollector<ColoredShape2d>()
        collector.addShape(circle1)
        collector.addShape(square)
        collector.addShape(rectangle1)
        collector.addShape(triangle)

        val sum = circle1.calcArea() + square.calcArea() + rectangle1.calcArea() + triangle.calcArea()
        assertEquals(collector.sumArea(), sum)
    }

    @Test
    fun testShapesWithBorderColor() {
        val collector1 = ShapeCollector<ColoredShape2d>()
        assertSame(collector1.shapesWithBorderColor(red), emptyList<ColoredShape2d>())

        val collector2 = ShapeCollector<ColoredShape2d>()
        collector2.addShape(circle1)
        collector2.addShape(square)
        collector2.addShape(rectangle1)
        collector2.addShape(triangle)
        assertNotSame(collector2.shapesWithBorderColor(red), emptyList<ColoredShape2d>())
        assertEquals(collector2.shapesWithBorderColor(red), collector2.listShape.filter { it.borderColor == red })
    }

    @Test
    fun testShapesWithFillColor() {
        val collector1 = ShapeCollector<ColoredShape2d>()
        assertSame(collector1.shapesWithFillColor(green), emptyList<ColoredShape2d>())

        val collector2 = ShapeCollector<ColoredShape2d>()
        collector2.addShape(circle1)
        collector2.addShape(square)
        collector2.addShape(rectangle2)
        collector2.addShape(triangle)
        assertNotSame(collector2.shapesWithFillColor(green), emptyList<ColoredShape2d>())
        assertEquals(collector2.shapesWithFillColor(green), collector2.listShape.filter { it.fillColor == green })
    }

    @Test
    fun testAllShapes() {
        val collector1 = ShapeCollector<ColoredShape2d>()
        assertSame(collector1.allShapes(), emptyList<ColoredShape2d>())

        val collector2 = ShapeCollector<ColoredShape2d>()
        collector2.addShape(circle1)
        collector2.addShape(square)
        collector2.addShape(rectangle2)
        collector2.addShape(triangle)

        val result = listOf(circle1, square, rectangle2, triangle)
        assertEquals(collector2.allShapes(), result)
    }

    @Test
    fun testNumberShapes() {
        val collector = ShapeCollector<ColoredShape2d>()
        collector.addShape(circle1)
        collector.addShape(square)
        collector.addShape(rectangle2)
        collector.addShape(triangle)

        assertEquals(collector.numberShapes(), collector.listShape.size)
    }

    @Test
    fun testShapeGroupedByBorderColor() {
        val collector1 = ShapeCollector<ColoredShape2d>()
        assertSame(collector1.shapesGroupedByBorderColor(), emptyMap<Color, List<ColoredShape2d>>())

        val collector2 = ShapeCollector<ColoredShape2d>()
        collector2.addShape(circle1)
        collector2.addShape(circle2)
        collector2.addShape(rectangle1)
        collector2.addShape(triangle)
        assertNotSame(collector2.shapesGroupedByBorderColor(), emptyMap<Color, List<ColoredShape2d>>())
        assertEquals(collector2.shapesGroupedByBorderColor(), collector2.listShape.groupBy { it.borderColor })
    }

    @Test
    fun testShapeGroupedByFillColor() {
        val collector1 = ShapeCollector<ColoredShape2d>()
        assertSame(collector1.shapesGroupedByFillColor(), emptyMap<Color, List<ColoredShape2d>>())

        val collector2 = ShapeCollector<ColoredShape2d>()
        collector2.addShape(square)
        collector2.addShape(circle2)
        collector2.addShape(rectangle1)
        collector2.addShape(triangle)
        assertNotSame(collector2.shapesGroupedByFillColor(), emptyMap<Color, List<ColoredShape2d>>())
        assertEquals(collector2.shapesGroupedByFillColor(), collector2.listShape.groupBy { it.fillColor })
    }

    @Test
    fun testShapesWithType() {
        val collector1 = ShapeCollector<ColoredShape2d>()
        assertSame(collector1.shapesWithType<Circle>(), emptyList<ColoredShape2d>())

        val collector2 = ShapeCollector<ColoredShape2d>()
        collector2.addShape(square)
        collector2.addShape(circle2)
        collector2.addShape(circle2)
        collector2.addShape(triangle)
        assertNotSame(collector2.shapesWithType<Circle>(), emptyList<ColoredShape2d>())
        assertEquals(collector2.shapesWithType<Circle>(), collector2.listShape.filterIsInstance<Circle>())
    }

    @Test
    fun testAddAll() {
        val collector1 = ShapeCollector<ColoredShape2d>()
        collector1.addShape(circle1)
        collector1.addShape(square)

        val collector2 = ShapeCollector<ColoredShape2d>()
        collector2.addShape(rectangle1)
        collector2.addShape(triangle)

        collector1.addAll(collector2)

        assertEquals(collector1.listShape[0], circle1)
        assertEquals(collector1.listShape[1], square)
        assertEquals(collector1.listShape[2], rectangle1)
        assertEquals(collector1.listShape[3], triangle)
    }

    @Test
    fun testGetSorted() {
        val collector1 = ShapeCollector<ColoredShape2d>()
        collector1.addShape(square)
        collector1.addShape(rectangle1)
        collector1.addShape(rectangle2)
        collector1.addShape(triangle)

        val list = collector1.getSorted(compareBy{it.calcArea()})
        assertEquals(list[0], triangle)
        assertEquals(list[1], rectangle2)
        assertEquals(list[2], rectangle1)
        assertEquals(list[3], square)
    }
}