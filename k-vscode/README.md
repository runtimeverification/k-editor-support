# K-syntax

K framework syntax highlight for VSCode.

The official extension maintained by Runtime Verification Inc.
and comunity contributors.

### Features

- Syntax highlighting for .k files and code blocks inside markdown files

- The K framework is an open source project and you can view the source code
here: https://github.com/runtimeverification/k

- Integration with the `klsp` that provides limited support for code completion
  and error reporting for syntax errors. Requires K v5.5.60 or higher.

- To enable `klsp` in Markdown you have to manually select the
  Language Mode in the bottom right. To make this the default behavior
  you can modify the Workspace Settings by adding
  `"files.associations": { "*.md": "kframework" }` in your `package.json`.

### Contribute

- Download this extension from https://github.com/runtimeverification/k-editor-support

- Import the `k-vscode` directory in VSCode

- Type `npm install` in the Terminal window to fetch the Node.js dependencies.

- Launch with `F5`

- For more details read `vsc-extension-quickstart.md`
