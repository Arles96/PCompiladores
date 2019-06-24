procedure factorial is
i, x: Integer;
y: Integer;
begin
  put("Ingrese un numero ");
  get(x);
  y := 1;
  i := 1;
  while i <= x loop
    y := y * i;
    i := i + 1;
  end loop;
  put("\nEl resultado es ");
  put(y);
end factorial;


