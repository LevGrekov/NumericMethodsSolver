package ru.levgrekov.polynomial.ru.levgrekov.polynomial.math
import ru.levgrekov.polynomial.math.Polynomial

class LagrangePolynomial(points: Map<Double,Double>,val fileName: String) : Polynomial() {
    private val _points: MutableMap<Double, Double>

    init {
        _points = points.toMutableMap()
        if (_points.isEmpty()) _coeffs[0] = 0.0
        else _coeffs.apply {
            clear()
            putAll(createLagrangePoly().coeffs)
        }
    }

    val points: Map<Double, Double>
        get() = _points.toMap()

    private fun createLagrangePoly(): Polynomial {
        val result = Polynomial(mapOf(0 to 0.0))
        var i = 0

        for ((x, fx) in _points.entries) {
            val fundamentalPoly = createFundamentalPoly(x)
            writeTextToFile("Фундаментальный полином l$i = $fundamentalPoly",fileName)
            val fundamentalPolyMY = fundamentalPoly * fx
            writeTextToFile("l$i * $fx = $fundamentalPolyMY \n",fileName)
            result += fundamentalPolyMY
            i++
        }
        writeTextToFile("\nКонечный полином : $result \n",fileName)
        return result
    }


    private fun createFundamentalPoly(xk: Double): Polynomial {
        val acc = Polynomial(1.0)
        for (it in _points.keys) {
            if (xk != it) {
                acc *= Polynomial(-it, 1.0) / (xk - it)
            }
        }
        return acc
    }

}
