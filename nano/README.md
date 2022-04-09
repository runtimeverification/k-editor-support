Basic K syntax highlighter for nano.

To install, checkout the ``k-editor-support`` repo to some ``<path>`` and then add ``include "<path>/k-editor-support/nano/k.nanorc"`` to the file ``~/.nanorc`` (create it if it doesn't exist).

Note: there is a mechanism to turn off highlighting for certain lines of code. To do so, add the following comment to mark the start of the portion of code for which you would like to turn off highlighting: ``//<K-IGNORE-NANO-HIGHLIGHT>``. And then insert this comment after the last line for which you want to turn off highlighting: ``//</K-IGNORE-NANO-HIGHLIGHT>$``.  Both comments must be the only content on their respective lines e.g. you can't add any trailing or leading whitespace. The reason for including this ability to turn off highlighting is that syntax higlighting via regexes is limited, and sometimes can get annoying if things are highlighted the wrong way. But most of the time, the regexes work OK, so it would be a shame to have to turn it off altogether just because of a few annoying corner cases.


