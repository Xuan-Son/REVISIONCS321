
import java_cup.runtime.*;


%%
%public
%class Scanner
%implements SimpleCSym

%line
%column

%cup
%cupdebug

//%state COMMENT

%{

    int _yyline, _yycolumn;
     
    private Symbol symbol(int type) {
       return new MySymbol(type, yyline+1, yycolumn+1, yytext());
    }

    private Symbol symbol(int type, Object value) {
      return new MySymbol(type, yyline+1, yycolumn+1, value);
    }
    
    public String getTokName(int token) {
      return getTokenName(token);
    }
    
%}

/* main character classes */

LineTerminator = \r|\n|\r\n

WhiteSpace = {LineTerminator} | [ \t\f]

IntegerLiteral = (0 | [1-9][0-9]*)


%%

<YYINITIAL> {

	
	"(" { return symbol(LPAREN); }
	
	")" { return symbol(RPAREN); }	

	";" { return symbol(SEMICOLON); }   
	
	"+" { return symbol(PLUS); }
	
	"-" { return symbol(MINUS); }
	
	"*" { return symbol(TIMES); }
	 
	"/" { return symbol(DIVIDE); }
	
	"." { return symbol(PERIOD); }
	
	"|" { return symbol(ABS); }

    {IntegerLiteral} {
        int val;
        try {
            val = (new Integer(yytext())).intValue();
        } catch (NumberFormatException e) {
            Errors.warn(yyline+1, yycolumn+1, "LEXICAL WARNING: integer literal too large; using max value");
            val = Integer.MAX_VALUE;
        } 
        return symbol(INTLITERAL, new Integer(val)); 
    }
  
    /* whitespace */
    {WhiteSpace}                   { /* ignore */ }

    .   { Errors.fatal(yyline+1, yycolumn+1, "LEXICAL ERROR: Illegal character \"" + yytext()+ "\""); 
        System.exit(-1); }           

    <<EOF>>                          { return symbol(EOF); }
}
