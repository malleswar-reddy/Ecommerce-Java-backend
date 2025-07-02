Here is your content in Markdown format for `README.md`:

---

## 🛠️ Setup & Configuration

| Command | Description |
|---|---|
| `git config --global user.name "Your Name"` | Set your Git username |
| `git config --global user.email "you@example.com"` | Set your Git email |
| `git config --list` | Show current configuration |

---

## 🔹 Repository Management

| Command | Description |
|---|---|
| `git init` | Initialize a new local Git repo |
| `git clone <repo_url>` | Clone a remote repo to your machine |

---

## 🔹 Basic Workflow

| Command | Description |
|---|---|
| `git status` | Check repo status (staged/unstaged files) |
| `git add <file>` | Stage file(s) for commit |
| `git add .` | Stage all changes |
| `git commit -m "message"` | Commit staged changes |
| `git push` | Push commits to remote repo |
| `git pull` | Fetch and merge changes from remote |
| `git fetch` | Download changes (without merging) |

---

## 🔹 Branching

| Command | Description |
|---|---|
| `git branch` | List branches |
| `git branch <branch_name>` | Create a new branch |
| `git checkout <branch_name>` | Switch to another branch |
| `git checkout -b <branch_name>` | Create and switch to a new branch |
| `git merge <branch_name>` | Merge specified branch into current |
| `git branch -d <branch_name>` | Delete a branch |

---

## 🔹 Logs & History

| Command | Description |
|---|---|
| `git log` | Show commit history |
| `git log --oneline` | Condensed commit history |
| `git show <commit_hash>` | Show details of a commit |

---

## 🔹 Undo & Fix Mistakes

| Command | Description |
|---|---|
| `git restore <file>` | Discard changes in working directory |
| `git reset <file>` | Unstage a file |
| `git reset --hard` | Remove all changes (dangerous) |
| `git revert <commit_hash>` | Create a new commit that undoes previous commit |

---

## 🔹 Remote Management

| Command | Description |
|---|---|
| `git remote -v` | Show remote URLs |
| `git remote add origin <url>` | Add a remote repository |
| `git push -u origin main` | Push first commit and set upstream |

---

## 🔹 Stashing (Temporary Save)

| Command | Description |
|---|---|
| `git stash` | Save uncommitted changes temporarily |
| `git stash list` | Show stashed changes |
| `git stash apply` | Re-apply stashed changes |
|         |             |

| `git stash pop` | Re-apply and remove stashed changes |