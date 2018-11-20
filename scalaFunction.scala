https://alvinalexander.com/scala/fp-book/how-write-functions-take-function-input-parameters

// Databricks notebook source
def add(x: Int, y: Int) = x+ y
print(add(4,5))

// COMMAND ----------

add(6,7)

// COMMAND ----------

def isEven(i: Int) = i % 2 == 0 
def isOdd(i:Int) = i%2 !=0

// COMMAND ----------

val list = List.range(0,20)
val evens = list.filter(isEven)
val odd = list.filter(isOdd)

// COMMAND ----------

val result = list.filter(_%2 == 0)

// COMMAND ----------

def sayHello(func: ()=> Unit) = {func()}


// COMMAND ----------

def helloAI():Unit = {println("Hello AI")}


// COMMAND ----------

sayHello(helloAI)

// COMMAND ----------

def hiGouri(): Unit = { println("Gouri Prasad Mahapatra") }

// COMMAND ----------

sayHello(hiGouri)

// COMMAND ----------

def runFunction(f: Int => Unit): Unit= {
  f(42)
}

// COMMAND ----------

def printAnInt(i: Int): Unit = {print (i+10)}

// COMMAND ----------

runFunction(printAnInt)

// COMMAND ----------

def executeNTimes(f: () => Unit, n: Int) {
    for (i <- 1 to n) f()
}

// COMMAND ----------

def helloWorld(): Unit = { println("Hello, world") }

// COMMAND ----------

executeNTimes(helloWorld, 10)

// COMMAND ----------

def executeAndPrint(f: (Int, Int) => Int, x: Int, y: Int): Unit = {
    val result = f(x, y)
    println(result)
}

// COMMAND ----------

def sum(x: Int, y: Int) = x + y
def multiply(x: Int, y: Int) = x * y
executeAndPrint(sum, 3, 11)       // prints 14
executeAndPrint(multiply, 3, 9)   // prints 27

// COMMAND ----------

executeAndPrint(sum, 3, 11)       // prints 14
executeAndPrint(multiply, 3, 9)   // prints 27

// COMMAND ----------

def execTwoFunctions(f1:(Int, Int) => Int, 
                     f2:(Int, Int) => Int, 
                     a: Int, 
                     b: Int): Tuple2[Int, Int] ={
    val result1 = f1(a, b)
    val result2 = f2(a, b)
    (result1, result2)
}
execTwoFunctions(sum,multiply,4,5)

// COMMAND ----------

execTwoFunctions(sum,multiply,4,5)

// COMMAND ----------


