--
-- This reads in a string on a line and skips leading blanks, but it uses
-- access types and dynamic allocation to return strings of any desired
-- type.
--
with Gnat.Io; use Gnat.Io;
procedure ReadRest3 is
   -- Create a type which references strings.
   type Str_Ref is access String;

   -- Read the next line into a string and return a pointer to it.
   function ReadStr return Str_Ref is
      Str: String(1..300);      -- Input buffer.  Limits final size.
      Ch: Character;            -- Input character.
      Ret_Val: Str_Ref;         -- Return value.
      Last: Integer;            -- Last input subscript.
   begin
      -- Read to first non-blank character and put it into the first
      -- position of the array.
      loop
         Get(Ch);
         exit when Ch /= ' ';
      end loop;
      Str(Str'first) := Ch;

      -- Read the rest of the line into the part of the array after the
      -- first position.
      Get_Line(Str(Str'First+1..Str'last), Last);

      -- Create the return pointer.
      Ret_Val := new String(1..Last);
      Ret_Val.all := Str(1..Last);
      return Ret_Val;
   end ReadStr;

   Fred: Str_Ref;
begin
   -- Get the string.
   Fred := ReadStr;

   -- Echo the the string, which does not contain any blank filler.
   Put_Line(Fred.all);
end ReadRest3;