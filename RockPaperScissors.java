package grade11Review;

//importing classes needed for simulation
import java.util.Scanner;
import java.util.Random;

public class RockPaperScissorsGame {

	public static void main(String[] args) {
		//random class to pick rock, paper or scissors at random
		Random rand = new Random();
		//scanner class to allow user to input choice
		Scanner in = new Scanner(System.in);
			
		
		int gamePlay = 0;
		int numRounds = 0;
		
		
		// Make game play choice
		System.out.println("Choose the type of gameplay from one of these options: ");
		System.out.println("For Computer VS Computer, Enter 1");	// User will enter 1 to see Computer VS Computer
		System.out.println("For Player VS Computer, Enter 2"); // User will enter 2 to see Computer VS Player
		System.out.println("For Player VS Player, Enter 3"); // User will enter 3 to see Player VS Player
		gamePlay = in.nextInt();
		
		//user input of 1 declaring two computers playing against each other
		if (gamePlay == 1)	{
			System.out.println("Two Computers will play against each other.");
		}
		//user input of 2 declaring a player and computer playing against each other
		else if (gamePlay == 2)	{
			System.out.println("A Player and a Computer will play against each other.");
		}
		//user input of 3 declaring two players playing against each other
		else if (gamePlay == 3)	{
			System.out.println("Two Players will play against each other.");
		}
		//user input of any other int asking user to enter a valid player choice
		else	{
			System.out.println("Enter a valid player choice from 1, 2 or 3!");
		}
		

		// Choose number of rounds
		System.out.println("Enter the number of rounds you want to play: ");
		numRounds = in.nextInt();
		System.out.println("The number of rounds to play are " + numRounds + " rounds.");
		
		//adding the results from the games and rounds played
		String results = playGame(gamePlay, numRounds);
		
		
		

	}// End of main method
	
