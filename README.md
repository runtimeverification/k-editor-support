# A collection of editor extensions for the K Framework

These extensions are built by the community or by
Runtime Verification. At this moment they only offer limited syntax
highlighting.

Check out each directory for instructions on how to
install the extensions in your editor of choice.

## Contribute

If you want to build an extension, the most up-to-date syntax
can always be found in this [JavaCC](https://github.com/runtimeverification/k/blob/master/kernel/src/main/javacc/Outer.jj) grammar.
K is a self defined language that is parsed in two stages.

1. The **outer syntax** is described in the above-mentioned file, and it contains
modules and syntax declarations in an extended Backus-Naur-Form.
2. The **inner syntax**, or bubbles, is the content of `rules`,
`configurations` and `contexts`. This is a dynamic language built by extending
the base K language ([kast.md](https://github.com/runtimeverification/k/blob/master/k-distribution/include/kframework/builtin/kast.md))
with the builtin library ([domains.md](https://github.com/runtimeverification/k/blob/master/k-distribution/include/kframework/builtin/domains.md))
and the syntax added by the user.

## Markdown integration
Usually we expect the `.k` extension, but the K tools can also interpret
Markdown files with the help of annotated code blocks. The compiler will
strip everything except for the properly annotated blocks and consider
the result a `source.k` file. (see kompile --md-selector for more info)

~~~
```k
module TEST
```
Markdown documentation

```{.k .tag}
endmodule
```
~~~
