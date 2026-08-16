# <img src="./images/aaa-mockmvc-icon-27.png" align="left"/> Changelog

## [2.0.2]

### 🧹 Improvements

- Refactored and reorganized internal architecture for clearer separation of responsibilities

### ☂️ Fixed

- Corrected `Accept` header handling for multiple media types. Accept values are now represented
  consistently as multiple HTTP header values. This may change the raw header representation from
  `application/json, application/pdf` to `application/json,application/pdf` while preserving the
  same HTTP semantics.

----

## [2.0.1]

### 🧹 Improvements

- Clean up dependencies

### 📦 Dependency Upgrades

- spring-boot 3.5.6 → 3.5.16
- lombok 1.18.42 → 1.18.46

----

## [2.0.0]

### ✨ New Features

- Step runner (`step(...)`) for grouped AAA blocks.
- Boolean support in `content().asBoolean()` and `answer().asBoolean()`.

### 🧹 Improvements

- Clearer snapshot semantics (`act().perform()` runs once).
- Refined exception model and better error guidance.
- Enhanced Spring/JUnit integration and documentation.

### 📦 Dependency Upgrades

- spring-boot 3.5.0 → 3.5.6
- lombok 1.18.40 → 1.18.42
- maven-javadoc-plugin 3.10.1 → 3.11.3
- central-publishing-maven-plugin 0.6.0 → 0.9.0
- maven-gpg-plugin 3.2.7 → 3.2.8
- maven-surefire-plugin 3.2.5 → 3.5.4
- jacoco-maven-plugin 0.8.12 → 0.8.13
- spotless-maven-plugin 2.43.0 → 3.0.0
- maven-compiler-plugin 3.13.0 → 3.14.1

---

## [1.5.0]

### ✨ New Features

- `AAAMockMvcAbstract` base class for integration tests.
- Automatic configuration via `AAAMockMvcConfig`.

### 🧹 Improvements

- Clearer documentation for Arrange section.

### 📦 Dependency Upgrades

- spring-boot 3.4.5 → 3.5.0

---

## [1.4.2]

### 📦 Dependency Upgrades

- spring-boot 3.4.4 → 3.4.5

---

## [1.4.1]

### 📦 Dependency Upgrades

- lombok 1.18.34 → 1.18.38
- spring-boot 3.4.0 → 3.4.4

### 🧹 Improvements

- reduced number of transitive dependencies

### ☂️ Fixed

- `answerAs*()` methods now return `null` instead of throwing on empty responses.

---

## [1.4.0]

### ✨ New Features

- Added assertions for class, collection, map, and byte arrays.

### 📦 Dependency Upgrades

- lombok 1.18.34 → 1.18.36
- spring-boot-starter-web 3.4.0 → 3.4.1
- spring-boot-starter-test 3.4.0 → 3.4.1
- mockito-core 5.14.2 → 5.15.2

---

## [1.3.0]

### ✨ New Features

- Added `answerAsObject`, `answerAsList`, `answerAsSet`, and `answerAsMap`.

### 🧹 Improvements

- Unicode's normalization for `assertContentEquals()` methods.

---

## [1.2.1]

### 🧹 Improvements

- This release improves general test coverage across the framework, enhancing reliability and
  stability in core functionalities.

---

## [1.2.0]

### ✨ New Features

- Introduced `TestAnswer` for reading HTTP results.

---

## [1.1.0]

### ✨ New Features

- Added custom assertions (`assertCustom()`).
- Added HTTP status assertions (`isOk()`, `isCreated()`, etc.).

---

## [1.0.0]

### 🌱 Initial Release

- First release of AAA-MockMvc.