	/****************************************************
	* Method Name: playGame
	* Parameters: int gamePlay, int numRounds
	* Return: empty string
	* Conditions: To pick game play, pick integers from 1 to 3
	* 	To pick strategy for game play 2, pick integers from 1 to 2
	* 	To pick the move for game plays 2 and 3, pick strings from "Rock", "Paper" and "Scissors"
	* Changes: none
	****************************************************/
	public static String playGame(int gamePlay, int numRounds)	{
		Scanner in = new Scanner(System.in);
		Random rand = new Random();
		// creating list of choices that the player or computer can choose
		String[] choices = {"Rock", "Paper", "Scissors"};
		String player1Choice;
		String player1ChoiceNew;
		String player2Choice;
		int countPlayer1Wins = 0;
		int countPlayer2Wins = 0;
		int player1Strategy;
		int player2Strategy = 0;
		
		// game play for Computer VS Computer
		if (gamePlay == 1)	{
			player1Strategy = 1;	// assigning player1Strategy as Strategy 1
			player2Strategy = 2;	// assigning player2Strategy as Strategy 2
		}
		
		//game play for Player VS Computer
		else if (gamePlay == 2)	{
			player1Strategy = 3;	// assigning player1Strategy as Strategy 3
			System.out.println("Choose Strategy 1 or 2 for Player 2:");
			player2Strategy = in.nextInt();	// choosing player2Strategy from 1 or 2
		}
		
		//game play for Player VS Player
		else if (gamePlay == 3)	{
			player1Strategy = 3;	//assigning player1Strategy as Strategy 3
			player2Strategy = 3;	//assigning player1Strategy as Strategy 3
		}
		
		////////////////////////////////////////////////////////////////////
		// Game ROUND 1
		////////////////////////////////////////////////////////////////////
		
		int currRound = 1;
		System.out.println("------------- ROUND 1 ------------");
		
		// Both players make a move
		if (gamePlay == 1)	{ 
			player1Choice = choices[rand.nextInt(choices.length)];
			player2Choice = choices[rand.nextInt(choices.length)];
		}
		//player choices if second game play
		else if (gamePlay == 2)	{
			//asking user to choose what they want to throw
			System.out.println("Player1, What do you want to throw?");
			in.nextLine();
			player1Choice = moveFromUserInput();
			player2Choice = choices[rand.nextInt(choices.length)];
		}
		//player choice if third game play
		else	{
			//asking user to choose what they want to throw
			System.out.println("Player1, What do you want to throw?");
			player1Choice = moveFromUserInput();
			//asking user to choose what they want to throw
			System.out.println("Player2, What do you want to throw?");
			player2Choice = moveFromUserInput();
		}
		
		System.out.println("Player 1 picked: " + player1Choice);
		System.out.println("Player 2 picked: " + player2Choice);
		//result used for report
		String result = getResult(player1Choice, player2Choice);
		if (result.equals("Player1"))	{
			countPlayer1Wins++;
		}
		else if (result.equals("Player2"))	{
			countPlayer2Wins++;
		}
		
		////////////////////////////////////////////////////////////////////
		// Game remaining rounds (based on user input of numRounds)
		////////////////////////////////////////////////////////////////////
		
		String previousResult = "";
		
		//while loop used to repeat the strategies for each round for each player in each game play
		while (currRound < numRounds)	{
			
			//adding 1 to numRounds
			currRound++;
			System.out.println("------------- ROUND " + currRound + " ------------");
			
			//repeat strategies for game play 1
			if (gamePlay == 1)	{ 
				//player1 uses strategy1
				if (result.equals("Player1"))	{
					previousResult = "win";
					player1Choice = moveFromStrategy1(previousResult, player1Choice, player2Choice);
				}
				else if (result.equals("Player2"))	{
					previousResult = "loss";
					player1Choice = moveFromStrategy1(previousResult, player2Choice, player1Choice);
				}
				else	{
					previousResult = "draw";
					player1Choice = moveFromStrategy1(previousResult, player1Choice, player2Choice);
				}
				
				//player2 uses strategy2
				player2Choice = moveFromStrategy2(player2Choice);
			}
			//repeat strategies for game play 2
			else if (gamePlay == 2)	{
				//asking user to choose what they want to throw
				System.out.println("Player1, What do you want to throw?");
				//player1 uses strategy3
				String player1PrevChoice = player1Choice;
				player1Choice = moveFromUserInput();
				
				//player2 uses either strategy1
				if (player2Strategy == 1)	{
					if (result.equals("Player1"))	{
						previousResult = "loss";
						player2Choice = moveFromStrategy1(previousResult, player1PrevChoice, player2Choice);
					}
					else if (result.equals("Player2"))	{
						previousResult = "win";
						player2Choice = moveFromStrategy1(previousResult, player2Choice, player1PrevChoice);
					}
					else	{
						previousResult = "draw";
						player2Choice = moveFromStrategy1(previousResult, player1PrevChoice, player2Choice);
					}
				}
				else	{
					//player 2 using strategy2
					player2Choice = moveFromStrategy2(player2Choice);
				}
			}
			//repeat strategies for game play 3
			else	{
				//asking user to choose what they want to throw
				System.out.println("Player1, What do you want to throw?");
				//player1 uses strategy3
				player1Choice = moveFromUserInput();
				//asking user to choose what they want to throw
				System.out.println("Player2, What do you want to throw?");
				//player2 uses strategy3
				player2Choice = moveFromUserInput();
			}
			
			System.out.println("Player 1 picked: " + player1Choice);
			System.out.println("Player 2 picked: " + player2Choice);
			//printing the result after every round
			result = getResult(player1Choice, player2Choice);
			
			if (result.equals("Player1"))	{
				countPlayer1Wins++;
			}
			else if (result.equals("Player2"))	{
				countPlayer2Wins++;
			}
		}
		getReport(numRounds, countPlayer1Wins, countPlayer2Wins, gamePlay);
		return "";
	}// End of playGame method
	
