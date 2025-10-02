# 📜 Release Commit Message Cheatsheet

| Release Type                                 | Version Example | Commit Message (short)                                                                                                                                                 |
|----------------------------------------------|-----------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Major** (Breaking changes)                 | `2.0.0`         | ```feat!: release 2.0.0 (general availability)```<br><br>Optional Footer:<br>```BREAKING CHANGE: old AAA API replaced with new unified flow```                         |
| **Release Candidate**                        | `2.0.0-RC1`     | ```feat!: release 2.0.0-RC1 (first candidate with redesigned AAA flow)```<br><br>Optional Footer:<br>```BREAKING CHANGE: old AAA API replaced with new unified flow``` |
| **Minor** (New feature, backward-compatible) | `2.1.0`         | ```feat: release 2.1.0 with feature X```                                                                                                                               |
| **Patch** (Bugfix, no new features)          | `2.1.1`         | ```fix: release 2.1.1 with bugfix for feature X```                                                                                                                     |
| **Meta / Only Version Bump**                 | `2.1.2`         | ```chore(release): 2.1.2```                                                                                                                                            |
