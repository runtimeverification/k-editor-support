
if exists('b:did_ftplugin')
  finish
endif
let b:did_ftplugin = 1

if exists('b:undo_ftplugin')
  let b:undo_ftplugin .= '|'
else
  let b:undo_ftplugin = ''
endif

let &l:include = '^\s*requires\s\+"\zs\f\+\ze"'
setl iskeyword+=-

let &l:define  = '^\s*module'
let &l:define .= '\|^\s*syntax[^=]*=\s*\ze\i\+\s*([^\[]*\[.*function'
let &l:define .= '\|^\s*syntax'
setl isident+=-

let b:undo_ftplugin .= 'setl inc< def< isk< isi<'
