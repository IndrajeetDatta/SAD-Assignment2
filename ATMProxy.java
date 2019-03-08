// In this situation the proxy both creates and destroys
// an ATMMachine Object

public class ATMProxy implements GetATMData {

	// Allows the user to access getATMState in the 
	// Object ATMMachine
	ATMMachine realATMMachine = new ATMMachine();
	ATMState hasCard;     
	ATMState noCard;
	ATMState hasCorrectPin;
	ATMState atmOutOfMoney;
	
	ATMState atmState;
	
	public ATMState getATMState() {
		
		
		return realATMMachine.getATMState();
	}

	// Allows the user to access getCashInMachine 
	// in the Object ATMMachine
	
	public int getCashInMachine() {
		
		
		return realATMMachine.getCashInMachine();
		
	}
	
	public void insertCard() {
		
		realATMMachine.insertCard();
	}
	
	public void ejectCard() {
		realATMMachine.ejectCard();
	}
	
	public void insertPin()
	{
		realATMMachine.insertPin();
	}
	
	public void requestCash(int cashToWithdraw)
	{
		realATMMachine.requestCash(cashToWithdraw);
	}
	public ATMState getYesCardState() { return realATMMachine.hasCard; }
	public ATMState getNoCardState() { return realATMMachine.noCard; }
	public ATMState getHasPin() { return realATMMachine.hasCorrectPin; }
	public ATMState getNoCashState() { return realATMMachine.atmOutOfMoney; }
	
	
}