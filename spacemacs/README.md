K Spacemacs Layer
=================

This layer provides syntax highlighting for the K framework.
It includes support for highlighting code blocks within Markdown files.

Key Bindings and Commands
=========================

| Key                | Function                       |
|--------------------|--------------------------------|
| <kbd>SPC m c</kbd> | Compile with a custom command. |

Installation
============

```sh
git clone https://github.com/k-editor-support/
cd k-spacemacs-layer 
mv k-editor-support/spacemacs/k-framework $HOME/.emacs.d/private/local
```

Add `k-framework` to the list `dotspacemacs-configuration-layers` in your spacemacs config.

Customization
=============

You can set variables for customizing the layer in.
In the `dotspacemacs-configuration-layers` list, you can declare custom variables as follows.

```elisp
(k-framework :variables k-custom-word-highlights '("andBool" "orBool" "notBool") k-custom-highlights-regex "<-\\|\\|->")
```

| Variable                    | Use                                                       |
|-----------------------------|-----------------------------------------------------------|
| `k-custom-word-highlights`  | Any extra words you want highlighted.                     |
| `k-custom-highlights-regex` | For more fine-grained control of your custom highlights.  |

Updating
========

This tutorial is used as reference for updating the syntax highlighting: <http://ergoemacs.org/emacs/elisp_syntax_coloring.html>
