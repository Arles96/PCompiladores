procedure prueba is
  function Minimo (A, B: Integer) return Integer is
  begin
    if A > B then return B;
    else return A;
    end if;
  end Minimo;
begin
  put(Minimo(1, 2));
end prueba;