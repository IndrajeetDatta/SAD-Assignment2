// In this situation the proxy both creates and destroys
// an ATMMachine Object

public class ATMProxy implements GetATMData {

	// Allows the user to access getATMState in the 
	// Object ATMMachine
	
	public ATMState getATMState() {
		
		ATMMachine realATMMachine = new ATMMachine();
		
		return realATMMachine.getATMState();
	}

	// Allows the user to access getCashInMachine 
	// in the Object ATMMachine
	
	public int getCashInMachine() {
		
		ATMMachine realATMMachine = new ATMMachine();
		
		return realATMMachine.getCashInMachine();
		
	}
	
	public void insertCard() {
		ATMMachine realATMMachine = new ATMMachine();
		
		realATMMachine.insertCard();
	}
	
	public void ejectCard() {
		ATMMachine realATMMachine = new ATMMachine();
		realATMMachine.ejectCard();
	}
	
	public void insertPin(int pinEntered)
	{
		ATMMachine realATMMachine = new ATMMachine();
		realATMMachine.insertPin(pinEntered);
	}
	
	public void requestCash(int cashToWithdraw)
	{
		ATMMachine realATMMachine = new ATMMachine();
		realATMMachine.requestCash(cashToWithdraw);
	}
	
}