	/****************************************************
	* Method Name: moveFromStrategy1
	* Parameters: String previousResult, String winnerChoice, String loserChoice
	* Return: String move
	* Conditions: If the previousResult = "win", the move will be the move the loser chose
	* 	If the previousResult = "loss", then move will be based on the counter of the move the winner chose
	* 	If draw, the players' moves will be picked on random
	* Changes: none
	****************************************************/
	public static String moveFromStrategy1(String previousResult, String winnerChoice, String loserChoice)	{
		//if player loses then loser should play counter to winnerChoice
		//if player wins then winner should play loserChoice
		//if both players draw then choose randomly
		String move;
		//when a player wins, the winner will play what the loser played
		if (previousResult.equals("win"))	{
			move = loserChoice;
		}
		//when a player loses, the loser will play the counter to each winner's play
		else if (previousResult.equals("loss"))	{
			//if winner played rock, loser should play paper
			if (winnerChoice.equals("Rock"))	{
				move = "Paper";
			}
			//if winner played paper, loser should play scissors
			else if (winnerChoice.equals("Paper"))	{
				move = "Scissors";
			}
			//if winner player scissors, loser should play rock
			else	{
				move = "Rock";
			}
		}
		//when the players draw, winner and loser's move will be chosen on random
		else	{
			Random rand = new Random();
			//creating list of moves available
			String[] moves = {"Rock", "Paper", "Scissors"};
			//the move will be picked randomly based on the number of the move in the list
			move = moves[rand.nextInt(moves.length)];
		}
		
		return move;
	}// End of moveFromStrategy1 method
	
	/****************************************************
	* Method Name: moveFromStrategy2
	* Parameters: String previousChoice
	* Return: String move
	* Conditions: The player's move will only be the move they played in the previous round
	* Changes: none
	****************************************************/
	public static String moveFromStrategy2(String previousChoice)	{
		//if players win or lose, they will continue playing the same choice that they picked in the previous round
		String move = previousChoice;
		return move;
	}// End of moveFromStrategy2 method
	
	/****************************************************
	* Method Name: moveFromUserInput
	* Parameters: none
	* Return: String move
	* Conditions: If the move is not equal to "Rock" OR the move is not equal to "Paper" OR the move is not equal to "Scissors", then
	* 		print that it is an invalid input
	* Changes: none
	****************************************************/
	public static String moveFromUserInput()	{
		// (Just for fun) - Player VS Player so the player will input every round's choice with their own strategy in mind
		Scanner in = new Scanner(System.in);
		String move = "";
		
		//allowing user to input move
		move = in.nextLine();
		
		//checks if user has input the valid input
		if (!move.equals("Rock") && !move.equals("Paper") && !move.equals("Scissors"))	{
			System.out.println("Invalid input! Pick a valid input!");
		}
		
		return move;
	}// End of moveFromUserInput method
	
