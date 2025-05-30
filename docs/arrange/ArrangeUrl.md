#### [← back](../../README.md)

### Arrange Url

- [URI](#uri)
- [URL](#url)
- [Path Variable](#path-variable)
- [Single Parameter](#parameter)
- [Multiple Parameters](#parameter)
- [Parameters as Map](#parameters-map)

---

### URI

This example demonstrates how to use a complete URI to direct the request to a specific endpoint.

First, the following constant is defined:

``` java
public static final URI ENDPOINT_URI_SIMPLE = URI.create(BASE + SIMPLE);
```

The controller defines an endpoint that handles a simple request at the specified URL:

``` java
  @GetMapping(SIMPLE)
  public ResponseEntity<Void> exampleSimple() {
    return ResponseEntity.ok().build();
  }
```

The corresponding test sets up the request using the predefined URL constant:

``` java
  get()
    .arrange()
    .arrangeUri(ENDPOINT_URI_SIMPLE)
    .act()
    ...
```

---

### URL

This example shows the simplest form of a request, utilizing only the base URL.

First, the following constant is defined:

``` java
  public static final String ENDPOINT_SIMPLE = BASE + SIMPLE;
```

The controller defines an endpoint that handles a simple request at the specified URL:

``` java
  @GetMapping(SIMPLE)
  public ResponseEntity<Void> exampleSimple() {
    return ResponseEntity.ok().build();
  }
```

The corresponding test sets up the request using the predefined URL constant:

``` java
  get()
    .arrange()
    .arrangeUrl(ENDPOINT_SIMPLE)
    .act()
    ...
```

---

### Path Variable

This example demonstrates how to construct a URL with a path variable, replacing a dynamic segment
to target a specific resource.

First, the following constants are defined:

``` java
 public static final String ID_8 = "8";
 public static final String VALUE_A = "A";

 public static final String PATH_VARIABLE_1 = "simple/{id}";
 public static final String PATH_VARIABLE_2 = "simple/{id}/{value}";
 
 public static final String ENDPOINT_PATH_VARIABLE_1 = BASE + PATH_VARIABLE_1;
 public static final String ENDPOINT_PATH_VARIABLE_2 = BASE + PATH_VARIABLE_2;
```

The controller defines an endpoint with a path variable to receive the dynamic value:

``` java
  @GetMapping(PATH_VARIABLE_1)
  public ResponseEntity<Void> examplePathVariable1(@PathVariable Integer id) {
    return ResponseEntity.ok().build();
  }
```

The corresponding test sets up the request using a placeholder for the path variable:

``` java
  get()
    .arrange()
    .arrangeUrl(ENDPOINT_PATH_VARIABLE_1, ID_8)
    .act()
    ...
```

Note: The method supports a variable number of path variables using varargs. This allows appending
multiple segments dynamically:

```java

@GetMapping(PATH_VARIABLE_2)
public ResponseEntity<Void> examplePathVariable2(
    @PathVariable Integer id, @PathVariable String value) {

  return ResponseEntity.ok().build();
}
```

``` java
 get()
    .arrange()
    .arrangeUrl(ENDPOINT_PATH_VARIABLE_2, ID_8, VALUE_A)
    .act()
    ...
```

---

### Parameter

This example demonstrates how to append a single query parameter to a URL in a structured test
setup.

First, the following constants are defined:

``` java
 public static final String KEY_1 = "key1";
 public static final String VALUE_1 = "value1";

 public static final String PARAM_1 = "simple/param1";
 public static final String ENDPOINT_PARAM_1 = BASE + PARAM_1;
```

The controller defines an endpoint that accepts a single query parameter:

``` java
  public ResponseEntity<Void> exampleParam3(
      @RequestParam(name = KEY_1) String value1,
      @RequestParam(name = KEY_2) int value2,
      @RequestParam(name = KEY_3) boolean value3) {

    return ResponseEntity.ok().build();
  }
```

The corresponding test configures the request with one key-value pair:

``` java
 get()
    .arrange()
    .arrangeUrl(ENDPOINT_PARAM_1)
    .arrangeParam()
    .arrangeKeyValue(KEY_1, VALUE_1)
    .act()
    ...
```

---

### Parameters

This example demonstrates how to add multiple query parameters to a URL in a structured test setup.

First, the following constants are defined:

``` java
  public static final String KEY_1 = "key1";
  public static final String KEY_2 = "key2";
  public static final String KEY_3 = "key3";
  
  public static final String VALUE_1 = "value1";
  public static final String VALUE_2 = "2";
  public static final String VALUE_3 = "true";
  
  public static final String PARAM_3 = "simple/param3";
  public static final String ENDPOINT_PARAM_3 = BASE + PARAM_3;
```

The controller defines an endpoint that expects three query parameters:

``` java
  @GetMapping(PARAM_3)
  public ResponseEntity<Void> exampleParam3(
      @RequestParam(name = KEY_1) String value1,
      @RequestParam(name = KEY_2) int value2,
      @RequestParam(name = KEY_3) boolean value3) {

    return ResponseEntity.ok().build();
  }
```

The corresponding test arranges the request with all three parameters:

``` java
 get()
    .arrangeUrl(ENDPOINT_PARAM_3)
    .arrangeParam()
    .arrangeKeyValue(KEY_1, VALUE_1)
    .arrangeKeyValue(KEY_2, VALUE_2)
    .arrangeKeyValue(KEY_3, VALUE_3)
    .act()
    ...
```

---

### Parameters (Map)

This example demonstrates how to pass multiple query parameters using a Map in a structured test
setup.

First, the following constant is defined:

``` java
 public static final Map<String, String> KEY_VALUE = Map.of(KEY_1, VALUE_1);
 
 public static final String PARAM_MAP = "simple/paramMap";
 public static final String ENDPOINT_PARAM_MAP = BASE + PARAM_MAP;
```

The controller defines an endpoint that accepts a dynamic set of query parameters as a map:

``` java 
  @GetMapping(PARAM_MAP)
  public ResponseEntity<Void> exampleParamMap(
      @RequestParam Map<String, String> keyValue) {

    return ResponseEntity.ok().build();
  }

```

The corresponding test arranges the request using the predefined map:

``` java
 get()
    .arrange()
    .arrangeUrl(ENDPOINT_PARAM_MAP)
    .arrangeParam()
    .arrangeKeyValue(KEY_VALUE)
    .act()
    ...
```

---

#### [← back](../../README.md)