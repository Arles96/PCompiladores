procedure prueba is
  function Minimo (A, B: String) return Integer is
  x,y:Integer;
  z:String;
  begin
    if A > B then return B;
    else return A;
    end if;
  end Minimo;
begin
  put(Minimo(1, 2));
end prueba;