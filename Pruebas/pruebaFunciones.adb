procedure prueba is
  function Minimo (a, b: Integer) return Integer is
  begin
    if a < b then return a;
    else return b;
    end if;
  end Minimo;
  function Minimo2 (a, b: Integer) return Integer is
  begin
    if a < b then return a;
    else return b;
    end if;
  end Minimo2;
begin
  put(Minimo(1, 2));
end prueba;