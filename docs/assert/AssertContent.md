#### [← back](../../README.md)

### Assert Content

- [Not Empty](#not-empty)
- [Empty](#empty)
- [Length](#length)
- [Equals (String)](#equals-string)
- [Equals (Object)](#equals-object)

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

### Length

In this example, the **`assertContentLength`** method is used to verify the expected length of the
response.

```
  get()
      ...
      .act()
      .actPerform()
      .asserts()
      .assertContent()
      .assertContentLength(19)
```

---

### Equals (String)

In this example, the **`assertContentEquals`** method is used to assert that the response content
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
      .assertContent()
      .assertContentEquals(A1_JSON)
```

---

### Equals (Object)

In this example, the **`assertContentEquals`** method is used to assert that the response content
matches an expected object.

```
A1 = new DemoSimple(A, 1);
```

```
  get()
      ...
      .act()
      .actPerform()
      .asserts()
      .assertContent()
      .assertContentEquals(DemoSimple.class, A1)
```

---

#### [← back](../../README.md)