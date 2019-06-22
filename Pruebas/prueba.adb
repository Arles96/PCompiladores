procedure Hello is
  X: INTEGER;
  C: INTEGER := 0;
  Z,Y: Boolean := TRUE;
  A, B: Boolean;
begin
Y := FALSE;
X := 4 + 4 - 4;
if Y < 3 OR X > 5 AND A = 1 OR A < 5 Then
  Z:= TRUE;
elsif Y > 5 Then
  Z := FALSE;
end if;
if Y < 3 Then
 Z := FALSE;
else
  Z := TRUE;
end if;
end Hello;