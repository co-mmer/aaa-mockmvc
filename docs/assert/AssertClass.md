#### [← back](../../README.md)

### Assert Content As Class 🔸 (New)

- [Not Empty](#not-empty)
- [Empty](#empty)
- [Equals](#equals)
- [Match All](#match-all)
- [Match Any](#match-any)
- [Match None](#match-none)

---

### Not Empty

In this example, the **`assertClassNotEmpty`** method is used to assert that the response content
is not empty.

```
  get()
      ...
      .act()
      .actPerform()
      .asserts()
      .assertContentAsClass()
      .assertClassNotEmpty()
```

---

### Empty

In this example, the **`assertClassEmpty`** method is used to assert that the response content
is empty.

```
  get()
      ...
      .act()
      .actPerform()
      .asserts()
      .assertContentAsClass()
      .assertClassEmpty()
```

---

### Equals

In this example, the **`assertClassEquals`** method is used to assert that the response content
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
      .assertContentAsClass()
      .assertClassEquals(DemoSimple.class, A1)
```

---

### Match All

The **`assertClassMatchAll`** method is used to verify that **all specified conditions** are
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
      .actPerform()
      .asserts()
      .assertContentAsClass()
      .assertClassMatchAll(DemoMatch.class, element -> element.name().equals(A));
```

```
  get()
      ...
      .act()
      .actPerform()
      .asserts()
      .assertContentAsClass()
      .assertClassMatchAll(DemoMatch.class,
            element -> element.name().equals(A),
            element -> element.status().equals(NEW));
```

---

### Match Any

The **`assertClassMatchAny`** method is used to verify that **at least one of the specified
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
      .actPerform()
      .asserts()
      .assertContentAsClass()
      .assertClassMatchAny(DemoMatch.class, element -> element.name().equals(A));
```

```
  get()
      ...
      .act()
      .actPerform()
      .asserts()
      .assertContentAsClass()
      .assertClassMatchAny(DemoMatch.class,
            element -> element.name().equals(A),
            element -> element.status().equals(CLOSE));
```

---

### Match None

The **`assertClassMatchNone`** method is used to verify that **none of the specified conditions**
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
      .actPerform()
      .asserts()
      .assertContentAsClass()
      .assertClassMatchNone(DemoMatch.class, element -> element.name().equals(B));
```

```
  get()
      ...
      .act()
      .actPerform()
      .asserts()
      .assertContentAsClass()
      .assertClassMatchNone(DemoMatch.class,
            element -> element.name().equals(B),
            element -> element.status().equals(CLOSE));
```

---

#### [← back](../../README.md)