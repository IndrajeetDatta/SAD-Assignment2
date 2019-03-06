// This interface will contain just those methods
// that you want the proxy to provide access to

public interface GetATMData
{
  public ATMState getATMState();
  public int getCashInMachine();
  
  public void insertCard();
	
	public void ejectCard();
	
	public void insertPin(int pinEntered);
	
	public void requestCash(int cashToWithdraw);
}