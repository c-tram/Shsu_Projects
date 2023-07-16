--- in file main.adb

with Ada.Text_IO, direct_io; use Ada.Text_IO;
with Ada.Integer_Text_IO; use Ada.Integer_Text_IO;
with hashfun; use hashfun;

procedure main is

--str :  String :=  "parasympathetic ";
--hash : Integer;
--str2 : String :=  "zzllo           ";
begin
  
  --hash := burrisKey(str);
  --hash := kiddyKey(str2);
  --generateMemHash ("words", 128, 0.4, LINEAR, BURRIS);
  generateMemHash ("words", 128, 0.8, RANDOM, SCRIPTKIDDY);

end main;

