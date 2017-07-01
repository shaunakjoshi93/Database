// Final_phase5 Java Code ;
/*
 * 
 * 
 * DBMS CSE 5330 Project on 'Automobile Showroom'
 * (Phase 5)
 * 
 * 
 * By
 * SHAUNAK JOSHI, ATHARV KASHYAP
 * Team ID :16
 * 
*/

import java.sql.*;
import java.util.Date;
import java.io.*;
import java.util.*;
import java.lang.*;

public class Final_Phase5 
{
	public static Connection conn=null;
    Statement stmt=null;
    
  //Function for Database connectivity.
    public void DBConnection()
    {
        String url = "jdbc:oracle:thin:@localhost:1521:orcl";
        String db_id="System"; // Replace with Network ID
        String db_pwd="Atharv2511";//Replace with Oracle Password
        try
        {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn=DriverManager.getConnection(url, db_id, db_pwd);
        }
        
        catch(ClassNotFoundException e)
        {
            System.out.println("LOADING ERROR:"+e.toString());
        }
        
        catch(SQLException se)
        {
            System.out.println("FAILED TO CONNECT TO DB : "+se.toString());               
        }
        
        catch(Exception ee)
        {
            System.out.println("Exception in connection : "+ee.toString());
            
        }  
    }
    
   
	public static void main(String args[]) throws IOException
	
