package sandbox.kotlin.playaround

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.IOException

class AlphabetGuessingGame {
	
	companion object {
		fun main() {
	val sc: BufferedReader = BufferedReader(InputStreamReader(System.`in`))
	var noOfTrials = 0; var lessThan27 = true;
	var x: Double; var b: Int; var c = 0; var d = 'A'; var e: Char
	
	while(lessThan27){
		x = Math.random()*100
		b = x.toInt()
		if (b<=26 && b!=0) {
			c = b
			lessThan27 = false
		} else continue
	}
	
	var i = 1
	while (i<c) {
		i++; d++
	}
	e = d
	var f = e.toString()
	println("The Answer is: "+f)
	println()
	
	
	println("-----------------------------");
	println("Random Alphabet Guessing Game");
	println("-----------------------------");
	println("I have picked a random Alphabet between 'A-Z', Can you guess that Alphabet? "
				+ "\n***Your Answer should be in Uppercase***\n\n\t\tGo!!!");
	println();
	
	while (true) {
		println()
		print("It is: ")
		var a = sc.readLine()
		var j: Char = a.elementAt(0)
		if (a.length != 1){
			println()
			println("Answer must be an Upper case Alphabet! Try again.");
		}
		else if(Character.toUpperCase(j)!=a.elementAt(0)) { 
				println();
				println("Answer must be an Upper case Alphabet! Try again.");
			}
			else if(a.compareTo(f)!=0) { 
				println();
				println("Wrong Answer! Try again");
			}
		  else if (a.compareTo(f)==0){ 
		  	println();
		  	println("------------------------------------");
				println("Yay, Correct");
				println("____________________________________");
				println("Your Number of trials: "+noOfTrials);
				System.exit(0);
		 }
	}
}
	}
}
