package codigo;
import static codigo.Tokens.*;
%%
%class Lexer
%type Tokens
L=[a-zA-Z_]+
D=[0-9]+
Comentario = "#"
espacio=[ ,\t,\r,\n]+
Linea= " "
ND=[!,:,.,...]+
igual= "="
signos=[*,+,-,/]+
comillas = "\""
%{
    public String lexeme;
%}
%%
PROGRAMA |
FINPROG |
SI |
ENTONCES |
SINO |
FINSI |
REPITE |
VECES |
FINREP |
IMPRIME |
LEE {lexeme=yytext(); return Reservadas;}
{espacio} {/*Ignore*/}
"//".* {/*Ignore*/}
">" |
"<" |
"==" {lexeme=yytext(); return OperadorRelacional;}
{espacio} {/*Ignore*/}
"//".* {/*Ignore*/}
{signos} {lexeme=yytext(); return OperadorAritmetico;}
{igual} {lexeme=yytext(); return Asignacion;}
{L}({L}|{D})* {lexeme=yytext(); return Identificador;}
{Comentario}({L}|{D}|{Linea}|{ND}|{igual}|{signos})* {lexeme=yytext(); return Comentario;}
("(-"{D}+")")|{D}+ {lexeme=yytext(); return Numero;}
{comillas}({L}|{D}|{Linea}|{ND}|{igual}|{signos})* {comillas} {lexeme=yytext(); return Texto;}
 . {return ERROR;}
