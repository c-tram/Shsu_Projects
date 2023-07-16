with Ada.Text_IO; use Ada.Text_IO;
with Integer_Containers; use Integer_Containers;


procedure l2main is

Head : Integer_Node_Point := new Integer_Container;
R : Integer_Node_Point := new Integer_Container;

listSize : Integer := 0;
--operationSuccess : Boolean;  

begin


--InsertFront(true) = on left of L. InsertRear(false) = on left of R.  
Integer_Containers.InsertFront(Head,R,33,listSize,false);
Integer_Containers.InsertFront(Head,R,57,listSize,true);
Integer_Containers.InsertRear(Head,R,85,listSize,false);
Integer_Containers.InsertFront(Head,R,95,listSize,true);
PrintList(Head,R,listSize);
Integer_Containers.delete(Head,R,listSize,57);
Integer_Containers.delete(Head,R,listSize,33);
Integer_Containers.delete(Head,R,listSize,33);
Integer_Containers.InsertFront(Head,R,22,listSize,true);
Integer_Containers.delete(Head,R,listSize,95);
PrintList(Head,R,listSize);
null;
end l2main;

--call new Integer_container; main program
