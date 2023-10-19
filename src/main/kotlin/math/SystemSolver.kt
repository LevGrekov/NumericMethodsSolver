package math

object SystemSolver {
    fun solveLinearSystem(coefficients: Array<DoubleArray>, constants: DoubleArray): DoubleArray {
        val n = constants.size
        val solution = DoubleArray(n)

        val determinantA = calculateDeterminant(coefficients)

        for (i in 0..<n) {
            val modifiedCoefficients = Array(n) { DoubleArray(n) }
            for (j in 0..<n) {
                for (k in 0..<n) {
                    modifiedCoefficients[j][k] = coefficients[j][k]
                    if (k == i) {
                        modifiedCoefficients[j][k] = constants[j]
                    }
                }
            }

            val determinantModified = calculateDeterminant(modifiedCoefficients)

            solution[i] = determinantModified / determinantA
        }

        return solution
    }
    private fun calculateDeterminant(matrix: Array<DoubleArray>): Double {
        val n = matrix.size

        if (n == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0]
        }

        var determinant = 0.0

        for (i in 0..<n) {
            val subMatrix = Array(n - 1) { DoubleArray(n - 1) }
            for (j in 1..<n) {
                for (k in 0..<n) {
                    if (k < i) {
                        subMatrix[j - 1][k] = matrix[j][k]
                    } else if (k > i) {
                        subMatrix[j - 1][k - 1] = matrix[j][k]
                    }
                }
            }
            determinant += matrix[0][i] * calculateDeterminant(subMatrix) * if (i % 2 == 0) 1 else -1
        }
        return determinant
    }
}