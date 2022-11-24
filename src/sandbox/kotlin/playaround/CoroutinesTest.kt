package sandbox.kotlin.playaround

import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.delay
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.Job
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.async
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.GlobalScope
import java.util.concurrent.atomic.AtomicInteger
import java.lang.ThreadLocal
import kotlinx.coroutines.ThreadContextElement
import kotlinx.coroutines.flow.flow
import java.util.Queue
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.MutableStateFlow

class CoroutinesTest {

}

fun main(args: Array<String>) {
	twoCoroutinesTest()
	
}

suspend fun print(): Int {
	delay(1000)
	println("Printed")
	println("Done!!!")
	return 43
}

fun twoCoroutinesTest()  = runBlocking {
		var y: Int
		var x: Deferred<Int> = async (Dispatchers.Default) {
				println("Started Async")
			delay(4000)
				43
			}
		val job1 = launch {
			repeat(10) {
				delay(1000)
				println("Loading...")
			}
		}
		job1.start()
		launch { 
			y = x.await()
			println("Done!")
			println(y)
			if (x.isCompleted) job1.cancel()
		}

			
		
	}

fun flowTest() = runBlocking {
		val list = arrayListOf("Something", "Somewhere", "Somehow", "Sometime")
		println("There are ${list.size} items.")

		val flow = flow {
			emit (list[0])
		}
	
		do {
			println("Type y to poll.")
			val x = readLine()
			if (x == "y") {
				flow.collect { it ->
					println("Value polled: $it")
					list.remove(it)
				}
			}
			
			if (list.isEmpty()) {
				println()
				println("Done!!")

			}
		} while (!list.isEmpty())
		
	}

fun mutableStateTest() = runBlocking {
	val list = arrayListOf("Something", "Somewhere", "Somehow", "Sometime")

	val flow = MutableStateFlow<String>("Somebody").also {
		list.forEach { str -> it.emit(str) } }
	do {
		println("Type y to poll.")
		val x = readLine()
		if (x == "y") {
			flow.collect { it ->
				println("Value polled: $it")
				list.remove(it)
        if (list.isEmpty()) {
        	println()
     			println("Done!!")
   				println()
        	println("Replay cache: ${flow.replayCache.toString()}")
        		
   			}
			}
			
		}
	} while (!list.isEmpty())
}

fun testPrint() {
	runBlocking {
		val x: Deferred<Int> = async {
			repeat(3) {
				delay(1000)
				println("Repeating...")
			}
			43
		}
			println(x.await())
	}
}

fun testingChannels() = runBlocking {
		var fv = 0
		val channel = Channel<Int>(Channel.UNLIMITED)
		
		repeat(4) {
			print("Input a Number: ")
			val x = readLine()?.toInt()?:0
			launch {
				channel.send(x)
			}
			
			launch {
				channel.apply {
					val y = receive()
					println("Adding $y to $fv")
					fv += y
					println("Current value $fv")
				}
			}
			
		}
	}