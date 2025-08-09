#### [← back](../../README.md)

### Assert Content As Class 🔸 (New)

- [isNotEmpty](#not-empty)
- [isEmpty](#empty)
- [Equals](#equals)
- [Match All](#match-all)
- [Match Any](#match-any)
- [Match None](#match-none)

---

### Not Empty

In this example, the **`assertContentIsNotEmpty`** method is used to assert that the response
content
is not empty.

```
  get()
      ...
      .act()
      .asserts()
      .assertContentAsClass()
      .assertContentIsNotEmpty()
```

---

### Empty

In this example, the **`assertContentIsEmpty`** method is used to assert that the response content
is empty.

```
  get()
      ...
      .act()
      .asserts()
      .assertContentAsClass()
      .assertContentIsEmpty()
```

---

### Equals

In this example, the **`assertContentEquals`** method is used to assert that the response content
matches an expected object.

```
A1 = new DemoSimple(A, 1);
```

```
  get()
      ...
      .act()
      .asserts()
      .assertContentAsClass()
      .assertContentEquals(DemoSimple.class, A1)
```

---

### Match All

The **`assertContentMatchAll`** method is used to verify that **all specified conditions** are
satisfied for the properties of a given class instance. This assertion allows for checking multiple
attributes or conditions within the instance, ensuring the instance meets the defined criteria.

- **Class Specification:** The class type (e.g., `DemoMatch.class`) must be specified to validate
  the correct type of the instance.
- **Conditions:** One or more conditions (e.g., predicates) can be provided. These conditions are
  applied to the properties of the class instance, and the assertion will pass only if **all
  conditions** are satisfied.

```
  public record DemoMatch(String name, Integer id, Status status) {}
```

```
  A1_NEW = new DemoMatch(A, 1, NEW);
```

```
  @GetMapping(...)
  public ResponseEntity<DemoMatch> get() {
    return new ResponseEntity<>(A1_NEW, OK);
  }
```

```
  get()
      ...
      .act()
      .asserts()
      .assertContentAsClass()
      .assertContentMatchAll(DemoMatch.class, element -> element.name().equals(A));
```

```
  get()
      ...
      .act()
      .asserts()
      .assertContentAsClass()
      .assertContentMatchAll(DemoMatch.class,
            element -> element.name().equals(A),
            element -> element.status().equals(NEW));
```

---

### Match Any

The **`assertContentMatchAny`** method is used to verify that **at least one of the specified
conditions** is satisfied for the properties of a given class instance. This assertion allows for
checking multiple attributes or conditions within the instance, passing if **any single condition**
is met.

- **Class Specification:** The class type (e.g., `DemoMatch.class`) must be specified to validate
  the correct type of the instance.
- **Conditions:** One or more conditions (e.g., predicates) can be provided. These conditions are
  applied to the properties of the class instance, and the assertion will pass if **any one of the
  conditions** is satisfied.

```
  public record DemoMatch(String name, Integer id, Status status) {}
```

```
  A1_NEW = new DemoMatch(A, 1, NEW);
```

```
  @GetMapping(...)
  public ResponseEntity<DemoMatch> get() {
    return new ResponseEntity<>(A1_NEW, OK);
  }
```

```
  get()
      ...
      .act()
      .asserts()
      .assertContentAsClass()
      .assertContentMatchAny(DemoMatch.class, element -> element.name().equals(A));
```

```
  get()
      ...
      .act()
      .asserts()
      .assertContentAsClass()
      .assertContentMatchAny(DemoMatch.class,
            element -> element.name().equals(A),
            element -> element.status().equals(CLOSE));
```

---

### Match None

The **`assertContentMatchNone`** method is used to verify that **none of the specified conditions**
are satisfied for the properties of a given class instance. This assertion ensures that the instance
does not meet any of the defined criteria.

- **Class Specification:** The class type (e.g., `DemoMatch.class`) must be specified to validate
  the correct type of the instance.
- **Conditions:** One or more conditions (e.g., predicates) can be provided. These conditions are
  applied to the properties of the class instance, and the assertion will pass only if **none of the
  conditions** are satisfied.

```
  public record DemoMatch(String name, Integer id, Status status) {}
```

```
  A1_NEW = new DemoMatch(A, 1, NEW);
```

```
  @GetMapping(...)
  public ResponseEntity<DemoMatch> get() {
    return new ResponseEntity<>(A1_NEW, OK);
  }
```

```
  get()
      ...
      .act()
      .asserts()
      .assertContentAsClass()
      .assertContentMatchNone(DemoMatch.class, element -> element.name().equals(B));
```

```
  get()
      ...
      .act()
      .asserts()
      .assertContentAsClass()
      .assertContentMatchNone(DemoMatch.class,
            element -> element.name().equals(B),
            element -> element.status().equals(CLOSE));
```

---

#### [← back](../../README.md)