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
	 * Which roll was last executed
	 */
	//private int //lastRollNum;
	
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
	 * Number of rolls in which a bonus will be applied
	 */
	private int bonusRolls;
	
	/**
	 * The bonus for the "next" roll (accounting for doubling up)
	 */
	private int nextRollBonus;
	
	/**
	 * The extra rolls after round 10
	 */
	private int extraRolls;
	
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
		bonusRolls	= 0;
		nextRollBonus = 1;
		//lastRollNum = 0;
		extraRolls = 0;
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
			return frameNum;
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
			return rollNum;//lastRollNum;
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
	 * Performs a roll simulation that knocks down the given number of pins
	 * @param pins
	 * The number of pins to knock down
	 */
	public void roll(int pins) {
		if (!(pins > pinsUp)) {
			boolean bonusRound = false;
			//boolean extraRound = false;
			
			if (extraRolls > 0) {
				//extraRound = true;
				extraRolls--;
			}
			
			
			if (bonusRolls > 0) {
				/* if we have a bonus roll this roll */
				score += nextRollBonus * pins;
				if (nextRollBonus > 1) {
					nextRollBonus --;
				}
				bonusRound = true;
				bonusRolls --;
			}
			
			
			if (pins == PINS) {
				/* strike */
				if (bonusRolls > 0) {
					nextRollBonus = 2;
					bonusRolls += 1;
				} else {
					bonusRolls += 2;
				}
				
				//lastRollNum = rollNum;
				rollNum = 1;
				frameNum ++;
				if (frameNum >= frameTot && extraRolls < 1) {
					/* last frame */
					extraRolls = 2;
				} else if (extraRolls > 0) {
					pinsUp = PINS;
				}
				pinsUp = PINS;
			} else if (rollNum < 2) {
				/* first roll */
				pinsUp -= pins;
				//lastRollNum = rollNum;
				rollNum ++;
				if (frameNum % 2 != 0 && frameNum >= frameTot - 1) {
					frameNum ++;
				}
			} else {
				/* second roll */
				if (pinsUp == pins) {
					/* spare occurs */
					if (bonusRolls > 0) {
						nextRollBonus = 2;
					} else {
						bonusRolls ++;
					}
					if (frameNum >= frameTot && extraRolls < 1) {
						/* last frame */
						extraRolls = 1;
					}
				}
				
				//lastRollNum = rollNum;
				rollNum = 1;
				frameNum++;
				pinsUp = PINS;
			}
			
			score += pins;

		}
		
		if (frameNum >= frameTot && extraRolls < 1) {
			gameOver = true;
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
		bonusRolls	= 0;
		nextRollBonus = 1;
		extraRolls = 0;
	}
}
