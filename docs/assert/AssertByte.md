#### [← back](../../README.md)

### Assert Byte

- [Not Empty](#Not-Empty)
- [Empty](#Empty)
- [Length](#length)
- [Equals](#equals)

---

### Not Empty

In this example, the **`assertContentNotEmpty`** method is used to assert that the response byte
is not empty.

```
  get()
      ...
      .act()
      .actPerform()
      .asserts()
      .assertContentAsByte()
      .assertContentNotEmpty()
```

---

### Empty

In this example, the **`assertContentEmpty`** method is used to assert that the response byte
is empty.

```
  get()
      ...
      .act()
      .actPerform()
      .asserts()
      .assertContentAsByte()
      .assertContentEmpty()
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