
/* JFlex specification for JCompiler */

package microjs.jcompiler.frontend.lexer;

import java_cup.runtime.*;
import java_cup.runtime.ComplexSymbolFactory.Location;
import java_cup.runtime.ComplexSymbolFactory.ComplexSymbol;
import microjs.jcompiler.frontend.parser.sym;

/**
 * This class is a simple example lexer.
 */

%%

%class Lexer
%public
%unicode
%implements java_cup.runtime.Scanner
%function next_token
%type java_cup.runtime.Symbol
%line
%column

%eofval{
  return symbol("EOF", sym.EOF);
%eofval}

%{
  private ComplexSymbolFactory symbolFactory = new ComplexSymbolFactory();
  // StringBuffer string = new StringBuffer();
    
  private Symbol symbol(String name, int type) {
    return symbolFactory.newSymbol(name, type,
             new Location(yyline+1, yycolumn +1),
	     new Location(yyline+1,yycolumn+yylength()));
  }
  private Symbol symbol(String name, int type, Object value) {
    return symbolFactory.newSymbol(name, type,
             new Location(yyline+1, yycolumn +1),
	     new Location(yyline+1,yycolumn+yylength()), value);
  }
%}

Identifier = [a-zA-Z][a-zA-Z0-9]* 

Digit = [0-9]





LineTerminator = ( \u000D\u000A
	               | [\u000A\u000B\u000C\u000D\u0085\u2028\u2029] )
    
%x COMMENTAIRE_C

 
%%


{LineTerminator} { /* ignore */ }

[ \t\f\n]+	{ /* ignore */ }

{Digit}+	{ return symbol("INT", sym.INT, Integer.parseInt(yytext())); }


var		{ return symbol("VAR", sym.VAR); }
let		{ return symbol("LET", sym.LET); }
true		{ return symbol("BOOL", sym.BOOL, true); }
false		{ return symbol("BOOL", sym.BOOL, false); }
if		{ return symbol("IF", sym.IF); }
else		{ return symbol("ELSE", sym.ELSE); }
function	{ return symbol("FUNCTION", sym.FUNCTION); }
lambda		{ return symbol("LAMBDA", sym.LAMBDA); }
return		{ return symbol("RETURN", sym.RETURN); }

while		{ return symbol("WHILE", sym.WHILE); }
do          { return symbol("DO", sym.DO); }

switch      { return symbol("SWITCH", sym.SWITCH); }
case        { return symbol("CASE", sym.CASE); }
break       { return symbol("BREAK", sym.BREAK); }

\;		{ return symbol("SEMICOL", sym.SEMICOL); }
\,		{ return symbol("COMMA", sym.COMMA); }
\=		{ return symbol("EQ", sym.EQ); }
\{		{ return symbol("LCURLY", sym.LCURLY); }
\}		{ return symbol("RCURLY", sym.RCURLY); }
\(		{ return symbol("LPAREN", sym.LPAREN); }
\)		{ return symbol("RPAREN", sym.RPAREN); }
\+		{ return symbol("PLUS", sym.PLUS); }
\-		{ return symbol("MINUS", sym.MINUS); }
\*		{ return symbol("TIMES", sym.TIMES); }
\/		{ return symbol("DIV", sym.DIV); }
\:      { return symbol("DEUXPOINTS", sym.DEUXPOINTS); }
"=="	{ return symbol("EQEQ", sym.EQEQ); }
"==="   { return symbol("EQEQEQ", sym.EQEQEQ); }
"+="    { return symbol("EQPLUS", sym.EQPLUS); }
"-="    { return symbol("EQMINUS", sym.EQMINUS); }
"*="    { return symbol("EQTIMES", sym.EQTIMES); }
"/="    { return symbol("EQDIV", sym.EQDIV); }

"<->"		{ return symbol("ECHANGE", sym.ECHANGE); }

{Identifier}	{ return symbol("IDENTIFIER", sym.IDENTIFIER, yytext()); }

\/\/.*\R	{ /* ignore */ }	/* commentaire en ligne */

"/*"                      { yybegin(COMMENTAIRE_C); } /* commentaire C */
<COMMENTAIRE_C>[^*]+      { /* ignore */ }
<COMMENTAIRE_C>\*+        { /* ignore */ }
<COMMENTAIRE_C>\**"*/"    { yybegin(YYINITIAL); }


/* error fallback */
.                         {  // very strange "bug"
                            if (yytext() == "\\u000A") { /* ignore */
                               System.err.println(
			         "WARNING: strange fallback character");
                            } else { throw new Error("Illegal character <"+
                                                      yytext()+">"); }
                                              
                          }
