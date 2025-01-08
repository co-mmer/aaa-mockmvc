#### [← back](../../README.md)

### Assert Content As String

- [Not Empty](#not-empty)
- [Empty](#empty)
- [Length](#length)
- [Equals ](#equals)

---

### Not Empty

In this example, the **`assertStringNotEmpty`** method is used to assert that the response content
is not empty.

```
  get()
      ...
      .act()
      .actPerform()
      .asserts()
      .assertContentAsString()
      .assertStringNotEmpty()
```

---

### Empty

In this example, the **`assertStringEmpty`** method is used to assert that the response content
is empty.

```
  get()
      ...
      .act()
      .actPerform()
      .asserts()
      .assertContentAsString()
      .assertStringEmpty()
```

---

### Length

In this example, the **`assertStringLength`** method is used to verify the expected length of the
response.

```
  get()
      ...
      .act()
      .actPerform()
      .asserts()
      .assertContentAsString()
      .assertStringLength(19)
```

---

### Equals

In this example, the **`assertStringEquals`** method is used to assert that the response content
matches an expected string.

```
A1_JSON = {"id":1,"name":"A"}
```

```
  get()
      ...
      .act()
      .actPerform()
      .asserts()
      .assertContentAsString()
      .assertStringEquals(A1_JSON)
```

---

#### [← back](../../README.md)