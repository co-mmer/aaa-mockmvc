#### [← back](../../README.md)

### Assert Head

- [Contains](#contains)
- [Not Contains](#not-contains)
- [Equals](#equals)

---

### Contains

This example asserts that the response contains a specific header.

```

head()
    ...
    .act()
    .actPerform()
    .asserts()
    .assertHead()
    .assertHeadContains(X_HEADER)

```

---

### Not Contains

This example asserts that a specific header is not present in the response.

```

head()
    ...
    .act()
    .actPerform()
    .asserts()
    .assertHead()
    .assertHeadNotContains(X_HEADER)

```

---

### Equals

This example is used to assert that a specific header key has the expected value.

```

head()
    ...
    .act()
    .actPerform()
    .asserts()
    .assertHead()
    .assertHeadEquals(X_HEAD_KEY, X_HEAD_VALUE)

```

---

#### [← back](../../README.md)