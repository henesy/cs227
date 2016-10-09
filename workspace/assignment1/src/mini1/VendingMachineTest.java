package mini1;
import mini1.VendingMachine;

public class VendingMachineTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		VendingMachine vm = new VendingMachine(2, 3, 4);
		
		System.out.println(vm.getQuarters());
		
		vm.insertDimes(2);
		vm.insertNickels(6);
		
		System.out.println(vm.getDimes());
		System.out.println(vm.getNickels());
		
		System.out.println(vm.getBalance());
		
		vm.purchase(10);
		
		System.out.println(vm.getQuarters());
		System.out.println(vm.getDimes());
		System.out.println(vm.getNickels());
		
		System.out.println(vm.getBalance());
		System.out.println(vm.toString());
		
		//
		System.out.println("BREAK");
		
		VendingMachine vm2 = new VendingMachine(2, 1, 0);
		vm2.insertNickels(20);
		System.out.println(vm2.toString());
		System.out.println(vm2.getBalance());
		vm2.purchase(5);
		System.out.println(vm2.toString());
		
		System.out.println("BREAK");
		
		VendingMachine vm3 = new VendingMachine(2, 1, 0);
		vm3.insertDimes(20);
		System.out.println(vm3.toString());
		System.out.println(vm3.getBalance());
		vm3.purchase(5);
		System.out.println(vm3.toString());
	}

}
