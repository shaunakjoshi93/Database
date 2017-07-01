/********************************************F16_16_Personal***********************************************************/ 
Create Table F16_16_Personal
(
	SSN Int NOT NULL,
	Address Varchar(50),
	Date_of_Birth Date,
	Phone_number Int NOT NULL,
	First_Name Varchar(20),
	Last_Name Varchar(20),
	Primary Key (SSN)
);

/********************************************F16_16_Employee**********************************************************/

Create Table F16_16_Employee
(
  ESSN Int NOT NULL,
  Type Varchar(20),
  Salary float NOT NULL,
  Primary Key(ESSN),
  Foreign Key(ESSN) references F16_16_Personal(SSN) ON DELETE CASCADE  
 );
 
 /********************************************F16_16_Customer*********************************************************/
 
 Create Table F16_16_Customer
( 
   CSSN Int NOT NULL,
   Primary Key(CSSN),
   Foreign Key(CSSN) references F16_16_Personal(SSN) ON DELETE CASCADE  
 );
 
 /********************************************F16_16_Car**************************************************************/
 
 Create Table F16_16_Car
(
  Car_ID         Int NOT NULL,
  Name           Varchar(20) NOT NULL,
  Power          Varchar(20) NOT NULL,
  Weight         Varchar(20) NOT NULL,
  Type           Varchar(20) NOT NULL,
  Basic_Price    Float NOT NULL,
  Purchase_Price Float NOT NULL,
  Primary Key(Car_ID)
 ); 
 
 /********************************************F16_16_Car_Feature*******************************************************/
 
 Create Table F16_16_Car_Feature
(
   C_ID    Int NOT NULL,
   Feature Varchar(20) NOT NULL,
   F_Price INT NOT NULL,
   F_ID INT NOT NULL,
   Primary Key(C_ID,Feature),
   Foreign Key (C_ID) references F16_16_Car(Car_ID) ON DELETE CASCADE  
 );
 
 /********************************************F16_16_Car_Color*******************************************************/
 
 Create Table F16_16_Car_Color
(
   C_ID Int NOT NULL,
   Color Varchar(10) NOT NULL,
   Primary Key(C_ID,Color),
   Foreign Key (C_ID) references F16_16_Car(Car_ID) ON DELETE CASCADE  
 );
 
 /********************************************F16_16_Automobile_Showroom***********************************************/
 
 Create Table F16_16_Automobile_Showroom
(
  Showroom_ID                      Int NOT NULL,
  Address                          Varchar(50),
  Region                           Varchar(10) NOT NULL,
  Showroom_Maintainence_Cost       Float NOT NULL,
  SC_Maintainence_Cost Float NOT NULL,
  R_ID Varchar(20) NOT NULL,
  Primary Key (Showroom_ID)
 );
 
 /********************************************F16_16_Works_For*******************************************************/
 
 Create Table F16_16_Works_For
(
  ESSN        int NOT NULL,
  Showroom_ID int NOT NULL,
  from_date   Date NOT NULL,
  To_date     Date NOT NULL,
  Primary Key (ESSN,Showroom_ID),
  Foreign Key (ESSN) references F16_16_Employee(ESSN) ON DELETE CASCADE  ,
  Foreign Key (Showroom_ID) references F16_16_Automobile_Showroom(Showroom_ID) ON DELETE CASCADE  
 );
 
 /********************************************F16_16_Visits_For_Quotation**********************************************/
 
 Create Table F16_16_Visits_For_Quotation
(
  CSSN          Int NOT NULL,
  Showroom_ID   Int NOT NULL,
  ESSN          Int NOT NULL,
  Date_of_Visit Date NOT NULL,
  Car_ID Int,
  Primary Key (CSSN,Showroom_ID,ESSN),
  Foreign Key (CSSN) references F16_16_Customer(CSSN) ON DELETE CASCADE  ,
  Foreign Key (Showroom_ID) references F16_16_Automobile_Showroom(Showroom_ID) ON DELETE CASCADE  ,
  Foreign Key (ESSN) references F16_16_Employee(ESSN) ON DELETE CASCADE  ,
  Foreign Key (Car_ID) references F16_16_Car(Car_ID) ON DELETE CASCADE  
 );
 
 /********************************************F16_16_Rates*************************************************************/
 
 Create Table F16_16_Rates
(
  CSSN int NOT NULL,
  Car_ID int NOT NULL,
  Showroom_ID int NOT NULL,
  Rate_Scale Int NOT NULL,
  Date_of_Rating Date NOT NULL,
  Primary Key(CSSN,Car_ID,Showroom_ID),
  Foreign Key (CSSN) references F16_16_Customer(CSSN) ON DELETE CASCADE  ,
  Foreign Key (Showroom_ID) references F16_16_Automobile_Showroom(Showroom_ID) ON DELETE CASCADE  ,
  Foreign Key (Car_ID) references F16_16_Car(Car_ID) ON DELETE CASCADE  
 );
 
 /********************************************F16_16_Buy*************************************************************/
 
 Create table F16_16_Buy
(
CSSN int NOT NULL,
ESSN int NOT NULL,
Car_ID int NOT NULL,
Showroom_ID int NOT NULL,
Date_of_Purchase Date NOT NULL,
F_ID Int NOT NULL,
Primary key (CSSN, ESSN, Car_ID, Showroom_ID),
Foreign key(CSSN) references F16_16_Customer(CSSN) ON DELETE CASCADE  ,
Foreign key(ESSN) references F16_16_Employee(ESSN) ON DELETE CASCADE  ,
Foreign key(Car_ID) references F16_16_Car(Car_ID) ON DELETE CASCADE  ,
Foreign key(Showroom_ID) references F16_16_Automobile_Showroom(Showroom_ID) ON DELETE CASCADE  
); 

/********************************************F16_16_Service*************************************************************/

Create Table F16_16_Service
(
CSSN int NOT NULL,
Car_ID int NOT NULL,
Showroom_ID int NOT NULL,
Primary key (CSSN, Car_ID, Showroom_ID),
Foreign key (CSSN)references F16_16_Customer(CSSN) ON DELETE CASCADE  ,
Foreign key (Car_ID) references F16_16_Car(Car_ID) ON DELETE CASCADE  ,
Foreign key (Showroom_ID) references F16_16_Automobile_Showroom(Showroom_ID) ON DELETE CASCADE  
);

/********************************************Servicing Details***************************************************/

Create Table F16_16_Servicing_Details
(
CSSN int NOT NULL,
Car_ID int NOT NULL,
Showroom_ID int NOT NULL,
Date_of_Service Date NOT NULL,
Cost_of_Service FLOAT NOT NULL,
Type Varchar(10) NOT NULL,
Primary key(CSSN, Car_ID, Showroom_ID, Date_of_Service),
Foreign key(CSSN) references F16_16_Customer(CSSN) ON DELETE CASCADE  ,
Foreign key(Car_ID) references F16_16_Car(Car_ID) ON DELETE CASCADE  ,
Foreign key(Showroom_ID) references F16_16_Automobile_Showroom(Showroom_ID) ON DELETE CASCADE  
);

