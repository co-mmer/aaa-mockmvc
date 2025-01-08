#### [← back](../../README.md)

### Arrange Body Raw

- [Content Raw](#content-raw)
- [Content Json](#content-json)
- [Content Json (Generic)](#content-json-generic)
    - [Sending a Single object](#example-1-sending-a-single-object)
    - [Sending a List of objects](#example-2-sending-a-list-of-objects)
    - [Sending a Set of objects](#example-3-sending-a-set-of-objects)
    - [Sending a Map of objects](#example-4-sending-a-map-of-objects)

---

### Content Raw

This example demonstrates how to send raw content in the body along with its media type.

```
 post()
      .arrange()
      .arrangeUrl(POST_EXAMPLE)
      .arrangeBody()
      .arrangeContent(RAW_CONTENT, RAW_MEDIATYPE)
      .act()
      ...
```

---

### Content Json

This example illustrates how to send JSON data in the request body.

```
JSON_1 = {"name":"A","id":1}
```

```
 post()
      .arrange()
      .arrangeUrl(POST_EXAMPLE)
      .arrangeBody()
      .arrangeJson(JSON_1)
      .act()
      ...
```

---

### Content Json (Generic)

The `arrangeJson` method simplifies the process of sending JSON payloads in `POST`, `PUT`,
or `PATCH` requests. It supports a variety of object types, including single objects, lists, sets,
and maps. Below are detailed examples showcasing its usage:

#### Example 1: Sending a Single Object

This example demonstrates how to send a single `DemoObject` as the JSON body in a POST request.

```
 demo = new DemoObject(A, 1);
```

```
 post()
      .arrange()
      .arrangeUrl(POST_EXAMPLE)
      .arrangeBody()
      .arrangeJson(demo) // Serialize the DemoObject object to JSON
      .act()
      ...
```

#### Example 2: Sending a List of Objects

This example shows how to send a `List<DemoObject>` containing multiple objects in the JSON body.

```
 A1 = new DemoObject(A, 1);
 A2 = new DemoObject(A, 2);
```

```
 List<DemoObject> demo = List.of(A1, A2);
```

```
 post()
      .arrange()
      .arrangeUrl(POST_EXAMPLE)
      .arrangeBody()
      .arrangeJson(demo) // Serialize the List of DemoObject objects to JSON
      .act()
      ...
```

### Example 3: Sending a Set of Objects

This example shows how to send a `Set<DemoObject>` containing multiple objects in the JSON body.

```
 A1 = new DemoObject(A, 1);
 A2 = new DemoObject(A, 2);
```

```
 Set<DemoObject> demo = Set.of(A1, A2);
```

```
 post()
      .arrange()
      .arrangeUrl(POST_EXAMPLE)
      .arrangeBody()
      .arrangeJson(demo) // Serialize the Set of DemoObject objects to JSON
      .act()
      ...
```

### Example 4: Sending a Map of Objects

This example demonstrates how to send a `Map<Integer, DemoObject>` as the JSON body. Both keys and
values are serialized.

```
 A1 = new DemoObject(A, 1);
 A2 = new DemoObject(A, 2);
```

```
 Map<Integer, DemoObject> demo = Map.of(1, A1, 2, A2);
```

```
 post()
      .arrange()
      .arrangeUrl(POST_EXAMPLE)
      .arrangeBody()
      .arrangeJson(demo) // Serialize the Map of DemoObject objects to JSON
      .act()
      ...
```

---

#### [← back](../../README.md)