	{
		Final_Phase5 j = new Final_Phase5();
		String Choice = "", table = "", column1 = "", column2 = "", new_value_str="", value2="",color = "";
        int scase = 0,scase2 =0, scase3 =0, id=0, max = 0, col_index = 0, new_value_int = 0,car = 0,rate,cost;
        int flag = 0;
        
        InputStreamReader i= new InputStreamReader(System.in);
        BufferedReader stdin = new BufferedReader(i);
        
		     while(scase!=7)
		     {
		    	    System.out.println("\n **********Automobile Showroom**********\n");
		            System.out.println("\n Please select from the following options : ");
		            System.out.println("\n 1. View Tables");
		            System.out.println("\n 2. Update");
		            System.out.println("\n 3. Generate Reports");
		            System.out.println("\n 4. Insert");
		            System.out.println("\n 5. Delete");
		            System.out.println("\n 6. Generate Reports based on User Input  ");
		            System.out.println("\n 7. Exit");
		            
		            System.out.println("\n\n PLEASE ENTER YOUR CHOICE :");
		            Choice=stdin.readLine();
		            scase=Integer.parseInt(Choice);
		            
		            switch(scase)
		            {
		            case 1:
		            {
		            	BufferedReader Input=new BufferedReader(new InputStreamReader(System.in));
		            	j.DBConnection();
		            	
		            	while(scase2!=14)
		            	{
		            		System.out.println("\n\n 1.  Display contents of Automobile Showroom table");
		            		System.out.println("\n\n 2.  Display contents of Buy table ");
		            		System.out.println("\n\n 3.  Display contents of Car table");
		            		System.out.println("\n\n 4.  Display contents of Car_Color table");
		            		System.out.println("\n\n 5.  Display contents of Car_Feature table");
		            		System.out.println("\n\n 6.  Display contents of Customer table");
		            		System.out.println("\n\n 7.  Display contents of Employee table");
		            		System.out.println("\n\n 8.  Display contents of Personal table");
		            		System.out.println("\n\n 9.  Display contents of Rates table");
		            		System.out.println("\n\n 10. Display contents of Services table");
		            		System.out.println("\n\n 11. Display contents of Servicing_Details table");
		            		System.out.println("\n\n 12. Display contents of Visits_for_Quotation table");
		            		System.out.println("\n\n 13. Display contents of Works_For table");
		            		System.out.println("\n\n 14. Go to previous menu ");
		            		
		            		System.out.println("\n\n PLEASE ENTER YOUR CHOICE :");
		            		scase2=Integer.parseInt(Input.readLine());
		            	
		            		switch(scase2)
		            		{
		            		case 1:
		            		   {
		            			j.display_automobile_showroom();
		            			break;
		            		   }
		            		
		            		case 2:
		            		   {
		            			j.display_buy();
		            			break;
		            		   }   
		            		
		            		case 3:
		            		   {
		            			j.display_car();
		            			break;
		            		   }      
		            		   
		            		case 4:
		            		   {
		            			j.display_car_color();
		            			break;
		            		   }     
		            		case 5:
		            		   {
		            			j.display_car_feature();
		            			break;
		            		   }        
		            		   
		            		case 6:
		            		   {
		            			j.display_customer();
		            			break;
		            		   }          
		            		   
		            		case 7:
		            		   {
		            			j.display_Employee();
		            			break;
		            		   }   
		            		   
		            		case 8:
		            		   {
		            			j.display_Personal();
		            			break;
		            		   }    
		            		   
		            		case 9:
		            		   {
		            			j.display_Rates();
		            			break;
		            		   }   
		            		
		            		case 10:
		            		   {
		            			j.display_Services();
		            			break;
		            		   }   
		            		
		            		case 11:
		            		   {
		            			j.display_ServicingDetails();
		            			break;
		            		   } 
		            		
		            		case 12:
		            		   {
		            			j.display_Visitsforquotation();
		            			break;
		            		   }   
		            		   
		            		case 13:
		            		   {
		            			j.display_works();
		            			break;
		            		   }    
		            		   
		            		case 14:
		            		   {
		            			
		            			break;
		            		   }    
		            		
		            		default:
	            			{
	            				System.out.println("\n\n Wrong Selection!");
	            				break;
	            			}
		            		   
		             }//end switch case for view table
		            		
		          }//end of while for view tables
		            break;	
		        }//End of case 1 for view tables
		         
		            
		    case 2:
		     {
		    	 BufferedReader Input = new BufferedReader(new InputStreamReader(System.in));
	            	j.DBConnection();
	            	
	            	while(scase2!=2)
	            	{
	            		
	            		System.out.println("\n\n  Table available for updating  is:");
	            		System.out.println("\n\n 1.  Car Color ");
	            		System.out.println("\n\n 2.  Go back to previous Menu");
	            		
	            		System.out.println("\n\n PLEASE ENTER YOUR CHOICE :");
	            		Choice = stdin.readLine();
	            		Choice.toString().trim();
	            		scase2 = Integer.parseInt(Choice);
	            		
	            switch(scase2)
	            {
	            case 1:
	              {
	            	table = "F16_16_Car_Color";
      				column1 = "C_ID";  
      				System.out.println("\n\n .................Updating Car Color table...............\n");
      				System.out.println("\n\n Displaying Content of Car_Color Table\n");
      				j.display_car_color();
      				
      				do{
      					System.out.println("\n\n Valid car IDs are between 11 and 21");
	                    System.out.println("\nPlease Enter valid car ID from above which you want to update: ");
	                    Choice = stdin.readLine();
	                    id=Integer.parseInt(Choice);	
      				} while(id<11 | id>21);
      				
      				
					do
      				{
      					
      					System.out.println("\n\nValid Car Color which you want to change: WHITE BLACK BLUE ROYAL BLUE");
      				    value2 = stdin.readLine();
      				    value2.toString().trim();
      					
      				}while(!value2.matches("[a-zA-Z]+"));
      				
      				
      				System.out.println("\n\n You can update column:");
    				
      				System.out.println("\n"+value2);
    				
    				while (scase3!=2)
    				{
    					System.out.println("\n\n 1. Car Color:");
    					System.out.println("\n\n 2. Go back to Previous menu:");
    					System.out.println("\n\n PLEASE ENTER YOUR CHOICE :");
    					Choice=stdin.readLine();
    					scase3=Integer.parseInt(Choice);
    					
    				switch(scase3)
      				{
      				case 1:
      				{
      				  column2="Color";
      				  col_index=2;
      				
      				do
      				{
      					System.out.println("\nEnter new Color for car\n");
          				new_value_str=stdin.readLine();
      					
      					new_value_str.toString().trim();
      					
      					if(new_value_str.length()<0 || new_value_str.length()>20)
      					{
      						System.out.println("\n\nPlease Enter value between 1 to 20 characters\n\n");
      						flag=1;
      					}
      					
      					else if(!new_value_str.matches("[a-zA-z]+"))
      					{
      					   System.out.println("Please do not enter numbers,enter only alphabets");
      					   flag=1;
      					}
      					
      					else
      					{
      						flag=0;
      					}
      				}while(flag==1); // Valid new name for station
      				
      				System.out.println("\n"+value2);  
      				j.update_table_str(table, column2, new_value_str, column1, id,value2);
    				System.out.println("\n\n Record successfully updated!!");
    				j.display_car_color();
      				break;
      	
      				}//End of case 1 for column display in switch case 3
      				
      				case 2:
      				{
      					break;
      				}
      				
      				default:
      					System.out.println("Wrong Input, Please correct the input");
      				    break;
      				
      			}  //End of switch for columns display menu
      				
	              }//end of while for scase3
      			break;
      			
	              }//End of case1 for update car color table
	            
	            case 2:
	            {
	            	break;
	            }		
	            
	            default:
	            	System.out.println("Wrong Input, Please correct the input");
	            
	            
	            }//End of switch for update table main menu
	            	
	            
	          }//end of while for update  tables
		    	 
		    break; //Come out of while loop and display main menu	 
		    	 
		     }// End of case 2 for update  Table
		            
		      
		     
		    case 3:
        	{
        		
        		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        		j.DBConnection();
        		do
        		{
        			System.out.println("\n\n1. Get the Report of In house Customers");
        			System.out.println("\n\n2. Get the Report of Out house Customers");
        			System.out.println("\n\n3. Get the Report of Potential Customers");
        			System.out.println("\n\n4. Get the Report of Top 3 Cars");
        			System.out.println("\n\n5. Get the Report of Bottom 2 Cars");
        			System.out.println("\n\n6. Get the Showroom Track Record");
        			System.out.println("\n\n7. Get the Detailed Showroom Track Record");
        			System.out.println("\n\n8. Get the Regional Showroom Report");
        			System.out.println("\n\n9. Get the Regional Service Center Report");
        			System.out.println("\n\n10. Get the Customer rating report");
        			System.out.println("\n\n11. Get the Sales Executive Report");
        			System.out.println("\n\n12. Get the Number One Employee Report");
        			System.out.println("\n\n13. Get the Priority Customer Report");
        			System.out.println("\n\n14. Get the Revenue Generated Per Car Report");
        			System.out.println("\n\n15. Get the Overall Showroom Profit Report");
        			System.out.println("\n\n16. Get the Overall Service Center Profit Report");
        			System.out.println("\n\n17. Get the Overall Profit Report");
        			System.out.println("\n\n18. Go to previous menu");
        			
        			
        			System.out.println("\n\n PLEASE ENTER YOUR CHOICE:");
        			scase2=Integer.parseInt(input.readLine());
        			
        			switch(scase2)
        			{
        				case 1:
        				{
        					j.in_house_customers();
        					break;
        				}
        				
        				case 2:
        				{
        					j.out_house_customers();
        					break;
        				}
        				
        				case 3:
        				{
        					j.potential_customers();
        					break;
        				}
        				
        				case 4:
        				{
        					j.top_three_cars();
        					break;
        				}
        				
        				case 5:
        				{
        					j.bottom_two_cars();
        					break;
        				}
        				
        				case 6:
        				{
        					j.showroom_track_record();
        					break;
        				}
        				
        				case 7:
        				{
        					j.detailed_showroom_track_record();
        					break;
        				}
        				
        				case 8:
        				{
        					j.regional_showroom_report();
        					break;
        				}
        				
        				case 9:
        				{
        					j.regional_service_report();
        					break;
        				}
        				
        				case 10:
        				{
        					j.customer_rating();
        					break;
        				}
        				
        				case 11:
        				{
        					j.sales_executive();
        					break;
        				}
        				
        				case 12:
        				{
        					j.number_one_employee();
        					break;
        				}
        				
        				case 13:
        				{
        					j.priority_customer();
        					break;
        				}
        				
        				case 14:
        				{
        					j.revenue_per_car();
        					break;
        				}
        				
        				case 15:
        				{
        					j.overall_showroom_profit();
        					break;
        				}
        				
        				case 16:
        				{
        					j.overall_service_profit();
        					break;
        				}
        				
        				case 17:
        				{
        					j.overall_profit();
        					break;
        				}
        				
        				case 18:
        				{
        					break;
        				}
        				
        				
        				default:
        				{
        					System.out.println("\n\n Wrong Selection!");
        					break;
        				}
        			}// end of switch cases for seeing business goals
        			
        		} while (scase2 != 18);
        		
        		break; // break out of Functionality menu and display Main Menu
        		
        	} // End of case 3 : Business Goals
		     
		     
		     
		    case 4:
			{
				BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        		j.DBConnection();
        		
        		while(scase2 != 2)
        		{
        			System.out.println("Tables available for inserting records");
        			System.out.println("\n\n (1) Personal table ");
            		System.out.println("\n\n (2) Go back to previous menu ");
            		
            		System.out.println("\n\n Please enter your choice :");
            		scase2 = Integer.parseInt(input.readLine());
            		
            		switch(scase2)
            		{
            			case 1:
            			{
            				table = "F16_16_Personal";
            				int iflag = 1;
            				int SSN = 0, ph_no = 0;
            				String add ="", fname ="", lname = "";
            				System.out.println("Inserting records into Personal table");
            				
            				while(iflag == 1)
            				{
            					System.out.println("Enter the SSN of the person: ");
            					SSN = Integer.parseInt(input.readLine());
            					
            					if(SSN < 682331100 || SSN > 682331999)
            					{
            						System.out.println("Please re-enter. Not a valid SSN");
            					}
            					else
            					{
            						iflag = 0;
            					}
            				}//end while - validating SSN
            				
            				while(iflag ==  0)
            				{
            					System.out.println("Please enter the add of the person: ");
            					add = stdin.readLine();
            					add.toString().trim();
            					
            					if(add.length() < 0 || add.length() > 50)
            					{
            						System.out.println("Length of the address can between 0 and 50 characters.");
            					}
            					else
            					{
            						iflag = 1;
            					}
            				}//end while - validating address
            			
            				            				
            				while(iflag == 1)
            				{
            					String temp;
            					System.out.println("Enter the phone number: ");
            					ph_no = Integer.parseInt(input.readLine());
            					temp = Integer.toString(ph_no);
            					//temp.toString().trim();
            					
            					if(temp.length() < 0 || temp.length() > 10 )
            					{
            						System.out.println("Please enter valid phone number of length = 10");
            					}
            					
            					else
            					{
            						iflag=0;
            					}
            					
            			
            				}//end while - phone number
            					
            				while(iflag == 0)
            				{
            					System.out.println("Enter the first name of the person: ");
            					fname = stdin.readLine();
            					fname.toString().trim();
            					
            					if(fname.length() < 0 || fname.length() > 20)
            					{
            						System.out.println("First name can be between 0 to 20 characters.");
            					}
            					else
            					{
            						iflag = 1;
            					}
            				}//end while - validating first name
            				
            				while(iflag == 1)
            				{
            					System.out.println("Enter the last name of the person: ");
            					lname = stdin.readLine();
            					lname.toString().trim();
            					
            					if(lname.length() < 0 || lname.length() > 20)
            					{
            						System.out.println("Last name can be between 0 and 20 characters.");
            					}
            					else
            					{
            						iflag = 0;
            					}
            				}//end while - validating last name
            				
            				System.out.println("Inserting a new record in Personal table");
            				j.insert_into_personal(table, SSN, add, ph_no, fname, lname);
            				System.out.println("After insertion");
            				j.display_personal_table_on(SSN);
            				System.out.println("The personal table after insertion of new record is :");
            				j.display_Personal();
            			}
            		
            			case 2:
            			{	
            				break;
            			}
            			
            			default:
            			{
            				System.out.println("\n\nWrong Selection!");
            				break;
            			}
            		}
        		}//end while loop
        		break;
			}//end case 4: insert   
		     

			//case 5: delete
			case 5:
			{
				BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
				j.DBConnection();
				while(scase2 != 2)
				{
					System.out.println("\n Tables available for Deleting Records: ");
					System.out.println("\n\n (1)Car_Color");
					System.out.println("\n\n (2)Go back to previous menu");
					
					System.out.println("Enter your choice: ");
					scase2 = Integer.parseInt(input.readLine());
					
					switch(scase2)
					{
						case 1:
						{
							table = "F16_16_Car_Color";
							column1 = "C_ID";
							column2 = "Color";
							System.out.println("Deleting record from Car_Color Table");
							System.out.println("Existing values in Car_Color Table are:");
							//j.display_car_color_table_on(car, color);
							j.display_car_color();
							
							
							do{
		      					System.out.println("\n\n Valid car IDs are between 11 and 21");
			                    System.out.println("\nPlease Enter valid car ID from above which you want to delete: ");
			                    Choice = stdin.readLine();
			                    car=Integer.parseInt(Choice);	
		      				} while(car<11 | car>21);
		      				
		      				
							do
		      				{
		      					
		      					System.out.println("\n\nValid Car Color which you want to change: WHITE BLACK BLUE ROYAL BLUE");
		      				    color=stdin.readLine();
		      				    color.toString().trim();
		      					
		      				}while(!color.matches("[a-zA-Z]+"));
							
							System.out.println("Do you want to delete the car with specified color: "+car +color);
							System.out.println("If you want to procede press y");
							System.out.println("To abort press any other key!");
							Choice = stdin.readLine();
							
							System.out.println("choice: "+Choice);
							
							if(Choice.toString().trim().equalsIgnoreCase("y"))
							{
								System.out.println("Deleting the selected car with specified color"+car);
								j.delete_from_car_color_table_on(table, column1, column2, car, color);
								System.out.println("\n\nRecord successfully deleted!");
						//		j.display_car_color_table_on(car, color);
								j.display_car_color();
								break;
							}
							
							System.out.println("\n\nDeletion aborted!");
							break;
									
						}//end case 3, 1
						
						case 2:
						{
							break;//go to previous menu 
						}
						
						default:
						{
							System.out.println("Wrong selection!");
						}
					}//end switch case
				}//end while
				break;
			}//end case 5 
		     
		     
			case 6: 
			{
				BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        		j.DBConnection();
        		
        		while(scase2 != 3)
        		{
        			System.out.println("\n Generating User defined  records");
        			System.out.println("\n\n (1) Get the cars who have rating greater than the user inputted rating ");
        			System.out.println("\n\n (2) Get the customers for whom the cost of service is greater than user inputted cost   ");
            		System.out.println("\n\n (3) Go back to previous menu ");
            		
            		System.out.println("\n\n Please enter your choice :");
            		scase2 = Integer.parseInt(input.readLine());
            		
            		
            		switch(scase2)
            		{
            		
            		     case 1:
            		             {
            		               System.out.println("\nEnter the minimum rate limit");
            		               rate=Integer.parseInt(stdin.readLine());
            		               System.out.println("\nThe generated report is:\n");
            		               j.cust_rating(rate);
            		                break;
            		              }
            		             
            		     case 2:
            		     {
            		    	System.out.println("\nEnter the  service cost limit");
      		               cost=Integer.parseInt(stdin.readLine());
      		               System.out.println("\nThe generated report is:\n");
      		               j.cost_of_service(cost);
      		               break;
            		     }
            		     
            		     
            		     case 3:
            		     {
            		    	 break; //return to previous menu
            		     }
            		     
            		     default: 
            		     {
            		    	 System.out.println("\nWrong input Entered!!!:");
            		     }
            		     
            		     
            	    }// end of switch case2 inside generating report
            	
            		
        		}// end of while loop 
        		break;
				
			}// end of case 6 for generating user based reports
				
		            
		             default:
		             {
		            	 System.out.println("\n\n Wrong Selection!");
         				break;
		             }
		            
		            
		        }//End of main Switch case
		            
		     }//End of while for main switch
		
	}//END of main function
	
	
	private void display_automobile_showroom()
	{
		try{
			stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT * FROM F16_16_Automobile_Showroom");
			System.out.println("\nDisplaying Results of Automobile Showroom table");
			System.out.println("\n\nShowroom_ID\t Address\t    Region\t Showroom_Maintainence_Cost\t Service_Center_Maintainence_Cost\t R_ID\t");
		
			
			while (rs.next()) 
            {
				System.out.println("\n"+rs.getString("Showroom_ID")+"\t\t"+rs.getString("Address")+"\t\t"+rs.getString("Region")+"\t\t"+rs.getString("Showroom_Maintainence_Cost")+"\t\t\t\t\t"+rs.getString("SC_Maintainence_Cost")+"\t\t\t"+rs.getString("R_ID"));
            }
			stmt.close();
		
		
		}
		
		
		catch(SQLException e)
		{
			System.out.println(e.toString());
  	}
		
	}// End of Display Automobile Showroom table 
		
