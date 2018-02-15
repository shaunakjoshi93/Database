package db2_2PhaseLocking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Transaction {
	
	int timestamp;
	String state;
	Set<Character> items_locked = new HashSet<Character>();
	List<String> pending_operations = new ArrayList<String>();
	

	
	public int getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(int timestamp) {
		this.timestamp = timestamp;
	}
	public String getState() {
		return state;
		
	}
	public void setState(String state) {
		this.state = state;
	}
	public Set<Character> getItems_locked() {
		return items_locked;
	}
	public void setItems_locked(Set<Character> items_locked) {
		this.items_locked = items_locked;
	}
	public List<String> getPending_operations() {
		return pending_operations;
	}
	public void setPending_operations(List<String> pending_operations) {
		this.pending_operations = pending_operations;
	}
}
