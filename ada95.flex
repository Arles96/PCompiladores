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
num = [0-9]("."[0-9])?
true = true
false = false
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
/* use = use */
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
/* with = with */
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
    {dataTypes} {return new Symbol(sym.dataTypes, yycolumn, yyline, yytext());}
    {comment} {return new Symbol(sym.comment, yycolumn, yyline, yytext());}
    {exp} {return new Symbol(sym.exp, yycolumn, yyline, yytext());}
    {abort} {return new Symbol(sym.abort, yycolumn, yyline, yytext());}
    {else} 	{return new Symbol(sym.else, yycolumn, yyline, yytext());}
    {new} {return new Symbol(sym.new, yycolumn, yyline, yytext());}
    {return} {return new Symbol(sym.return, 0, 0);}
    {abs} {return new Symbol(sym.abs, yycolumn, yyline, yytext());}
    {elsif} {return new Symbol(sym.elsif, 0, 0);}
    {not} {return new Symbol(sym.not, yycolumn, yyline, yytext());}
    {reverse} {return new Symbol(sym.reverse, 0, 0);}
    {abstract} {return new Symbol(sym.abstract, 0, 0);}
    {end} {return new Symbol(sym.end, yycolumn, yyline, yytext());}
    {null} {return new Symbol(sym.null, yycolumn, yyline, yytext());}
    {accept} {return new Symbol(sym.accept, yycolumn, yyline, yytext());}
    {entry} {return new Symbol(sym.entry, yycolumn, yyline, yytext());}
    {select} 	{return new Symbol(sym.select, yycolumn, yyline, yytext());}
    {access} {return new Symbol(sym.access, yycolumn, yyline, yytext());}
    {exception} {return new Symbol(sym.exception, yycolumn, yyline, yytext());}
    {of} {return new Symbol(sym.of, yycolumn, yyline, yytext());}
    {separate} {return new Symbol(sym.separate, yycolumn, yyline, yytext());}
    {aliased} {return new Symbol(sym.aliased, yycolumn, yyline, yytext());}
    {exit} 	{return new Symbol(sym.exit, yycolumn, yyline, yytext());}
    {or} {return new Symbol(sym.or, yycolumn, yyline, yytext());}
    {all} {return new Symbol(sym.all, yycolumn, yyline, yytext());}
    {others} {return new Symbol(sym.others, yycolumn, yyline, yytext());}
    {subtype} {return new Symbol(sym.subtype, yycolumn, yyline, yytext());}
    {and} {return new Symbol(sym.and, yycolumn, yyline, yytext());}
    {for} {return new Symbol(sym.for, yycolumn, yyline, yytext());}
    {out} {return new Symbol(sym.out, yycolumn, yyline, yytext());}
    {array} {return new Symbol(sym.array, yycolumn, yyline, yytext());}
    // {function} {return new Symbol(sym.function, 0, 0);}
    {at} {return new Symbol(sym.at, yycolumn, yyline, yytext());}
    {tagged} {return new Symbol(sym.tagged, yycolumn, yyline, yytext());}
    {generic} {return new Symbol(sym.generic, yycolumn, yyline, yytext());}
    {package} {return new Symbol(sym.package, yycolumn, yyline, yytext());}
    {task} {return new Symbol(sym.task, yycolumn, yyline, yytext());}
    {begin} {return new Symbol(sym.begin, yycolumn, yyline, yytext());}
    {goto} {return new Symbol(sym.goto, yycolumn, yyline, yytext());}
    {pragma} 	{return new Symbol(sym.pragma, yycolumn, yyline, yytext());}
    {terminate} {return new Symbol(sym.terminate, yycolumn, yyline, yytext());}
    {body} 	{return new Symbol(sym.body, yycolumn, yyline, yytext());}
    {private}	{return new Symbol(sym.private, yycolumn, yyline, yytext());}
    {then} {return new Symbol(sym.then, yycolumn, yyline, yytext());}
    {if} {return new Symbol(sym.if, yycolumn, yyline, yytext());}
    {procedure} {return new Symbol(sym.procedure, yycolumn, yyline, yytext());}
    {function} {return new Symbol(sym.function, yycolumn, yyline, yytext());}
    {type} {return new Symbol(sym.type, yycolumn, yyline, yytext());}
    {case} 	{return new Symbol(sym.case, yycolumn, yyline, yytext());}
    {in} {return new Symbol(sym.in, yycolumn, yyline, yytext());}
    {protected} {return new Symbol(sym.protected, yycolumn, yyline, yytext());}
    {constant} {return new Symbol(sym.constant, yycolumn, yyline, yytext());}
    {until}  {return new Symbol(sym.until, yycolumn, yyline, yytext());}
    {is} {return new Symbol(sym.is, yycolumn, yyline, yytext());}
    {raise} {return new Symbol(sym.raise, yycolumn, yyline, yytext());}
    // {use} {return new Symbol(sym.use, 0, 0);}
    {declare} {return new Symbol(sym.declare, yycolumn, yyline, yytext());}
    {range} {return new Symbol(sym.range, yycolumn, yyline, yytext());}
    {delay} {return new Symbol(sym.delay, yycolumn, yyline, yytext());}
    {limited} {return new Symbol(sym.limited, yycolumn, yyline, yytext());}
    {record} {return new Symbol(sym.record, yycolumn, yyline, yytext());}
    {when} {return new Symbol(sym.when, yycolumn, yyline, yytext());}
    {delta} {return new Symbol(sym.delta, yycolumn, yyline, yytext());}
    {loop} {return new Symbol(sym.loop, yycolumn, yyline, yytext());}
    {rem} {return new Symbol(sym.rem, yycolumn, yyline, yytext());}
    {while} {return new Symbol(sym.while, yycolumn, yyline, yytext());}
    {digits} {return new Symbol(sym.digits, yycolumn, yyline, yytext());}
    {renames} {return new Symbol(sym.renames, yycolumn, yyline, yytext());}
    // {with} {return new Symbol(sym.with, 0, 0);}
    {do} {return new Symbol(sym.do, yycolumn, yyline, yytext());}
    {get} {return new Symbol(sym.get, yycolumn, yyline, yytext());}
    {put} {return new Symbol(sym.put, yycolumn, yyline, yytext());}
    {mod} {return new Symbol(sym.mod, yycolumn, yyline, yytext());}
    {requeue} {return new Symbol(sym.requeue, 0, 0);}
    {xor} {return new Symbol(sym.xor, yycolumn, yyline, yytext());}
	{arrow} {return new Symbol(sym.arrow, yycolumn, yyline, yytext());}
    {doubd} {return new Symbol(sym.doubd, yycolumn, yyline, yytext());}
    {doubaps} {return new Symbol(sym.doubaps, yycolumn, yyline, yytext());}
    {assign} {return new Symbol(sym.assign, yycolumn, yyline, yytext());}
    {noteq} {return new Symbol(sym.noteq, yycolumn, yyline, yytext());}
    // {maseq} {return new Symbol(sym.maseq, 0, 0);}
    // {lesseq} {return new Symbol(sym.lesseq, 0, 0);}
    {llb} {return new Symbol(sym.llb, yycolumn, yyline, yytext());}
    {rlb} {return new Symbol(sym.rlb, yycolumn, yyline, yytext());}
    {box} {return new Symbol(sym.box, yycolumn, yyline, yytext());}
    {lp} {return new Symbol(sym.lp, yycolumn, yyline, yytext());}
    {rp} {return new Symbol(sym.rp, yycolumn, yyline, yytext());}
    // {qm} {return new Symbol(sym.qm, 0, 0);}
    {ns} {return new Symbol(sym.ns, yycolumn, yyline, yytext());}
    {am} {return new Symbol(sym.am, yycolumn, yyline, yytext());}
    {ap} {return new Symbol(sym.ap, yycolumn, yyline, yytext());}
    // {aps}  {return new Symbol(sym.aps, 0, 0);}
    // {mas}  {return new Symbol(sym.mas, 0, 0);}
    {coma} {return new Symbol(sym.coma, yycolumn, yyline, yytext());}
    // {dash}  {return new Symbol(sym.dash, 0, 0);}
    {punto} {return new Symbol(sym.punto, yycolumn, yyline, yytext());}
    // {pleca}  {return new Symbol(sym.pleca, 0, 0);}
    {dos}  {return new Symbol(sym.dos, yycolumn, yyline, yytext());}
    {pc}  {return new Symbol(sym.pc, yycolumn, yyline, yytext());}
    // {less} {return new Symbol(sym.less, 0, 0);}
    // {eql}  {return new Symbol(sym.eql, 0, 0);}
    // {mas}  {return new Symbol(sym.mas, 0, 0);}
    {guinb}  {return new Symbol(sym.guinb, yycolumn, yyline, yytext());}
    {vertical} {return new Symbol(sym.vertical, yycolumn, yyline, yytext());}
    {lsqb} {return new Symbol(sym.lsqb, yycolumn, yyline, yytext());}
    {rsqb}  {return new Symbol(sym.rsqb, yycolumn, yyline, yytext());}
    {lcb} {return new Symbol(sym.lcb, yycolumn, yyline, yytext());}
    {rcb} {return new Symbol(sym.rcb, yycolumn, yyline, yytext());}
    {op} {return new Symbol(sym.op, yycolumn, yyline, yytext());}
    {oprel} {return new Symbol(sym.oprel, yycolumn, yyline, yytext());}
    // {idFunc} {return new Symbol(sym.idFunc, yycolumn, yyline, yytext());}
    /* {with} {yybegin(IMPORTS);}
    {use} {yybegin(IMPORTS);} */
    {id} {return new Symbol(sym.id, yycolumn, yyline, yytext());}
    // {plus} {return new Symbol(sym.id, 0, 0);}
    . {}
}

<STRING>{
    \" {}
    {exp} {System.out.print(yytext());}
    . {}
}

/* <IMPORTS>{
    {pc} {yybegin(YYINITIAL);}
    . {}
} */

<PROC>{
    // {is} {}
    {idProc} {return new Symbol(sym.idProc, yycolumn, yyline, yytext());yybegin(YYINITIAL);}
    . {}
}

<ENDPROC>{
    {pc} {return new Symbol(sym.pc, yycolumn, yyline, yytext());yybegin(YYINITIAL);}
    {idProc} {return new Symbol(sym.idProc, yycolumn, yyline, yytext());}
    . {}
}