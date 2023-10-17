package ru.levgrekov.polynomial.ru.levgrekov.polynomial.math

import java.io.File
import java.io.IOException
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.ulp

fun Double.eq(other: Double, eps: Double) = abs(this - other) < eps

infix fun Double.eq(other: Double) = abs(this - other) < max(ulp, other.ulp) * 10.0

fun Double.neq(other: Double, eps: Double) = !this.eq(other, eps)

infix fun Double.neq(other: Double) = !this.eq(other)

infix fun Double.mod(other: Double) = this.rem(other)

fun Double.modEq(other: Double) = Math.abs(this.rem(other)) < max(ulp, other.ulp) * 10.0

fun Double.modNeq(other: Double) = !this.modEq(other)

fun writeTextToFile(text: String, fileName: String) {
    try {
        File(fileName).appendText(text + "\n") // Добавляем текст и перевод строки
        println("Данные успешно добавлены в файл: $fileName")
    } catch (e: IOException) {
        println("Произошла ошибка при записи данных в файл: ${e.message}")
    }
}

fun factorial(n: Int): Int {
    var result = 1
    for (i in 1..n) {
        result *= i
    }
    return result
}