;;; packages.el --- k-framework layer packages file for Spacemacs.

(setq k-framework-packages
  '(
    (k-mode :location local)
    markdown-mode
   )
)

(defun k-framework/init-k-mode ()
  (use-package k-mode
    :defer t
    :mode "\\.k\\'"
  )
)

(defun k-framework/post-init-markdown-mode ()
  (use-package markdown-mode)
  (push '("k" . k-mode) markdown-code-lang-modes)
)

(defun k-framework/post-init-k-mode ()
  (spacemacs/set-leader-keys-for-major-mode 'k-mode
    "c" 'compile
    )
)
