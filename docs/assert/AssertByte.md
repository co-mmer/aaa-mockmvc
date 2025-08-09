#### [← back](../../README.md)

### Assert Byte

- [IsNotEmpty](#isNotEmpty)
- [IsEmpty](#isEmpty)
- [Length](#length)
- [Equals](#equals)

---

### isNotEmpty

In this example, the **`assertContentIsNotEmpty`** method is used to assert that the response byte
is not empty.

```
  get()
      ...
      .act()
      .actPerform()
      .asserts()
      .assertContentAsByte()
      .assertContentIsNotEmpty()
```

---

### isEmpty

In this example, the **`assertContentIsEmpty`** method is used to assert that the response byte
is empty.

```
  get()
      ...
      .act()
      .actPerform()
      .asserts()
      .assertContentAsByte()
      .assertContentIsEmpty()
```

---

### Length

In this example, the **`assertContentLength`** method is used to verify the expected byte length of
the
response.

```
  get()
      ...
      .act()
      .actPerform()
      .asserts()
      .assertContentAsByte()
      .assertContentLength(5)
```

---

### Equals

In this example, the **`assertContentEquals`** method is used to assert that the response byte
matches an expected byte array.

```
  get()
      ...
      .act()
      .actPerform()
      .asserts()
      .assertContentAsByte()
      .assertContentEquals(EXPECTED_BYTE_ARRAY)
```

---

#### [← back](../../README.md)