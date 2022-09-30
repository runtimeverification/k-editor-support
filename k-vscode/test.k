requires "abc.k"

module SYMBOLIC
    imports INT

    syntax Symbolic ::= "#nondetInt" [anywhere]
    rule #nondetInt => ?_I:Int
endmodule

module TEST
    imports BOOL
    imports INT
    imports MAP
    imports SYMBOLIC
    imports ID

    syntax Decl ::= "var" Id ";"
    syntax MapLookup ::= "lookup" Id ";"
    syntax MapTouch ::= "map" "[" Id "]" ";"

    syntax Stmt ::= Decl | MapLookup | MapTouch

    syntax Src ::= List{Stmt, ""}

    configuration <T color="123" foo="123" bar="123">
        <k color="red"> $PGM:Src </k>
        <vars> .Map </vars>
        <map> .Map </map>
    </T>

    rule <k> S:Stmt SS:Src => S ~> SS ...</k>
    rule <k> .Src => . ...</k>
    
    rule <k> var X:Id ; => #nondetInt ~> var X ; ...</k>
    rule <k> I:Int ~> var X:Id ; => . ...</k>
        <vars> M => M [ X <- I ] </vars>

    syntax KItem ::= "freezer_map_touch" "(" ")"
    rule <k> map [ X:Id ] ; => X ~> freezer_map_touch() ...</k>
        requires true
    
    rule <k> X:Id ~> freezer_map_touch() => . ...</k>
        <vars>... X |-> I ...</vars>
        <map> M:Map => M [ I <- 0 ] </map>
    
    rule <k> lookup X:Id ; => I in_keys(M) ...</k>
        <vars>... X |-> I ...</vars>
        <map> M:Map </map>

endmodule