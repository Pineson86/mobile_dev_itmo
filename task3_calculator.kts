println("input first number")

val firstNum = readLine()!!.toDouble()

println("input operation: +, -, *, /")
val oper = readLine()!!

println("input second number")
val secondNum = readLine()!!.toDouble()

if (oper == "/") {
    if (secondNum == 0.0) {
        println("На ноль делить нельзя!")
    } else {
        val result = firstNum / secondNum
        println(result)
    }
} else if (oper == "+") {
    val result = firstNum + secondNum
    println(result)
} else if (oper == "-") {
    val result = firstNum - secondNum
    println(result)
} else if (oper == "*") {
    val result = firstNum * secondNum
    println(result)
} else {
    println("Операция не поддерживается")
}
