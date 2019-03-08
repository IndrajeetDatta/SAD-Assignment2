public interface ATMState {
	
	void insertCard();
	void ejectCard();
	void insertPin();
	boolean requestCash(int cashToWithdraw);
	
}