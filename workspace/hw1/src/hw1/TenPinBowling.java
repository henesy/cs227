package hw1;

public class TenPinBowling {
	/**
	 * Which frame number we are on; -1 if game is over
	 */
	private int frameNum;
	
	/**
	 * Total number of frames to utilize
	 */
	private int frameTot;
	
	/**
	 * Which roll number we are going into
	 */
	private int rollNum;
	
	/**
	 * The current number of pins standing
	 */
	private int pinsUp;
	
	/**
	 * Is the game over? True if game is over; False otherwise
	 */
	private boolean gameOver;
	
	/**
	 * Current player score
	 */
	private int score;
	
	/**
	 * The second to last roll type
	 * 0: strike 1: spare 2: normal
	 */
	private int secondToLastRoll;
	
	/**
	 * The last roll type
	 * 0: strike 1: spare 2: normal
	 */
	private int lastRoll;
	
	/**
	 * The extra rolls after round 10
	 */
	private int extraRolls;
	
	/**
	 * keeps track of total extra rolls
	 */
	private int extraRollsTot;
	
	/**
	 * checks whether we've had extra rolls
	 */
	private boolean extraRollsHad;
	
	/**
	 * Total number of pins
	 */
	public static final int PINS = 10;
	
	
	/**
	 * Initializes a new Ten Pin Bowling session
	 * @param howManyFrames
	 * How many frames the game will utilize
	 */
	public TenPinBowling(int howManyFrames) {
		frameTot	= howManyFrames;
		pinsUp		= PINS;
		gameOver	= false;
		rollNum		= 1;
		frameNum	= 1;
		score		= 0;
		lastRoll 	= 2;
		secondToLastRoll = 2;
		extraRolls = 0;
		extraRollsHad = false;
		extraRollsTot = 0;
	}
	
	
	/**
	 * Returns the current frame number; -1 if game is over
	 * @return
	 * Current frame number
	 */
	public int getFrameNumber() {
		if (gameOver) {
			return -1;
		} else {
			if (frameNum > frameTot) {
				return frameTot;
			} else {
				return frameNum;
			}
		}
	}
	
	/**
	 * Returns the current roll number
	 * @return
	 * Current roll number
	 */
	public int getRollNumber() {
		if (gameOver) {
			return -1;
		} else {
			if (extraRolls > 0) {
				if (secondToLastRoll == 0 && lastRoll == 0) {
					return rollNum +1;
				} else {
					int num = Math.min(extraRolls, extraRollsTot);
					return rollNum + num + 1;
				}
			
			} else {
				return rollNum;
			}
		}
	}
	
	/**
	 * Returns the number of standing pins in the game, or -1 if game is over
	 * @return
	 * The number of pins still standing
	 */
	public int getPinsUp() {
		if (gameOver) {
			return -1;
		} else {
			return pinsUp;
		}
	}
	
	/**
	 * Returns true if game is over; false otherwise
	 * @return
	 * True if game is over; false otherwise
	 */
	public boolean isOver() {
		return gameOver;
	}
	
	/**
	 * Returns the current score of the game
	 * @return
	 * The current game score for the player
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * Helper that returns the number of extra rolls
	 * @return
	 * Number of extra rolls
	 */
	private int getExtraRolls() {
		return extraRolls;
	}
	
	/**
	 * Performs a roll simulation that knocks down the given number of pins
	 * @param pins
	 * The number of pins to knock down
	 */
	public void roll(int pins) {
		if (!(pins > pinsUp)) {
			int rollType = 2;
			int multiplier = 1;
			boolean finalFrame = false;
			boolean extraRound = false;
			/* determine if we're on the final frame*/
			if (frameNum == frameTot) {
				finalFrame = true;
			}
			
			if (pins == PINS) {
				/* strike */
				
				rollNum = 1;
				frameNum ++;
				rollType = 0;
				pinsUp = PINS;
			} else if (rollNum < 2) {
				/* first roll */
				
				rollNum ++;
				rollType = 2;
				pinsUp -= pins;
				
				/* check if we're the final frame */
				if (finalFrame) {
					
				}
			} else {
				/* second roll */
				
				if (pinsUp == pins) {
					/* spare occurs */
					rollType = 1;
				} else {
					rollType = 2;
				}
				
				rollNum = 1;
				frameNum ++;
				pinsUp = PINS;
			}
			
			/* handle final frame */
			if (extraRolls > 0) {
				extraRolls --;
				extraRound = true;
			}
			if (finalFrame) {
				if (extraRollsHad == false) {
					if (rollType == 0) {
						extraRolls = 2;
						extraRollsTot = extraRolls;
						extraRollsHad = true;
					} else if (rollType == 1) {
						extraRolls = 1;
						extraRollsTot = extraRolls;
						extraRollsHad = true;
					}
				}
				
			}
			
			/* scoring for each round */
			if (secondToLastRoll == 0) {
				multiplier ++;
			}
			if (lastRoll == 0 || lastRoll == 1) {
				multiplier ++;
			}
		
			
			/* rotate roll history */
			secondToLastRoll = lastRoll;
			lastRoll = rollType;
			
			/* check if game is over */
			if (frameNum > frameTot && extraRolls < 1) {
				gameOver = true;
			}
			
			/* actually increment score */
			if (!(gameOver) || !(extraRound)) {
				score += pins*multiplier;
			}
		}
	}
	
	/**
	 * Resets the game to the starting state ala the constructor
	 */
	public void reset() {
		pinsUp		= PINS;
		gameOver	= false;
		rollNum		= 1;
		frameNum	= 1;
		score		= 0;
		lastRoll 	= 2;
		secondToLastRoll = 2;
		extraRolls = 0;
		extraRollsHad = false;
		extraRollsTot = 0;
	}
}
