#!/usr/bin/python
from setuptools import setup, find_packages

setup(
    name='pygments-extk',
    description='Pygments lexer for K.',
    packages=find_packages(),
    install_requires=['pygments >= 1.4'],
    entry_points='''[pygments.lexers]
                    extk=pygments_extk:ExtKLexer''',
    version=0.1,
)

