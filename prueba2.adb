procedure prueba is
  function Minimo (A, B: String) return Integer is
  x,y:Integer;
  z:Integer := 2.0;
  begin
    y := 4;
    if A > B then return B;
    else return A;
    end if;
  end Minimo;
  procedure prueba2 is
  begin
    put(2);
  end prueba2;
begin
  put(Minimo(1, 2));
end prueba;