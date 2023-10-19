package ru.levgrekov.polynomial.ru.levgrekov.polynomial.math
import math.Polynomial

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
            val fundamentalPolyMY = fundamentalPoly * fx
            writeTextToFile("l$i * y$i = $fundamentalPolyMY \n",fileName)
            result += fundamentalPolyMY
            i++
        }
        writeTextToFile("\nКонечный полином : $result \n",fileName)
        return result
    }

    fun findW() : Polynomial {
        val result = Polynomial(1.0)
        points.keys.forEach {
            result *= Polynomial(-it, 1.0)
        }
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
