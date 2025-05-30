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
public static final URI URI_SIMPLE = URI.create(Api.BASE + Api.SIMPLE);
```

The controller defines an endpoint that handles a simple request at the specified URL:

``` java
  @GetMapping(SIMPLE)
  public ResponseEntity<Void> getSimple() {
    return ResponseEntity.ok().build();
  }
```

The corresponding test sets up the request using the predefined URL constant:

``` java
 get()
      .arrange()
      .arrangeUri(URI_SIMPLE)
      .act()
      ...
```

---

### URL

This example shows the simplest form of a request, utilizing only the base URL.

First, the following constant is defined:

``` java
 public static final String URL_SIMPLE = Api.BASE + Api.SIMPLE;
```

The controller defines an endpoint that handles a simple request at the specified URL:

``` java
  @GetMapping(SIMPLE)
  public ResponseEntity<Void> getSimple() {
    return ResponseEntity.ok().build();
  }
```

The corresponding test sets up the request using the predefined URL constant:

``` java
 get()
      .arrange()
      .arrangeUrl(URL_SIMPLE)
      .act()
      ...
```

---

### Path Variable

This example demonstrates how to construct a URL with a path variable, replacing a dynamic segment
to target a specific resource.

First, the following constant is defined:

``` java
 public static final String ID_8 = "8";
```

The controller defines an endpoint with a path variable to receive the dynamic value:

``` java
  @GetMapping(PATH_VARIABLE)
  public ResponseEntity<Void> examplePathVariable(@PathVariable Integer id) {
    return ResponseEntity.ok().build();
  }
```

The corresponding test sets up the request using a placeholder for the path variable:

``` java
 get()
      .arrange()
      .arrangeUrl(URL_SIMPLE, ID_8)
      .act()
      ...
```

Note: The method supports a variable number of path variables using varargs. This allows appending
multiple segments dynamically:

``` java
 get()
      .arrange()
      .arrangeUrl(URL_SIMPLE, ID_8, USER_A, ...)
      .act()
      ...
```

---

### Parameter

This example demonstrates how to append a single query parameter to a URL in a structured test
setup.

First, the following constants are defined:

``` java
 public static final String PARAM_KEY_1   = "param1";
 public static final String PARAM_VALUE_1 = "value1";
```

The controller defines an endpoint that accepts a single query parameter:

``` java
  @GetMapping(PARAM_1)
  public ResponseEntity<Void> exampleParameter(
      @RequestParam(name = PARAM_KEY_1) String value1) {

    return ResponseEntity.ok().build();

  }
```

The corresponding test configures the request with one key-value pair:

``` java
 get()
      .arrange()
      .arrangeUrl(URL_SIMPLE)
      .arrangeParam()
      .arrangeKeyValue(PARAM_KEY_1, PARAM_VALUE_1)
      .act()
      ...
```

---

### Parameters

This example demonstrates how to add multiple query parameters to a URL in a structured test setup.

First, the following constants are defined:

``` java
 public static final String PARAM_KEY_1   = "param1";
 public static final String PARAM_VALUE_1 = "value1";

 public static final String PARAM_KEY_2   = "param2";
 public static final String PARAM_VALUE_2 = "2";
 
 public static final String PARAM_KEY_3   = "param3";
 public static final String PARAM_VALUE_3 = "true";
```

The controller defines an endpoint that expects three query parameters:

``` java
  @GetMapping(PARAM_3)
  public ResponseEntity<Void> exampleParameters(
      @RequestParam(name = PARAM_KEY_1) String value1,
      @RequestParam(name = PARAM_KEY_2) int value2,
      @RequestParam(name = PARAM_KEY_3) boolean value3) {

    return ResponseEntity.ok().build();
  }
```

<div style="background-color:rgba(255,255,255,0); border-left:5px solid #abc095; margin:10px 0;">
The corresponding test arranges the request with all three parameters:
</div>

```
get()
    .arrange()
    .arrangeUrl(URL_SIMPLE)
    .arrangeParam()
    .arrangeKeyValue(PARAM_KEY_1, PARAM_VALUE_1)
    .arrangeKeyValue(PARAM_KEY_2, PARAM_VALUE_2)
    .arrangeKeyValue(PARAM_KEY_3, PARAM_VALUE_3)
    .act()
    ...
```

---

### Parameters (Map)

This example demonstrates how to pass multiple query parameters using a Map in a structured test
setup.

First, the following constant is defined:

``` java
 public static final Map<String, String> PARAM_MAP = Map.of(PARAM_KEY_1, PARAM_VALUE_1);
```

The controller defines an endpoint that accepts a dynamic set of query parameters as a map:

``` java 
  @GetMapping(PARAM_MAP)
  public ResponseEntity<Void> exampleParameterMap(
      @RequestParam Map<String, String> keyValue) {

    return ResponseEntity.ok().build();
  }

```

The corresponding test arranges the request using the predefined map:

``` java
 get()
      .arrange()
      .arrangeUrl(URL_SIMPLE)
      .arrangeParam()
      .arrangeKeyValue(PARAM_MAP)
      .act()
      ...
```

---

#### [← back](../../README.md)