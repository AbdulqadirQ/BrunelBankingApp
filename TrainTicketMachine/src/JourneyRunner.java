
public class JourneyRunner {

	public static void main(String[] args) {

		JourneyController jController = new JourneyController();

		jController.runController();

	}

}

/*
 * CODE CONSIDERATIONS:
 *	- ensured all requirements are met
 *	- avoided unnecessary/redundant lines of code
 *	- implementation of MVC design pattern
 *	- use of encapsulation (would allow for easier unit testing, allows for better readability of code)
 *	- naming of variables/methods considered for better readability
 *	- commenting throughout for better readability and documentation
 *	- Efficiency considered -> linear efficiency of O(n) obtained by avoiding nested loops
 *	- Error handling
 *	- stack size considered to ensure a chain of too many objects is not created, leading to stack overflow
 * 
 */