	private void display_buy()
	{
		try{
			stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT * FROM F16_16_Buy");
			System.out.println("Displaying Results of Buy table");
			System.out.println("\n\nCSSN\t \t   ESSN\t\tCAR_ID\t\tShowroom_ID\t Date of Purchase\t F_ID\t");
		
			while (rs.next()) 
            {
                // Get the data from the row using the column index
				 System.out.println("\n"+rs.getString(1)+"\t   "+rs.getString(2)+"\t   "+rs.getString(3)+"\t\t"+rs.getString(4)+"\t\t"+rs.getString(5).substring(0, 10)+"\t\t   "+rs.getString(6));
            }
			stmt.close();
		
		
		}
		
		
		catch(SQLException e)
		{
			System.out.println(e.toString());
  	}
		
	}// End of Display Buy table 

	private void display_car()
	{
		try{
			stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT * FROM F16_16_Car");
			System.out.println("Displaying Results of Car table");
			System.out.println("\n\nCar_ID\t Name\t\t Power\t Weight\t Type\t\t Basic Price\t\t Purchase Price");
		
			while (rs.next()) 
            {
                // Get the data from the row using the column index
				 System.out.println("\n"+rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4)+"\t"+rs.getString(5)+"\t\t"+rs.getString(6)+"\t\t\t"+rs.getString(7));
            }
			stmt.close();
		
		
		}
		
		
		catch(SQLException e)
		{
			System.out.println(e.toString());
  	    }
		
		
		
		
	}// End of Display Car table 
	
	private void display_car_color()
	{
		try{
			stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT * FROM F16_16_Car_Color");
			System.out.println("Displaying Results of Car_Color table");
			System.out.println("\n\nCar_ID\t Color\t ");
		
			while (rs.next()) 
            {
                // Get the data from the row using the column index
				 System.out.println("\n"+rs.getString(1)+"\t"+rs.getString(2));
            }
			stmt.close();
		
		
		}
		
		
		catch(SQLException e)
		{
			System.out.println(e.toString());
  	    }
		
		
		
		
	}// End of Display Car_Color table 
	
	private void display_car_feature()
	{
		try{
			stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT * FROM F16_16_Car_Feature");
			System.out.println("Displaying Results of Car_feature table");
			System.out.println("\n\nCar_ID\t Feature        Feature_Price\t    F_ID\t ");
		
			while (rs.next()) 
            {
                // Get the data from the row using the column index
				 System.out.println("\n  "+rs.getString(1)+"\t  "+rs.getString(2)+"\t\t"+rs.getString(3)+"\t\t  "+rs.getString(4));
            }
			stmt.close();
		
		
		}
		
		
		catch(SQLException e)
		{
			System.out.println(e.toString());
  	    }
		
		
		
		
	}// End of Display Car_Feature table 
	
	private void display_customer()
	{
		try{
			stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT * FROM F16_16_Customer");
			System.out.println("Displaying Results of Customer table");
			System.out.println("\n\nCustomer_SSN\t ");
		
			while (rs.next()) 
            {
                // Get the data from the row using the column index
				 System.out.println("\n"+rs.getString(1));
            }
			stmt.close();
		
		
		}
		
		
		catch(SQLException e)
		{
			System.out.println(e.toString());
  	    }
		
		
		
		
	}// End of Display Customer table 

	private void display_Employee()
	{
		try{
			stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT * FROM F16_16_Employee");
			System.out.println("\nDisplaying Results of Employee table");
			System.out.println("\n\nEmployee_SSN\tType\tSalary\t ");
		
			while (rs.next()) 
            {
                // Get the data from the row using the column index
				 System.out.println("\n"+rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3));
            }
			stmt.close();
		
		
		}
		
		
		catch(SQLException e)
		{
			System.out.println(e.toString());
  	    }
		
		
		
		
	}// End of Display Employee table 
	
	private void display_Personal()
	{
		try{
			stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT * FROM F16_16_Personal");
			System.out.println("Displaying Results of Personal table");
			System.out.println("\n SSN\t\tAddress\t\tDate of birth\t Phone no\t First name\t Last Name ");
		
			while (rs.next()) 
            {
                System.out.println("\n"+rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3).substring(0,10)+"\t"+rs.getString(4)+"\t"+rs.getString(5)+"\t\t"+rs.getString(6));
            }
			stmt.close();
		
		
		}
		
		
		catch(SQLException e)
		{
			System.out.println(e.toString());
  	    }
		
		
		
		
	}// End of Display Personal table 
	
	private void display_Rates()
	{
		try{
			stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT * FROM F16_16_Rates");
			System.out.println("Displaying Results of Rates table");
			System.out.println("\nCustomer_SSN    Car_ID\t Showroom_ID\t Rate Scale\t Date of Rating\t  ");
		
			while (rs.next()) 
            {
                // Get the data from the row using the column index
				 System.out.println("\n  "+rs.getString(1)+"\t  "+rs.getString(2)+"\t    "+rs.getString(3)+"\t   "+rs.getString(4)+"\t\t   "+rs.getString(5).substring(0,10));
            }
			stmt.close();
		
		
		}
		
		
		catch(SQLException e)
		{
			System.out.println(e.toString());
  	    }
		
		
		
		
	}// End of Display rates table 
	
	private void display_Services()
	{
		try{
			stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT * FROM F16_16_Service");
			System.out.println("Displaying Results of Services table");
			System.out.println("\nCustomer_SSN   Car_ID\t Showroom_ID\t   ");
		
			while (rs.next()) 
            {
                // Get the data from the row using the column index
				 System.out.println("\n  "+rs.getString(1)+"\t"+rs.getString(2)+"\t   "+rs.getString(3));
            }
			stmt.close();
		
		
		}
		
		
		catch(SQLException e)
		{
			System.out.println(e.toString());
  	    }
		
		
		
		
	}// End of Display service table 
		
	private void display_ServicingDetails()
	{
		try{
			stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT * FROM F16_16_Servicing_Details");
			System.out.println("\nDisplaying Results of Servicing Details table");
			System.out.println("\nCustomer_SSN   Car_ID\t Showroom_ID\t Date of Service\t Cost of Service\t Type\t   ");
		
			while (rs.next()) 
            {
                // Get the data from the row using the column index
				 System.out.println("\n  "+rs.getString(1)+"\t"+rs.getString(2)+"\t   "+rs.getString(3)+"\t\t   "+rs.getString(4).substring(0,10)+"\t\t     "+rs.getString(5)+"\t\t      "+rs.getString(6));
            }
			stmt.close();
		
		
		}
		
		
		catch(SQLException e)
		{
			System.out.println(e.toString());
  	    }
		
		
		
		
	}// End of Display servicing details table
	
	private void display_Visitsforquotation()
	{
		try{
			stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT * FROM F16_16_Visits_For_Quotation");
			System.out.println("Displaying Results of Vists for Quotation table");
			System.out.println("\nCustomer_SSN\t Showroom_ID\t Employee SSN\t Date of Visit\t Car_ID\t    ");
		
			while (rs.next()) 
            {
                // Get the data from the row using the column index
				 System.out.println("\n"+rs.getString(1)+"\t  "+rs.getString(2)+"\t\t   "+rs.getString(3)+"\t  "+rs.getString(4).substring(0,10)+"\t   "+rs.getString(5));
            }
			stmt.close();
		
		
		}
		
		
		catch(SQLException e)
		{
			System.out.println(e.toString());
  	    }
		
		
		
		
	}// End of Display Visits for quotation table table
		
	private void display_works()
	{
		try{
			stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT * FROM F16_16_Works_For");
			System.out.println("Displaying Results of works for table");
			System.out.println("\nEmployee SSN\t  Showroom_ID\t From Date \t To Date\t    ");
		
			while (rs.next()) 
            {
                // Get the data from the row using the column index
				 System.out.println("\n  "+rs.getString(1)+"\t      "+rs.getString(2)+"\t"+rs.getString(3).substring(0,10)+"\t"+rs.getString(4).substring(0,10));
            }
			stmt.close();
		
		
		}
		
		
		catch(SQLException e)
		{
			System.out.println(e.toString());
  	    }
		
		
		
		
	}// End of Display workson table
	
	private void update_table_str(String table, String column2, String new_value_str,
			String column1, int id,String value2) 
    {
    	try 
		{
    		System.out.println("\n"+value2);
    		stmt = conn.createStatement();
			
			String sql="UPDATE F16_16_Car_Color SET Color ='"+new_value_str+"' WHERE C_ID="+id+"AND Color= '"+value2+"'";
			stmt.executeQuery(sql);
			//stmt.executeQuery(" UPDATE "+table+" SET "+column2+" = ' "+new_value_str+" ' WHERE "+column1+" = "+id);
			//stmt.executeQuery(" UPDATE "+table+" SET "+column2+" = ' "+new_value_str+" ' WHERE "+column1+" = "+id+" AND "+column2+" = '"+value2+"'");
			//stmt.executeQuery("UPDATE F16_16_Car_Color SET Color = ' "+new_value_str+" ' WHERE C_ID ="+id);
			//stmt.executeQuery("UPDATE F16_16_Car_Color SET Color = '"+new_value_str+"' WHERE C_ID ="+id+"AND Color='"+value2+"'");
			//stmt.executeQuery("UPDATE F16_16_Car_Color SET Color = ' "+new_value_str+" ' WHERE Color = ' "+value2+" '");
			stmt.close();
			
		} 
		
		catch (SQLException e) 
		{
			System.out.println(e.toString());
		}
    
	} // end of update string function
	
	public int ispresent(String table, String new_value, int col_index)
    {
        int flag=0;
        try 
        {
            // Create a result set containing all data from my_table
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM "+table+"");
            
            // Fetch each row from the result set
            while (rs.next()) 
            {
                // Get the data from the row using the column index
                
                if(rs.getString(col_index).equals(new_value))
                {
                       flag=1;
                       break;
                }
    
            }
            stmt.close();
        } 
        catch (SQLException e) 
        {
        	System.out.println(e.toString());
        }     
        return flag;
    } // end of ispresent() 

	private void in_house_customers()
	{
		try
		{
			stmt = conn.createStatement();
			String query = "SELECT p.SSN, p.First_Name, p.Last_Name, p.Phone_number, p.Address, s.CSSN, COUNT(s.CSSN) AS Number_Of_Visits FROM F16_16_Personal p, F16_16_Service s WHERE p.SSN = s.CSSN GROUP BY s.CSSN, p.SSN, p.First_Name, p.Last_Name, p.Phone_number, p.Address  HAVING COUNT(s.CSSN) >= 1 ORDER BY s.CSSN";
			ResultSet rs = stmt.executeQuery(query);
			System.out.println("\nDisplaying the in house Customers Report:");
			System.out.println("\n---------------------------------");
			System.out.println("\n\n   SSN\t\tFirst name\tLast name\tPhone number\t\tAddress\t\t\t\tCSSN\t\t\tNumber of Visits");
			
			while (rs.next())
			{
				// Get the data from the row using the column index
				System.out.println("\n"+rs.getString(1)+"\t"+rs.getString(2)+"\t\t"+rs.getString(3)+"\t\t"+rs.getString(4)+"\t\t"+rs.getString(5)+"\t\t"+rs.getString(6)+"\t\t\t"+rs.getString(7));
			}
			
			stmt.close();
			
			System.out.println("\n---------------------------------");
				
		}
		
		catch (SQLException e)
		{
			System.out.println(e.toString());
		}
	}  // end of in_house_customers()
		
	private void out_house_customers()
	{
		try
		{
			stmt = conn.createStatement();
			String query = "SELECT p.SSN, p.First_Name, p.Last_Name, p.Phone_number, p.Address FROM F16_16_Personal p, F16_16_Customer c  WHERE c.CSSN NOT IN (SELECT sd.CSSN FROM F16_16_Servicing_Details sd)";
			ResultSet rs = stmt.executeQuery(query);
			System.out.println("\nDisplaying the out house Customers Report:");
			System.out.println("\n---------------------------------");
			System.out.println("\n\n   SSN\t\t\tFirst name\tLast name\t   Phone number\t\tAddress ");
			
			while (rs.next())
			{
				// Get the data from the row using the column index
				System.out.println("\n"+rs.getString(1)+"\t\t"+rs.getString(2)+"\t\t"+rs.getString(3)+"\t\t"+rs.getString(4)+"\t\t"+rs.getString(5));
			}
			
			stmt.close();
			
			System.out.println("\n---------------------------------");
				
		}
		
		catch (SQLException e)
		{
			System.out.println(e.toString());
		}
	}  // end of out_house_customers()
	
	private void potential_customers()
	{
		try
		{
			stmt = conn.createStatement();
			String query = "SELECT p.SSN, p.First_Name, p.Last_Name, p.Phone_Number, p.Address FROM F16_16_Personal p WHERE p.SSN  IN (SELECT v.CSSN FROM F16_16_Visits_For_Quotation v Where v.CSSN NOT IN(SELECT b.CSSN FROM F16_16_Buy b))";
			ResultSet rs = stmt.executeQuery(query);
			System.out.println("\nDisplaying the potential Customers Report:");
			System.out.println("\n---------------------------------");
			System.out.println("\n\n  SSN\t\t\tFirst name\tLast name\tPhone number\t\t  Address ");
			
			while (rs.next())
			{
				// Get the data from the row using the column index
				System.out.println("\n"+rs.getString(1)+"\t\t"+rs.getString(2)+"\t\t"+rs.getString(3)+"\t\t"+rs.getString(4)+"\t\t"+rs.getString(5));
			}
			
			stmt.close();
			
			System.out.println("\n---------------------------------");
				
		}
		
		catch (SQLException e)
		{
			System.out.println(e.toString());
		}
	}  // end of potential_customers()
	
	private void top_three_cars()
	{
		try
		{
			stmt = conn.createStatement();
			String query = "SELECT * FROM( SELECT F16_16_Buy.Car_ID, F16_16_Car.Name, COUNT(F16_16_Buy.Car_ID) AS Car_Count FROM F16_16_Buy, F16_16_Car WHERE F16_16_Buy.Car_ID = F16_16_Car.Car_ID GROUP BY F16_16_Buy.Car_ID, F16_16_Car.Name ORDER BY Car_Count DESC ) WHERE rownum <= 3";
			ResultSet rs = stmt.executeQuery(query);
			System.out.println("\nDisplaying the top three cars Report:");
			System.out.println("\n---------------------------------");
			System.out.println("\n\nCar ID\t\tCar name\t    Car Count  ");
			
			while (rs.next())
			{
				// Get the data from the row using the column index
				System.out.println("\n"+rs.getString(1)+"\t\t"+rs.getString(2)+"\t\t"+rs.getString(3));
			}
			
			stmt.close();
			
			System.out.println("\n---------------------------------");
				
		}
		
		catch (SQLException e)
		{
			System.out.println(e.toString());
		}
	}  // end of top three cars()
	
	private void bottom_two_cars()
	{
		try
		{
			stmt = conn.createStatement();
			String query = "SELECT * FROM( SELECT F16_16_Buy.Car_ID, F16_16_Car.Name, COUNT(F16_16_Buy.Car_ID) AS Car_Count FROM F16_16_Buy, F16_16_Car WHERE F16_16_Buy.Car_ID = F16_16_Car.Car_ID GROUP BY F16_16_Buy.Car_ID, F16_16_Car.Name ORDER BY Car_Count  ) WHERE rownum <= 2";
			ResultSet rs = stmt.executeQuery(query);
			System.out.println("\nDisplaying the Bottom Two cars Report:");
			System.out.println("\n---------------------------------");
			System.out.println("\n\nCar ID\t\tCar name\t     Car Count  ");
			
			while (rs.next())
			{
				// Get the data from the row using the column index
				System.out.println("\n"+rs.getString(1)+"\t\t"+rs.getString(2)+"\t\t"+rs.getString(3));
			}
			
			stmt.close();
			
			System.out.println("\n---------------------------------");
				
		}
		
		catch (SQLException e)
		{
			System.out.println(e.toString());
		}
	}  // end of bottom two cars()
	
	private void showroom_track_record()
	{
		try
		{
			stmt = conn.createStatement();
			String query = "select count(F16_16_Buy.Car_ID) AS NumberOfCars, F16_16_Buy.Showroom_ID FROM   F16_16_Buy Group by F16_16_Buy.Showroom_ID  Having  Count(*)>=1 order by F16_16_Buy.Showroom_ID";
			ResultSet rs = stmt.executeQuery(query);
			System.out.println("\nDisplaying the showroom track record Report:");
			System.out.println("\n---------------------------------");
			System.out.println("\n\nNumber of cars   Showroom ID   ");
			
			while (rs.next())
			{
				// Get the data from the row using the column index
				System.out.println("\n"+rs.getString(1)+"\t\t"+rs.getString(2));
			}
			
			stmt.close();
			
			System.out.println("\n---------------------------------");
				
		}
		
		catch (SQLException e)
		{
			System.out.println(e.toString());
		}
	}  // end of showroom_track_record()
	
	private void detailed_showroom_track_record()
	{
		try
		{
			stmt = conn.createStatement();
			String query = "SELECT F16_16_Buy.Showroom_ID, F16_16_Buy.Car_ID, F16_16_Car.Name, F16_16_Car.Type,F16_16_Car.Power, (F16_16_Car.Basic_Price + F16_16_Car_Feature.F_Price) AS Selling_Price, F16_16_Car_Feature.F_ID,F16_16_Car_Feature.F_Price FROM   F16_16_Buy INNER JOIN F16_16_Car ON F16_16_Buy.Car_ID = F16_16_Car.Car_ID INNER JOIN F16_16_Car_Feature ON F16_16_Car.Car_ID = F16_16_Car_Feature.C_ID AND F16_16_Buy.F_ID = F16_16_Car_Feature.F_ID ORDER BY F16_16_Buy.Showroom_ID";
			ResultSet rs = stmt.executeQuery(query);
			System.out.println("\nDisplaying the Detailed Showroom track record Report:");
			System.out.println("\n---------------------------------");
			System.out.println("\n\nShowroom ID  Car ID  Car name               Car Type              Power                 Selling Price         Feature_ID            Feature_Price ");
			
			while (rs.next())
			{
				// Get the data from the row using the column index
				System.out.println("\n"+rs.getString(1)+"\t\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t\t"+rs.getString(4)+"\t\t"+rs.getString(5)+"\t\t\t"+rs.getString(6)+"\t\t\t"+rs.getString(7)+"\t\t\t"+rs.getString(8));
			}
			
			stmt.close();
			
			System.out.println("\n---------------------------------");
				
		}
		
		catch (SQLException e)
		{
			System.out.println(e.toString());
		}
	}  // end of detailed_showroom_track_record()
	
	private void regional_showroom_report()
	{
		try
		{
			stmt = conn.createStatement();
			String query = "SELECT a.R_ID, a.Region, b.Showroom_ID, COUNT(b.Car_ID) AS NumberOfCars FROM   F16_16_Automobile_Showroom a, F16_16_Buy b WHERE  a.Showroom_ID = b.Showroom_ID GROUP BY a.R_ID, a.Region, b.Showroom_ID ORDER BY b.Showroom_ID";
			ResultSet rs = stmt.executeQuery(query);
			System.out.println("\nDisplaying the Regional Showroom  Report:");
			System.out.println("\n---------------------------------");
			System.out.println("\n\nRegion ID      Region       Showroom_ID     Number of Cars ");
			
			while (rs.next())
			{
				// Get the data from the row using the column index
				System.out.println("\n"+rs.getString(1)+"\t\t"+rs.getString(2)+"\t\t"+rs.getString(3)+"\t\t"+rs.getString(4));
			}
			
			stmt.close();
			
			System.out.println("\n---------------------------------");
				
		}
		
		catch (SQLException e)
		{
			System.out.println(e.toString());
		}
	}  // end of regional_showroom_report()
	
	private void regional_service_report()
	{
		try
		{
			stmt = conn.createStatement();
			String query = "SELECT a.R_ID, a.Region, s.Showroom_ID, COUNT(s.Car_ID) AS Number_Of_Servicings FROM   F16_16_Automobile_Showroom a, F16_16_Servicing_Details s WHERE  a.Showroom_ID = s.Showroom_ID GROUP BY a.R_ID, a.Region, s.Showroom_ID ORDER BY s.Showroom_ID";
			ResultSet rs = stmt.executeQuery(query);
			System.out.println("\nDisplaying the Regional Showroom  Report:");
			System.out.println("\n---------------------------------");
			System.out.println("\n\nRegion ID         Region     Showroom_ID  Number of Services ");
			
			while (rs.next())
			{
				// Get the data from the row using the column index
				System.out.println("\n"+rs.getString(1)+"\t\t"+rs.getString(2)+"\t\t"+rs.getString(3)+"\t\t"+rs.getString(4));
			}
			
			stmt.close();
			
			System.out.println("\n---------------------------------");
				
		}
		
		catch (SQLException e)
		{
			System.out.println(e.toString());
		}
	}  // end of regional_service_report()
	
	private void customer_rating()
	{
		try
		{
			stmt = conn.createStatement();
			String query = "Select r.Car_ID,r.Rate_Scale,c.Name,r.CSSN,p.First_name,p.Last_name,p.Phone_Number From F16_16_Rates r, F16_16_Personal p, F16_16_Car c where r.CSSN=p.SSN and r.Car_ID=c.Car_ID GROUP BY r.Car_ID,r.Rate_Scale,r.CSSN,c.Name,p.First_name,p.Last_name,p.Phone_Number Order BY r.Car_ID";
			ResultSet rs = stmt.executeQuery(query);
			System.out.println("\nDisplaying the Customer Rating Report:");
			System.out.println("\n---------------------------------");
			System.out.println("\n\nCar ID           Rate Scale     Car Name                  CSSN                  First name                 Last name          Phone number ");
			
			while (rs.next())
			{
				// Get the data from the row using the column index
				System.out.println("\n"+rs.getString(1)+"\t\t"+rs.getString(2)+"\t\t"+rs.getString(3)+"\t\t"+rs.getString(4)+"\t\t"+rs.getString(5)+"\t\t\t"+rs.getString(6)+"\t\t\t"+rs.getString(7));
			}
			
			stmt.close();
			
			System.out.println("\n---------------------------------");
				
		}
		
		catch (SQLException e)
		{
			System.out.println(e.toString());
		}
	}  // end of customer_rating()
	
	private void sales_executive()
	{
		try
		{
			stmt = conn.createStatement();
			String query = "SELECT b.ESSN, p.First_Name, p.Last_Name, w.Showroom_ID, COUNT(b.ESSN)AS cars_Sold FROM F16_16_Employee e, F16_16_Personal p, F16_16_Buy b, F16_16_Works_For w WHERE p.SSN = b.ESSN AND e.ESSN = w.ESSN AND e.ESSN = b.ESSN GROUP BY b.ESSN, p.First_Name, p.Last_Name, w.Showroom_ID";
			ResultSet rs = stmt.executeQuery(query);
			System.out.println("\nDisplaying the sales_executive  Report:");
			System.out.println("\n---------------------------------");
			System.out.println("\n\nESSN\t\t\t     First Name\t\t     LAST_NAME\t\t     SHOWROOM_ID\t\t      CARS_SOLD");
			
			while (rs.next())
			{
				// Get the data from the row using the column index
				System.out.println("\n"+rs.getString(1)+"\t\t"+rs.getString(2)+"\t\t\t"+rs.getString(3)+"\t\t\t"+rs.getString(4)+"\t\t\t\t"+rs.getString(5));
			}
			
			stmt.close();
			
			System.out.println("\n---------------------------------");
				
		}
		
		catch (SQLException e)
		{
			System.out.println(e.toString());
		}
	}  // end of sales_executive()
	
	private void number_one_employee()
	{
		try
		{
			stmt = conn.createStatement();
			String query = "SELECT * FROM( SELECT b.ESSN, p.First_Name, p.Last_Name, w.Showroom_ID, (COUNT(b.ESSN))AS No_1_Employee FROM F16_16_Employee e, F16_16_Personal p, F16_16_Buy b, F16_16_Works_For w WHERE p.SSN = b.ESSN AND e.ESSN = w.ESSN AND e.ESSN = b.ESSN GROUP BY b.ESSN, p.First_Name, p.Last_Name, w.Showroom_ID ORDER BY No_1_Employee DESC) WHERE rownum=1";
			ResultSet rs = stmt.executeQuery(query);
			System.out.println("\nDisplaying the Number One Employee  Report:");
			System.out.println("\n---------------------------------");
			System.out.println("\n\nESSN\t\t\t First Name\t  LAST_NAME\t  SHOWROOM_ID  Number 1 Employee");
			
			while (rs.next())
			{
				// Get the data from the row using the column index
				System.out.println("\n"+rs.getString(1)+"\t\t"+rs.getString(2)+"\t\t"+rs.getString(3)+"\t\t"+rs.getString(4)+"\t\t"+rs.getString(5));
			}
			
			stmt.close();
			
			System.out.println("\n---------------------------------");
				
		}
		
		catch (SQLException e)
		{
			System.out.println(e.toString());
		}
	}  // end of number_one_employee()
	
	private void priority_customer()
	{
		try
		{
			stmt = conn.createStatement();
			String query = "SELECT p.SSN, p.First_Name, p.Last_Name, p.Phone_number, p.Address, b.CSSN, COUNT(b.CSSN) No_Of_Cars FROM F16_16_Personal p, F16_16_Buy b WHERE p.SSN = b.CSSN GROUP BY b.CSSN, p.SSN, p.First_Name, p.Last_Name, p.Phone_number, p.Address HAVING COUNT(b.CSSN) >= 3 ORDER BY b.CSSN";
			ResultSet rs = stmt.executeQuery(query);
			System.out.println("\nDisplaying the priority Customers Report:");
			System.out.println("\n---------------------------------");
			System.out.println("\n\nSSN \t\t       First name \t\t  Last name \t\t   Phone number \t\t   Address \t\t\t  CSSN \t\t\t\tNumber of Cars ");
			
			while (rs.next())
			{
				// Get the data from the row using the column index
				System.out.println("\n"+rs.getString(1)+"\t\t"+rs.getString(2)+"\t\t\t"+rs.getString(3)+"\t\t\t"+rs.getString(4)+"\t\t\t"+rs.getString(5)+"\t\t\t"+rs.getString(6)+"\t\t\t"+rs.getString(7));
			}
			
			stmt.close();
			
			System.out.println("\n---------------------------------");
				
		}
		
		catch (SQLException e)
		{
			System.out.println(e.toString());
		}
	}  // end of priority_customer()
	
	private void revenue_per_car()
	{
		try
		{
			stmt = conn.createStatement();
			String query = "SELECT c.Car_ID, c.Name, SUM((c.Basic_Price + cf.F_Price)) AS Selling_Price FROM F16_16_Buy b, F16_16_Car c, F16_16_Car_Feature cf WHERE b.Car_ID = c.Car_ID AND c.Car_ID = cf.C_ID AND b.F_ID = cf.F_ID GROUP BY c.Car_ID, c.Name";
			ResultSet rs = stmt.executeQuery(query);
			System.out.println("\nDisplaying the revenue per car Report:");
			System.out.println("\n---------------------------------");
			System.out.println("\n\nCar ID  \tCar name \t    Selling Price  ");
			
			while (rs.next())
			{
				// Get the data from the row using the column index
				System.out.println("\n"+rs.getString(1)+"\t\t"+rs.getString(2)+"\t\t"+rs.getString(3));
			}
			
			stmt.close();
			
			System.out.println("\n---------------------------------");
				
		}
		
		catch (SQLException e)
		{
			System.out.println(e.toString());
		}
	}  // end of revenue_per_car()
	
	private void overall_showroom_profit()
	{
		try
		{
			stmt = conn.createStatement();
			String query = "SELECT c.Car_ID, c.Name, SUM((c.Basic_Price + cf.F_Price)) AS Selling_Price, (SUM(c.Basic_Price + cf.F_Price)-(SUM(c.Purchase_Price)+ 12000 + ((SELECT SUM(e.Salary) FROM F16_16_Employee e WHERE e.Type = '01')/(SELECT COUNT(*) FROM F16_16_Buy)))) AS Overall_Profit, SUM(e.Salary) AS Emp_Sal  FROM F16_16_Buy b, F16_16_Car c, F16_16_Car_Feature cf, F16_16_Employee e WHERE b.Car_ID = c.Car_ID AND c.Car_ID = cf.C_ID AND b.F_ID = cf.F_ID AND e.ESSN = b.ESSN GROUP BY c.Car_ID, c.Name";
			ResultSet rs = stmt.executeQuery(query);
			System.out.println("\nDisplaying the overall showroom profit Report:");
			System.out.println("\n---------------------------------");
			System.out.println("\n\nCar ID \t\t Car name\t\tSelling Price \t\t\t Overall_Profit\t\t\t\tEmp Sal  ");
			
			while (rs.next())
			{
				// Get the data from the row using the column index
				System.out.println("\n"+rs.getString(1)+"\t\t"+rs.getString(2)+"\t\t"+rs.getString(3)+"\t\t"+rs.getString(4)+"\t\t"+rs.getString(5));
			}
			
			stmt.close();
			
			System.out.println("\n---------------------------------");
				
		}
		
		catch (SQLException e)
		{
			System.out.println(e.toString());
		}
	}  //overall_showroom_profit();
	
	private void overall_service_profit()
	{
		try
		{
			stmt = conn.createStatement();
			String query = "SELECT sd.Car_ID, c.Name,(SUM(sd.Cost_of_Service) - (2000 + ((SELECT SUM(e.Salary) FROM F16_16_Employee e WHERE e.Type = '02')/(SELECT COUNT(*) FROM F16_16_Servicing_Details)))) AS Overall_Servicing_Profit FROM F16_16_Servicing_Details sd, F16_16_Car c WHERE sd.Car_ID = c.Car_ID GROUP BY sd.Car_ID, c.Name";
			ResultSet rs = stmt.executeQuery(query);
			System.out.println("\nDisplaying the overall service profit  Report:");
			System.out.println("\n---------------------------------");
			System.out.println("\n\nCar ID \t\t Car name \t\t\t  Overall Servicing Profit");
			
			while (rs.next())
			{
				// Get the data from the row using the column index
				System.out.println("\n"+rs.getString(1)+"\t\t"+rs.getString(2)+"\t\t"+rs.getString(3));
			}
			
			stmt.close();
			
			System.out.println("\n---------------------------------");
				
		}
		
		catch (SQLException e)
		{
			System.out.println(e.toString());
		}
	}  //overall_showroom_profit();
	
	private void overall_profit()
	{
		try
		{
			stmt = conn.createStatement();
			String query = "CREATE VIEW F16_16_OverView_Service AS SELECT sd.Car_ID, c.Name,(SUM(sd.Cost_of_Service) - (2000 + ((SELECT SUM(e.Salary) FROM F16_16_Employee e WHERE e.Type = '02')/(SELECT COUNT(*) FROM F16_16_Servicing_Details)))) AS Overall_Servicing_Profit FROM F16_16_Servicing_Details sd, F16_16_Car c WHERE sd.Car_ID = c.Car_ID GROUP BY sd.Car_ID, c.Name";
			stmt.executeQuery(query);
			String query1="CREATE VIEW F16_16_OverView_Showroom AS SELECT c.Car_ID, c.Name, SUM((c.Basic_Price + cf.F_Price)) AS Selling_Price, (SUM(c.Basic_Price + cf.F_Price)-(SUM(c.Purchase_Price)+ 12000 + ((SELECT SUM(e.Salary) FROM F16_16_Employee e WHERE e.Type = '01')/(SELECT COUNT(*) FROM F16_16_Buy)))) AS Overall_Showroom_Profit, SUM(e.Salary) as Emp_Sal FROM F16_16_Buy b, F16_16_Car c, F16_16_Car_Feature cf, F16_16_Employee e WHERE b.Car_ID = c.Car_ID AND c.Car_ID = cf.C_ID AND b.F_ID = cf.F_ID AND e.ESSN = b.ESSN GROUP BY c.Car_ID, c.Name";
			stmt.executeQuery(query1);
			String query2="CREATE VIEW F16_16_Total AS SELECT SUM(o.Overall_Servicing_Profit) AS Overall_Servicing_Profit, SUM(os.Overall_Showroom_Profit) AS Overall_Showroom_Profit FROM F16_16_OverView_Service o,F16_16_OverView_Showroom os";
			stmt.executeQuery(query2);
			String query3="SELECT * FROM F16_16_Total";
			ResultSet rs = stmt.executeQuery(query3);
			System.out.println("\nDisplaying the overall  profit  Report:");
			System.out.println("\n---------------------------------");
			System.out.println("\n\n\tOverall Servicing Profit \t\t\t\t   Overall Showroom Profit");
			
			while (rs.next())
			{
				// Get the data from the row using the column index
				System.out.println("\n"+rs.getString(1)+"\t\t"+rs.getString(2));
			}
			
			stmt.close();
			
			System.out.println("\n---------------------------------");
				
		}
		
		catch (SQLException e)
		{
			System.out.println(e.toString());
		}
	}  //overall_profit();
	
	private void insert_into_personal(String table, int SSN, String add, int ph_no, String fname, String lname)
	    {
	    	try 
			{
				stmt = conn.createStatement();
				
				stmt.executeQuery("INSERT INTO "+table+" VALUES("+SSN+",'"+add+"','18-NOV-1993',"+ph_no+",'"+fname+"','"+lname+"')");
				stmt.close();
			} 
			
			catch (SQLException e) 
			{
				System.out.println(e.toString());
			}
	    } //end of insert records function
	    
	private void display_personal_table_on(int SSN)
	    {
	    	try
	    	{
	    		stmt = conn.createStatement();
	    		ResultSet rs = stmt.executeQuery("SELECT * FROM F16_16_Personal WHERE SSN ="+SSN);
	    		System.out.println("\n\nSSN Address Phone_Number DOB First_Name Last_Namre");
	    		
	    		while(rs.next())
	    		{
	    			System.out.println("\n "+rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4)+"\t"+rs.getString(5)+rs.getString(6));
	    		}
	    		stmt.close();
	    	}
	    	
	    	catch(SQLException e)
	    	{
	    		System.out.println(e.toString());
	    	}
	    } //end of personal table on SSN
	
	private void delete_from_car_color_table_on(String table, String column1, String column2, int car, String color)
	    {
	    	try 
			{
				stmt = conn.createStatement();
				stmt.executeQuery("DELETE FROM "+table+" WHERE "+column1+" = "+car+ "AND " +column2+ " = '"+color+"'");
				stmt.close();
			} 
			
			catch (SQLException e) 
			{
				System.out.println(e.toString());
			}
	    }  //end of delete from table
	    
	private void display_car_color_table_on(int car, String color)
	    {
	    	try
	    	{
	    		stmt = conn.createStatement();
	    		ResultSet rs = stmt.executeQuery("SELECT * FROM F16_16_Car_Color WHERE C_ID ="+car+ " AND Color ="+color); 
	    		System.out.println("\n\nC_ID Color");
	    		
	    		while(rs.next())
	    		{
	    			System.out.println("\n "+rs.getString(1)+"\t"+rs.getString(2));
	    		}
	    		stmt.close();
	    	} // end of delete from table on ID
	    	
	    	catch(SQLException e)
	    	{
	    		System.out.println(e.toString());
	    	}
	    }
	
	private void cust_rating(int rate) 
	    {
	    	try 
			{
				stmt = conn.createStatement();
				
				ResultSet rs = stmt.executeQuery("SELECT Car_ID, Rate_Scale FROM F16_16_Rates WHERE Rate_Scale > "+rate);
				System.out.println("\n\nCar_ID     Rate_Scale");
				
				while (rs.next()) 
	            {
	                // Get the data from the row using the column index
					 System.out.println("\n  "+rs.getString(1)+"\t\t"+rs.getString(2));      
	            }
				stmt.close();
				
			} 
			
			catch (SQLException e) 
			{
				System.out.println(e.toString());
			}
			
		}
	
	private void cost_of_service(int cost) 
	    {
	    	try 
			{
				stmt = conn.createStatement();
				
				ResultSet rs = stmt.executeQuery("SELECT CSSN,Car_ID,Cost_of_Service FROM F16_16_Servicing_Details WHERE Cost_of_Service >"+cost);
				System.out.println("\n\n     CSSN\t\tCar_ID\tCost_of_Service");
				
				while (rs.next()) 
	            {
					
	                // Get the data from the row using the column index
					 System.out.println("\n  "+rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3));      
	            }
				stmt.close();
				
			} 
			
			catch (SQLException e) 
			{
				System.out.println(e.toString());
			}
			
		}
	
}//End of class

