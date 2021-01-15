package TicTacToe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
	
	static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
	static ArrayList<Integer> botPositions = new ArrayList<Integer>(); 

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char [][] gameBoard = { {' ', '|', ' ', '|', ' '},
								{'-', '+', '-', '+', '-'},
								{' ', '|', ' ', '|', ' '},
								{'-', '+', '-', '+', '-'},
								{' ', '|', ' ', '|', ' '}};
		
		printGameBoard(gameBoard);
		
		while(true) {
			Scanner sc  = new Scanner(System.in);
	
			System.out.println("Enter you placement (1-9): ");
			int playerPos = sc.nextInt();
			while (playerPos<1 || playerPos>9 || playerPositions.contains(playerPos) || botPositions.contains(playerPos)) {
				System.out.println("Enter you placement (1-9): ");
				playerPos = sc.nextInt();
			}
	
			placePiece(gameBoard, playerPos, "player");
			String res = checkWinner();
			if(res.length() > 0) {
				System.out.println("******* RESULT ********");
				printGameBoard(gameBoard);
				System.out.println(res);
				break;
			}
			
			Random r = new Random();
			int botPos =r.nextInt(9) + 1;
			while(playerPositions.contains(botPos) || botPositions.contains(botPos)) {
				botPos =r.nextInt(9) + 1;
			}
			placePiece(gameBoard, botPos, "bot");
			
			printGameBoard(gameBoard);
			
			res = checkWinner();
			if(res.length() > 0) {
				System.out.println("******* RESULT ********");
				printGameBoard(gameBoard);
				System.out.println(res);
				break;
			}
		}
		
	}
	
	
	public static void printGameBoard(char [][] gameBoard) {
		for(char[] row : gameBoard) {
			for(char c : row) {
				System.out.print(c);
			}
			System.out.println();
		}
	}
	
	public static void placePiece(char [][] gameBoard, int pos, String user) {
		
		char symbol = ' ';
		if(user.equals("player")) {
			symbol = 'X';
			playerPositions.add(pos);
		}
		else {
			symbol = 'O';
			botPositions.add(pos);
		}
				
		switch(pos) {
			case 1: gameBoard[0][0] = symbol; break;
			case 2: gameBoard[0][2] = symbol; break;
			case 3: gameBoard[0][4] = symbol; break;
			case 4: gameBoard[2][0] = symbol; break;
			case 5: gameBoard[2][2] = symbol; break;
			case 6: gameBoard[2][4] = symbol; break;
			case 7: gameBoard[4][0] = symbol; break;
			case 8: gameBoard[4][2] = symbol; break;
			case 9: gameBoard[4][4] = symbol; break;
			default: break;
		}
	}
	
	public static String checkWinner() {
		List topRow = Arrays.asList(1, 2, 3);
		List midRow = Arrays.asList(4, 5, 6);
		List botRow = Arrays.asList(7, 8, 9);
		List leftCol = Arrays.asList(1, 4, 7);
		List midCol = Arrays.asList(2, 5, 8);
		List rightCol = Arrays.asList(3, 6, 9);
		List diag1 = Arrays.asList(1, 5, 9);
		List diag2 = Arrays.asList(3, 5, 7);
		
		List<List> winning = new ArrayList<List>();
		winning.add(topRow);
		winning.add(midRow);
		winning.add(botRow);
		winning.add(leftCol);
		winning.add(midCol);
		winning.add(rightCol);
		winning.add(diag1);
		winning.add(diag2);
		
		for(List l : winning) {
			if(playerPositions.containsAll(l))
				return "You Won !!";
			else if(botPositions.containsAll(l))
				return "BOT Won !! :(";
			else if (playerPositions.size() + botPositions.size() == 9)
				return "Tie !!";
		}
			
		
		return "";
	}

}
