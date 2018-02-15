package db2_mongodbProj;

import java.io.BufferedReader;

import java.io.FileReader;

import java.sql.Connection;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.bson.Document;
import org.json.JSONObject;
import org.json.XML;

import java.sql.*;

import Beans.DepartmentDocument;
import Beans.Employee;
import Beans.ProjectDocument;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.util.JSON;
public class MainClass {

	//db object from mysql database connection
	static DbManager db = new DbManager();
	static Connection con = db.getConnection();
	
	static Statement stmt = null;
	static Statement stmt1 = null;
	
	static BufferedReader br = null;
	static FileReader fr = null;
	
	//Hash map for holding all the project data after join query in mysql
	static HashMap<Integer,ProjectDocument> hmap = new HashMap<Integer,ProjectDocument>();
	

	//Hash map for holding all the department data after join query in mysql
	static HashMap<Integer, DepartmentDocument> hmap1 = new HashMap<Integer, DepartmentDocument>();
	
	//db object for mongodb connection
 	static MongodbManager mongoDBManager = new MongodbManager();
 	static DB mongodb = mongoDBManager.getMongoDBConnection();
 	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// load the data from file to sql db
		insertIntoDb("employee");
		insertIntoDb("department");
		insertIntoDb("department_locations");
		insertIntoDb("works_on");
		insertIntoDb("project");

		//String js = new JSONObject().put("");
		System.out.println("All the file data is loaded into db");
		
		//fetch all the rquired fields from different tables in mysql and keep in hash map
		fetchProjectDataFromDB();
		//save the hashmap data by converting into json to mongodb
		displayProjectFromNoSqlDb();
		
