procedure Calc is
   Op: Integer;
   Disp: Integer := 0;
   In_Val: Integer;
begin
   loop
      Put(Disp);

      Put("> ");

      loop
         Get(Op);
         exit when Op /= ' ';
      end loop;

      exit when Op = 'Q' or Op = 'q';

      Get(In_Val);

      case Op is
         when '='      => Disp := In_Val;
         when '+'      => Disp := Disp + In_Val;
         when '-'      => Disp := Disp - In_Val;
         when '*'      => Disp := Disp * In_Val;
         when '/'      => Disp := Disp / In_Val;
         when '^'      => Disp := Disp ** In_Val;
         when '0'..'9' => Put_Line("Please specify an operation.");
         when others   => Put_Line("What is " & Op & "?");
      end case;
   end loop;
end Calc;