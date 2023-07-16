with Ada.Text_IO; use Ada.Text_IO;
with Ada.Unchecked_Conversion;
with Direct_IO; -- Why Direct_IO vs Stream_IO
with urandint;

package hashfun is
   
   --used for input and hash algorithm
   subtype input is String(1..17); --CR/LF fix?
   subtype table_Value is String(1..16);
   subtype slice is String(1..8);
   
   --my data types for hash table/functions and enums for procedure calls
   type my_int is mod 2**64;
   type probeType is (LINEAR, RANDOM);       
   type methodType is (SCRIPTKIDDY, BURRIS);     
   
   type table_element is record
     value    : table_Value;
     hash_Address : Integer;         
     probes  : Integer := 1;
   end record;

   --takes input from desired File.
   package inputToHash is new Direct_IO(input); use inputToHash;   
  
   --write to desired File if chosen 
   package file_IO is new Direct_IO(table_element);   use file_IO;  
   
   --used for probe statistics
   package float_IO is new Ada.Text_IO.Float_IO(Float);   use float_IO; 

   --memHash table
   type hash_Table is array (Integer range <>) of table_element; 
   
   --conversions used for hash function
   function strToMyInt is new Ada.Unchecked_Conversion(String, my_int);
   function myIntToInt is new Ada.Unchecked_Conversion(my_int, Integer);
   function intToMyInt is new Ada.Unchecked_Conversion(Integer, my_int);
   function myIntToStr is new Ada.Unchecked_Conversion(my_int, slice);
   
   --my key and your key
   function kiddyKey  (value : table_Value) return Integer;
   function burrisKey (value : table_Value) return Integer;  
   
   -- generates hash table in memory. I/O redirect to file "Results"
   procedure generateMemHash (inputFile : String; table_Size : Integer; capacityFull : Float; probeMethod : probeType; hashMethod : methodType);
   
   -- generates hash table in output file. No part of hash table stored in memory
   procedure generateFileHash (inputFile : String; outputFile : String; table_Size : Integer; capacityFull : Float; probeMethod : probeType; hashMethod : methodType);
   
   -- do  probe math for memHash
   procedure probeFunMem(inputFile : inputToHash.File_Type; elementTable : hash_Table; table_Size : Integer; probeMethod : probeType; hashMethod : methodType; lowerBound : Integer; upperBound : Integer);
   
   --do probe math for fileHash
   procedure probeFunFile(inputFile : inputToHash.File_Type; storageFile : file_IO.File_Type; table_Size : Integer; lowerBound : Integer; upperBound : Integer; probeMethod : probeType; hashMethod : methodType);
   

end hashfun;
    
