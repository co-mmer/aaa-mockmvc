#### [← back](../../README.md)

### Assert Content As Collection 🔸 (New)

- [Not Empty](#not-empty)
- [Empty](#empty)
- [Size](#size)
- [Equals](#equals)
- [Contains Any Order](#contains-any-order)
- [Not Contains](#not-contains)
- [Contains](#contains)
- [Match All](#match-all)
- [Match Any](#match-any)
- [Match None](#match-none)

---

### Not Empty

The **`assertContentNotEmpty`** method verifies that the collection returned in the response is
**not** empty. If it is needed, it must be called first. Once invoked, other collection-related
assertions can follow. However, if any other assertCollection methods are called before
assertCollectionNotEmpty, the framework will not provide this method again, as it ensures that only
methods which are contextually appropriate are available.

```
      get()
          ...
          .act()
          .asserts()
          .assertContentAsCollection()
          .assertContentNotEmpty()
```

---

### Empty

The **`assertContentEmpty`**  method verifies that the collection returned in the response is
empty. After this assertion, no further collection-related assertions can be performed, as it would
be semantically incorrect to validate additional properties on an empty result.

```
      get()
          ...
          .act()
          .asserts()
          .assertContentAsCollection()
          .assertContentEmpty();
```

---

### Size

The **`assertContentSize`** method verifies that the size of the collection returned in the
response matches the expected size.

```
  get()
      ...
      .act()
      .asserts()
      .assertContentAsCollection()
      .assertContentSize(2)
```

---

### Equals

The **`assertContentEquals`** method ensures that the collection returned in the response matches
an
expected List of objects both in content and order.

- **Class Specification**: The class type of the objects within the collection (e.g., DemoDto.class)
  must be specified.
- **Expected Collection**: The expected collection (e.g. EXPECTED_LIST) is provided to compare
  against the response.

```
  public record DemoObject(String name, Integer id) {}
```

```
  A1 = new DemoObject(A, 1);
  A2 = new DemoObject(A, 2);
```

```
  @GetMapping(...)
  public ResponseEntity<List<DemoObject>> getAll() {
    return new ResponseEntity<>(List.of(A1, A2), OK);
  }
```

```  
  get()
      ...
      .act()
      .asserts()
      .assertContentAsCollection()
      .assertContentEquals(DemoObject.class, List.of(A1, A2))
```

---

### Contains Any Order

The **`assertContentContainsAnyOrder`**  method verifies that the collection returned in the
response matches the expected collection, ignoring the order of the elements.

```
  public record DemoObject(String name, Integer id) {}
```

```
  A1 = new DemoObject(A, 1);
  A2 = new DemoObject(A, 2);
```

```
  @GetMapping(...)
  public ResponseEntity<List<DemoObject>> getAll() {
    return new ResponseEntity<>(List.of(A1, A2), OK);
  }
```

```
  get()
      ...
      .act()
      .asserts()
      .assertContentAsCollection()
      .assertContentContainsAnyOrder(DemoObject.class, List.of(A2, A1));
```

---

### Not Contains

The **`assertContentNotContains`**  method is used to verify that the specified objects do **not
**
exist within the collection returned in the response.

- **Class Specification**: The class type of the objects within the collection (e.g.,
  DemoObject.class) must be explicitly provided to ensure accurate comparison.
- **Expected Objects**: The method checks that the given objects (e.g., A3, A4) are not present in
  the returned collection. These objects can be specified as a single object, as varargs, or as a
  collection.

```
  public record DemoObject(String name, Integer id) {}
```

```
  A1 = new DemoObject(A, 1);
  A2 = new DemoObject(A, 2);
  A3 = new DemoObject(A, 3);
  A4 = new DemoObject(A, 4);
```

```
  @GetMapping(...)
  public ResponseEntity<List<DemoObject>> getAll() {
    return new ResponseEntity<>(List.of(A1, A2), OK);
  }
```

```
  get()
      ...
      .act()
      .asserts()
      .assertContentAsCollection()
      .assertContentNotContains(DemoObject.class, A3);
```

```
  get()
      ...
      .act()
      .asserts()
      .assertContentAsCollection()
      .assertContentNotContains(DemoObject.class, A3, A4, ...);
```

```
  get()
      ...
      .act()
      .asserts()
      .assertContentAsCollection()
      .assertContentNotContains(DemoObject.class, List.of(A3, A4));
```

---

### Contains

The **`assertContentContains`**  method is used to verify that the specified objects do
exist within the collection returned in the response.

- **Class Specification**: The class type of the objects within the collection (e.g.,
  DemoObject.class) must be explicitly provided to ensure accurate comparison.
- **Expected Objects**: The method checks that the given objects (e.g., A1, A2) are present in
  the returned collection. These objects can be specified as a single object, as varargs, or as a
  collection.

```
  public record DemoObject(String name, Integer id) {}
```

```
  A1 = new DemoObject(A, 1);
  A2 = new DemoObject(A, 2);
```

```
  @GetMapping(...)
  public ResponseEntity<List<DemoObject>> getAll() {
    return new ResponseEntity<>(List.of(A1, A2), OK);
  }
```

```
  get()
      ...
      .act()
      .asserts()
      .assertContentAsCollection()
      .assertContentContains(DemoObject.class, A1);
```

```
  get()
      ...
      .act()
      .asserts()
      .assertContentAsCollection()
      .assertContentContains(DemoObject.class, A1, A2, ...);
```

```
  get()
      ...
      .act()
      .asserts()
      .assertContentAsCollection()
      .assertContentContains(DemoObject.class, List.of(A1, A2));
```

---

### Match All

The **`assertContentMatchAll`** method is used to verify that all elements in the collection
match the specified conditions. This assertion allows for checking multiple attributes or conditions
for every element in the collection.

- **Class Specification:** The class type of the objects within the collection (
  e.g., `DemoMatch.class`) must be defined to ensure the proper type is being validated.
- **Conditions:** One or more conditions (e.g., predicates) can be provided. These conditions will
  be applied to each element in the collection, and the assertion will pass only if **all elements**
  satisfy the provided conditions.

```
  public record DemoMatch(String name, Integer id, Status status) {}
```

```
  A1_NEW = new DemoMatch(A, 1, NEW);
  A2_NEW = new DemoMatch(A, 2, NEW);
```

```
  @GetMapping(...)
  public ResponseEntity<List<DemoMatch>> getAll() {
    return new ResponseEntity<>(List.of(A1_NEW, A2_NEW), OK);
  }
```

```
  get()
      ...
      .act()
      .asserts()
      .assertContentAsCollection()
      .assertContentMatchAll(DemoMatch.class, element -> element.name().equals(A));
```

```
  get()
      ...
      .act()
      .asserts()
      .assertContentAsCollection()
      .assertContentMatchAll(DemoMatch.class,
            element -> element.name().equals(A),
            element -> element.status().equals(NEW));
```

---

### Match Any

The **`assertContentMatchAny`** method is used to verify that at least one element in the
collection matches the specified conditions. This assertion allows for checking multiple attributes
or conditions, and it will pass if **any element** satisfies at least one of the provided
conditions.

- **Class Specification:** The class type of the objects within the collection (
  e.g., `DemoMatch.class`) must be defined to ensure proper validation.
- **Conditions:** One or more conditions (e.g., predicates) can be provided. The assertion will pass
  if **any element** satisfies any of the given conditions.

```
  public record DemoMatch(String name, Integer id, Status status) {}
```

```
  A1_NEW = new DemoMatch(A, 1, NEW);
  A2_NEW = new DemoMatch(A, 2, NEW);
```

```
  @GetMapping(...)
  public ResponseEntity<List<DemoMatch>> getAll() {
    return new ResponseEntity<>(List.of(A1_NEW, A2_NEW), OK);
  }
```

```
  get()
      ...
      .act()
      .asserts()
      .assertContentAsCollection()
      .assertContentMatchAny(DemoMatch.class, element -> element.name().equals(A));
```

```
  get()
      ...
      .act()
      .asserts()
      .assertContentAsCollection()
      .assertContentMatchAny(DemoMatch.class,
            element -> element.name().equals(A),
            element -> element.status().equals(CLOSE));
```

---

### Match None

The **`assertContentMatchNone`** method is used to verify that no elements in the collection
match the specified conditions. This assertion allows for checking multiple attributes or
conditions, and it will pass if **none of the elements** satisfy the given conditions.

- **Class Specification:** The class type of the objects within the collection (
  e.g., `DemoMatch.class`) must be defined to ensure proper validation.
- **Conditions:** One or more conditions (e.g., predicates) can be provided. The assertion will pass
  only if **none of the elements** match any of the specified conditions.

```
  public record DemoMatch(String name, Integer id, Status status) {}
```

```
  A1_NEW = new DemoMatch(A, 1, NEW);
  A2_NEW = new DemoMatch(A, 2, NEW);
```

```
  @GetMapping(...)
  public ResponseEntity<List<DemoMatch>> getAll() {
    return new ResponseEntity<>(List.of(A1_NEW, A2_NEW), OK);
  }
```

```
  get()
      ...
      .act()
      .asserts()
      .assertContentAsCollection()
      .assertContentMatchNone(DemoMatch.class, element -> element.name().equals(B));
```

```
  get()
      ...
      .act()
      .asserts()
      .assertContentAsCollection()
      .assertContentMatchNone(DemoMatch.class,
            element -> element.name().equals(B),
            element -> element.status().equals(CLOSE));
```

---

#### [← back](../../README.md)