procedure Hello is

function hola return Integer is
  hello2 : Integer;
  begin
    hello2 := 2;
  end hola;
  hello3 : Integer;

begin
  hola := (id mod 2 );
  if (id mod 2) = 0 then
    put(hola);
  end if;
  put(hola);
end Hello;