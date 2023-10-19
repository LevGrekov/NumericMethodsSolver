
import java.io.File
import kotlin.math.exp
import kotlin.math.pow

fun main() {
    val fileName = "D:/ZTestWork.txt"
    File(fileName).writeText("")
    //val solve = TestWorkOnNumericalMethods(-1.0,1.0, {x-> exp(x/2) + 2.0.pow((2.0 * x + 1.0)/3)},fileName)
    val solve = TestWorkOnNumericalMethods(
        -1.0,
        1.0,
        {x-> exp(x/2) + 2.0.pow((2.0 * x + 1.0)/3.0)},
        -5.0/8.0,
        5.0/7.0,
        2.0/7.0,
        fileName
    )

    solve.firstExercise1()
    solve.firstExercise2()
    solve.secondExercise()
    solve.thirdExercise()


}

