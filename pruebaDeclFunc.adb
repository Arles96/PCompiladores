procedure x is
  id, id1, id2: Integer;
  function Minimo (a, b: Integer) return Integer is
  begin
    if a < b then return a;
    else return b;
    end if;
  end Minimo;
  procedure x3 is
  begin
    put("Hola Mundo");
  end x3;
begin
  put(Minimo(1,2));
end x;