import java_cup.runtime.*;
import java.util.*;
%%

%unicode
%class Ada95
%line
%column
%int
%cup
%ignorecase

%{
  private String currentText="";
  public static LinkedList<String> msgErrores = new LinkedList<String>();
  public String getCurrentText(){return currentText;}
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

DATATYPES = Integer|Boolean|Float|Double|String

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

SALTO = \n|\r|\t|\f|" "

COMMENT = "-""-".*(\n|\r)

EXP = \".*\" //[a-zA-Z]+
EXPINC = \".*
%state STRING
%state IMPORTS
%state PROC
%state ENDPROC
%state COMMENT
%%
<YYINITIAL>{
    {DATATYPES} {currentText=yytext(); return new Symbol(sym.DATATYPES, yycolumn, yyline, yytext());}
    {SALTO}     {  }
    {NUM}       {currentText=yytext(); return new Symbol(sym.NUM, yycolumn, yyline, yytext());}
    {COMMENT} {/* currentText=yytext(); return new Symbol(sym.COMMENT, yycolumn, yyline, yytext()); */}
    {EXP} {currentText=yytext(); return new Symbol(sym.EXP, yycolumn, yyline, yytext());}
    {EXPINC} {msgErrores.add("Error Lexico: en linea " + (yyline + 1) + ", columna " + (yycolumn + 1) + "; no se cerro la cadena'" +yytext()+"'");}
    {ABORT} {currentText=yytext(); return new Symbol(sym.ABORT, yycolumn, yyline, yytext());}
    {ELSE} 	{currentText=yytext(); return new Symbol(sym.ELSE, yycolumn, yyline, yytext());}
    {TRUE} {currentText=yytext(); return new Symbol(sym.TRUE, yycolumn, yyline, yytext());}
    {FALSE} {currentText=yytext(); return new Symbol(sym.FALSE, yycolumn, yyline, yytext());}
    {NEW} {currentText=yytext(); return new Symbol(sym.NEW, yycolumn, yyline, yytext());}
    {RETURN} {currentText=yytext(); return new Symbol(sym.RETURN, yycolumn, yyline, yytext());}
    {ABS} {currentText=yytext(); return new Symbol(sym.ABS, yycolumn, yyline, yytext());}
    {ELSIF} {currentText=yytext(); return new Symbol(sym.ELSIF, yycolumn, yyline, yytext());}
    {NOT} {currentText=yytext(); return new Symbol(sym.NOT, yycolumn, yyline, yytext());}
    {REVERSE} {currentText=yytext(); return new Symbol(sym.REVERSE, yycolumn, yyline, yytext());}
    {ABSTRACT} {currentText=yytext(); return new Symbol(sym.ABSTRACT, yycolumn, yyline, yytext());}
    {END} {currentText=yytext(); return new Symbol(sym.END, yycolumn, yyline, yytext());}
    {NULL} {currentText=yytext(); return new Symbol(sym.NULL, yycolumn, yyline, yytext());}
    {ACCEPT} {currentText=yytext(); return new Symbol(sym.ACCEPT, yycolumn, yyline, yytext());}
    {ENTRY} {currentText=yytext(); return new Symbol(sym.ENTRY, yycolumn, yyline, yytext());}
    {SELECT} 	{currentText=yytext(); return new Symbol(sym.SELECT, yycolumn, yyline, yytext());}
    {ACCESS} {currentText=yytext(); return new Symbol(sym.ACCESS, yycolumn, yyline, yytext());}
    {EXCEPTION} {currentText=yytext(); return new Symbol(sym.EXCEPTION, yycolumn, yyline, yytext());}
    {OF} {currentText=yytext(); return new Symbol(sym.OF, yycolumn, yyline, yytext());}
    {SEPARATE} {currentText=yytext(); return new Symbol(sym.SEPARATE, yycolumn, yyline, yytext());}
    {ALIASED} {currentText=yytext(); return new Symbol(sym.ALIASED, yycolumn, yyline, yytext());}
    {EXIT} 	{currentText=yytext(); return new Symbol(sym.EXIT, yycolumn, yyline, yytext());}
    {OR} {currentText=yytext(); return new Symbol(sym.OR, yycolumn, yyline, yytext());}
    {ALL} {currentText=yytext(); return new Symbol(sym.ALL, yycolumn, yyline, yytext());}
    {OTHERS} {currentText=yytext(); return new Symbol(sym.OTHERS, yycolumn, yyline, yytext());}
    {SUBTYPE} {currentText=yytext(); return new Symbol(sym.SUBTYPE, yycolumn, yyline, yytext());}
    {AND} {currentText=yytext(); return new Symbol(sym.AND, yycolumn, yyline, yytext());}
    {FOR} {currentText=yytext(); return new Symbol(sym.FOR, yycolumn, yyline, yytext());}
    {OUT} {currentText=yytext(); return new Symbol(sym.OUT, yycolumn, yyline, yytext());}
    {ARRAY} {currentText=yytext(); return new Symbol(sym.ARRAY, yycolumn, yyline, yytext());}
    // {function} {currentText=yytext(); return new Symbol(SYM.function, 0, 0);}
    {AT} {currentText=yytext(); return new Symbol(sym.AT, yycolumn, yyline, yytext());}
    {TAGGED} {currentText=yytext(); return new Symbol(sym.TAGGED, yycolumn, yyline, yytext());}
    {GENERIC} {currentText=yytext(); return new Symbol(sym.GENERIC, yycolumn, yyline, yytext());}
    /* {PACKAGEA} {currentText=yytext(); return new Symbol(sym.PACKAGEA, yycolumn, yyline, yytext());} */
    {TASK} {currentText=yytext(); return new Symbol(sym.TASK, yycolumn, yyline, yytext());}
    {BEGIN} {currentText=yytext(); return new Symbol(sym.BEGIN, yycolumn, yyline, yytext());}
    {GOTO} {currentText=yytext(); return new Symbol(sym.GOTO, yycolumn, yyline, yytext());}
    {PRAGMA} 	{currentText=yytext(); return new Symbol(sym.PRAGMA, yycolumn, yyline, yytext());}
    {TERMINATE} {currentText=yytext(); return new Symbol(sym.TERMINATE, yycolumn, yyline, yytext());}
    {BODY} 	{currentText=yytext(); return new Symbol(sym.BODY, yycolumn, yyline, yytext());}
    {PRIVATE}	{currentText=yytext(); return new Symbol(sym.PRIVATE, yycolumn, yyline, yytext());}
    {THEN} {currentText=yytext(); return new Symbol(sym.THEN, yycolumn, yyline, yytext());}
    {IF} {currentText=yytext(); return new Symbol(sym.IF, yycolumn, yyline, yytext());}
    {PROCEDURE} {currentText=yytext(); return new Symbol(sym.PROCEDURE, yycolumn, yyline, yytext());}
    {FUNCTION} {currentText=yytext(); return new Symbol(sym.FUNCTION, yycolumn, yyline, yytext());}
    {TYPE} {currentText=yytext(); return new Symbol(sym.TYPE, yycolumn, yyline, yytext());}
    {CASE} 	{currentText=yytext(); return new Symbol(sym.CASE, yycolumn, yyline, yytext());}
    {IN} {currentText=yytext(); return new Symbol(sym.IN, yycolumn, yyline, yytext());}
    {PROTECTED} {currentText=yytext(); return new Symbol(sym.PROTECTED, yycolumn, yyline, yytext());}
    {CONSTANT} {currentText=yytext(); return new Symbol(sym.CONSTANT, yycolumn, yyline, yytext());}
    {UNTIL}  {currentText=yytext(); return new Symbol(sym.UNTIL, yycolumn, yyline, yytext());}
    {IS} {currentText=yytext(); return new Symbol(sym.IS, yycolumn, yyline, yytext());}
    {RAISE} {currentText=yytext(); return new Symbol(sym.RAISE, yycolumn, yyline, yytext());}
    // {use} {currentText=yytext(); return new Symbol(SYM.use, 0, 0);}
    {DECLARE} {currentText=yytext(); return new Symbol(sym.DECLARE, yycolumn, yyline, yytext());}
    {RANGE} {currentText=yytext(); return new Symbol(sym.RANGE, yycolumn, yyline, yytext());}
    {DELAY} {currentText=yytext(); return new Symbol(sym.DELAY, yycolumn, yyline, yytext());}
    {LIMITED} {currentText=yytext(); return new Symbol(sym.LIMITED, yycolumn, yyline, yytext());}
    {RECORD} {currentText=yytext(); return new Symbol(sym.RECORD, yycolumn, yyline, yytext());}
    {WHEN} {currentText=yytext(); return new Symbol(sym.WHEN, yycolumn, yyline, yytext());}
    {DELTA} {currentText=yytext(); return new Symbol(sym.DELTA, yycolumn, yyline, yytext());}
    {LOOP} {currentText=yytext(); return new Symbol(sym.LOOP, yycolumn, yyline, yytext());}
    {REM} {currentText=yytext(); return new Symbol(sym.REM, yycolumn, yyline, yytext());}
    {WHILE} {currentText=yytext(); return new Symbol(sym.WHILE, yycolumn, yyline, yytext());}
    {DIGITS} {currentText=yytext(); return new Symbol(sym.DIGITS, yycolumn, yyline, yytext());}
    {RENAMES} {currentText=yytext(); return new Symbol(sym.RENAMES, yycolumn, yyline, yytext());}
    // {with} {currentText=yytext(); return new Symbol(SYM.with, 0, 0);}
    {DO} {currentText=yytext(); return new Symbol(sym.DO, yycolumn, yyline, yytext());}
    {GET} {currentText=yytext(); return new Symbol(sym.GET, yycolumn, yyline, yytext());}
    {PUT} {currentText=yytext(); return new Symbol(sym.PUT, yycolumn, yyline, yytext());}
    {MOD} {currentText=yytext(); return new Symbol(sym.MOD, yycolumn, yyline, yytext());}
    {REQUEUE} {currentText=yytext(); return new Symbol(sym.REQUEUE, yycolumn, yyline, yytext());}
    {XOR} {currentText=yytext(); return new Symbol(sym.XOR, yycolumn, yyline, yytext());}
	{ARROW} {currentText=yytext(); return new Symbol(sym.ARROW, yycolumn, yyline, yytext());}
    {DOUBD} {currentText=yytext(); return new Symbol(sym.DOUBD, yycolumn, yyline, yytext());}
    {DOUBAPS} {currentText=yytext(); return new Symbol(sym.DOUBAPS, yycolumn, yyline, yytext());}
    {ASSIGN} {currentText=yytext(); return new Symbol(sym.ASSIGN, yycolumn, yyline, yytext());}
    {NOTEQ} {currentText=yytext(); return new Symbol(sym.NOTEQ, yycolumn, yyline, yytext());}
    // {maseq} {currentText=yytext(); return new Symbol(SYM.maseq, 0, 0);}
    // {lesseq} {currentText=yytext(); return new Symbol(SYM.lesseq, 0, 0);}
    {LLB} {currentText=yytext(); return new Symbol(sym.LLB, yycolumn, yyline, yytext());}
    {RLB} {currentText=yytext(); return new Symbol(sym.RLB, yycolumn, yyline, yytext());}
    {BOX} {currentText=yytext(); return new Symbol(sym.BOX, yycolumn, yyline, yytext());}
    {LP} {currentText=yytext(); return new Symbol(sym.LP, yycolumn, yyline, yytext());}
    {RP} {currentText=yytext(); return new Symbol(sym.RP, yycolumn, yyline, yytext());}
    // {qm} {currentText=yytext(); return new Symbol(SYM.qm, 0, 0);}
    {NS} {currentText=yytext(); return new Symbol(sym.NS, yycolumn, yyline, yytext());}
    {AM} {currentText=yytext(); return new Symbol(sym.AM, yycolumn, yyline, yytext());}
    {AP} {currentText=yytext(); return new Symbol(sym.AP, yycolumn, yyline, yytext());}
    // {aps}  {currentText=yytext(); return new Symbol(SYM.aps, 0, 0);}
    // {mas}  {currentText=yytext(); return new Symbol(SYM.mas, 0, 0);}
    {COMA} {currentText=yytext(); return new Symbol(sym.COMA, yycolumn, yyline, yytext());}
    // {dash}  {currentText=yytext(); return new Symbol(SYM.dash, 0, 0);}
    {PUNTO} {currentText=yytext(); return new Symbol(sym.PUNTO, yycolumn, yyline, yytext());}
    // {pleca}  {currentText=yytext(); return new Symbol(SYM.pleca, 0, 0);}
    {DOS}  {currentText=yytext(); return new Symbol(sym.DOS, yycolumn, yyline, yytext());}
    {PC}  {currentText=yytext(); return new Symbol(sym.PC, yycolumn, yyline, yytext());}
    // {less} {currentText=yytext(); return new Symbol(SYM.less, 0, 0);}
    // {eql}  {currentText=yytext(); return new Symbol(SYM.eql, 0, 0);}
    // {mas}  {currentText=yytext(); return new Symbol(SYM.mas, 0, 0);}
    {GUINB}  {currentText=yytext(); return new Symbol(sym.GUINB, yycolumn, yyline, yytext());}
    {VERTICAL} {currentText=yytext(); return new Symbol(sym.VERTICAL, yycolumn, yyline, yytext());}
    {LSQB} {currentText=yytext(); return new Symbol(sym.LSQB, yycolumn, yyline, yytext());}
    {RSQB}  {currentText=yytext(); return new Symbol(sym.RSQB, yycolumn, yyline, yytext());}
    {LCB} {currentText=yytext(); return new Symbol(sym.LCB, yycolumn, yyline, yytext());}
    {RCB} {currentText=yytext(); return new Symbol(sym.RCB, yycolumn, yyline, yytext());}
    {OP} {currentText=yytext(); return new Symbol(sym.OP, yycolumn, yyline, yytext());}
    {OPREL} {currentText=yytext(); return new Symbol(sym.OPREL, yycolumn, yyline, yytext());}
    // {idFunc} {currentText=yytext(); return new Symbol(sym.IDFUNC, yycolumn, yyline, yytext());}
    /* {with} {yybegin(IMPORTS);}
    {USE} {yybegin(IMPORTS);} */
    {ID} {currentText=yytext(); return new Symbol(sym.ID, yycolumn, yyline, yytext());}
    // {plus} {currentText=yytext(); return new Symbol(SYM.id, 0, 0);}
    . {msgErrores.add("Error Lexico: en linea " + (yyline + 1) + ", columna " + (yycolumn + 1) + "; no se reconoce: '" +yytext()+"'");}
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
    {IDPROC} {currentText=yytext(); return new Symbol(sym.idProc, yycolumn, yyline, yytext());yybegin(YYINITIAL);}
    . {}
}

<ENDPROC>{
    {PC} {currentText=yytext(); return new Symbol(sym.pc, yycolumn, yyline, yytext());yybegin(YYINITIAL);}
    {IDPROC} {currentText=yytext(); return new Symbol(sym.idProc, yycolumn, yyline, yytext());}
    . {}
} */