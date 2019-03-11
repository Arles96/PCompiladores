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
get = "GET" | "get"
put = "PUT" | "put"

comment = "-""-".*(\n|\r)

exp = \".*\" //[a-zA-Z]+
%state STRING
%state IMPORTS
%state PROC
%state ENDPROC
%state COMMENT
%%
<YYINITIAL>{
    {dataTypes} {return new Symbol(sym.dataTypes, 0, 0);}
    {comment} {return new Symbol(sym.comment, 0, 0);}
    {exp} {return new Symbol(sym.exp, 0, 0);}
    {abort} {return new Symbol(sym.abort, 0, 0);}
    {else} 	{return new Symbol(sym.else, 0, 0);}
    {new} {return new Symbol(sym.new, 0, 0);}
    {return} {return new Symbol(sym.return, 0, 0);}
    {abs} {return new Symbol(sym.abs, 0, 0);}
    {elsif} {return new Symbol(sym.elsif, 0, 0);}
    {not} {return new Symbol(sym.not, 0, 0);}
    {reverse} {return new Symbol(sym.reverse, 0, 0);}
    {abstract} {return new Symbol(sym.abstract, 0, 0);}
    {end} {return new Symbol(sym.end, 0, 0);}
    {null} {return new Symbol(sym.null, 0, 0);}
    {accept} {return new Symbol(sym.accept, 0, 0);}
    {entry} {return new Symbol(sym.entry, 0, 0);}
    {select} 	{return new Symbol(sym.select, 0, 0);}
    {access} {return new Symbol(sym.access, 0, 0);}
    {exception} {return new Symbol(sym.exception, 0, 0);}
    {of} {return new Symbol(sym.of, 0, 0);}
    {separate} {return new Symbol(sym.separate, 0, 0);}
    {aliased} {return new Symbol(sym.aliased, 0, 0);}
    {exit} 	{return new Symbol(sym.exit, 0, 0);}
    {or} {return new Symbol(sym.or, 0, 0);}
    {all} {return new Symbol(sym.all, 0, 0);}
    {others} {return new Symbol(sym.others, 0, 0);}
    {subtype} {return new Symbol(sym.subtype, 0, 0);}
    {and} {return new Symbol(sym.and, 0, 0);}
    {for} {return new Symbol(sym.for, 0, 0);}
    {out} {return new Symbol(sym.out, 0, 0);}
    {array} {return new Symbol(sym.array, 0, 0);}
    // {function} {return new Symbol(sym.function, 0, 0);}
    {at} {return new Symbol(sym.at, 0, 0);}
    {tagged} {return new Symbol(sym.tagged, 0, 0);}
    {generic} {return new Symbol(sym.generic, 0, 0);}
    {package} {return new Symbol(sym.package, 0, 0);}
    {task} {return new Symbol(sym.task, 0, 0);}
    {begin} {return new Symbol(sym.begin, 0, 0);}
    {goto} {return new Symbol(sym.goto, 0, 0);}
    {pragma} 	{return new Symbol(sym.pragma, 0, 0);}
    {terminate} {return new Symbol(sym.terminate, 0, 0);}
    {body} 	{return new Symbol(sym.body, 0, 0);}
    {private}	{return new Symbol(sym.private, 0, 0);}
    {then} {return new Symbol(sym.then, 0, 0);}
    {if} {return new Symbol(sym.if, 0, 0);}
    {procedure} {return new Symbol(sym.procedure, 0, 0);}
    {function} {return new Symbol(sym.function, 0, 0);}
    {type} {return new Symbol(sym.type, 0, 0);}
    {case} 	{return new Symbol(sym.case, 0, 0);}
    {in} {return new Symbol(sym.in, 0, 0);}
    {protected} {return new Symbol(sym.protected, 0, 0);}
    {constant} {return new Symbol(sym.constant, 0, 0);}
    {until}  {return new Symbol(sym.until, 0, 0);}
    {is} {return new Symbol(sym.is, 0, 0);}
    {raise} {return new Symbol(sym.raise, 0, 0);}
    // {use} {return new Symbol(sym.use, 0, 0);}
    {declare} {return new Symbol(sym.declare, 0, 0);}
    {range} {return new Symbol(sym.range, 0, 0);}
    {delay} {return new Symbol(sym.delay, 0, 0);}
    {limited} {return new Symbol(sym.limited, 0, 0);}
    {record} {return new Symbol(sym.record, 0, 0);}
    {when} {return new Symbol(sym.when, 0, 0);}
    {delta} {return new Symbol(sym.delta, 0, 0);}
    {loop} {return new Symbol(sym.loop, 0, 0);}
    {rem} {return new Symbol(sym.rem, 0, 0);}
    {while} {return new Symbol(sym.while, 0, 0);}
    {digits} {return new Symbol(sym.digits, 0, 0);}
    {renames} {return new Symbol(sym.renames, 0, 0);}
    // {with} {return new Symbol(sym.with, 0, 0);}
    {do} {return new Symbol(sym.do, 0, 0);}
    {get} {return new Symbol(sym.get, 0, 0);}
    {put} {return new Symbol(sym.put, 0, 0);}
    {mod} {return new Symbol(sym.mod, 0, 0);}
    {requeue} {return new Symbol(sym.requeue, 0, 0);}
    {xor} {return new Symbol(sym.xor, 0, 0);}
	{arrow} {return new Symbol(sym.arrow, 0, 0);}
    {doubd} {return new Symbol(sym.doubd, 0, 0);}
    {doubaps} {return new Symbol(sym.doubaps, 0, 0);}
    {assign} {return new Symbol(sym.assign, 0, 0);}
    {noteq} {return new Symbol(sym.noteq, 0, 0);}
    // {maseq} {return new Symbol(sym.maseq, 0, 0);}
    // {lesseq} {return new Symbol(sym.lesseq, 0, 0);}
    {llb} {return new Symbol(sym.llb, 0, 0);}
    {rlb} {return new Symbol(sym.rlb, 0, 0);}
    {box} {return new Symbol(sym.box, 0, 0);}
    {lp} {return new Symbol(sym.lp, 0, 0);}
    {rp} {return new Symbol(sym.rp, 0, 0);}
    // {qm} {return new Symbol(sym.qm, 0, 0);}
    {ns} {return new Symbol(sym.ns, 0, 0);}
    {am} {return new Symbol(sym.am, 0, 0);}
    {ap} {return new Symbol(sym.ap, 0, 0);}
    // {aps}  {return new Symbol(sym.aps, 0, 0);}
    // {mas}  {return new Symbol(sym.mas, 0, 0);}
    {coma} {return new Symbol(sym.coma, 0, 0);}
    // {dash}  {return new Symbol(sym.dash, 0, 0);}
    {punto} {return new Symbol(sym.punto, 0, 0);}
    // {pleca}  {return new Symbol(sym.pleca, 0, 0);}
    {dos}  {return new Symbol(sym.dos, 0, 0);}
    {pc}  {return new Symbol(sym.pc, 0, 0);}
    // {less} {return new Symbol(sym.less, 0, 0);}
    // {eql}  {return new Symbol(sym.eql, 0, 0);}
    // {mas}  {return new Symbol(sym.mas, 0, 0);}
    {guinb}  {return new Symbol(sym.guinb, 0, 0);}
    {vertical} {return new Symbol(sym.vertical, 0, 0);}
    {lsqb} {return new Symbol(sym.lsqb, 0, 0);}
    {rsqb}  {return new Symbol(sym.rsqb, 0, 0);}
    {lcb} {return new Symbol(sym.lcb, 0, 0);}
    {rcb} {return new Symbol(sym.rcb, 0, 0);}
    {op} {return new Symbol(sym.op, 0, 0);}
    {oprel} {return new Symbol(sym.oprel, 0, 0);}
    {idFunc} {return new Symbol(sym.idFunc, 0, 0);}
    {with} {yybegin(IMPORTS);}
    {use} {yybegin(IMPORTS);}
    {id} {return new Symbol(sym.id, 0, 0);}
    // {plus} {return new Symbol(sym.id, 0, 0);}
    . {}
}

<STRING>{
    \" {}
    {exp} {System.out.print(yytext());}
    . {}
}

<IMPORTS>{
    {pc} {yybegin(YYINITIAL);}
    . {}
}

<PROC>{
    // {is} {}
    {idProc} {return new Symbol(sym.idProc, 0, 0);yybegin(YYINITIAL);}
    . {}
}

<ENDPROC>{
    {pc} {return new Symbol(sym.pc, 0, 0);yybegin(YYINITIAL);}
    {idProc} {return new Symbol(sym.idProc, 0, 0);}
    . {}
}