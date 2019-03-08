public class NoCash implements ATMState {
	
	ATMMachine atmMachine;
	
	public NoCash(ATMMachine newATMMachine){
		
		atmMachine = newATMMachine;
		
	}

	public void insertCard() {
		
		System.out.println("We don't have any money");
		System.out.println("Your card is ejected");
		
	}

	public void ejectCard() {
		
		System.out.println("We don't have any money");
		System.out.println("There is no card to eject");
		
	}

	public boolean requestCash(int cashToWithdraw) {
		
		System.out.println("We don't have any money");
		return false;
		
	}

	public void insertPin() {
		
		System.out.println("We don't have any money");
		
	}	
}