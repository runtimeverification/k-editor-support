# kakoune syntax highlighting for k (https://github.com/kframework/k)
# ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾

# Detection
# ‾‾‾‾‾‾‾‾‾

hook global BufCreate .*[.](k) %{
    set-option buffer filetype k
}

# Initialization
# ‾‾‾‾‾‾‾‾‾‾‾‾‾‾

hook global WinSetOption filetype=k %{
    require-module k

    set-option window static_words %opt{k_static_words}

    hook window InsertChar \n -group k-insert k-insert-on-new-line
    hook window InsertChar \n -group k-indent k-indent-on-new-line
    # cleanup trailing whitespaces on current line insert end
    hook window ModeChange pop:insert:.* -group k-trim-indent %{ try %{ execute-keys -draft <semicolon> <a-x> s ^\h+$ <ret> d } }
    hook -once -always window WinSetOption filetype=.* %{ remove-hooks window k-.+ }
}

hook -group k-highlight global WinSetOption filetype=k %{
    add-highlighter window/k ref k
    hook -once -always window WinSetOption filetype=.* %{ remove-highlighter window/k }
}

provide-module k %§

# Highlighters & Completion
# ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾

add-highlighter shared/k               regions

# K strings
add-highlighter shared/k/double_string region               '"'   (?<!\\)(\\\\)*" fill string

# K comments
add-highlighter shared/k/line_comment  region               '//'  '$'             fill comment
add-highlighter shared/k/comment       region               '/\*' '\*/'           fill comment

add-highlighter shared/k/code          default-region group

# Integer format
add-highlighter shared/k/code/ regex '\b([1-9]\d*|0)l?\b' 0:value
# Float formats
add-highlighter shared/k/code/ regex '\b\d+[eE][+-]?\d+\b' 0:value
add-highlighter shared/k/code/ regex '(\b\d+)?\.\d+\b' 0:value
add-highlighter shared/k/code/ regex '\b\d+\.' 0:value

add-highlighter shared/k/code/ regex (?<=[\w\s\d\)\]'"_])(\[\]|#isConcrete|#isVariable|#collectOdd|#location) 0:operator
#add-highlighter shared/k/code/ regex (?<=[\w\s\d\)\]'"_])(\+Int|\*Int|-Int|/Int|>Int|<Int) 0:operator
add-highlighter shared/k/code/ regex (?<=[\s])(\.\.\.|==|=/=|:=|:/=) 0:builtin   #space before
add-highlighter shared/k/code/ regex (?<=[\s])((::=|=>|~>|\||>)\s)   0:builtin   #space before and after
add-highlighter shared/k/code/ regex (:|::|:>)                       0:builtin   #no space restrictions
add-highlighter shared/k/code/ regex ^\h*(?:import|imports)\h+(\S+) 1:module
add-highlighter shared/k/code/ regex (?<=[\w\s\d'"_])((#if|#then|#else|#fi|#Ceil|#Equals|#And|#Or|#as|#fun)\s) 0:keyword
add-highlighter shared/k/code/ regex (?<=[\w\s\d'"_])(#Layout) 0:type

#rule labels?

# K cells
add-highlighter shared/k/tag           region               '(?!<Rat)((?!<String)((?!<Float)((?!<Int)((?=<[\w/])<))))'  (?<=[\w/"'])>  regions
add-highlighter shared/k/tag/          region               '"' (?<!\\)(\\\\)*"        fill string
add-highlighter shared/k/tag/          region               "'" "'"                    fill string

add-highlighter shared/k/tag/base      default-region group
add-highlighter shared/k/tag/base/ regex \b([a-zA-Z0-9_-]+)=? 1:attribute
add-highlighter shared/k/tag/base/ regex </?(\w+) 1:builtin



evaluate-commands %sh{
    # Grammar
    values="true false Infinity NaN undef"
    meta="imports import"

    # attributes
    attributes="strict seqstrict klabel symbol left right function functional macro kast"
    attributes="${attributes} assoc comm unit idem element index format latex owise hook"
    attributes="${attributes} impure symbolic kore prefer anywhere simplification smtlib"
    attributes="${attributes} token boolOperation prec concrete freshGenerator smt-hook"
    attributes="${attributes} avoid equalEqualK notEqualEqualK bracket memo unused notInRules"
    attributes="${attributes} autoReject heat cool result hybrid alias macro-rec alias-rec"
    attributes="${attributes} smt-lemma lemma trusted unboundVariables priority all-path"
    attributes="${attributes} one-path locations color colors structural not-lr1"
    
    # Keyword list
    keywords="configuration module endmodule syntax rule require requires context"
    keywords="${keywords} syntax\ priorities syntax\ left syntax\ right syntax\ non-assoc"
    keywords="${keywords} context\ alias claim"

    types="Bool String Bytes Float Int List Set Map Bag Array K KItem KBott StringBuffer KResult"

    functions="andBool andThenBool orBool orElseBool notBool xorBool impliesBool ListItem SetItem HOLE"

    join() { sep=$2; eval set -- $1; IFS="$sep"; echo "$*"; }

    # Add the language's grammar to the static completion list
    printf %s\\n "declare-option str-list k_static_words $(join "${values} ${meta} ${attributes} ${methods} ${keywords} ${types} ${functions}" ' ')"

    # Highlight keywords
    printf %s "
        add-highlighter shared/k/code/ regex '\b($(join "${values}" '|'))\b'     0:value
        add-highlighter shared/k/code/ regex '\b($(join "${meta}" '|'))\b'       0:meta
        add-highlighter shared/k/code/ regex '\b($(join "${attributes}" '|'))\b' 0:attribute
        add-highlighter shared/k/code/ regex '\b($(join "${keywords}" '|'))\b'   0:keyword
        add-highlighter shared/k/code/ regex '\b($(join "${functions}" '|'))\b'  0:operator
        add-highlighter shared/k/code/ regex '\b($(join "${types}" '|'))\b'      0:type
        add-highlighter shared/k/code/ regex '^\h*(@[\w_.]+))'                   1:attribute
    "
}


# Commands
# ‾‾‾‾‾‾‾‾

define-command -hidden k-insert-on-new-line %{
    evaluate-commands -draft -itersel %{
        # copy '//' comment prefix and following white spaces
        try %{ execute-keys -draft k <a-x> s ^\h*//\h* <ret> y jgh P }
    }
}
define-command -hidden k-indent-on-new-line %<
    evaluate-commands -draft -itersel %<
        # preserve previous line indent
        try %{ execute-keys -draft <semicolon> K <a-&> }
        # cleanup trailing whitespaces from previous line
        try %{ execute-keys -draft k <a-x> s \h+$ <ret> d }
        # indent after line ending with :
        #try %{ execute-keys -draft <space> k <a-x> <a-k> :$ <ret> j <a-gt> }
        # deindent closing brace/bracket when after cursor (for arrays and dictionaries)
        try %< execute-keys -draft <a-x> <a-k> ^\h*[}\]] <ret> gh / [}\]] <ret> m <a-S> 1<a-&> >
    >
>

§
