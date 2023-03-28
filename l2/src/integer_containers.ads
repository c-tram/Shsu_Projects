package Integer_Containers is 
	type Integer_Container is private;
	type Integer_Node_Point is access Integer_Container;
	
	procedure PrintList(L: in out Integer_Node_Point; R: in out Integer_Node_Point; listSize: in Integer);
	
	procedure InsertFront(L: in out Integer_Node_Point; R: in out Integer_Node_Point; amt: in Integer; currentListSize: in out Integer; choice: in Boolean);
	
	procedure InsertRear( L: in out Integer_Node_Point; R: in out Integer_Node_Point; amt: in Integer; currentListSize: in out Integer; choice: in Boolean);
	
	function findItem( L: in out Integer_Node_Point; aValue: Integer) return Integer_Node_point;
	
	procedure delete(L: in out Integer_Node_Point;R: in out Integer_Node_Point; listSize : in out Integer; dp : in Integer);
	
-- Additional functions and procedures as needed.

private
	type Integer_Container is record 
		value: Integer;
		next, prev: Integer_Node_Point;
	end record;
   -- rest of specification.
   
   
end Integer_Containers;  -- followed by the body in another file

