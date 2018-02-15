package db2_mongodbProj;

import com.mongodb.DB;
import com.mongodb.MongoClient;

public class MongodbManager {

	DB db1;
	
	
	@SuppressWarnings("deprecation")
	public DB getMongoDBConnection(){
	
		@SuppressWarnings("resource")
		MongoClient mongoclient = new MongoClient("localhost",27017);
		this.db1 = mongoclient.getDB("company");
		return db1;
	}
}
