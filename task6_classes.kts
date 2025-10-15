import kotlin.random.Random

abstract class Animal(private val name: String) {
    abstract val species: String
    abstract fun speak(): String
    open fun eat() = println("$species $name ест.")
    open fun drink() = println("$species $name пьёт.")
    open fun walk() = println("$species $name гуляет.")
    private var alive = true

    fun isAlive() = alive
    fun die() { alive = false; println("$species $name умер.") }
    fun getName() = name

    fun act(others: List<Animal>): Animal? {
        if (!alive) return null
        when (Random.nextInt(6)) {
            0 -> eat()
            1 -> drink()
            2 -> walk()
            3 -> getSick()
            4 -> return tryReproduce(others)
            5 -> println("$species $name говорит: ${speak()}")
        }
        return null
    }

    private fun getSick() {
        if (Random.nextInt(100) < 25) die()
        else println("$species $name приболел, но выжил.")
    }

    private fun tryReproduce(others: List<Animal>): Animal? {
        val partner = others.filter { it.species == this.species && it != this && it.isAlive() }.randomOrNull()
        if (partner != null && Random.nextInt(100) < 30) {
            val child = create(randomName())
            println("$species $name и ${partner.getName()} родили ${child.getName()}.")
            return child
        }
        return null
    }

    abstract fun create(name: String): Animal
    abstract fun randomName(): String
}

class Cat(name: String) : Animal(name) {
    override val species = "Кошка"
    override fun speak() = "мяу"
    override fun create(name: String) = Cat(name)
    override fun randomName() = listOf("Мурка", "Сима", "Лапка", "Багира").random()
}

class Dog(name: String) : Animal(name) {
    override val species = "Собака"
    override fun speak() = "гав"
    override fun create(name: String) = Dog(name)
    override fun randomName() = listOf("Шарик", "Рекс", "Тузик", "Джек").random()
}

class Rabbit(name: String) : Animal(name) {
    override val species = "Кролик"
    override fun speak() = "фр-фр"
    override fun create(name: String) = Rabbit(name)
    override fun randomName() = listOf("Пушок", "Бублик", "Снежок", "Лапус").random()
}

class Parrot(name: String) : Animal(name) {
    override val species = "Попугай"
    override fun speak() = "карр!"
    override fun create(name: String) = Parrot(name)
    override fun randomName() = listOf("Кеша", "Рико", "Гоша", "Жорик").random()
}

class Bear(name: String) : Animal(name) {
    override val species = "Медведь"
    override fun speak() = "ррр!"
    override fun create(name: String) = Bear(name)
    override fun randomName() = listOf("Миша", "Тимоша", "Потап", "Боря").random()
}

class Zoo {
    private val animals = mutableListOf<Animal>()
    private val types = listOf(::Cat, ::Dog, ::Rabbit, ::Parrot, ::Bear)

    fun populate() {
        for (type in types) {
            val sample = type.call("")
            repeat(Random.nextInt(2, 4)) { animals += type.call(sample.randomName()) }
        }
    }

    fun simulateDay() {
        println("\n--- Новый день в зоопарке ---")
        val newborns = mutableListOf<Animal>()
        for (a in animals.toList()) {
            a.act(animals)?.let { newborns += it }
        }
        animals += newborns
        animals.removeIf { !it.isAlive() }
        println("Животных осталось: ${animals.size}")
    }
}

val zoo = Zoo()
zoo.populate()
repeat(5) { zoo.simulateDay() }
println("\nЖизнь в зоопарке завершена.")
