import java_cup.runtime.*;
%%

%unicode
%class Ada95
%line
%column
%int
%cup
%ignorecase

%{
%}

%eof{
%eof}

//palabras reservadas
ID = [a-zA-Z]{1}[a-zA-Z0-9]*
NUM = [0-9]+("."[0-9]+)?
TRUE = true
FALSE = false
ABORT = abort
ELSE = else
NEW = new
RETURN = return
ABS	= abs
ELSIF = elsif
NOT	= not
REVERSE = reverse
ABSTRACT = abstract
END	= end
NULL = null
ACCEPT = accept
ENTRY = entry
SELECT = select
ACCESS = access
EXCEPTION = exception
OF = of
SEPARATE = separate
ALIASED = aliased
EXIT = exit
OR = or
ALL = all
OTHERS = others
SUBTYPE = subtype
AND	= and
FOR	= for
OUT = out
ARRAY = array
FUNCTION = function
AT = at
TAGGED = tagged
GENERIC	= generic
/* PACKAGEA = package */
TASK = task
BEGIN = begin
GOTO = goto
PRAGMA = pragma
TERMINATE = terminate
BODY = body
PRIVATE	= private
THEN = then
IF = if
PROCEDURE = procedure
TYPE = type
CASE = case
IN = in
PROTECTED = protected
CONSTANT = constant
UNTIL = until
IS = is
RAISE = raise
/* use = use */
DECLARE	= declare
RANGE = range
DELAY = delay
LIMITED	= limited
RECORD	= record
WHEN = when
DELTA = delta
LOOP = loop
REM = rem
WHILE = while
DIGITS = digits
RENAMES = renames
/* with = with */
DO = do
MOD = mod
REQUEUE = requeue
XOR = xor

DATATYPES = Integer|Natural|Positive|Long"_"Integer|Short"_"Integer|Float|Short"_"Float|Long"_"Float|Boolean|Character|Wide"_"Character|Unsigned"_"Integer|Byte"_"Integer|Unsigned"_"Byte"_"Integer|Word"_"Integer|Unsigned"_"Word"_"Integer|Dword"_"Integer|Unsigned"_"Dword"_"Integer|Qword"_"Integer|Byte"_"Boolean|Word"_"Boolean|Dword"_"Boolean|String"("{ID}|[0-9]".""."{ID}|[0-9]")"

ARROW = "=>"
DOUBD = ".."
DOUBAPS = "**"
ASSIGN = ":="
NOTEQ = "/="
// maseq = ">="
// lesseq = "<="
LLB = "<<"
RLB = ">>"
BOX = "<>"

QM = \"
NS = "#"
AM = &
AP = \'
LP = "("
RP = ")"

OP = ["*""+""-""/"]
// aps = "*"
// plus = "+"
// dash = "-"
// pleca = "/"
COMA = ","
PUNTO = "."


OPREL = ["<"">""="">=""<=""/="]
DOS = ":"
PC = ";"
// less = "<"
// eql = "="
// mas= ">"
GUINB = "_"
VERTICAL = "|"
LSQB = "["
RSQB = "]"
LCB = "{"
RCB = "}"
GET = "GET" | "get"
PUT = "PUT" | "put"

SALTO = \n

COMMENT = "-""-".*(\n|\r)

