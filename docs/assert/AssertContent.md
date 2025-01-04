#### [← back](../../README.md)

### Assert Content

- [Not Empty](#not-empty)
- [Empty](#empty)
- [Equals (byte)](#equals-byte)
- [Equals (string)](#equals-string)

---

### Not Empty

In this example, the **`assertContentNotEmpty`** method is used to assert that the response content
is not empty.

```
  get()
      ...
      .act()
      .actPerform()
      .asserts()
      .assertContent()
      .assertContentNotEmpty()
```

---

### Empty

In this example, the **`assertContentEmpty`** method is used to assert that the response content
is empty.

```
  get()
      ...
      .act()
      .actPerform()
      .asserts()
      .assertContent()
      .assertContentEmpty()
```

---

### Equals (Byte)

In this example, the **`assertContentEquals`** method is used to assert that the response content
matches an expected byte array.

```
  get()
      ...
      .act()
      .actPerform()
      .asserts()
      .assertContent()
      .assertContentEquals(EXPECTED_BYTE_ARRAY)
```

---

### Equals (String)

In this example, the **`assertContentEquals`** method is used to assert that the response content
matches an expected string.

```
  get()
      ...
      .act()
      .actPerform()
      .asserts()
      .assertContent()
      .assertContentEquals(EXPECTED_STRING)
```

---

#### [← back](../../README.md)