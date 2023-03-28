with Ada.Numerics.Elementary_Functions; 
  use Ada.Numerics.Elementary_Functions;
with Ada.Integer_Text_IO; 
  use Ada.Integer_Text_IO;

package body hashfun is
  
  
  --midsquare hash variant. Makes a 64 bit integer 
  --then splits into 32 bits.
  --32bit / 63 gives maximum modulus (table_Size) range
  function kiddyKey  (value : table_Value) return Integer is
  
  tempInt : my_int; 
  hashString : String(1..8);
  hashAddress : Integer;
    
  begin
    
    tempInt := strToMyInt(value(1..8)) * strToMyInt(value(9..16));
    
    hashString := myIntToStr(tempInt); 
    tempInt := strToMyInt(hashString);
    
  
    tempInt := tempInt / 63;
    
    
    
    hashAddress := myIntToInt(tempInt mod 128); -- mod prime number
    
    --put(Integer'image(hashAddress)); --used for testing
    return hashAddress;
  end kiddyKey;
  
  --condensing 64 bits into 
  function burrisKey (value : table_Value) return Integer is
  
  temp : my_int;
  hashAddress : Integer;
    
  begin
    
    temp := ((strToMyInt(value(1..1)) + strToMyInt(value(3..3) )) / 256) + strToMyInt(value(6..6)) + 30;
    temp := temp mod 64;
    hashAddress := myIntToInt(temp);
    --put(Integer'image(hashAddress)); --used for testing
    return hashAddress;
  
  end burrisKey;
  
  
   procedure generateMemHash (inputFile : String; table_Size : Integer; capacityFull : Float; probeMethod : probeType; hashMethod : methodType) is
   
    
   currentInput : inputToHash.File_Type;
   tableLimit : Integer;
   
   --do math while making table
   iterate: Integer := 0;
   
   first30min : Integer := 1000;
   first30max : Integer := 0;
   first30avg : Float := 0.0;
   
   last30min : Integer := 1000;
   last30max : Integer := 0;
   last30avg : Float := 0.0;
   
   totalProbes : Integer := 0;
   theoreticalProbes : Float := 0.0;
   theoreticalAverage : Float := 0.0;
   myAverage : Float := 0.0;
   
   begin
   
     tableLimit := Integer( Float(table_Size) * capacityFull);
     put_line("================================");
     put_line("Memory Type :  ONBOARD");
     put_line("Probe Method:  " & probeMethod'Image);
     put_line("Hash Method :  " & hashMethod'Image);
     put_line("Key Number  : " & Integer'Image(tableLimit));
     put_line("Capacity    : " & Integer'Image(Integer((Float( capacityFull * 
     100.00)))) & "% FULL");     
     put_line("================================");
     
     Open(currentInput, in_file, inputFile);
     Reset(currentInput); -- must reset if doing multiple reads
     
     declare
       
       nullElement : table_element := ("                ", 0, 0); -- 0 probe default
       elementTable : hash_Table(1..table_Size);
         
       begin --initialize empty hash table
         
         for I in elementTable'Range loop
           elementTable(I) := nullElement; 
         end loop;
       
       
     
     --main loop for forming hash table  
     for I in 	1..tableLimit loop
       
       declare 
       
         --record
         hashRecord : table_element;
         tempValue : input;
       
         R : Integer := 1;
         div : Integer := 7; -- 2**7 for size 128.  logbase2(table size) = 7
         offset : Integer := 0;
         package randomOffset is new urandint(div);  use randomOffset;
       
       begin
         
         --read input
         Read(currentInput, tempValue, inputToHash.Count(i));
         hashRecord.value := tempValue(1..16);
         
         --assign hash address
         if hashMethod = SCRIPTKIDDY then 
         hashRecord.hash_Address := kiddyKey(hashRecord.value);
         else
         hashRecord.hash_Address := burrisKey(hashRecord.value);
         end if;
         
         --make sure hash address is in random range. Circularize Hash Table
         if hashRecord.hash_Address > 127 then
         hashRecord.hash_Address := 1;
         --put_line("it happen");
         end if;
         
         --check if address is available for table element. compare to nullElement
         while elementTable((hashRecord.hash_Address + offset) mod table_Size).value 
         /= nullElement.value loop
         
         --if there is a collision what probe do we use.
         if probeMethod = RANDOM then
         offset := R + UniqueRandInteger;
         else
         offset := offset + 1;
         end if;
         
         --iterate numberOfProbes
         hashRecord.probes := hashRecord.probes + 1;
         
         end loop;
         
         --now insert after checking for collisions
         elementTable((hashRecord.hash_Address + offset) mod table_Size) := 
         hashRecord;
         
         
         --first 30 logic
         if iterate < 31 then
          
           if hashRecord.probes < first30min then
             first30min := hashRecord.probes;
           end if;
          
           if hashRecord.probes > first30max then
             first30max := hashRecord.probes;  
           end if;
         
           first30avg := first30avg + Float(hashRecord.probes);
         
         end if;
         
         --second 30 logic
         if iterate >= tableLimit - 30 then
         
           if hashRecord.probes < last30min then
             last30min := hashRecord.probes;
           end if;
          
           if hashRecord.probes > last30max then
             last30max := hashRecord.probes;  
           end if;
         
           last30avg := last30avg + Float(hashRecord.probes);
         
         end if;
         
         
         
         --my logic
         totalProbes := totalProbes + hashRecord.probes;
         
         --iterate
       	 iterate := iterate + 1;
       	 
       end;
       
     end loop;
     
     myAverage := Float(totalProbes) / Float(tableLimit);
     theoreticalProbes := Float(tableLimit) / Float(table_Size);


         if probeMethod = RANDOM then
            theoreticalAverage := (1.0 / theoreticalProbes) * (Log(1.0 - 
            theoreticalProbes));
         else
     
         theoreticalAverage := (1.0 - (theoreticalProbes / 2.0)) / (1.0 - 
         theoreticalProbes);
         
         end if;
     
     for I in 1..table_Size loop
       
       if elementTable(I).value = nullElement.value then
         put_line("Offset Address " & Integer'Image(I) & " : NULL");
       else
         put_line("Offset Address " & Integer'Image(I) & " : " & 		   
         elementTable(I).value & " : Original Address " & 
         Integer'Image(elementTable(I).hash_Address) & " : Probes " & 
         Integer'Image(elementTable(I).probes));

         
       end if;
     
     end loop;
     
     
     
     
     put_line("================================");
     put_line("Minimum Probes   : " & Integer'Image(first30min));
     put_line("Maximum Probes   : " & Integer'Image(first30max));
     put("Total Probes     :  ");
     float_IO.put(Item => first30avg, Aft => 2, Exp => 0);
     new_line;
     put("First 30 avg     : ");
     float_IO.put(Item => first30avg / 30.0, Aft => 2, Exp => 0);
     new_line;
     put_line("================================");
     put_line("Minimum Probes   : " & Integer'Image(last30min));
     put_line("Maximum Probes   : " & Integer'Image(last30max));
     put("Total Probes     :  ");
     float_IO.put(Item => last30avg, Aft => 2, Exp => 0);
     new_line;
     put("Last 30 avg      : ");
     float_IO.put(Item => last30avg / 30.0, Aft => 2, Exp => 0);
     new_line;
     put_line("================================");
     put_Line("Total Probes     : " & Integer'Image(totalProbes));
     put("Method avg       : ");
     float_IO.put(Item => myAverage, Aft => 2, Exp => 0);
     new_line;
     put("Theoretical avg  : ");
     float_IO.put(Item => abs(theoreticalAverage), Aft => 2, Exp => 0);
     new_line;
     put_line("================================"); 
            
     end;
     
     Close(currentInput); --Close input if theres multiple hash table generations
     
   end generateMemHash;
  
   procedure generateFileHash (inputFile : String; outputFile : String; table_Size : Integer; capacityFull : Float; probeMethod : probeType; hashMethod : methodType) is
   
   begin
     
     null;
       
   end generateFileHash;
   
   procedure probeFunMem(inputFile : inputToHash.File_Type;  elementTable : hash_Table; table_Size : Integer; probeMethod : probeType; hashMethod : methodType; lowerBound : Integer; upperBound : Integer) is
     
     
   begin
      
     null;    
     
   end probeFunMem;
     
     procedure probeFunFile(inputFile : inputToHash.File_Type; storageFile : file_IO.File_Type; table_Size : Integer; lowerBound : Integer; upperBound : Integer; probeMethod : probeType; hashMethod : methodType) is
     
     begin
     
       null;
       
     end probeFunFile;
  
end hashfun;