	/****************************************************
	* Method Name: getResult
	* Parameters: String player1Choice, String player2Choice
	* Return: String result
	* Conditions: If the player1Choice = player2Choice, print "Draw" and result = "Draw"
	* 	If player1 picks scissors and player2 picks paper, print "Scissors cuts Paper, Player 1 Wins and Player 2 Loses" and result = "Player1"
	* 	If player1 picks scissors and player2 picks rock, print "Rock breaks Scissors, Player 2 Wins and Player 1 Loses" and result = "Player2"
	* 	If player1 picks rock and player2 picks scissors, print "Rock breaks Scissors, Player 1 Wins and Player 2 Loses" and result = "Player1"
	* 	If player1 picks rock and player2 picks paper, print "Paper covers Rock, Player 2 Wins and Player 1 Loses" and result = "Player2"
	* 	If player1 picks paper and player2 picks rock, print "Paper covers Rock, Player 1 Wins and Player 2 Loses" and result = "Player1"
	* 	If player1 picks paper and player2 picks scissors, print "Scissors cuts Paper, Player 2 Wins and Player 1 Loses" and result = "Player2"
	* Changes: none
	****************************************************/
	public static String getResult(String player1Choice, String player2Choice)	{
		String result = "";
		
		//if S == S or R == R or P == P then Draw
		if (player1Choice.equals(player2Choice))	{
			System.out.println("Draw");
			result = "Draw";
		}
		
		// CONDITIONS IF PLAYER 1 PICKS SCISSORS
		//if S > P then S wins
		else if (player1Choice.equals("Scissors") && player2Choice.equals("Paper"))	{
			System.out.println("Scissors cuts Paper! Player 1 Wins and Player 2 Loses!");
			result = "Player1";
		}
		//if S < R then R wins
		else if (player1Choice.equals("Scissors") && player2Choice.equals("Rock"))	{
			System.out.println("Rock breaks Scissors! Player 2 Wins and Player 1 Loses!");
			result = "Player2";
		}
		
		// CONDITIONS IF PLAYER 1 PICKS ROCK
		//if R > S then R wins
		else if (player1Choice.equals("Rock") && player2Choice.equals("Scissors"))	{
			System.out.println("Rock breaks Scissors! Player 1 Wins and Player 2 Loses!");
			result = "Player1";
		}
		//if R < P then P wins
		else if (player1Choice.equals("Rock") && player2Choice.equals("Paper"))	{
			System.out.println("Paper covers Rock! Player 2 Wins and Player 1 Loses!");
			result = "Player2";
		}
		
		// CONDITIONS IF PLAYER 1 PICKS PAPER
		//if P > R then P wins
		else if (player1Choice.equals("Paper") && player2Choice.equals("Rock"))	{
			System.out.println("Paper covers Rock! Player 1 Wins and Player 2 Loses!");
			result = "Player1";
		}
		//if P < S then S wins
		else if (player1Choice.equals("Paper") && player2Choice.equals("Scissors"))	{
			System.out.println("Scissors cuts Paper! Player 2 Wins and Player 1 Loses!");
			result = "Player2";
		}
		
		return result;
	}// End of getResult method
	
