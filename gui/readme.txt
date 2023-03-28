SCHEMA

create table CarInfo (Model text, Make text, carID text, Color text, Year int, Class text, DailyRate real, PRIMARY KEY (carID));

create table Renter (Name text, DOB text, SSN int, PickupDate text, DropOffDate text, InsuranceID int, carID text, PRIMARY KEY (SSN)); 

create table Insurance (CompanyName text, InsuranceID int, CoverageAmount real, CoverageTimeInDays int, PRIMARY KEY (InsuranceID));

create table Login (username text, password text, PRIMARY KEY(username));

copy CarInfo FROM '/Users/alt/cinfo.csv' DELIMITERS ',' CSV;

copy Renter FROM '/Users/alt/cuinfo.csv' DELIMITERS ',' CSV;

copy Insurance FROM '/Users/alt/iinfo.csv' DELIMITERS ',' CSV;

alter table renter add constraint a FOREIGN KEY (carid) references carinfo(carID);

=============
create a new project in Eclipse called postGUI
right click the new project and click import - > general -> File System (import folder in zip folder called 'PostGUI')
===================

right click project again and click properties and go to the java build path. Once in this tab click Libraries at the top.
click classpath in the onscreen menu and "add external jar". Add the jar in the zip folder.

=============
TO START APPLICATION: right click project folder -> run As -> Java Application -> (if it asks start at loginFrame)

=============

NOTES:
When populating the database  (copy commands) you will need to change the location of the csv files to where they are located on your computer.

You will need to edit the location of your local postgres database in the data.java file under PostGUI package. 
This will be under the  "static Connection connect() method" 


=============


