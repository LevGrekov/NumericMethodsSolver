package ru.levgrekov.polynomial.ru.levgrekov.polynomial.math

class TestWorkOnNumericalMethods(val a: Double, val b: Double, val ownFunction: (Double) -> Double,val x1 : Double,val x2:Double,val x3:Double,val fileName : String) {

    private val controlPoints = mapOf(1 to x1, 2 to x2, 3 to x3)
    fun firstExercise(){
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


        //        val secondXY = getPoints({k -> (b-a)/2.0 * cos((kotlin.math.PI * (2*k-1))/10) + (b+2)/2.0},1,5)
    }

    private fun getPoints(func: (Double) -> Double, lowLim: Int, upLim: Int): Map<Double, Double> {
        val xyMap = mutableMapOf<Double, Double>()
        writeTextToFile("Найдем x,y для построения полинома Лагранжа",fileName)
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