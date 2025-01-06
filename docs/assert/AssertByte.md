#### [← back](../../README.md)

### Assert Byte

- [Not Empty](#not-empty)
- [Empty](#empty)
- [Equals](#equals)

---

### Not Empty

In this example, the **`assertByteNotEmpty`** method is used to assert that the response byte
is not empty.

```
  get()
      ...
      .act()
      .actPerform()
      .asserts()
      .assertByte()
      .assertByteNotEmpty()
```

---

### Empty

In this example, the **`assertByteEmpty`** method is used to assert that the response byte
is empty.

```
  get()
      ...
      .act()
      .actPerform()
      .asserts()
      .assertByte()
      .assertByteEmpty()
```

---

### Equals

In this example, the **`assertByteEquals`** method is used to assert that the response byte
matches an expected byte array.

```
  get()
      ...
      .act()
      .actPerform()
      .asserts()
      .assertByte()
      .assertByteEquals(EXPECTED_BYTE_ARRAY)
```

---

#### [← back](../../README.md)