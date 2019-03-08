public class NoCard implements ATMState {
	
	ATMMachine atmMachine;
	
	public NoCard(ATMMachine newATMMachine){
		
		atmMachine = newATMMachine;
		
	}

	public void insertCard() {
		
		System.out.println("Please enter your pin");
		atmMachine.setATMState(atmMachine.getYesCardState());
		System.out.println(atmMachine.getATMState());
		
	}

	public void ejectCard() {
		
		System.out.println("You didn't enter a card");
		
	}

	public boolean requestCash(int cashToWithdraw) {
		
		System.out.println("You have not entered your card");
		return false;
		
	}

	public void insertPin() {
		
		System.out.println("You have not entered your card");
		
	}
}