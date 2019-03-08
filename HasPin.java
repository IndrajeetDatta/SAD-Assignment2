public class HasPin implements ATMState {
	
	ATMMachine atmMachine;
	
	public HasPin(ATMMachine newATMMachine){
		
		atmMachine = newATMMachine;
		
	}

	public void insertCard() {
		
		System.out.println("You already entered a card");
		
	}

	public void ejectCard() {
		
		System.out.println("Your card is ejected");
		atmMachine.setATMState(atmMachine.getNoCardState());
		
	}

	public boolean requestCash(int cashToWithdraw) {
		
		if(cashToWithdraw > atmMachine.cashInMachine){
			
			System.out.println("You don't have that much cash available");
			System.out.println("Your card is ejected");
			atmMachine.setATMState(atmMachine.getNoCardState());
			return false;
			
		} 
		else if(atmMachine.cashInMachine <= 0){ 
			
			atmMachine.setATMState(atmMachine.getNoCashState());
			
			return false;
		}
		else 
		{
			
			System.out.println(cashToWithdraw + " is provided by the machine");
			atmMachine.setCashInMachine(atmMachine.cashInMachine - cashToWithdraw);
			
			System.out.println("Your card is ejected");
			atmMachine.setATMState(atmMachine.getNoCardState());
						
			return true;
		} 
	}

	public void insertPin() {
		
		System.out.println("You already entered a PIN");
		
	}	
}