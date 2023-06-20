# Change Log

All notable changes to the "K framework" extension will be documented in this file.

## [Unreleased]

- Full error reporting inside the editor

## [0.2.4] - 2023-6-20

- Automatically associate Markdown files with the K extension. To get the old
  behavior you can manually select the extension mode in the bottom right, or
  disable this extension.

## [0.2.3] - 2023-03-08

- Small updates to the readme, and make the logo blue.

- Automate the release process using GitHub Actions.

## [0.2.2] - 2023-02-27

- Goto definition for required files, imports and terms inside rules.

- Find occurances for modules and productions.

- Require K v5.5.103 or higher.

- In editor error reporting for syntax errors

- Selection range. Alt+Shift+LRArrows increases the selection based on the AST.

- Added limited support for workspace mode. Find the first `cache.bin` file in the
  workspace directory and use the parsing caches for rules.

## [0.1.2] - 2023-01-30

- Rename extension name from RuntimeVerification.k-syntax to RuntimeVerification.k-vscode
  to better encapsulate the scope of the project.

- Require K v5.5.73 or higher. The extension is still going to work but the klsp adds more functionality, like:
  - Goto definition for imported modules and required files
  - Contextual completion for keywords, imported modules and syntax with tabstops.

## [0.1.1] - 2023-01-23

- Added VSCode option to set the path to the `klsp` executable.

## [0.1.0] - 2023-01-13

- Added support for klsp. Requires the [K framework](https://github.com/runtimeverification/k) v5.5.60 or higher.
  This brings realtime code completion and outer parsing syntax error reporting in the editor.

## [0.0.3] - 2022-12-16

- Improved syntax highlighting for .k files

- Added syntax highlighting for annotated K code embedded in Markdown code blocks.
  To activate the extension, select the Language Mode in the bottom right.

- Updated readmes and licensing information

## [0.0.2] - 2022-12-16

- Initial release
