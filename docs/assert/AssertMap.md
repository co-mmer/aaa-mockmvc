#### [← back](../../README.md)

### Assert Content As Map 🔸 (New)

- [IsNotEmpty](#not-empty)
- [IsEmpty](#empty)
- [Size](#size)
- [Equals](#equals)

---

### Not Empty

The **`assertContentIsNotEmpty`** method is used to verify that the map returned in the response is
not
empty. This assertion ensures that the map contains at least one entry.

```
      get()
          ...
          .act()
          .asserts()
          .assertContentAsMap()
          .assertContentIsNotEmpty()
```

---

### Empty

The **`assertContentIsEmpty`** method is used to verify that the map returned in the response is
empty.
This assertion ensures that the map does not contain any entries.

```
      get()
          ...
          .act()
          .asserts()
          .assertContentAsMap()
          .assertContentIsEmpty()
```

---

### Size

The **`assertContentSize`** method verifies that the map returned in the response contains the
expected
number of entries. This assertion ensures that the map size matches the specified value.

```
  get()
      ...
      .act()
      .asserts()
      .assertContentAsMap()
      .assertContentSize(2)
```

---

### Equals

The **`assertContentEquals`** method is used to assert that the content of the response matches an
expected **`map`** of objects. This allows you to compare the returned map with a predefined one to
ensure the correct key-value pairs are present.

- **keyClass**: The class type of the keys in the map (e.g., `Integer.class`).
- **valueClass**: The class type of the values in the map (e.g., `DemoObject.class`).
- **expectedMap**: The expected map containing the key-value pairs you want to match against the
  response.

```
  public record DemoObject(String name, Integer id) {}
```

```
  A1 = new DemoObject(A, 1);
  A2 = new DemoObject(A, 2);
```

```
  @GetMapping(...)
  public ResponseEntity<Map<Integer, DemoObject>> getAll() {
    return new ResponseEntity<>(Map.of(1, A1, 2, A2), OK);
  }
```

```

  get()
    ...
    .act()
    .asserts()
    .assertContentAsMap()
    .assertContentEquals(Integer.class, DemoObject.class, Map.of(1, A1, 2, A2));

```

---

#### [← back](../../README.md)