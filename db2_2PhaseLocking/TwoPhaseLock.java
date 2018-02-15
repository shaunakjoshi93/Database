// Name: Neha Shet (1001387308)
// Name: Shaunak Joshi (1001417919)

package db2_2PhaseLocking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TwoPhaseLock{

	//This is for transaction table
	static HashMap<Integer, Transaction> transactionTable = new HashMap<Integer, Transaction>();
	//This is for lock table
	static HashMap<Character, LockTable> lockTable = new HashMap<Character, LockTable>();

	static BufferedWriter bw = null;
	static FileWriter fw = null;
	
	public static void main(String[] args){
		// TODO Auto-generated method stub
		BufferedReader br = null;
		FileReader fr = null;
		List<String> input_file = new ArrayList<String>();
		try {
			fr = new FileReader("input.txt");
			br = new BufferedReader(fr);
			fw = new FileWriter("output.txt");
			bw = new BufferedWriter(fw);

			String sCurrentLine;
			//read data from input file and keep in list
			while ((sCurrentLine = br.readLine()) != null){
				if (sCurrentLine.length() > 0){
					sCurrentLine = sCurrentLine.substring(0, sCurrentLine.length() - 1);
					input_file.add(sCurrentLine);
			    }
				//System.out.println(sCurrentLine);	
			}
			System.out.print("\n The Input file is");
			System.out.println(input_file);
			bw.write("\n The Input file is: ");
			for(String s : input_file) {
				bw.write(s+" ");
			}
			//call the transcation function to process each operation
			transaction(input_file);
			
			// display the lock table
			displayTransactionTable();
		
		    
		} catch (IOException e){
			e.printStackTrace();
		}
		 finally {
	            try {
	                // Close the writer regardless of what happens...
	                bw.close();
	            } catch (Exception e) {
	            }
	        }
	}
	
	public static void displayTransactionTable(){
		
		
		Set set = transactionTable.entrySet();
		Iterator i = set.iterator();
		try {
			bw.write("\n TRANSACTION TABLE \n \n");
			bw.write("Transcation id - Timestamp - State - Items locked - pending operations \n ");
			bw.write("-------------------------------------------------------------------------------------");
			while(i.hasNext()){
		         Map.Entry me = (Map.Entry)i.next();
		         System.out.print(me.getKey() + " : ");
		         Transaction transcObj = (Transaction)me.getValue();
		         System.out.println("State - " + transcObj.getState());
		         System.out.println("timestamp : " + transcObj.getTimestamp());
		         //write each field of transaction table to output file 
				bw.write("\n "+(int) me.getKey()+" - "+transcObj.getTimestamp()+" - "+transcObj.getState()+ " - "+transcObj.getItems_locked()+ " - "+transcObj.getPending_operations());
				
		         
		       
			}  
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void displayLockingTable(){
		
		
		Set set = lockTable.entrySet();
		Iterator i = set.iterator();
		try {
			bw.write("\n\n LOCK TABLE");
			bw.write("\n Item Name - Lock Mode - Locking Transcations - Waiting Transactions \n ");
			bw.write("-------------------------------------------------------------------------------------");
			while(i.hasNext()){
		         Map.Entry me = (Map.Entry)i.next();
		         System.out.print("Item Name "+me.getKey() + " : ");
		         LockTable lockObj = (LockTable)me.getValue();
		         System.out.println("LOCK Mode : " + lockObj.getLock_mode());
		         System.out.println("Locking Transcations : " + lockObj.getLocking_transactions());
		        // System.out.println("Waiting Transcations : " + lockObj.getLocking_transactions());
		         
					bw.write("\n "+me.getKey()+" - "+lockObj.getLock_mode()+" - "+lockObj.getLocking_transactions()+ " - "+lockObj.getWaiting_transactions());
				
		         
		       
			}  
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void transaction(List<String> input_file){
		
		for(int i = 0; i < input_file.size(); i++){
			String operation = input_file.get(i);
			
			processOperation(operation);
		}
	}//end func transaction

	//process each operation and take actions accordingly
	public static void processOperation(String operation) {
		
		try {
			bw.write("\n Current operation - "+operation);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(operation.charAt(0) == 'b'){
			createTransaction(operation);
		}
		else if(operation.charAt(0) == 'e'){
			if(!checkIfBlocked(operation)){  //checks if the transaction is blocked
				endTransaction(operation);   // end the transaction if the 'e' command encountered
				
			}
		}
		else if(operation.charAt(0) == 'r'){
			//checks if the transaction is blocked 
			if(!checkIfBlocked(operation)){

				readItem(operation);	// read the transaction if the 'r' command encountered
			}
		}
		else if(operation.charAt(0) == 'w'){
			if(!checkIfBlocked(operation)){
				writeItem(operation);		// write the transaction if the 'w' command encountered
			}
		} else {
			System.out.println("Invalid operation");
		}
		
		displayTransactionTable();
		displayLockingTable();
	}
	public static void endTransaction(String operation){
		
		// TODO Auto-generated method stub
		
		int transid = Integer.parseInt(Character.toString(operation.charAt(1)));
		Transaction trans1 = (Transaction) transactionTable.get(transid);
		//System.out.println(trans1.getTransaction_id());
		trans1.setState("committed");
		transactionTable.put(transid, trans1);
		unlockItem(operation);
	}
	public static void resumeTransaction(String operation) {
		
		int trans_id = Integer.parseInt(Character.toString(operation.charAt(1)));
		System.out.println("Resume the blocked Transaction "+trans_id);
		Transaction trans = transactionTable.get(trans_id);
		transaction(trans.pending_operations);
	}
	public static void unlockItem(String operation) {
		
		// TODO Auto-generated method stub
		
		
		int trans_id = Integer.parseInt(Character.toString(operation.charAt(1)));
		
		System.out.println("Unlock all the items held by Transaction "+trans_id);
		try {
			bw.write("\n Unlock all the items held by Transaction "+trans_id);
			
			//int indexOfItem = operation.indexOf('(') + 1;
			Transaction trans1 = (Transaction) transactionTable.get(trans_id);
			 
			Iterator<Character> iterator = trans1.items_locked.iterator();
			//iterate through all the elements of from the lock table
			while (iterator.hasNext()) {
				
			    Character item = iterator.next();
				LockTable lt = (LockTable) lockTable.get(item);
				lt.locking_transactions.remove(trans_id);
				iterator.remove();
				transactionTable.put(trans_id, trans1);
				//trans1.items_locked.remove(item);
				//Check if no other transaction has locked the item except the current transaction and waiting list is empty
				if(lt.locking_transactions.size() == 0 && lt.getWaiting_transactions().size() == 0){
					
					lockTable.remove(item);
					
					System.out.println("Remove the entry of item "+item+" from lock table");
					bw.write("\n Remove the entry of item "+item+" from lock table");
			
				} else if(lt.locking_transactions.size() == 1 && !lt.getWaiting_transactions().isEmpty()){
					
					//check if the the transaction from the waiting list can be provided with the lock
					
					String dequeued_transacion = lt.getWaiting_transactions().remove();
					int waitingTransId = Integer.parseInt(Character.toString(dequeued_transacion.charAt(1)));
					if(lt.locking_transactions.contains(waitingTransId))
					{
						
						Transaction waitingTrans = (Transaction) transactionTable.get(waitingTransId);
						waitingTrans.setState("active");
						
						lockTable.put(item, lt);
						bw.write("\nResume Transcation "+dequeued_transacion.charAt(1)+" is eligible for acquiring lock on item "+item);
						
						while(waitingTrans.getPending_operations().size() > 0){
							
							if(waitingTrans.getState()=="active"){
								String ops = waitingTrans.getPending_operations().remove(0);
								transactionTable.put(waitingTransId, waitingTrans);
								processOperation(ops);
							} else {
								break;
							}
							
							
						}
						
					}
					
				 	
				} else if(lt.locking_transactions.size() == 0 && lt.getWaiting_transactions().isEmpty()) {
					
					//No more locking transactions but waiting transactions, so awaken one of the waiting transaction
					String dequeued_transacion = lt.getWaiting_transactions().remove();
					int waitingTransId = Integer.parseInt(Character.toString(lt.getWaiting_transactions().remove().charAt(1)));
					Transaction waitingTrans = (Transaction) transactionTable.get(waitingTransId);
					waitingTrans.setState("active");
					
					lockTable.put(item, lt);
					bw.write("\nResume Transcation "+dequeued_transacion.charAt(1)+" is eligible for acquiring lock on item "+item);
					
					while(waitingTrans.getPending_operations().size() > 0){
						
						if(waitingTrans.getState()=="active"){
							String ops = waitingTrans.getPending_operations().remove(0);
							transactionTable.put(waitingTransId, waitingTrans);
							processOperation(ops);
						} else {
							break;
						}
						
						
					}
				
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// tring to acquire read lock
	public static void readItem(String operation) {
		
		// TODO Auto-generated method stub
		int trans_id = Integer.parseInt(Character.toString(operation.charAt(1)));
		int indexOfItem = operation.indexOf('(') + 1;
		char item = operation.charAt(indexOfItem);
		Transaction trans1 = (Transaction) transactionTable.get(trans_id);
		//check if the item is already locked
		if(!checkIfLocked(item)) {
			
			LockTable lt_obj = new LockTable();
			lt_obj.setLock_mode(operation.charAt(0));
			lt_obj.getLocking_transactions().add(trans_id);
			lockTable.put(item, lt_obj);
			
			//updating items lock field in transaction table
			
			trans1.getItems_locked().add(item);
			transactionTable.put(trans_id, trans1);
			System.out.println("Operation " + operation + "is successful.");
			try {
				bw.write("\n Operation " + operation + "is successful.");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			//check if the item is read locked and the requesting transaction is the read request
			LockTable ltObj = (LockTable) lockTable.get(item);
			if(ltObj.getLock_mode() == 'r'){
				
				ltObj.getLocking_transactions().add(trans_id);
				lockTable.put(item, ltObj);
				
				//updating items lock field in trasnaction table
				trans1.getItems_locked().add(item);
				transactionTable.put(trans_id, trans1);
				System.out.println("Operation " + operation + "is successful.");
				try {
					bw.write("\n Operation " + operation + "is successful.");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				
				int timpestampOfCurrent = trans1.getTimestamp();
				Set<Integer> lockingTrans = ltObj.getLocking_transactions();
				int flag = 0;
				for (int lt : lockingTrans) {
					int lockTimeStamp = transactionTable.get(lt).getTimestamp();
					if(timpestampOfCurrent > lockTimeStamp){
						
						flag = 1;
						break;
					}
				}
				if(flag == 0){
					
					//allow to wait as it is the older transaction
					ltObj.getWaiting_transactions().add(operation);
					lockTable.put(item, ltObj);
					trans1.setState("blocked");
					trans1.getPending_operations().add(operation);
					transactionTable.put(trans_id, trans1);
					System.out.println("Operation " + operation + "is waiting now.");
					try {
						bw.write("\n Using wait/die protocol");
						bw.write("\n Operation " + operation + "is waiting now. Transaction "+trans_id+" is blocked.");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				} else {
					//abort transaction as it the younger transaction
					trans1.setState("aborted");
					transactionTable.put(trans_id, trans1);
					System.out.println("Operation " + operation + "is aborted. The transaction "+trans_id+" is younger one");
					try {
						bw.write("\n Using wait/die protocol");
						bw.write("\n Operation " + operation + "is aborted. The transaction "+trans_id+" is aborted as it is younger one");
						abortTransaction(trans_id,operation);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
			
		}
	}

	public static boolean checkIfLocked(char item) {
		// TODO Auto-generated method stub
		
		if(lockTable.containsKey(item)){
			return true;
		}
		return false;
		//LockTable lock_table = (LockTable) lockTable.get(operation.charAt(indexOfItem));
	}
//acquiring the write lock on the item
	public static void writeItem(String operation){
		// TODO Auto-generated method stub
		int trans_id = Integer.parseInt(Character.toString(operation.charAt(1)));
		int indexOfItem = operation.indexOf('(') + 1;
		char item = operation.charAt(indexOfItem);
		Transaction trans1 = (Transaction) transactionTable.get(trans_id);
		// check if the item is not locked and make an entry for item in lock table
		if(!checkIfLocked(item)) {
			
			LockTable lt_obj = new LockTable();
			lt_obj.setLock_mode(operation.charAt(0));
			lt_obj.getLocking_transactions().add(trans_id);
			lockTable.put(item, lt_obj);
			
			//updating items lock field in trasnaction table
			
			trans1.getItems_locked().add(item);
			transactionTable.put(trans_id, trans1);
			System.out.println("Operation " + operation + "is successful. Write lock is acquired");	
		}
		else {
			LockTable ltObj = (LockTable) lockTable.get(item);
			
			if(ltObj.getLock_mode() == 'r' && ltObj.locking_transactions.size() == 1 && ltObj.locking_transactions.contains(trans_id)){
			
				ltObj.setLock_mode(operation.charAt(0));
				System.out.println("Operation " + operation + "is successful.The Lock is Upgraded");
				
			} else {
				//using the wait/die protocol
				int timpestampOfCurrent = trans1.getTimestamp();
				Set<Integer> lockingTrans = ltObj.getLocking_transactions();
				int flag = 0;
				for (int lt : lockingTrans) {
					int lockTimeStamp = transactionTable.get(lt).getTimestamp();
					if(timpestampOfCurrent > lockTimeStamp){
						
						flag = 1;
						break;
					}
				}
				if(flag == 0){
					
					//allow to wait as the transaction is older one
					ltObj.getWaiting_transactions().add(operation);
					lockTable.put(item, ltObj);
					
					trans1.setState("blocked");
					//System.out.println(trans1.getPending_operations().add("here"));
					trans1.getPending_operations().add(operation);
					transactionTable.put(trans_id, trans1); //update transaction table
					try {
						bw.write("\n Using wait/die protocol");
						bw.write("\n Operation " + operation + "is waiting now. Transaction "+trans_id+" is blocked.");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				} else {
					//abort transaction as it is the younger transaction
					
					try {
						bw.write("\n Using wait/die protocol");
						bw.write("\nWrite Operation " + operation + "is aborted. The transaction "+trans_id+" is aborted as it is younger one");
						trans1.setState("aborted");;
						transactionTable.put(trans_id, trans1);
						abortTransaction(trans_id, operation);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("Operation " + operation + "is aborted.");
				}
				
			}	
		}		
		
	}
	//start the transaction and make an entry in the transaction table
	public static void createTransaction(String operation){
		// TODO Auto-generated method stub
		int timestamp = 0;
		Transaction trans = new Transaction();
		int t_size = transactionTable.size();
	
		int trans_id = Integer.parseInt(Character.toString(operation.charAt(1)));
		try {
			bw.write("\n Transaction "+trans_id+ " is started");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		trans.setState("active");
		trans.setTimestamp(t_size + 1);
		transactionTable.put(trans_id, trans);
	}//end func createTransaction
	
	//abort the transaction and unlock all the items held by it
	public static void abortTransaction(int trans_id,String operation){
		
		Transaction trans = (Transaction) transactionTable.get(trans_id);
		
		trans.setState("aborted");
		transactionTable.put(trans_id, trans);
		unlockItem(operation);

	}//end func abortTransaction
	
	//checks if the transaction is blocked or aborted or active
	public static boolean checkIfBlocked(String operation){
		
		int trans_id = Integer.parseInt(Character.toString(operation.charAt(1)));
		Transaction trans = new Transaction();
		trans = (Transaction) transactionTable.get(trans_id);
		if(trans.state == "active"){
			return false;
		}
		else{
			try {
				if(trans.state == "blocked"){
				//add the current operation to the ordered list of the transaction table
					trans.getPending_operations().add(operation);
					transactionTable.put(trans_id, trans);
					System.out.println("Current transaction "+trans_id+" is blocked. Add the operation to pending operation list" );
					bw.write("\n Current transaction "+trans_id+" is blocked. Add the operation to pending operation list");
					return true;
				}
				else if(trans.state == "aborted"){
					System.out.println("Current transaction "+trans_id+" is already Aborted. Ignoring its operations");
					bw.write("\n Current transaction "+trans_id+" is already Aborted. Ignoring its operations");
					return true;
				}
			} catch(IOException e){
				e.printStackTrace();
			}
		}
		return false;
	}//end func checkIfBlocked
	
	
}//end class TwoPhaseLock