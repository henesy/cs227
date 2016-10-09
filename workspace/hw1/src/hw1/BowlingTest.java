package hw1;

public class BowlingTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TenPinBowling bowl = new TenPinBowling(5);
		bowl.roll(2);
		System.out.println(bowl.getFrameNumber());
		System.out.println(bowl.getRollNumber());
		System.out.println(bowl.isOver());
		
		bowl.roll(4);
		System.out.println(bowl.getFrameNumber());
		System.out.println(bowl.getRollNumber());
		System.out.println(bowl.isOver());

		bowl.roll(1);
		System.out.println(bowl.getFrameNumber());
		System.out.println(bowl.getRollNumber());
		System.out.println(bowl.isOver());

		bowl.roll(3);
		System.out.println(bowl.getFrameNumber());
		System.out.println(bowl.getRollNumber());
		System.out.println(bowl.isOver());

		bowl.roll(10);
		System.out.println(bowl.getFrameNumber());
		System.out.println(bowl.getRollNumber());
		System.out.println(bowl.isOver());

		bowl.roll(10);
		System.out.println(bowl.getFrameNumber());
		System.out.println(bowl.getRollNumber());
		System.out.println(bowl.isOver());

		bowl.roll(10);
		System.out.println(bowl.getFrameNumber());
		System.out.println(bowl.getRollNumber());
		System.out.println(bowl.isOver());

	}

}
