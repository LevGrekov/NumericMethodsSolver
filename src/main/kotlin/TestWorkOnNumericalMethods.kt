
import math.Polynomial
import math.SystemSolver
import ru.levgrekov.polynomial.ru.levgrekov.polynomial.math.LagrangePolynomial
import ru.levgrekov.polynomial.ru.levgrekov.polynomial.math.NewtonPolynomial2
import ru.levgrekov.polynomial.ru.levgrekov.polynomial.math.calculateDerivativeAtPoint
import ru.levgrekov.polynomial.ru.levgrekov.polynomial.math.writeTextToFile
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.pow


class TestWorkOnNumericalMethods(val a: Double, val b: Double, val ownFunction: (Double) -> Double,val x1 : Double,val x2:Double,val x3:Double,val fileName : String) {

    private val controlPoints = mapOf(1 to x1, 2 to x2, 3 to x3)
    fun firstExercise1(){

        val firstXY = getPoints({ k -> a + k * (b-a)/4 },0,4)

        val firstLagrangePolynomial = LagrangePolynomial(firstXY,fileName)

        writeTextToFile("Вычислим L5(f, x) в контрольных точках xi, i = 1..3 и сравним со значениями f(xi)\n",fileName)
        for (i in controlPoints.keys){
            val xInControlPoint = firstLagrangePolynomial(controlPoints[i]!!).also {
                writeTextToFile("Подставим x$i в полином: L(f,x$i) = $it",fileName)
            }
            val xInFunc = ownFunction(controlPoints[i]!!)
            val epsilon = xInFunc - xInControlPoint
            writeTextToFile("r$i = f(x$i) - L(f,x$i) = $xInFunc - $xInControlPoint = $epsilon",fileName)
        }

    }
    fun firstExercise2(){
        writeTextToFile("\n\nПункт 2",fileName)
        val secondXY = getPoints({k -> ((b-a)/2.0) * cos((PI * (2.0*k-1.0))/10.0) + (b+a)/2.0},1,5)
        val secondLagrangePolynomial = LagrangePolynomial(secondXY,fileName)
        writeTextToFile("Вычислим L5(f, x) в контрольных точках xi, i = 1..3 и сравним со значениями f(xi)\n",fileName)

        for (i in controlPoints.keys){
            val xInControlPoint = secondLagrangePolynomial(controlPoints[i]!!).also {
                writeTextToFile("Подставим x$i в полином: L(f,x$i) = $it",fileName)
            }
            val xInFunc = ownFunction(controlPoints[i]!!)
            val epsilon = xInFunc - xInControlPoint
            writeTextToFile("r$i = f(x$i) - L(f,x$i) = $xInFunc - $xInControlPoint = $epsilon",fileName)
        }
        val toFindMax = secondLagrangePolynomial.findW()
        writeTextToFile("$toFindMax",fileName)
    }
    fun secondExercise(){
        writeTextToFile("\nНаёдм Полином Ньютона.",fileName)
        writeTextToFile("Найдем базисные полиномы и разделенные разности для каждого слогаемого",fileName)
        writeTextToFile("Базисные Полиномы будем искать используя предыдущие значения",fileName)
        val points = getPoints({ k -> a + k * (b-a)/4 },0,4)
        val np = NewtonPolynomial2(points,fileName)
        writeTextToFile("Конечный Полином Ньютона будет иметь вид: $np",fileName)

    }

    fun thirdExercise(){
        writeTextToFile("\n\n 3 Задание",fileName)
        val points =
            getPoints({ k -> a + k * (b-a)/4.0 },0,4)
        val lagrange = LagrangePolynomial(points,fileName)
        val w = lagrange.findW()

        val h4List = mutableListOf<Double>()
        points.keys.forEach{
            val funcD =
                calculateDerivativeAtPoint(ownFunction,it,10e-5)
            val l5d = lagrange.derivative(1)(it)
            val wd = w.derivative(1)(it)
            h4List.add( (funcD - l5d)/wd )
         }

        val system = generateMatrix(points)
        val solution = SystemSolver.solveLinearSystem(system,h4List.toDoubleArray())
        val H4 = Polynomial(*solution)
        val H9 = lagrange + w * H4
        writeTextToFile("H4 = $H4",fileName)
        writeTextToFile("H9 = $H9",fileName)
        println(H9)
        writeTextToFile("Вычисляем погрешности",fileName)
        for (i in controlPoints.keys){
            val xInControlPoint = H9(controlPoints[i]!!)
            val xInFunc = ownFunction(controlPoints[i]!!)
            val epsilon = xInFunc - xInControlPoint
            writeTextToFile("r_$i = f'(x^($i)) - H'_9(f,x) = $xInFunc - $xInControlPoint = $epsilon",fileName)
        }
        for (i in controlPoints.keys){
            val xInControlPoint = H9.derivative(1)(controlPoints[i]!!)
            val xInFunc = calculateDerivativeAtPoint(ownFunction,controlPoints[i]!!)
            val epsilon = xInFunc - xInControlPoint
            writeTextToFile("r^d_$i = f(x^($i)) - H_9(f,x) = $xInFunc - $xInControlPoint = $epsilon" , fileName)
        }
    }

    fun generateMatrix(points: Map<Double,Double>) : Array<DoubleArray> {
        val system: Array<DoubleArray> = Array(5) { DoubleArray(5) }
        var i = 0
        points.keys.forEach{
            val p = doubleArrayOf(1.0, it.pow(1.0), it.pow(2.0), it.pow(3.0), it.pow(4.0))
            system[i] = p
            i++
        }
        return system
    }
    private fun getPoints(func: (Double) -> Double, lowLim: Int, upLim: Int): Map<Double, Double> {
        val xyMap = mutableMapOf<Double, Double>()
        writeTextToFile("Найдем x,y для построения полинома",fileName)
        for (k in lowLim..upLim) {
            val x = func(k.toDouble())
            val y = ownFunction(x)
            writeTextToFile("На шаге $k x = $x",fileName)
            writeTextToFile("y = $y",fileName)
            xyMap[x] = y
        }
        writeTextToFile("\n",fileName)
        return xyMap
    }
}
fun printMatrix(matrix: Array<DoubleArray>) {
    for (row in matrix) {
        println(row.joinToString(" "))
    }
}