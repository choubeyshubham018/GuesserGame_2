package guesserGame;
import java.util.Scanner;
import java.util.ArrayList;

class Guesser{
	int guesNum;
    // Taking the Guesser Input for the Game 
	// n decides the range for guesses based on number of Player and level of Game chosen by the Players.
	public int takingGuesrNum(int n) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Guesser Kindly Guess a Number between 0-"+n+" and Keep it Secret with Others");
		guesNum=sc.nextInt();
		// for checking that the Guesser is Guessing the Number in the Predefined Range or not ,if not then he has to chose again.
		if(guesNum<0 || guesNum >n) {
			System.out.println("Kindly Provide a valid Value");
			takingGuesrNum(n);
		}
		return guesNum;
	}
}

class Player{
	int playrNum;
	// Taking the Players input .
	// n decides the range for guesses based on number of Players and level of Game chosen by the Players.
	public void takingPlayrNum(int n,int i) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Player"+i+" Kindly Guess a Number between 0-"+n+" and Keep it Secret with Others");
		playrNum=sc.nextInt();
		// for checking that the Players is Guessing the Number in the Predefined Range or not ,if not then he has to chose again.
		if(playrNum<0 || playrNum >n) {
			System.out.println("Kindly Provide a valid Value");
			takingPlayrNum( n,i);
		}
}
	// Returning the Number Chosen by the player.
	public int getPlayrNum() {
		return playrNum;
	}

	
	
	
}
class Umpire{
	
	Player[]playrNum;  //To holds the reference of all the players Playing the Game.
	
	int guesNum;
	
	int n,temp,lev,nxtRnd;
	
	ArrayList<String>ar=new ArrayList<>(); 
	
	Scanner sc=new Scanner(System.in);
	//for setting the Basic Requirement of the Game that is--> num of players,level of Game 
	// provide the facility to chose, Guesser will be a player or the Umpire will play the Guesser Role.
	public void SettingRequirement() {
		System.out.println("Kindly Enter the Number of Player who wants to play the Guesser Game ,Should be atleast 2 or at Max 10");
		n=sc.nextInt();
		// Checking that the Number of Players with in the range or not
		while(n<2 || n>10) {
			System.out.println("Kindly Provide the Valid Number of players ,Should be atleast 2 or at Max 10");
			n=sc.nextInt();
		}
		// Provide the option of Umpire as a Guesser if Players Demand That.
		System.out.println("if you want  Umpire to be your Guesser Kindly Press 0 ,Else any other Key followed by Enter ");
	     temp=sc.nextInt();
		System.out.println("Kindly Select the level of the Game --> Easy press'0' , Medium press'1' , Hard press'2'");
		int p=sc.nextInt();   // Specifying the level of the game just by increasing or Decreasing the Range of the Guessing for given num of players.
		if(p==2) {
			System.out.println("Playing at Hard Level, Game will be tough get Ready...");
			lev=n*10;
		}
		else if(p==1) {
			System.out.println("Starting the Game at Medium Level");
		    lev=n*5;	
		}
		else {
			System.out.println("Lets Play Rookie level Game");
			lev=n*2;
		}
	}
	
// Taking Input from the Guesser and all the Players Playing the Game.
	public void takingInput() {
		if(temp==0) {
		guesNum=(int)(Math.random()*(lev+1));
	   }
	else {
		Guesser g=new Guesser();
	    guesNum=g.takingGuesrNum( lev);
	}
		playrNum=new Player[n];
		
	for (int i = 0; i < playrNum.length; i++) {
		playrNum[i]=new Player();
		playrNum[i].takingPlayrNum(lev,i+1); // Taking the Input for all the Players through their refernces Stored in the Players Array.
	}	
	}
	
	// Finding the winner by Comparing the Guesser I/P to the Players I/P Stored in each Player Object Property(playrNum).
	public void findingWinner() {
		for (int i = 1; i <= playrNum.length; i++) {
			if(guesNum==playrNum[i-1].getPlayrNum()) {
			ar.add("Player "+i);	// Adding all the Winners name  in the String ArrayList.
			}
			}
			if(ar.isEmpty()) {          // If ArrayList is Empty then not a Single Player Guessed the right number.
				System.out.println("No one Guess the Right Num, Better Luck next time");
			}
			else if(ar.size()==1) {     //If ArraList size is '1' that means we have found our Winner.
				System.out.println("The Winner is "+ ar.get(0));
			}
			else {   //if ArrayList Size is greater then '1' , Then more the one player Guessed the right Number.
				System.out.println("There is a Tie between Players");
				for (int j = 0; j < ar.size(); j++) {
					System.out.println(ar.get(j));
				}
		        System.out.println("Want to check who is the real Winner in the next round then press '0' else any other Number followed by Enter");
		        nxtRnd=sc.nextInt(); // Providing the Players who tied the option, to find the Winner between then in the next round.
		        if(nxtRnd==0) {      
		        	nxtRndForTiers();
		        }
			
		}
	}
     
	public void nxtRndForTiers() {  // Setting all the Values According to number of Players 'Tied the Game' in the First Round. 
		n=ar.size();
		lev=n*5;
		ar.removeAll(ar);
		System.out.println("Umpire is your Guesser for 2nd Round");
		temp=0;
		takingInput();
		findingWinner();
		// Provide the Facility to Start the Game Again with new Players,or Exit if does not want to Play AnyMore.
		System.out.println("If you want to Play again Kindly type 'REPLAY' on the Console , Else press any other key followed by Enter");
		String str=sc.next();
		if(str.equalsIgnoreCase("replay")) { 
			System.out.println("Restarting the game");
			startingGame();
		}
		else {
	        System.out.println("Thank you ,See you in Next Game");
		    System.exit(0);
		}
	
	}
	
	public void startingGame() { // Starting the Game by Arranging Every Methods/Actions in the Proper Order.
		ar.removeAll(ar);
		SettingRequirement() ;
		takingInput();
		findingWinner();
		// Provide the Facility to Start the Game Again with new Players,or Exit if does not want to Play AnyMore.
		System.out.println("If you want to Play again Kindly type 'REPLAY' on the Console , Else press any other key followed by Enter");
		String str=sc.next();
		if(str.equalsIgnoreCase("replay")) {
			System.out.println("Restarting the game");
			startingGame();
		}
		else {
        System.out.println("Thank you ,See you in Next Game");
	    System.exit(0);
		}
	}
	
}

public class GuesserGame {

	public static void main(String[] args) {
		Umpire ump=new Umpire();
		System.out.println("*******WELCOME TO GUESSER GAME********");
		ump.startingGame();
        
	}

}
