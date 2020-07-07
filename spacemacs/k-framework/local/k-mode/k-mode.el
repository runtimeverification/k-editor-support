;;; k-mode.el -- Emacs mode for the K Framework

;; Usage: add the below to your .emacs file:
;;     (setq load-path (cons "path/to/this/file" load-path))
;;     (load-library "k-mode")
;;     (add-to-list 'auto-mode-alist '("\\.k$" . k-mode)) ;; to launch k-mode for .k files
;; If you want to enable inline syntax highlighting in Markdown:
;;     (use-package markdown-mode)
;;     (push '("k" . k-mode) markdown-code-lang-modes) ;; Use C-c C-x C-f to turn on highlighting on and off.

;; Currently has syntax highlighting for:
;;  - keywords
;;  - cells and rewrites
;;  - comments
;;  - declarations (e.g. ops, syntax, etc)
;;  - quoted identifiers (e.g. for terminals in the syntax)
;;  - annotations
;;  - matching-logic symbols
;;  - custom highlights through user-controlled variables
;; Also has a menu and compilation through C-c C-c

;; Authors: Michael Ilseman & Rikard Hjort

(require 'comint)

;;;; Options ;;;;
(defvar k-dash-comments nil
  "Set to make \"--\" be used as a beginning of a line comment
   (emacs's syntax table is unable to differentiate 3 character long comment beginners)"
)

(defvar k-custom-word-highlights nil
  "A list of words to highlight, beyond the builtin syntax. For example \"'(\"andBool\" \"orBool\" \"+Int\")\""
  )

(defvar k-custom-highlights-regex nil
  "A list of words to highlight, beyond the builtin syntax. For example \"<-\\|\\|->\""
  )

;;;; Syntax Highlighting ;;;;
(setq k-keywords '("configuration" "context" "endmodule" "non-assoc" "ensures" "imports" "left" "module" "priorities" "require" "requires" "right" "rule" "sort" "syntax" "when"))

;; TODO: Only highlight these when inside square brackets.
(setq k-annotations '("alias" "alias-rec" "anywhere" "bracket" "concrete" "context" "cool" "freshGenerator" "function" "functional" "heat" "hook" "hybrid" "klabel" "left" "macro" "macro-rec" "memo" "owise" "priority" "result" "right" "seqstrict" "simplification" "smtlib" "strict" "symbol" "token" "unboundVariables"))

;; Set up the regexes

;; Metalanguage.
(setq k-keywords-regex (regexp-opt k-keywords 'words)
      k-annotations-regex (regexp-opt k-annotations 'symbols)
      k-keywords-special-regex "::=\\||"
      k-declarations "\\(syntax\\|sort\\|op\\) +\\(\\({.+} +\\)?[a-zA-Z{}\\-]+\\)"
      k-rewrites-regex "=>\\|<[^>*/|\"_[:space:]]+>\\|</[^>*/|\"_[:space:]]+>\\|<[^>*/|\"_[:space:]]+/>")

;; Common constructs.
(setq k-syntax-terminals-regex "\\.\\.\\.\\|~>\\||->\\|\\.\\s-\\|`\\w+"
      k-custom-word-highlights-regex (regexp-opt k-custom-word-highlights 'words)
      k-hash-symbols-regex "\\(#\\(?:And\\|Ceil\\|E\\(?:\\(?:qual\\|xist\\)s\\)\\|False\\|Not\\|Or\\|True\\|as\\|else\\|f\\(?:i\\|un\\)\\|if\\|then\\)\\)\\b"
)

;; Put them all together
(setq k-font-lock-keywords
      `((,k-custom-word-highlights-regex . font-lock-constant-face)
        (,k-custom-highlights-regex      . font-lock-constant-face)
        (,k-rewrites-regex               . font-lock-type-face)
        (,k-syntax-terminals-regex       . font-lock-constant-face)
        (,k-hash-symbols-regex           . font-lock-constant-face)
        (,k-declarations                 2 font-lock-function-name-face)
        (,k-keywords-regex               . font-lock-keyword-face)
        (,k-keywords-special-regex       . font-lock-keyword-face)
        (,k-annotations-regex            . font-lock-builtin-face)))

;; Handle comments
(defun set-comment-highlighting ()
  "Set up comment highlighting"

  ;; comment style "// ..." and "/* ... */"
  (modify-syntax-entry ?\/ ". 124b" k-mode-syntax-table)
  (modify-syntax-entry ?\n "> b" k-mode-syntax-table)
  (modify-syntax-entry ?* ". 23" k-mode-syntax-table)

  ;; comment style "-- ..."
  (if k-dash-comments (modify-syntax-entry ?- ". 1b2b" k-mode-syntax-table))
)

;;;; K Bindings and menu ;;;;
(defvar k-prev-load-file nil
  "Record the last directory and file used in loading or compiling"
)
(defcustom k-source-modes '(k-mode)
  "Determine if a buffer represents a k file"
)

(defun k-mode-about ()
  (interactive)
  (message "k-mode for the K Framework")
)

(defun setup-k-mode-map ()
  (setq k-mode-map (make-sparse-keymap))

  ;; Keyboard shortcuts
  (define-key k-mode-map (kbd "C-c C-c") 'compile)

  ;; Define the menu
  (define-key k-mode-map [menu-bar] (make-sparse-keymap))

  (let ((menuMap (make-sparse-keymap "K Framework")))
    (define-key k-mode-map [menu-bar k] (cons "K Framework" menuMap))
    (define-key menuMap [about]
      '("About k-mode" . k-mode-about))
    ;; (define-key menuMap [customize]
    ;;   '("Customize k-mode" . k-customize))
    (define-key menuMap [separator]
      '("--"))
    (define-key menuMap [kompile]
      '("kompile" . compile)))
)





;;;; K Mode ;;;;

(define-derived-mode k-mode fundamental-mode
  "k mode"
  "Major Mode for the K framwork"
  (setq font-lock-defaults '((k-font-lock-keywords)))

  ;; Comment entries
  (set-comment-highlighting)

  ;; Set comment start characters
  (setq comment-start "//")

  ;; Shortcuts and menu
  (setup-k-mode-map)
  (use-local-map k-mode-map)
)

(provide 'k-mode)
