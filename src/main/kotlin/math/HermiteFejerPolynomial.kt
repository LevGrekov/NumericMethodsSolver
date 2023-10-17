package math

import ru.levgrekov.polynomial.ru.levgrekov.polynomial.math.LagrangePolynomial

class HermiteFejerPolynomial(lagrangePoly : LagrangePolynomial): Polynomial() {
    private val lagrangePoly : LagrangePolynomial
    private val n: Int
        get() = lagrangePoly.points.size
    init {
        this.lagrangePoly = lagrangePoly

    }
    private fun findMultiplicationXminXi() : Polynomial {
        val result = Polynomial(0.0)
        lagrangePoly.points.values.forEach {
            result += Polynomial(-it, 1.0)
        }
        return result
    }

    private fun experiment(n: Int){
        val A: Int
        val B: Int
        val C: Int
        val D: Int
        val E: Int

        //Создаем x^4 + x^3 + x^2 + x + 1
        val polyWithABCDE =
            Polynomial(1.0 ,1.0 ,1.0 ,1.0 ,1.0 )



        val H = findMultiplicationXminXi().derivative(1)
    }

}