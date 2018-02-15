package db2_2PhaseLocking;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class LockTable {
		String name;
		char lock_mode;
		Set<Integer> locking_transactions = new HashSet<Integer>();
		Queue<String> waiting_transactions = new LinkedList<String>();
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public char getLock_mode() {
			return lock_mode;
		}
		public void setLock_mode(char c) {
			this.lock_mode = c;
		}
		public Set<Integer> getLocking_transactions() {
			return locking_transactions;
		}
		public void setLocking_transactions(Set<Integer> locking_transactions) {
			this.locking_transactions = locking_transactions;
		}
		public Queue<String> getWaiting_transactions() {
			return waiting_transactions;
		}
		public void setWaiting_transactions(Queue<String> waiting_transactions) {
			this.waiting_transactions = waiting_transactions;
		}
		
		
}

