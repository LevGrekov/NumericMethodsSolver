import java.io.File
import kotlin.math.PI
import kotlin.math.cbrt
import kotlin.math.cos
import kotlin.math.exp

fun main() {
    val fileName = "D:/ZTestWork.txt"
    File(fileName).writeText("")
    //val solve = TestWorkOnNumericalMethods(-1.0,1.0, {x-> exp(x/2) + 2.0.pow((2.0 * x + 1.0)/3)},fileName)
    val solve = TestWorkOnNumericalMethods(
        0.0,
        (2 * PI) / 3.0,
        { x -> exp(cbrt(x / 3) * cos(4 * x)) },
        0.095 * PI,
        0.18 * PI,
        0.42 * PI,
        fileName
    )
    solve.firstExercise1()
    solve.firstExercise2()
    solve.secondExercise()
}