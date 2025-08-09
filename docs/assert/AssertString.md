#### [← back](../../README.md)

### Assert Content As String

- [Not Empty](#not-empty)
- [Empty](#empty)
- [Length](#length)
- [Equals ](#equals)

---

### Not Empty

In this example, the **`assertContentNotEmpty`** method is used to assert that the response content
is not empty.

```
  get()
      ...
      .act()
      .asserts()
      .assertContentAsString()
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
      .asserts()
      .assertContentAsString()
      .assertContentEmpty()
```

---

### Length

In this example, the **`assertContentLength`** method is used to verify the expected length of the
response.

```
  get()
      ...
      .act()
      .asserts()
      .assertContentAsString()
      .assertContentLength(19)
```

---

### Equals

In this example, the **`assertContentEquals`** method is used to assert that the response content
matches an expected string.

```
A1_JSON = {"id":1,"name":"A"}
```

```
  get()
      ...
      .act()
      .asserts()
      .assertContentAsString()
      .assertContentEquals(A1_JSON)
```

---

#### [← back](../../README.md)