procedure prueba is
  function Minimo2 (a, b: Integer) return Integer is
  X : Integer := 0;
  begin
    if a < b then return a;
    else return b;
    end if;
  end Minimo2;
begin
  put(Minimo2(1, 2));
end prueba;