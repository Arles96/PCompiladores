procedure hola is
  I, X : Integer;
begin
  I := 1;
  X := 4;
  while I <= 6 AND X = 4 loop
    put(I);
    I := I + 1;
  end loop;
  X := 4 + 4;
  put("Fin del programa \n");
end hola;