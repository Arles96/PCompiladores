%%

%unicode
%class ada95
%line
%column
%int
%standalone
%ignorecase

%{
%}

%eof{    
%eof}

//palabras reservadas
id = [a-zA-Z]{1}[a-zA-Z0-9]*
idProc = [a-zA-Z]{1}[a-zA-Z0-9]*
idFunc = [a-zA-Z]{1}[a-zA-Z0-9]*"("
abort = abort	
else = else	
new = new
return = return	
abs	= abs
elsif = elsif	
not	= not
reverse = reverse	
abstract = abstract 
end	= end
null = null		
accept = accept	
entry = entry		
select = select	
access = access
exception = exception
of = of	
separate = separate	
aliased = aliased	
exit = exit	
or = or		
all = all		
others = others	
subtype = subtype	
and	= and
for	= for
out = out		
array = array	
function = function			
at = at			
tagged = tagged	
generic	= generic
package = package	
task = task
begin = begin	
goto = goto	
pragma = pragma	
terminate = terminate	
body = body		
private	= private
then = then	
if = if	
procedure = procedure	
type = type	
case = case	
in = in	
protected = protected	
constant = constant	
until = until 	
is = is	
raise = raise	
use = use	
declare	= declare	
range = range		
delay = delay	
limited	= limited
record	= record
when = when	
delta = delta	
loop = loop	
rem = rem	
while = while	
digits = digits
renames = renames	
with = with	
do = do	
mod = mod	
requeue = requeue 
xor = xor

dataTypes = Integer|Natural|Positive|Long"_"Integer|Short"_"Integer|Float|Short"_"Float|Long"_"Float|Boolean|Character|Wide"_"Character|Unsigned"_"Integer|Byte"_"Integer|Unsigned"_"Byte"_"Integer|Word"_"Integer|Unsigned"_"Word"_"Integer|Dword"_"Integer|Unsigned"_"Dword"_"Integer|Qword"_"Integer|Byte"_"Boolean|Word"_"Boolean|Dword"_"Boolean|String"("{id}|[0-9]".""."{id}|[0-9]")"

arrow = "=>"
doubd = ".." 
doubaps = "**" 
assign = ":=" 
noteq = "/=" 
// maseq = ">="
// lesseq = "<=" 
llb = "<<"
rlb = ">>"
box = "<>"

qm = \"
ns = "#"
am = &
ap = \'
lp = "("
rp = ")"

op = ["*""+""-""/"]
// aps = "*" 
// plus = "+" 
// dash = "-" 
// pleca = "/"
coma = ","
punto = "."


oprel = ["<"">""="">=""<="]
dos = ":" 
pc = ";" 
// less = "<" 
// eql = "=" 
// mas= ">" 
guinb = "_" 
vertical = "|" 
lsqb = "["
rsqb = "]"
lcb = "{"
rcb = "}"

comment = "-""-".*(\n|\r)

