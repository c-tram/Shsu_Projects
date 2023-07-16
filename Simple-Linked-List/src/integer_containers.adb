--refer to page 67-70 in DataStructures notes for doubly linked list methods

with Ada.Text_IO; use Ada.Text_IO;
package body Integer_Containers is

package Integer_IO is new Ada.Text_IO.Integer_IO(Integer);
use Integer_IO;

procedure PrintList(L: in out Integer_Node_Point; R: in out Integer_Node_Point; listSize: in Integer) is


T: Integer_Node_Point := new Integer_Container;

begin
  
  put("List is as follows: ");
  put(L.value);
    T := L.next;
  for variable in 2..listSize  loop
     
    put(Integer'Image(T.value));
    put(" ");
    T := T.next;

  end loop;
  new_line(1);
  --put (L.value); put (R.value);
end PrintList;

procedure InsertFront(L: in out Integer_Node_Point; R: in out Integer_Node_Point; amt: in Integer; currentListSize: in out Integer; choice: in Boolean) is
  
  I: Integer_Node_Point;
  
  begin
  
    I := new Integer_Container'(amt,null,null);
    
    if R.next = null then
      R.next := L;
      R.prev := L;
      L.prev := R;
      L.next := R;
      L.value := amt;
      currentListSize := currentListSize + 1;
    elsif currentListSize = 1 and choice = true then
      R.value := L.value;
      L.value := amt;
      currentListSize := currentListSize + 1;
    elsif currentListSize = 1 and choice = false then
      R.value := amt;
      currentListSize := currentListSize + 1;
      
    elsif choice = true and currentListSize >= 2 then --front of L
      I.value := L.value;
      L.value := amt;
      I.next := L.next;
      L.next := I;
      I.prev := L;
      --I.next := L.next;
      currentListSize := currentListSize + 1;
    elsif choice = false and currentListSize >= 2 then -- rear of L
      I.prev := L;
      I.next := L.next;
      I.prev := L;
      L.next := I;
      currentListSize := currentListSize + 1;
    else
      null;
        
    end if;
end InsertFront;
	
procedure InsertRear(L: in out Integer_Node_Point; R: in out Integer_Node_Point; amt: in Integer; currentListSize: in out Integer; choice: in Boolean) is

  I: Integer_Node_Point;
  savedValue: Integer ;
 
  begin
  
    I := new Integer_Container'(amt,null,null);
    
    if R.next = null then
      R.next := L;
      R.prev := L;
      L.prev := R;
      L.next := R;
      L.value := amt;
      currentListSize := currentListSize + 1;
    elsif currentListSize = 1 and choice = true then
      R.value := L.value;
      L.value := amt;
      currentListSize := currentListSize + 1;
    elsif currentListSize = 1 and choice = false then
      R.value := amt;
      currentListSize := currentListSize + 1;
      
    elsif choice = true and currentListSize >= 2 then --rear of R
      I.value := L.value;
      L.value := amt;
      I.next := L.next;
      L.next := I;
      I.prev := L;
      
      R.next := L;
      L := L.next;
      R := R.next;

      --put(R.value);
      currentListSize := currentListSize + 1;
    elsif choice = false and currentListSize >= 2 then -- front of R
      
      I.value := L.value;
      L.value := amt;
      I.next := L.next;
      L.next := I;
      I.prev := L;
      
      R.next := L;
      L := L.next;
      R := R.next;
      
      
      I := I.prev;
      I := I.prev;
      savedValue := I.value;

      
      
      --flip values
      I.value := R.value;
      R.value := savedValue;
      
      --put(L.value);
      
      
      currentListSize := currentListSize + 1;
    else
      null;
        
    end if;
    
end InsertRear;
	

function findItem( L: in out Integer_Node_Point; aValue: in Integer) return Integer_Node_point is
begin
return null;
end findItem;

procedure delete(L: in out Integer_Node_Point; R: in out Integer_Node_Point; listSize : in out Integer; dp : in Integer) is

S : Integer_Node_Point;
T : Integer_Node_Point;
index : Integer;
found : Boolean;

begin

  found := false;
  index := 1;

  T := L.next;

  while found /= true and index <= listSize loop
  
    if L.value = dp then
      
      L := L.next;
      R.next := L;
      
      found := true;
      index := listSize + 1;
      listSize := listSize - 1;
      --put (L.value); put (R.value); 
    end if;  
        
    
    if T.value = dp and index <= listSize then
      
      S := T.prev;
      S.next := T.next;
      R := R.prev;
   
      found := true;
      index := listSize + 1;
      listSize := listSize - 1;
      
    end if;
    T := T.next;
    index := index + 1;
  end loop;


end delete;

end Integer_Containers;
  
  


