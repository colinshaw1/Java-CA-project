/*
*Author: Colin Shaw
*CountDown
*07/05/2020
*Intro to Programing Countdown project
*/

import javax.swing.JOptionPane;
import java.util.*;
public class CountDown {
   /*
	* Declaring variables
	*/
    private int numVowels;
    private int numCons;
    private int index;
    private int playerOneScore;
    private String playerOneWord;
    private int playerTwoScore;
    private String playerTwoWord;
    private String letters;
    private int TotalTurns;
    private int Turns;

    StringBuffer randomHold;
	private String javaKeywordsArray[] = new String[]{"abstract", "assert", "boolean", "break", "byte", "case", "catch", "char", "class", "continue",
	            "default", "do", "double", "else", "enum", "extends", "final", "finally", "float", "for", "if",
	            "implements", "import", "instanceof", "int", "interface", "long", "native", "new", "package", "private",
	            "protected", "public", "return", "short", "static", "strictfp", "super", "switch", "synchronized", "this",
	            "throw", "throws", "transient", "try", "void", "volatile", "while"};

	    private char cons[] = new char[]{'B', 'C', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'V', 'W', 'X', 'Y', 'Z'};
		private char vowels[] = new char[]{'A', 'E', 'I', 'O', 'U'};


    /*
     * Constructor
     */
    public CountDown() {
        numVowels = 0;
        numCons = 0;
        TotalTurns = 0;
        letters = "";
        randomHold = new StringBuffer();
    }

    /*
     * Letters Getter
     */
    public String getLetters() {
        return letters;
    }

    /*
     * Turns Setter
     */
    public void setTurns(int TotalTurns) {
        this.TotalTurns = TotalTurns;
    }

    /*
     * TotalTurns Getter
     */
    public int getTotalTurns() {
        return TotalTurns;
    }

    /*
     * Vowels Getter
     */
    public int getVowels() {
        return numVowels;
    }

    /*
     * Vowels Setter
     */
    public void setVowels() {
        this.numVowels = numVowels;
        numVowels = Integer.parseInt(JOptionPane.showInputDialog(null, "How many vowels?"));
    }

    /*
     * Consonants Getter
     */
    public int getCons() {
        return numCons;
    }

    /*
     * Consonants Setter
     */
    public void setCons() {
        this.numCons = numCons;
        numCons = Integer.parseInt(JOptionPane.showInputDialog(null, "How many consonants?"));
    }

    /*
     * generateLetters generates all the consonants and
     * vowels before the game starts
     */
    public void generateLetters() {
        for (int i = 0; i < getVowels(); i++) {
            index = (int) (Math.random() * 5);
            randomHold.append(vowels[index]);
        }

        for (int i = 0; i < getCons(); i++) {
            index = (int) (Math.random() * 21);
            randomHold.append(cons[index]);
        }

        letters = randomHold.toString();
        randomHold.setLength(0);
    }

    /*
     * generateGameLetters calls a number of methods in specific
     * order to obtain the letters that are required for the game
     */
    public void setGameProperties() {
        setVowels();
        setCons();
        getVowels();
        getCons();
        generateLetters();
        getLetters();
        LetterAmount();
    }

    /*
     * Get the input in the JOptionPane from playerOne
     */
    public void PlayerOneInput() {
        playerOneWord = JOptionPane.showInputDialog(null, "Player 2: Please Enter a java keyword from the following word Selection: " + letters);
    }

    /*
     * Get the input in the JOptionPane from playerTwo
     */
    public void PlayerTwoInput() {
        playerTwoWord = JOptionPane.showInputDialog(null, "Player 2: Please Enter a java keyword from the following word Selection: " + letters);
    }

    /*
     * gameInProgress takes the number of turns from the user input
     * in JOptionPane and iterate through the game for the number
     * of turns stated
     */
    public void gameInProgress() {
        int numVowels;
        int numCons;
        resetScore();
        Turns = 0;

        String Letters;

        TotalTurns = Integer.parseInt(JOptionPane.showInputDialog(null, "How many turns would you like to play?"));
        setTurns(TotalTurns);
        getTotalTurns();

        do {
            setGameProperties();
            Letters = getLetters();
            JOptionPane.showMessageDialog(null, Letters);
            PlayerOneInput();
            PlayerOneCheck();
            PlayerTwoInput();
            PlayerTwoCheck();
            Turns++;
        } while (Turns < TotalTurns);
    }