	/****************************************************
	* Method Name: getReport
	* Parameters: int numRounds, int countPlayer1Wins, int countPlayer2Wins, int gamePlay
	* Return: none
	* Conditions: none
	* Changes: none
	****************************************************/
	public static void getReport(int numRounds, int countPlayer1Wins, int countPlayer2Wins, int gamePlay)	{
		System.out.println("----------------------------------");
		System.out.println("------------- REPORT -------------");
		System.out.println("----------------------------------");
		
		// gamePlay1 = Computer VS Computer
		if (gamePlay == 1)	{
			//calculations for success rates of strategies 1 and 2
			int strat1SuccessRate = countPlayer1Wins * 100 / numRounds;
			int strat2SuccessRate = countPlayer2Wins * 100 / numRounds;
			//printing report on # of wins for player 1 and success rate of player 1
			System.out.println("Player 1 used Strategy 1. They won " + countPlayer1Wins + " times.");
			System.out.println("Success Rate of Strategy 1: " + strat1SuccessRate + "%.");
			//printing report on # of wins for player 2 and success rate of player 2
			System.out.println("Player 2 used Strategy 2. They won " + countPlayer2Wins + " times.");
			System.out.println("Success Rate of Strategy 2: " + strat2SuccessRate + "%.");
			//if success rate of player 1 is higher than success rate of player 2, prints that and prints player 1 wins!
			if (strat1SuccessRate > strat2SuccessRate)	{
				System.out.println("The Success Rate of Strategy 1 was higher than the Success Rate of Strategy 2.");
				System.out.println("----------------------------------");
				System.out.println("------------- WINNER -------------");
				System.out.println("----------------------------------");
				System.out.println("Player 1 WINS!");
			}
			//if success rate of player 2 is higher than success rate of player 1, prints that and prints player 2 wins!
			else if (strat2SuccessRate > strat1SuccessRate) 	{
				System.out.println("The Success Rate of Strategy 2 was higher than the Success Rate of Strategy 1.");
				System.out.println("----------------------------------");
				System.out.println("------------- WINNER -------------");
				System.out.println("----------------------------------");
				System.out.println("Player 2 WINS!");
			}
			//if success rate of player 1 is equal to success rate of player 2, prints that
			else	{
				System.out.println("The Success Rates of both strategies is equal.");
			}
		}
		// gamePlay = 2 - Player VS Computer
		else if (gamePlay == 2) 	{
			//calculations for success rates for each player strategy
			int player1SuccessRate = countPlayer1Wins * 100 / numRounds;
			int computerStrategySuccessRate = countPlayer2Wins * 100 / numRounds;
			//printing report on # of wins for player 1 and success rate of player 1
			System.out.println("Player 1 used their own Strategy. They won " + countPlayer1Wins + " times.");
			System.out.println("Success Rate of their strategy is: " + player1SuccessRate + "%.");
			//printing report on # of wins for player 2 and success rate of player 2
			System.out.println("Player 2 used the strategy picked for them by the user. They won " + countPlayer2Wins + " times.");
			System.out.println("Success Rate of their strategy is " + computerStrategySuccessRate + "%.");
			//if success rate of player 1 is higher than success rate of player 2, prints that and prints player 1 wins!
			if (player1SuccessRate > computerStrategySuccessRate)	{
				System.out.println("The Success Rate of Player 1's Strategy was higher than the Success Rate of Player 2's Strategy.");
				System.out.println("----------------------------------");
				System.out.println("------------- WINNER -------------");
				System.out.println("----------------------------------");
				System.out.println("Player 1 WINS!");
			}
			//if success rate of player 2 is higher than success rate of player 1, prints that and prints player 2 wins!
			else if (computerStrategySuccessRate > player1SuccessRate)	{
				System.out.println("The Success Rate of Player 2's Strategy was higher than the Success Rate of Player 1's Strategy.");
				System.out.println("----------------------------------");
				System.out.println("------------- WINNER -------------");
				System.out.println("----------------------------------");
				System.out.println("Player 2 WINS!");
			}
			//if success rate of player 1 is equal to success rate of player 2, prints that
			else	{
				System.out.println("The Success Rates of both strategies is equal.");
			}	
		}
		// gamePlay = 3 - Player VS Player
		else	{
			//calculations for success rates for each player strategy
			int player1SuccessRate = countPlayer1Wins * 100 / numRounds;
			int player2SuccessRate = countPlayer2Wins * 100 / numRounds;
			//printing report on # of wins for player 1 and success rate of player 1
			System.out.println("Player 1 won " + countPlayer1Wins + " times.");
			System.out.println("Success Rate of their strategy is: " + player1SuccessRate + "%.");
			//printing report on # of wins for player 2 and success rate of player 2
			System.out.println("Player 2 won " + countPlayer2Wins + " times.");
			System.out.println("Success Rate of their strategy is: " + player2SuccessRate + "%.");
			//if success rate of player 1 is higher than success rate of player 2, prints that and prints player 1 wins!
			if (player1SuccessRate > player2SuccessRate)	{
				System.out.println("The Success Rate of Player 1's Strategy was higher than the Success Rate of Player 2's Strategy.");
				System.out.println("----------------------------------");
				System.out.println("------------- WINNER -------------");
				System.out.println("----------------------------------");
				System.out.println("Player 1 WINS!");
			}
			//if success rate of player 2 is higher than success rate of player 1, prints that and prints player 2 wins!
			else if (player2SuccessRate > player1SuccessRate)	{
				System.out.println("The Success Rate of Player 2's Strategy was higher than the Success Rate of Player 1's Strategy.");
				System.out.println("----------------------------------");
				System.out.println("------------- WINNER -------------");
				System.out.println("----------------------------------");
				System.out.println("Player 2 WINS!");
			}
			//if success rate of player 1 is equal to success rate of player 2, prints that
			else	{
				System.out.println("The Success Rates of both strategies is equal.");
			}
		}
		
	}// End of getReport method
	
}// End of RockPaperScissors Class