exp = \".*\" //[a-zA-Z]+
%state STRING
%state IMPORTS
%state PROC
%state ENDPROC
%state COMMENT
%%
<YYINITIAL>{
    {dataTypes} {System.out.println("Tipo de datos: " + yytext());}
    {comment} {System.out.println("Comentario: " + yytext());}
    {exp} {System.out.println("String: " + yytext());}
    {abort} {System.out.println("Palabra reservada: "+yytext());}	
    {else} 	{System.out.println("Palabra reservada: "+yytext());}
    {new} {System.out.println("Palabra reservada: "+yytext());}
    {return} {System.out.println("Palabra reservada: "+yytext());}	
    {abs} {System.out.println("Palabra reservada: "+yytext());}	
    {elsif} {System.out.println("Palabra reservada: "+yytext());}	
    {not} {System.out.println("Palabra reservada: "+yytext());}	
    {reverse} {System.out.println("Palabra reservada: "+yytext());}	
    {abstract} {System.out.println("Palabra reservada: "+yytext());}
    {end} {System.out.println("Palabra reservada: "+yytext());}	
    {null} {System.out.println("Palabra reservada: "+yytext());}	
    {accept} {System.out.println("Palabra reservada: "+yytext());}	
    {entry} {System.out.println("Palabra reservada: "+yytext());}
    {select} 	{System.out.println("Palabra reservada: "+yytext());}
    {access} {System.out.println("Palabra reservada: "+yytext());}
    {exception} {System.out.println("Palabra reservada: "+yytext());}
    {of} {System.out.println("Palabra reservada: "+yytext());}
    {separate} {System.out.println("Palabra reservada: "+yytext());}	
    {aliased} {System.out.println("Palabra reservada: "+yytext());}	
    {exit} 	{System.out.println("Palabra reservada: "+yytext());}
    {or} {System.out.println("Palabra reservada: "+yytext());}	
    {all} {System.out.println("Palabra reservada: "+yytext());}		
    {others} {System.out.println("Palabra reservada: "+yytext());}	
    {subtype} {System.out.println("Palabra reservada: "+yytext());}	
    {and} {System.out.println("Palabra reservada: "+yytext());}	
    {for} {System.out.println("Palabra reservada: "+yytext());}	
    {out} {System.out.println("Palabra reservada: "+yytext());}		
    {array} {System.out.println("Palabra reservada: "+yytext());}	
    // {function} {System.out.println("Palabra reservada: "+yytext());}			
    {at} {System.out.println("Palabra reservada: "+yytext());}		
    {tagged} {System.out.println("Palabra reservada: "+yytext());}	
    {generic} {System.out.println("Palabra reservada: "+yytext());}
    {package} {System.out.println("Palabra reservada: "+yytext());}	
    {task} {System.out.println("Palabra reservada: "+yytext());}
    {begin} {System.out.println("Palabra reservada: "+yytext());}	
    {goto} {System.out.println("Palabra reservada: "+yytext());}	
    {pragma} 	{System.out.println("Palabra reservada: "+yytext());}
    {terminate} {System.out.println("Palabra reservada: "+yytext());}
    {body} 	{System.out.println("Palabra reservada: "+yytext());}
    {private}	{System.out.println("Palabra reservada: "+yytext());}
    {then} {System.out.println("Palabra reservada: "+yytext());}
    {if} {System.out.println("Palabra reservada: "+yytext());}
    {procedure} {System.out.println("Palabra reservada: "+yytext()); yybegin(PROC);}	
    {function} {System.out.println("Palabra reservada: "+yytext()); yybegin(PROC);}	
    {type} {System.out.println("Palabra reservada: "+yytext());}
    {case} 	{System.out.println("Palabra reservada: "+yytext());}
    {in} {System.out.println("Palabra reservada: "+yytext());}
    {protected} {System.out.println("Palabra reservada: "+yytext());}	
    {constant} {System.out.println("Palabra reservada: "+yytext());}	
    {until}  {System.out.println("Palabra reservada: "+yytext());}	
    {is} {System.out.println("Palabra reservada: "+yytext());}
    {raise} {System.out.println("Palabra reservada: "+yytext());}	
    // {use} {System.out.println("Palabra reservada: "+yytext());}
    {declare} {System.out.println("Palabra reservada: "+yytext());}		
    {range} {System.out.println("Palabra reservada: "+yytext());}		
    {delay} {System.out.println("Palabra reservada: "+yytext());}	
    {limited} {System.out.println("Palabra reservada: "+yytext());}	
    {record} {System.out.println("Palabra reservada: "+yytext());}	
    {when} {System.out.println("Palabra reservada: "+yytext());}
    {delta} {System.out.println("Palabra reservada: "+yytext());}	
    {loop} {System.out.println("Palabra reservada: "+yytext());}	
    {rem} {System.out.println("Palabra reservada: "+yytext());}	
    {while} {System.out.println("Palabra reservada: "+yytext());}	
    {digits} {System.out.println("Palabra reservada: "+yytext());}
    {renames} {System.out.println("Palabra reservada: "+yytext());} 	
    // {with} {System.out.println("Palabra reservada: "+yytext());}
    {do} {System.out.println("Palabra reservada: "+yytext());}
    {mod} {System.out.println("Palabra reservada: "+yytext());}
    {requeue} {System.out.println("Palabra reservada: "+yytext());}
    {xor} {System.out.println("Palabra reservada: "+yytext());}
	{arrow} {System.out.println(yytext());}
    {doubd} {System.out.println(yytext());}
    {doubaps} {System.out.println(yytext());}
    {assign} {System.out.println("Asignacion: " + yytext());}
    {noteq} {System.out.println(yytext());}
    // {maseq} {System.out.println(yytext());}
    // {lesseq} {System.out.println(yytext());}
    {llb} {System.out.println(yytext());}
    {rlb} {System.out.println(yytext());}
    {box} {System.out.println(yytext());}
    {lp} {System.out.println(yytext());}
    {rp} {System.out.println(yytext());}
    // {qm} {System.out.print("String: \""); yybegin(STRING);}
    {ns} {System.out.println(yytext());}
    {am} {System.out.println(yytext());}
    {ap} {System.out.println(yytext());}
    // {aps}  {System.out.println(yytext());}
    // {mas}  {System.out.println(yytext());}
    {coma} {System.out.println(yytext());}
    // {dash}  {System.out.println(yytext());}
    {punto} {System.out.println(yytext());}
    // {pleca}  {System.out.println(yytext());}
    {dos}  {System.out.println(yytext());}
    {pc}  {System.out.println(yytext());}
    // {less} {System.out.println(yytext());}
    // {eql}  {System.out.println(yytext());}
    // {mas}  {System.out.println(yytext());}
    {guinb}  {System.out.println(yytext());}
    {vertical} {System.out.println(yytext());}
    {lsqb} {System.out.println(yytext());}
    {rsqb}  {System.out.println(yytext());}
    {lcb} {System.out.println(yytext());}
    {rcb} {System.out.println(yytext());}
    {op} {System.out.println("Operador: " + yytext());}
    {oprel} {System.out.println("Operador relacional: " + yytext());}
    {idFunc} {System.out.println("IDFunc: " + yytext());}
    {with} {yybegin(IMPORTS);}
    {use} {yybegin(IMPORTS);}
    {id} {System.out.println("ID: " + yytext());}
    // {plus} {System.out.println(yytext());}
    . {}
}

<STRING>{
    \" {System.out.println("\""); yybegin(YYINITIAL);}
    {exp} {System.out.print(yytext());}
    . {}
}

<IMPORTS>{
    {pc} {yybegin(YYINITIAL);}
    . {}
}

<PROC>{
    // {is} {System.out.println("Palabra reservada: "+yytext()); yybegin(YYINITIAL);}
    {idProc} {System.out.println("Procedimiento: " + yytext()); yybegin(YYINITIAL);}
    . {}
}

<ENDPROC>{
    {pc} {System.out.println(yytext()); yybegin(YYINITIAL);}
    {idProc} {System.out.println("Procedimiento: " + yytext());}
    . {}
}