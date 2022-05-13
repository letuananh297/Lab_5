class ShapeCollector<T: ColoredShape2d> {
    val listShape: MutableList<T> = mutableListOf()

    fun addShape(shape: T) {
        listShape.add(shape)
    }

    fun minArea(): T? {
        return if (listShape.isEmpty())
            null
        else {
            var min = listShape[0]
            for (temp in listShape) {
                if (temp.calcArea() < min.calcArea()) {
                    min = temp
                }
            }
            return min
        }
    }

    fun maxArea(): T? {
        return if (listShape.isEmpty())
            null
        else {
            var max = listShape[0]
            for (temp in listShape) {
                if (temp.calcArea() > max.calcArea()) {
                    max = temp
                }
            }
            return max
        }
    }

    fun sumArea(): Double {
        return if (listShape.isEmpty())
            0.0
        else {
            var sum = 0.0
            for (temp in listShape) {
                sum += temp.calcArea()
            }
            return sum
        }
    }

    fun shapesWithBorderColor(color: Color): List<T> {
        return if (listShape.isEmpty())
            emptyList()
        else {
            val newList: MutableList<T> = mutableListOf()
            for (temp in listShape) {
                if (temp.borderColor == color)
                    newList.add(temp)
            }
            return newList
        }
    }

    fun shapesWithFillColor(color: Color): List<T> {
        return if (listShape.isEmpty())
            emptyList()
        else {
            val newList: MutableList<T> = mutableListOf()
            for (temp in listShape) {
                if (temp.fillColor == color)
                    newList.add(temp)
            }
            return newList
        }
    }

    fun allShapes(): List<T> {
        return listShape.toList()
    }

    fun numberShapes(): Int {
        var count = 0
        for (temp in listShape) {
            count += 1
        }
        return count
    }

    fun shapesGroupedByBorderColor(): Map<Color, List<T>> {
        return if (listShape.isEmpty())
            emptyMap()
        else {
            val map = mutableMapOf<Color, List<T>>()
            for (shape in listShape) {
                map[shape.borderColor] = shapesWithBorderColor(shape.borderColor)
            }
            return map
        }
    }

    fun shapesGroupedByFillColor(): Map<Color, List<T>> {
        return if (listShape.isEmpty())
            emptyMap()
        else {
            val map = mutableMapOf<Color, List<T>>()
            for (shape in listShape) {
                map[shape.fillColor] = shapesWithFillColor(shape.fillColor)
            }
            return map
        }
    }

    inline fun <reified Type> shapesWithType(): List<T> {
        return if (listShape.isEmpty())
            emptyList()
        else {
            val newList = mutableListOf<T>()
            for (shape in listShape) {
                if (shape is Type) {
                    newList.add(shape)
                }
            }
            return newList
        }
    }

    fun addAll(shapes: ShapeCollector<T>) {
        listShape += shapes.allShapes()
    }

    fun getSorted(comparator: Comparator<T>): List<T> {
        return listShape.sortedWith(comparator)
    }
}