#### [← back](../../README.md)

### Arrange Head

- [Accept](#accept)
- [Auth](#auth)
- [Content-Type](#content-type)
- [Single Key-Value](#key-value-single)
- [Multiple Key-Value](#key-value-multiple)
- [Key-Value as Map](#key-value-map)

---

### Accept

This example demonstrates how to configure the Accept header in a structured test setup to specify
the desired media type for the response.

First, the following constants are defined:

``` java  
  public static final String HEADER_ACCEPT = "header/accept";
  public static final String ENDPOINT_HEADER_ACCEPT = BASE + HEADER_ACCEPT;
```

The controller defines an endpoint that reads the Accept header from the request:

``` java 
  @GetMapping(HEADER_ACCEPT)
  public ResponseEntity<Void> exampleAccept(
      @RequestHeader("Accept") String accept) {

    return ResponseEntity.ok().build();
  }
```

The corresponding test sets up the request by configuring the Accept header using the predefined
media type:

``` java
  get()
    .arrange()
    .arrangeUrl(ENDPOINT_HEADER_ACCEPT)
    .arrangeHead()
    .arrangeAccept(APPLICATION_PDF)
    .act()
    ...
```

---

### Auth

This example demonstrates how to configure the Authorization header in a structured test setup to
simulate authenticated requests.

First, the following constants are defined:

``` java
  public static final String TOKEN = "123";
  
  public static final String HEADER_AUTH = "header/auth";
  public static final String ENDPOINT_HEADER_AUTH = BASE + HEADER_AUTH;
```

The controller defines an endpoint that reads the Authorization header from the request:

``` java 
  @GetMapping(HEADER_AUTH)
  public ResponseEntity<Void> exampleAuthorization(
      @RequestHeader("Authorization") String auth) {

    return ResponseEntity.ok().build();
  }
```

The corresponding test sets up the request by configuring the Authorization header using the
predefined token:

``` java
  get()
    .arrange()
    .arrangeUrl(ENDPOINT_HEADER_AUTH)
    .arrangeHead()
    .arrangeAuth(TOKEN)
    .act()
    ...
```

---

### Content-Type

This example demonstrates how to set the Content-Type header explicitly in a structured request
test.

First, the following constants are defined:

``` java
  public static final String HEADER_CONTENT_TYPE = "header/contentType";
  public static final String ENDPOINT_HEADER_CONTENT_TYPE = BASE + HEADER_CONTENT_TYPE;
```

The controller defines an endpoint that reads the Content-Type header from the incoming request:

``` java 
  @PostMapping(HEADER_CONTENT_TYPE)
  public ResponseEntity<Void> exampleContentType(
      @RequestHeader("Content-Type") String contentType) {

    return ResponseEntity.ok().build();
  }
```

The corresponding test sets up the request with the Content-Type header using a predefined media
type

``` java
  post()
    .arrange()
    .arrangeUrl(ENDPOINT_HEADER_CONTENT_TYPE)
    .arrangeHead()
    .arrangeContentType(APPLICATION_PDF)
    .act()
    ...
```

---

### Key-Value (Single)

This example demonstrates how to configure a single custom header using a key-value pair in a
structured test setup.

First, the following constants are defined:

``` java
  public static final String HEAD_X_CUSTOM_1 = "X-Custom-Head1";
  public static final String VALUE_1 = "value1";
 
  public static final String HEADER_KEY_VALUE_1 = "header/keyValue1";
  public static final String ENDPOINT_HEADER_KEY_VALUE_1 = BASE + HEADER_KEY_VALUE_1;
```

The controller defines an endpoint that expects a single custom header:

``` java 
  @GetMapping(HEADER_KEY_VALUE_1)
  public ResponseEntity<Void> exampleKeyValue1(
      @RequestHeader(HEAD_X_CUSTOM_1) String xCustomHeader1) {

    return ResponseEntity.ok().build();
  }
```

The corresponding test sets up the request with the custom header using a key-value configuration:

``` java
  get()
    .arrange()
    .arrangeUrl(ENDPOINT_HEADER_KEY_VALUE_1)
    .arrangeHead()
    .arrangeKeyValue(HEAD_X_CUSTOM_1, VALUE_1)
    .act()
    ...
```

---

### Key-Value (Multiple)

This example demonstrates how to configure multiple custom headers using key-value pairs in a
structured test setup.

First, the following constants are defined:

``` java
  public static final String HEAD_X_CUSTOM_1 = "X-Custom-Head1";
  public static final String HEAD_X_CUSTOM_2 = "X-Custom-Head2";
  
  public static final String VALUE_1 = "value1";
  public static final String VALUE_2 = "2";
 
  public static final String HEADER_KEY_VALUE_1 = "header/keyValue1";
  public static final String ENDPOINT_HEADER_KEY_VALUE_1 = BASE + HEADER_KEY_VALUE_1;
```

The controller defines an endpoint that expects two custom headers:

``` java 
  @GetMapping(HEADER_KEY_VALUE_2)
  public ResponseEntity<Void> exampleKeyValue2(
      @RequestHeader(HEAD_X_CUSTOM_1) String xCustomHeader1,
      @RequestHeader(HEAD_X_CUSTOM_2) String xCustomHeader2) {

    return ResponseEntity.ok().build();
  }
```

The corresponding test sets up the request with both headers using key-value configurations:

``` java
  get()
    .arrange()
    .arrangeUrl(ENDPOINT_HEADER_KEY_VALUE_2)
    .arrangeHead()
    .arrangeKeyValue(HEAD_X_CUSTOM_1, VALUE_1)
    .arrangeKeyValue(HEAD_X_CUSTOM_2, VALUE_2)
    .act()
    ...
```

---

### Key-Value (Map)

This example demonstrates how to configure multiple custom headers using a Map in a structured test
setup.

First, the following constants are defined:

``` java
  public static final Map<String, Object> HEAD_MAP = Map.of(HEAD_X_CUSTOM_1, VALUE_1);
  
  public static final String HEADER_KEY_VALUE_MAP = "header/keyValueMap";
  public static final String ENDPOINT_HEADER_KEY_VALUE_MAP = BASE + HEADER_KEY_VALUE_MAP;
```

The controller defines an endpoint that accepts all request headers as a map:

``` java 
  @GetMapping(HEADER_KEY_VALUE_MAP)
  public ResponseEntity<Void> exampleKeyValueMap(@RequestHeader Map<String, Object> headers) {
    return ResponseEntity.ok().build();
  }
```

The corresponding test sets up the request headers using the predefined map:

``` java
  get()
    .arrange()
    .arrangeUrl(ENDPOINT_HEADER_KEY_VALUE_MAP)
    .arrangeHead()
    .arrangeKeyValue(HEAD_MAP)
    .act()
    ...
```

---

#### [← back](../../README.md)