EXP = \".*\" //[a-zA-Z]+
%state STRING
%state IMPORTS
%state PROC
%state ENDPROC
%state COMMENT
%%
<YYINITIAL>{
    {DATATYPES} {return new Symbol(sym.DATATYPES, yycolumn, yyline, yytext());}
    {SALTO}     {  }
    {NUM}       {return new Symbol(sym.NUM, yycolumn, yyline, yytext());}
    {COMMENT} {/* return new Symbol(sym.COMMENT, yycolumn, yyline, yytext()); */}
    {EXP} {return new Symbol(sym.EXP, yycolumn, yyline, yytext());}
    {ABORT} {return new Symbol(sym.ABORT, yycolumn, yyline, yytext());}
    {ELSE} 	{return new Symbol(sym.ELSE, yycolumn, yyline, yytext());}
    {TRUE} {return new Symbol(sym.TRUE, yycolumn, yyline, yytext());}
    {FALSE} {return new Symbol(sym.FALSE, yycolumn, yyline, yytext());}
    {NEW} {return new Symbol(sym.NEW, yycolumn, yyline, yytext());}
    {RETURN} {return new Symbol(sym.RETURN, yycolumn, yyline, yytext());}
    {ABS} {return new Symbol(sym.ABS, yycolumn, yyline, yytext());}
    {ELSIF} {return new Symbol(sym.ELSIF, yycolumn, yyline, yytext());}
    {NOT} {return new Symbol(sym.NOT, yycolumn, yyline, yytext());}
    {REVERSE} {return new Symbol(sym.REVERSE, yycolumn, yyline, yytext());}
    {ABSTRACT} {return new Symbol(sym.ABSTRACT, yycolumn, yyline, yytext());}
    {END} {return new Symbol(sym.END, yycolumn, yyline, yytext());}
    {NULL} {return new Symbol(sym.NULL, yycolumn, yyline, yytext());}
    {ACCEPT} {return new Symbol(sym.ACCEPT, yycolumn, yyline, yytext());}
    {ENTRY} {return new Symbol(sym.ENTRY, yycolumn, yyline, yytext());}
    {SELECT} 	{return new Symbol(sym.SELECT, yycolumn, yyline, yytext());}
    {ACCESS} {return new Symbol(sym.ACCESS, yycolumn, yyline, yytext());}
    {EXCEPTION} {return new Symbol(sym.EXCEPTION, yycolumn, yyline, yytext());}
    {OF} {return new Symbol(sym.OF, yycolumn, yyline, yytext());}
    {SEPARATE} {return new Symbol(sym.SEPARATE, yycolumn, yyline, yytext());}
    {ALIASED} {return new Symbol(sym.ALIASED, yycolumn, yyline, yytext());}
    {EXIT} 	{return new Symbol(sym.EXIT, yycolumn, yyline, yytext());}
    {OR} {return new Symbol(sym.OR, yycolumn, yyline, yytext());}
    {ALL} {return new Symbol(sym.ALL, yycolumn, yyline, yytext());}
    {OTHERS} {return new Symbol(sym.OTHERS, yycolumn, yyline, yytext());}
    {SUBTYPE} {return new Symbol(sym.SUBTYPE, yycolumn, yyline, yytext());}
    {AND} {return new Symbol(sym.AND, yycolumn, yyline, yytext());}
    {FOR} {return new Symbol(sym.FOR, yycolumn, yyline, yytext());}
    {OUT} {return new Symbol(sym.OUT, yycolumn, yyline, yytext());}
    {ARRAY} {return new Symbol(sym.ARRAY, yycolumn, yyline, yytext());}
    // {function} {return new Symbol(SYM.function, 0, 0);}
    {AT} {return new Symbol(sym.AT, yycolumn, yyline, yytext());}
    {TAGGED} {return new Symbol(sym.TAGGED, yycolumn, yyline, yytext());}
    {GENERIC} {return new Symbol(sym.GENERIC, yycolumn, yyline, yytext());}
    /* {PACKAGEA} {return new Symbol(sym.PACKAGEA, yycolumn, yyline, yytext());} */
    {TASK} {return new Symbol(sym.TASK, yycolumn, yyline, yytext());}
    {BEGIN} {return new Symbol(sym.BEGIN, yycolumn, yyline, yytext());}
    {GOTO} {return new Symbol(sym.GOTO, yycolumn, yyline, yytext());}
    {PRAGMA} 	{return new Symbol(sym.PRAGMA, yycolumn, yyline, yytext());}
    {TERMINATE} {return new Symbol(sym.TERMINATE, yycolumn, yyline, yytext());}
    {BODY} 	{return new Symbol(sym.BODY, yycolumn, yyline, yytext());}
    {PRIVATE}	{return new Symbol(sym.PRIVATE, yycolumn, yyline, yytext());}
    {THEN} {return new Symbol(sym.THEN, yycolumn, yyline, yytext());}
    {IF} {return new Symbol(sym.IF, yycolumn, yyline, yytext());}
    {PROCEDURE} {return new Symbol(sym.PROCEDURE, yycolumn, yyline, yytext());}
    {FUNCTION} {return new Symbol(sym.FUNCTION, yycolumn, yyline, yytext());}
    {TYPE} {return new Symbol(sym.TYPE, yycolumn, yyline, yytext());}
    {CASE} 	{return new Symbol(sym.CASE, yycolumn, yyline, yytext());}
    {IN} {return new Symbol(sym.IN, yycolumn, yyline, yytext());}
    {PROTECTED} {return new Symbol(sym.PROTECTED, yycolumn, yyline, yytext());}
    {CONSTANT} {return new Symbol(sym.CONSTANT, yycolumn, yyline, yytext());}
    {UNTIL}  {return new Symbol(sym.UNTIL, yycolumn, yyline, yytext());}
    {IS} {return new Symbol(sym.IS, yycolumn, yyline, yytext());}
    {RAISE} {return new Symbol(sym.RAISE, yycolumn, yyline, yytext());}
    // {use} {return new Symbol(SYM.use, 0, 0);}
    {DECLARE} {return new Symbol(sym.DECLARE, yycolumn, yyline, yytext());}
    {RANGE} {return new Symbol(sym.RANGE, yycolumn, yyline, yytext());}
    {DELAY} {return new Symbol(sym.DELAY, yycolumn, yyline, yytext());}
    {LIMITED} {return new Symbol(sym.LIMITED, yycolumn, yyline, yytext());}
    {RECORD} {return new Symbol(sym.RECORD, yycolumn, yyline, yytext());}
    {WHEN} {return new Symbol(sym.WHEN, yycolumn, yyline, yytext());}
    {DELTA} {return new Symbol(sym.DELTA, yycolumn, yyline, yytext());}
    {LOOP} {return new Symbol(sym.LOOP, yycolumn, yyline, yytext());}
    {REM} {return new Symbol(sym.REM, yycolumn, yyline, yytext());}
    {WHILE} {return new Symbol(sym.WHILE, yycolumn, yyline, yytext());}
    {DIGITS} {return new Symbol(sym.DIGITS, yycolumn, yyline, yytext());}
    {RENAMES} {return new Symbol(sym.RENAMES, yycolumn, yyline, yytext());}
    // {with} {return new Symbol(SYM.with, 0, 0);}
    {DO} {return new Symbol(sym.DO, yycolumn, yyline, yytext());}
    {GET} {return new Symbol(sym.GET, yycolumn, yyline, yytext());}
    {PUT} {return new Symbol(sym.PUT, yycolumn, yyline, yytext());}
    {MOD} {return new Symbol(sym.MOD, yycolumn, yyline, yytext());}
    {REQUEUE} {return new Symbol(sym.REQUEUE, yycolumn, yyline, yytext());}
    {XOR} {return new Symbol(sym.XOR, yycolumn, yyline, yytext());}
	{ARROW} {return new Symbol(sym.ARROW, yycolumn, yyline, yytext());}
    {DOUBD} {return new Symbol(sym.DOUBD, yycolumn, yyline, yytext());}
    {DOUBAPS} {return new Symbol(sym.DOUBAPS, yycolumn, yyline, yytext());}
    {ASSIGN} {return new Symbol(sym.ASSIGN, yycolumn, yyline, yytext());}
    {NOTEQ} {return new Symbol(sym.NOTEQ, yycolumn, yyline, yytext());}
    // {maseq} {return new Symbol(SYM.maseq, 0, 0);}
    // {lesseq} {return new Symbol(SYM.lesseq, 0, 0);}
    {LLB} {return new Symbol(sym.LLB, yycolumn, yyline, yytext());}
    {RLB} {return new Symbol(sym.RLB, yycolumn, yyline, yytext());}
    {BOX} {return new Symbol(sym.BOX, yycolumn, yyline, yytext());}
    {LP} {return new Symbol(sym.LP, yycolumn, yyline, yytext());}
    {RP} {return new Symbol(sym.RP, yycolumn, yyline, yytext());}
    // {qm} {return new Symbol(SYM.qm, 0, 0);}
    {NS} {return new Symbol(sym.NS, yycolumn, yyline, yytext());}
    {AM} {return new Symbol(sym.AM, yycolumn, yyline, yytext());}
    {AP} {return new Symbol(sym.AP, yycolumn, yyline, yytext());}
    // {aps}  {return new Symbol(SYM.aps, 0, 0);}
    // {mas}  {return new Symbol(SYM.mas, 0, 0);}
    {COMA} {return new Symbol(sym.COMA, yycolumn, yyline, yytext());}
    // {dash}  {return new Symbol(SYM.dash, 0, 0);}
    {PUNTO} {return new Symbol(sym.PUNTO, yycolumn, yyline, yytext());}
    // {pleca}  {return new Symbol(SYM.pleca, 0, 0);}
    {DOS}  {return new Symbol(sym.DOS, yycolumn, yyline, yytext());}
    {PC}  {return new Symbol(sym.PC, yycolumn, yyline, yytext());}
    // {less} {return new Symbol(SYM.less, 0, 0);}
    // {eql}  {return new Symbol(SYM.eql, 0, 0);}
    // {mas}  {return new Symbol(SYM.mas, 0, 0);}
    {GUINB}  {return new Symbol(sym.GUINB, yycolumn, yyline, yytext());}
    {VERTICAL} {return new Symbol(sym.VERTICAL, yycolumn, yyline, yytext());}
    {LSQB} {return new Symbol(sym.LSQB, yycolumn, yyline, yytext());}
    {RSQB}  {return new Symbol(sym.RSQB, yycolumn, yyline, yytext());}
    {LCB} {return new Symbol(sym.LCB, yycolumn, yyline, yytext());}
    {RCB} {return new Symbol(sym.RCB, yycolumn, yyline, yytext());}
    {OP} {return new Symbol(sym.OP, yycolumn, yyline, yytext());}
    {OPREL} {return new Symbol(sym.OPREL, yycolumn, yyline, yytext());}
    // {idFunc} {return new Symbol(sym.IDFUNC, yycolumn, yyline, yytext());}
    /* {with} {yybegin(IMPORTS);}
    {USE} {yybegin(IMPORTS);} */
    {ID} {return new Symbol(sym.ID, yycolumn, yyline, yytext());}
    // {plus} {return new Symbol(SYM.id, 0, 0);}
    . {}
}

<STRING>{
    \" {}
    {EXP} {System.out.print(yytext());}
    . {}
}

/* <IMPORTS>{
    {pc} {yybegin(YYINITIAL);}
    . {}
} */

/* <PROC>{
    // {is} {}
    {IDPROC} {return new Symbol(sym.idProc, yycolumn, yyline, yytext());yybegin(YYINITIAL);}
    . {}
}

<ENDPROC>{
    {PC} {return new Symbol(sym.pc, yycolumn, yyline, yytext());yybegin(YYINITIAL);}
    {IDPROC} {return new Symbol(sym.idProc, yycolumn, yyline, yytext());}
    . {}
} */