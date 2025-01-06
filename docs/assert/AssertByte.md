#### [← back](../../README.md)

### Assert Byte

- [Not Empty](#not-empty)
- [Empty](#empty)
- [Length](#length)
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

### Length

In this example, the **`assertByteLength`** method is used to verify the expected byte length of the
response.

```
  get()
      ...
      .act()
      .actPerform()
      .asserts()
      .assertByte()
      .assertByteLength(5)
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