    /*
     * resetScore of both players
     */
    public void resetScore() {
        this.playerOneScore = 0;
        this.playerTwoScore = 0;
    }

    /*
     * determineWinner compares scores of player one
     * and player two and displays the appropriate dialog.
     */
    public void determineWinner() {
        if (Turns == TotalTurns) {
            if (playerOneScore > playerTwoScore) {
                JOptionPane.showMessageDialog(null, "Player one wins!!");
            } else if (playerTwoScore > playerOneScore) {
                JOptionPane.showMessageDialog(null, "Player two wins!!");
            } else {
                JOptionPane.showMessageDialog(null, "It's a draw");
            }
        }
    }

    /*
     * replayMatch allows the users to start a new game
     * once the previous game is completed
     */
    public void replayMatch() {
        int replay = JOptionPane.showConfirmDialog(null, "Would you like to play again?", "Play Again?", JOptionPane.YES_NO_OPTION);
        if (replay == JOptionPane.YES_OPTION) {
            startGame();
        } else {
            JOptionPane.showMessageDialog(null, "Goodbye, thanks for playing!");
            System.exit(0);
        }
    }

    /*
     * startGame() is called to start the game this calls
     * gameInProgress, determineWinner and replayMatch
     * in order of completion to complete a full game
     */
    public void startGame() {
        gameInProgress();
        determineWinner();
        replayMatch();
    }

    /*
     * Checks to see if the amount of letters chosen
     * by the players is a total of 9, if it is not it will the
     * players will be require to make their selection again.
     */
    public void LetterAmount() {
        this.letters = letters;
        if (letters.length() != 9) {
            JOptionPane.showMessageDialog(null, "Must be a total of 9 letters, please choose again");
            setGameProperties();
        }
    }

    /*
     * Method to check Player One's Keyword.
     * Calls isWordInArray to check if the word is present in the Array.
     */
    public void PlayerOneCheck() {
        if (isLetterInArray(playerOneWord)) {
            if (isWordInArray(playerOneScore, playerOneWord)) {
                this.playerOneScore += playerOneWord.length();
            }
        }
    }

    /*
     * Method to check Player Two's Keyword.
     * Calls isWordInArray to check if the word is present in the Array.
     */
    public void PlayerTwoCheck() {
        if (isLetterInArray(playerTwoWord)) {
            if (isWordInArray(playerTwoScore, playerTwoWord)) {
                this.playerTwoScore += playerTwoWord.length();
            }
        }
    }


    /*
     * Method to check if the letters entered are in the array
     * This method loops through each letter in the String and if
     * present add's it to the hashmap. If the hashmap is the same
     * size as the word entered by the player the player has
     * successfully entered a valid word.
     */
    public boolean isLetterInArray(String playerWord) {
	        HashSet<Character> found = new HashSet<Character>();
	        String playerWordUpperCase = playerWord.toUpperCase();
	      	//Uncomment next line and enter keyword new to test succesful flow!
	      	//String letters = "AMNHEYW";
	        for (int i = 0; i < playerWordUpperCase.length() && found.size() < playerWordUpperCase.length(); i++) {
	            if (letters.indexOf(playerWordUpperCase.charAt(i)) != -1) {
	                found.add(playerWordUpperCase.charAt(i));
	            }
	        }
	        if (found.size() >= playerWordUpperCase.length()) {
	            return true;
	        } else {
	            JOptionPane.showMessageDialog(null, "Invalid word, letter selection does not match");
	            return false;
	        }
    }

    /*
     * Check players keyword takes in two parameters,
     * int playerScore - the current players score
     * String word - the word entered by the player
     * This method carries out all the generic logic requried
     * to check if the enetered word is a java keyword.
     */
    public boolean isWordInArray(int playerScore, String word) {
        boolean isJavaKeyword = false;
        for (int i = 0; i < javaKeywordsArray.length; i++) {
            if (word.equalsIgnoreCase(javaKeywordsArray[i])) {
                isJavaKeyword = true;
            }
        }
        if (isJavaKeyword) {
            playerScore += word.length();
            JOptionPane.showMessageDialog(null, "This is a " + word.length() + " letter java keyword. Your Score is " + playerScore);
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "this not a java keyword ");
            return false;
        }
    }
}