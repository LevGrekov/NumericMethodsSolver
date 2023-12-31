package ru.levgrekov.polynomial.ru.levgrekov.polynomial.math

import math.Polynomial

class NewtonPolynomial2(points: Map<Double,Double>,val fileName: String) : Polynomial() {

    private val n: Int
        get() = _points.size

    private val _points: MutableList<Pair<Double,Double>> = mutableListOf()

    private val lastFundPoly: Polynomial = Polynomial(1.0);
    init {
        _coeffs[0] = 0.0
        this.addPoints(points.toList().toMutableList())
    }
    private fun fundPoly(j: Int): Polynomial {
        if (j != 1) {
            lastFundPoly *= Polynomial(-_points[j-2].first, 1.0)
        }
        return lastFundPoly
    }
    private fun dividedDifference(k: Int): Double = (0..k).sumOf { j ->
        val multiplication = (0..k)
            .filter { i -> i != j }
            .fold(1.0) { acc, i -> acc * (_points[j].first - _points[i].first)}
        _points[j].second / multiplication
    }
    fun addPoint(x: Double, f: Double) {
        _points.add(Pair(x, f))

        val dd = dividedDifference(n - 1)
        val expressionDD = buildString {
            append("f(")
            for (i in 1..n) {
                append("x$i ")
            }
            append(")")
        }
        writeTextToFile("$expressionDD = $dd",fileName)

        val expressionfp = buildString {
            for (i in 1..<n) {
                append("(x - x$i)")
            }
        }
        val fp = fundPoly(n)
        writeTextToFile("$expressionfp = $fp",fileName)
        this += fp * dd
        writeTextToFile("",fileName)
    }
    fun addPoints( pointsList: List<Pair<Double,Double>>) =
        pointsList.forEach{this.addPoint(it.first,it.second)}
}

