--Caso de prueba 1: programa sencillo con declaración entremezclada y recursión
--Ejemplo sacado de : http://www.dwheeler.com/lovelace/s17s2.htm

procedure Main is
--parte declarativa
	Var1: Integer := 2;
	function Factorial(A : Integer) return Integer is
		begin
			 if A < 0 then                -- Stop recursion if A <= 0.
		         Put("Hola");
			 elsif A = 0 then
			    return 1;
			 else
			   return A * Factorial(A - 1);   -- recurse.
			 end if;
		end Factorial;
	Var2: Float := 300;

	function Minimum (A, B : Integer) return Integer is
	begin
	   if A < B then
	      return A;
	   else
	      return B;
	   end if;
	end Minimum;

	Var3: Boolean := TRue;
	Prompt: Integer;
--inicio de Main
begin
	while Prompt >= 0 loop
		For i in 1..10 loop
			if i mod 2 = 0 then
				put(Factorial(Var1));
			else
				put(Minimum(i,var1));
			end if;
		end loop;--end del for
	end loop; --end del while
end Main;


