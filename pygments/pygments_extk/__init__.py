from pygments.lexer import RegexLexer, words, include
from pygments.token import Text, Comment, Keyword, Name, String, Number, Punctuation

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
            (words(('kmod', 'endkm', 'including', '::=', '|', 'subsort', 'rule', 'eq', 'ceq', 'load'), suffix = r'\b'), Keyword),
            (words(('syntax', 'sort', 'op'), suffix = r'\b'), Keyword.Declaration),
        ],
        'literals': [
            (r'"(\\\\|\\"|[^"])*"', String),
        ],
        'identifiers': [
            (r'[a-zA-Z_][a-zA-Z_0-9]*', Name.Variable),
        ],
        'numbers': [
            (r'[+-]?[0-9]+', Number.Integer),
        ],
        'root': [
            include('whitespace'),
            include('comments'),
            include('keywords'),
            include('literals'),
            include('identifiers'),
            include('numbers'),
        ],
    }

