from pygments.lexer import RegexLexer, words, include, bygroups
from pygments.token import Text, Comment, Keyword, Name, String, Number, Punctuation, Operator, Generic

__all__ = ["KLexer"]


class KLexer(RegexLexer):

    name = "K"
    aliases = ["k"]

    tokens = {
        'whitespace': [
            (r'\s+', Text.Whitespace)
        ],
        'comments': [
            (r'/\*.*?\*/', Comment.Multiline),
            (r'//.*?\n', Comment.Single),
        ],
        'keywords': [
            (words(
                ('endmodule', 'syntax', 'rule', 'configuration', 'claim')), Keyword.Reserved),
            (r'(require)(\s+)(".+")', Keyword.Pseudo),
        ],
        'literals': [
            (r'"(\\\\|\\"|[^"])*"', String),
        ],
        'identifiers': [
            (r'(module|imports)(\s+)([A-Z][-A-Z]*)$', bygroups(Keyword.Reserved,
                                                               Text, Name.Namespace)),
            (r'[$@%.#]*[a-zA-Z_][a-zA-Z_0-9]*', Name.Variable),
            (r'</?[$@%.#]*[a-zA-Z_][a-zA-Z_0-9]*>', Name.Function),
        ],
        'numbers': [
            (r'[+-]?[0-9]+', Number.Integer),
        ],
        'symbols': [
            (words(('{', '}', '(', ')')), Punctuation),
            (words(('BEGIN', 'END')), Keyword),
            (words((',', ';', ':', ':>', '=>', '::=', '|->', '=/=', '...')), Punctuation),
        ],
        'other': [
            (r'\[.*\]$', Name.Attribute),
            ('.', Generic),
        ],
        'root': [
            include('comments'),
            include('whitespace'),
            include('keywords'),
            include('identifiers'),
            include('literals'),
            include('numbers'),
            include('symbols'),
            include('other'),
        ],
    }
