import kotlin.math.sqrt

val cathetusCB = 8.0
val cathetusAC = 6.0
val rectangleSquare = cathetusCB * cathetusAC
val rectanglePerimeter = 2 * (cathetusCB + cathetusAC)
val hypotenuseAB = sqrt(cathetusCB * cathetusCB + cathetusAC * cathetusAC)
println("Гипотенуза: $hypotenuseAB, периметр: $rectanglePerimeter, площадь: $rectangleSquare")