		fetchDeptDataFromDB();
		//save the hashmap data by converting into json to mongodb
		displayDepartmentFromNoSqlDb();
		//execute queries on Documents : department and project and display in xml
		displayFromProject();
		displayDepartmentQueries();
	}

	//execute queries on Documents : department and project and display in xml
	public static void displayFromProject(){
		
		JSON json1 = new JSON();
		String serialize;
		
		
		/*
		 * 
		 * 
		 * MongoCollection<Document> collection = database.getCollection("myCollection");

AggregateIterable<Document> output = collection.aggregate(Arrays.asList(
        new Document("$unwind", "$views"),
        new Document("$match", new Document("views.isActive", true)),
        new Document("$sort", new Document("views.date", 1)),
        new Document("$limit", 200),
        new Document("$project", new Document("_id", 0)
                    .append("url", "$views.url")
                    .append("date", "$views.date"))
        ));
		 * 
		 * 
		 */
		
//		DBCollection collection = mongodb.getCollection("project");
//		Iterable<DBObject> output  = (Iterable<DBObject>) collection.aggregate(Arrays.asList(
//				(DBObject) new BasicDBObject("$unwind", "$empList"),
//				(DBObject) new BasicDBObject("$match", new BasicDBObject("empList.hours",new BasicDBObject("$gt",30.00))),
//				(DBObject)  new BasicDBObject("$project",new BasicDBObject("project_name",1).append("hours", "empList.hours").append("fname", "empList.fname"))
//				));
//		System.out.println("\n List of employees working on project with hours > 30");
//		for (DBObject dbResult : output)
//		{
//		    System.out.println(dbResult);
//		}
		
		
		
		
		//sort based on department name
		System.out.println("Sort the result on department name in PROJECT ");
		
		DBCollection collection = mongodb.getCollection("project");
		BasicDBObject fields = new BasicDBObject();
		fields.put("project_name", 1);
		fields.put("department_name", 1);
		
		DBCursor cursor = collection.find(new BasicDBObject(),fields);
		cursor.sort(new BasicDBObject("department_name",1));
		
		while(cursor.hasNext()){
			System.out.println(cursor.next());
		}
		
		serialize = json1.serialize(cursor);
        serialize = "{ project:"+serialize+"}";
     //   System.out.println(serialize);
        displayInXML(serialize);
		
		//show the first five departments
		System.out.println("Limit the result to 5 records");
		BasicDBObject field1 = new BasicDBObject();
		field1.put("project_name", 1);
		field1.put("department_name", 1);
		//field1.put("_id", 0);
		
		DBCursor cursor1 = collection.find(new BasicDBObject(),fields).limit(5);
		
		
		while(cursor1.hasNext()){
			System.out.println(cursor1.next());
		}
		
		serialize = json1.serialize(cursor1);
        serialize = "{ project:"+serialize+"}";
     //   System.out.println(serialize);
        displayInXML(serialize);
		
		
		
		
		System.out.println("Show the projects starting with letter letter P");
		//db.users.findOne({"username" : {$regex : ".*son.*"}});
		BasicDBObject queryFields = new BasicDBObject();
		queryFields.put("project_name",
				new BasicDBObject("$regex", "^P"));
		DBCursor cursor2 = collection.find(queryFields,fields);
		//cursor.sort(new BasicDBObject("department_name",1));
		while(cursor2.hasNext()){
			System.out.println(cursor2.next());
		}
		
		serialize = json1.serialize(cursor2);
        serialize = "{ project:"+serialize+"}";
     //   System.out.println(serialize);
        displayInXML(serialize);
		
		
	}
	

	//execute queries on Documents : department and project and display in xml
	public static void displayDepartmentQueries(){
		
		JSON json1 =new JSON();
		String serialize;
		
		//sort based on department name
				System.out.println("Sort the Department Documents on department name in PROJECT ");
				
				DBCollection collection = mongodb.getCollection("department");
				BasicDBObject fields = new BasicDBObject();
				fields.put("_id", 0);
				
				DBCursor cursor = collection.find(new BasicDBObject(),fields);
				cursor.sort(new BasicDBObject("department_name",1));
				
				while(cursor.hasNext()){
					System.out.println(cursor.next());
				}
				
				serialize = json1.serialize(cursor);
		        serialize = "{ department:"+serialize+"}";
		        displayInXML(serialize);
		        
		        
			// Retrieve department based on the last name of manager = Wong
				System.out.println("Retrieve department based on the last name of manager = Wong");
				BasicDBObject fieldsQuery = new BasicDBObject();
				fieldsQuery.put("department_manager", "Wong");
				BasicDBObject fields1 = new BasicDBObject();
				fields1.put("_id", 0);
				fields1.put("department_manager", 1);
				fields1.put("department_name", 1);
				
				DBCursor cursor1 = collection.find(fieldsQuery,fields1);
				
				
				while(cursor1.hasNext()){
					System.out.println(cursor1.next());
				}
				
				serialize = json1.serialize(cursor1);
		        serialize = "{ department:"+serialize+"}";
		        displayInXML(serialize);
			//	Fetch the manager of the department"
				
				System.out.println("Fetch the manager of the department");
				
				BasicDBObject fieldsProject = new BasicDBObject();
				fieldsProject.put("_id", 0);
				fieldsProject.put("department_manager", 1);
				fieldsProject.put("department_name", 1);

				DBCursor cursor3 = collection.find(new BasicDBObject(),fieldsProject);
				
				while(cursor3.hasNext()){
					System.out.println(cursor3.next());
				}
		        
				serialize = json1.serialize(cursor3);
		        serialize = "{ department:"+serialize+"}";
		     //   System.out.println(serialize);
		        displayInXML(serialize);
		        
		        
		        
				
	}
  
	public static void displayInXML(String serialize){
		
		  JSONObject jobj = new JSONObject(serialize);
	      String xml = XML.toString(jobj);
	      System.out.println("-----------XML----------");
	      System.out.println(xml);
	      System.out.println("--------------------------------------------- \n");
	}
	
	//save the hashmap data by converting into json to mongodb

	@SuppressWarnings("deprecation")
	public static void displayProjectFromNoSqlDb(){
		Set set = hmap.entrySet();
		Iterator i = set.iterator();

		System.out.println("----------------------------Project JSON data to store in Mongodb---------------------------------------------------------\n");
		while(i.hasNext()){
			
		     Map.Entry me = (Map.Entry)i.next();
		     Gson gson = new Gson(); //used gson library to convert the java object to json
		     
		     ProjectDocument projectObj = (ProjectDocument)me.getValue();
		     projectObj.set_id(projectObj.getProject_id());
		    
		     String json = gson.toJson(projectObj);
		    System.out.println(json);
		 	DBCollection coll = mongodb.getCollection("project");
		 	DBObject dbObject = (DBObject) JSON.parse(json);
			coll.save(dbObject); // save the object in mongodb
		}
	}
	
	
	//save the hashmap data by converting into json to mongodb
	public static void displayDepartmentFromNoSqlDb() {
		
		// TODO Auto-generated method stub
		Set set = hmap1.entrySet();
		Iterator i = set.iterator();
		System.out.println("----------------------------Department JSON data to store in Mongodb-------------------------------------------------------/n");
		
		while(i.hasNext()){
			
		     Map.Entry me = (Map.Entry)i.next();
		     Gson gson = new Gson();  //used for converting to json object
		     
		     DepartmentDocument deptObj = (DepartmentDocument)me.getValue();  
		     String json = gson.toJson(deptObj);
		     System.out.println(json);
		 	 DBCollection coll = mongodb.getCollection("department");
		 	 DBObject dbObject = (DBObject) JSON.parse(json);
			 coll.save(dbObject);		     
		}

	}
	
	//fetch all the fields from mysql database to construct department document
	public static void fetchDeptDataFromDB() {
		// TODO Auto-generated method stub
		try {
			String query = "SELECT dept_id, dept_name as Department_Name, lname as Manager_Lname, location as Dept_locations "
					+ "FROM department_locations as dl INNER JOIN department as d ON dl.l_dept_id = d.dept_id INNER JOIN employee as e ON d.d_ssn = e.ssn ORDER BY dept_name;";
			
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				
				DepartmentDocument dept_obj= new DepartmentDocument();
				
				if(hmap1.get(rs.getInt(1)) != null) { //if the map already contains department id
					
					dept_obj = (DepartmentDocument) hmap1.get(rs.getInt(1));
					dept_obj.getDept_locations().add(rs.getString(4));
					hmap1.put(rs.getInt(1), dept_obj);
				}
				else {

					dept_obj.setDepartment_name(rs.getString(2));
					dept_obj.setDepartment_manager(rs.getString(3));
					dept_obj.getDept_locations().add(rs.getString(4));
					dept_obj.set_id(rs.getInt(1));
					hmap1.put(rs.getInt(1), dept_obj);
				}
			}
			stmt.close();
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
	}
	

	
	//fetch all the fields from mysql database to construct project document
	public static void fetchProjectDataFromDB() {
		// TODO Auto-generated method stub
		
		try {
			
			String query = "SELECT fname as FirstName, lname as LastName, hours, project_name , project_id, dept_name FROM works_on AS w JOIN (employee as e, project as p, department as d)  ON (e.ssn = w.w_ssn and w.w_project_id = p.project_id and p.department_id =d.dept_id) ORDER BY p.project_id";
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				
				Employee emp = new Employee();
				emp.setFname(rs.getString(1));
				emp.setLname(rs.getString(2));
				emp.setHours(rs.getFloat(3));
				
				ProjectDocument projectDoc = new ProjectDocument();
				// check if the map already contains project id
				if(hmap.get(rs.getInt(5)) != null){  
					
					projectDoc = (ProjectDocument) hmap.get(rs.getInt(5));
					projectDoc.getEmpList().add(emp);
					hmap.put(rs.getInt(5),projectDoc);
					
				} else {
					
					projectDoc.setProject_id(rs.getInt(5));
					projectDoc.setProject_name(rs.getString(4));
					projectDoc.setDepartment_name(rs.getString(6));
					projectDoc.getEmpList().add(emp);
					hmap.put(rs.getInt(5),projectDoc);
				}
				//System.out.println(rs.getString(1));
				//i = i+1;
				
			}
			stmt.close();
			
		} catch(Exception e) {
			
		}
		
	}
	//load the data into mysql database
	public static void insertIntoDb(String modelName) {
		
		try {
					
					String deleteQuery = "delete from "+modelName;
					
					stmt = con.createStatement();
					stmt.executeUpdate(deleteQuery);
					stmt.close();
					
					fr = new FileReader(modelName+".txt");
					br = new BufferedReader(fr);
					
					String sCurrentLine;
					//read data from input file and keep in list
					while ((sCurrentLine = br.readLine()) != null){
						
						String[] tuples = sCurrentLine.split(", ");
						//System.out.println(tuples.length);
						String attr = "";
						
						for(int i =0;i<tuples.length;i++) {
							
							attr = attr+tuples[i]+",";
						}
						String query = "INSERT INTO "+modelName+" values("+attr.substring(0, attr.length() - 1)+")";
						
						stmt = con.createStatement();
						stmt.executeUpdate(query);
						stmt.close();
						//System.out.println(sCurrentLine);	
					}
				} catch(Exception e) {
					e.printStackTrace();
				}
				
	}

}
