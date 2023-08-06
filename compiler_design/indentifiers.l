%{
#include <stdio.h>
int keywords = 0;
%}

%%
"int"|"if"|"while"|"for"|"then"|"else"|"double"|"float"|"char"|"const"|"return" {
    printf("token: %s\n",yytext);
    printf("keyword: %s\n",yytext);
    }
[a-z]+[a-zA-Z0-9]* {
    printf("token: %s\n",yytext);
    printf("indentifier: %s\n",yytext);
    }
"("|")"|"{"|"}"|";"|";"|"=" {
    printf("token: %s\n",yytext);
}
"="|[0-9]+|[0-9]+.[0-9]+|"'"[a-zA-Z0-9]"'" {    
    printf("token: %s\n",yytext);
    printf("literals: %s\n",yytext);
}
.  {printf("");};      
%%

int yywrap(){
    return 0;
}

int main() {
    yylex();
    return 0;
}
