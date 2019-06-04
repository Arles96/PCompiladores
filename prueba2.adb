procedure prueba is
x,y:boolean;
z:Integer := 2.0;
function Minimo (A, B: String) return Integer is
  xi:Integer;
  begin
    y := 4;
    if A > B then return B;
    else return A;
    end if;
end Minimo;
begin
  put(Minimo(1, 2));
end prueba;