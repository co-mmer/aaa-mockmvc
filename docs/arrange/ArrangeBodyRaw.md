#### [← back](../../README.md)

### Arrange Body Raw

- [Content Raw](#content-raw)
- [Content Json](#content-json)
- [Content Json (Generic)](#content-json-generic)
- [Content Json (List)](#content-json-list)
- [Content Json (Set)](#content-json-set)
- [Content Json (Map)](#content-json-map)

---

### Content Raw

This example demonstrates how to send a JSON body in a structured test setup using a request.

First, the following constants are defined:

``` java
  public static final String SIMPLE_A1_JSON = "{"name":"A","id":1}"
  
  public static final String BODY = "simple/body";
  public static final String ENDPOINT_BODY = BASE + BODY;
```

The controller defines an endpoint that receives the request body as a deserialized object:

``` java 
  @PostMapping(BODY)
  public ResponseEntity<Void> exampleBody(@RequestBody SimpleObject value) {
    return ResponseEntity.ok().build();
  }
```

The corresponding test sets up the request body using the predefined JSON and specifies the content
type:

``` java
 post()
      .arrange()
      .arrangeUrl(ENDPOINT_BODY)
      .arrangeBody()
      .arrangeContent(SIMPLE_A1_JSON, APPLICATION_JSON)
      .act()
      ...
```

---

### Content Json

This example demonstrates how to send a JSON body using a simplified method in a structured POST
request test setup.

First, the following constants are defined:

``` java
  public static final String SIMPLE_A1_JSON = "{"name":"A","id":1}"
  
  public static final String BODY = "simple/body";
  public static final String ENDPOINT_BODY = BASE + BODY;
```

The controller defines an endpoint that receives the request body as a deserialized object:

``` java 
  @PostMapping(BODY)
  public ResponseEntity<Void> exampleBody(@RequestBody SimpleObject value) {
    return ResponseEntity.ok().build();
  }
```

The corresponding test sets up the request by directly assigning the JSON content:

``` java
 post()
      .arrange()
      .arrangeUrl(ENDPOINT_BODY)
      .arrangeBody()
      .arrangeJson(SIMPLE_A1_JSON)
      .act()
      ...
```

---

### Content Json (Generic)

This example demonstrates how to send a Java object as JSON in a structured request test setup.
The object is automatically serialized to JSON by the test framework.

First, the following constants are defined:

``` java
  public static final SimpleObject SIMPLE_A1 = new SimpleObject( "A", 1);
  
  public static final String BODY = "simple/body";
  public static final String ENDPOINT_BODY = BASE + BODY;
```

The controller defines an endpoint that receives the request body as a deserialized object:

``` java 
  @PostMapping(BODY)
  public ResponseEntity<Void> exampleBody(@RequestBody SimpleObject value) {
    return ResponseEntity.ok().build();
  }
```

The corresponding test sets up the request by providing the Java object, which is internally
serialized to JSON:

``` java
 post()
      .arrange()
      .arrangeUrl(ENDPOINT_BODY)
      .arrangeBody()
      .arrangeJson(SIMPLE_A1)
      .act()
      ...
```

---

### Content Json (List)

This example demonstrates how to send a list of Java objects as a JSON array in a structured
request test setup. The list is automatically serialized to JSON by the test framework.

First, the following constants are defined:

``` java
  public static final SimpleObject SIMPLE_A1 = new SimpleObject("A", 1);
  public static final SimpleObject SIMPLE_A2 = new SimpleObject("A", 2);
  
  public static final List<SimpleObject> SIMPLE_LIST = List.of(SIMPLE_A1, SIMPLE_A2);
  
  public static final String BODY_LIST = "simple/bodyList";
  public static final String ENDPOINT_BODY_LIST = BASE + BODY_LIST;
```

The controller defines an endpoint that receives a list of deserialized objects:

``` java 
  @PostMapping(BODY_LIST)
  public ResponseEntity<Void> exampleBodyList(@RequestBody List<SimpleObject> value) {
    return ResponseEntity.ok().build();
  }
```

The corresponding test sets up the request by providing the list, which is internally serialized to
a JSON array:

``` java
 post()
      .arrange()
      .arrangeUrl(ENDPOINT_BODY_LIST)
      .arrangeBody()
      .arrangeJson(SIMPLE_LIST)
      .act()
      ...
```

---

### Content Json (Set)

This example demonstrates how to send a set of Java objects as a JSON array in a structured
request test setup. The set is automatically serialized to JSON by the test framework.

First, the following constants are defined:

``` java
  public static final SimpleObject SIMPLE_A1 = new SimpleObject("A", 1);
  public static final SimpleObject SIMPLE_A2 = new SimpleObject("A", 2);
  
  public static final Set<SimpleObject> SIMPLE_SET = Set.of(SIMPLE_A1, SIMPLE_A2);
  
  public static final String BODY_SET = "simple/bodySet";
  public static final String ENDPOINT_BODY_SET = BASE + BODY_SET;
```

The controller defines an endpoint that receives a set of deserialized objects:

``` java 
  @PostMapping(BODY_SET)
  public ResponseEntity<Void> exampleBodySet(@RequestBody Set<SimpleObject> value) {
    return ResponseEntity.ok().build();
  }
```

The corresponding test sets up the request by providing the set, which is internally serialized to a
JSON array:

``` java
 post()
      .arrange()
      .arrangeUrl(ENDPOINT_BODY_SET)
      .arrangeBody()
      .arrangeJson(SIMPLE_SET)
      .act()
      ...
```

---

### Content Json (Map)

This example demonstrates how to send a map as a JSON object in a structured request test
setup. The map is automatically serialized to JSON by the test framework.

First, the following constants are defined:

``` java
  public static final SimpleObject SIMPLE_A1 = new SimpleObject("A", 1);
  
  public static final Map<Integer, SimpleObject> SIMPLE_MAP = Map.of(1, SIMPLE_A1);
  
  public static final String BODY_MAP = "simple/bodyMap";
  public static final String ENDPOINT_BODY_MAP = BASE + BODY_MAP;
```

The controller defines an endpoint that receives the request body as a map, where the key is an
integer and the value is a deserialized object:

``` java 
  @PostMapping(BODY_MAP)
  public ResponseEntity<Void> exampleBodyMap(@RequestBody Map<Integer, SimpleObject> value) {
    return ResponseEntity.ok().build();
  }
```

The corresponding test sets up the request by providing the map, which is internally serialized to a
JSON object:

``` java
 post()
      .arrange()
      .arrangeUrl(ENDPOINT_BODY_MAP)
      .arrangeBody()
      .arrangeJson(SIMPLE_MAP)
      .act()
      ...
```

---

#### [← back](../../README.md)