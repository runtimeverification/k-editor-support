from pygments.lexer import RegexLexer, words, include
from pygments.token import Text, Comment, Keyword, Name, String, Number, Punctuation, Operator

__all__ = ["ExtKLexer"]

class ExtKLexer(RegexLexer):

    name = "ExtK"
    aliases = ["extk"]

    tokens = {
        'whitespace': [
            (r'\s+', Text.Whitespace)
        ],
        'comments': [
            (r'/\*.*?\*/', Comment.Multiline),
            (r'//.*?\n', Comment.Single),
        ],
        'keywords': [
            (words(('kmod', 'endkm', 'including', 'subsort', 'eq', 'ceq', 'load', 'when', 'requires', 'module', 'endmodule', 'imports'), suffix = r'\b'), Keyword),
            (words(('syntax', 'sort', 'op', 'rule'), suffix = r'\b'), Keyword.Declaration),
            (words(('If', 'then', 'else', 'Let', 'Do', "Return"), suffix = r'\b'), Keyword),
        ],
        'literals': [
            (r'"(\\\\|\\"|[^"])*"', String),
        ],
        'identifiers': [
            (r'[$@%.#]*[a-zA-Z_][a-zA-Z_0-9-]*', Name.Variable),
            (r'</?[$@%.#]*[a-zA-Z_][a-zA-Z_0-9-]*>', Name.Tag),
        ],
        'numbers': [
            (r'[+-]?[0-9]+', Number.Integer),
        ],
        'symbols': [
            (words(('[', ']', '{', '}', '(', ')')), Punctuation),
            (words(('BEGIN', 'END')), Keyword),
            (words((',', ';', ':', ':>', '=>', '::=', '|->', '=/=', '...')), Punctuation),
        ],
        'operators': [
            (words(('|', '=')), Operator),
        ],
        'root': [
            include('comments'),
            include('whitespace'),
            include('keywords'),
            include('literals'),
            include('numbers'),
            include('symbols'),
            include('operators'),
            include('identifiers'),
        ],
    }

