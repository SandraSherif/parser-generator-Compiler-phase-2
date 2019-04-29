# parser
Compiler phase 2
Projects steps: 
1- reading grammar file and specify if the tokens are terminals or non terminls (DONE)

2-Compute first of each terminal (DONE)

3- compute follow of each terminal

4-check if grammar is LL(1) or not, in case its not LL(1) an error message should appears and the program ends

5-start constructing the parsing table

6-Deal with panic mode error recovery in left mmost derivation for certain test I/P

7-allow predictive parser (recursive descent parser)

Bonus:

8-apply left factoring and left recursion on the grammar before parsing step


Notes:
-you will fing attached 3 test grammars, first 2 for easy testing so you can solve it manually to check the output is true in certain step,
that's for the first and second files, the third file is the required grammar in the PDF.

-All what you need to work on any of the above steps of the project are the (terminals and non terminal) arraylists,in addition to the cfg
hashmap which contains all the grammar.

**** VI: we will need the lexical analyzer output to test on, also its noticed in the pdf to make both 2 phases workk togther at the end
