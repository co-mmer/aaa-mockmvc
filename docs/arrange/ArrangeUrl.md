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

```
 get()
      .arrange()
      .arrangeUrl(GET_EXAMPLE_URI)
      .act()
      ...
```

---

### URL

This example shows the simplest form of a request, utilizing only the base URL.

```
 get()
      .arrange()
      .arrangeUrl(GET_EXAMPLE)
      .act()
      ...
```

---

### Path Variable

This example demonstrates the use of a URL with a path variable, replacing a dynamic segment of the
URL to retrieve specific data.

```
 get()
      .arrange()
      .arrangeUrl(GET_EXAMPLE, PATH_VAR_8)
      .act()
      ...
```

---

### Parameter

This example demonstrates how to append a single query parameter to a URL.

```
 get()
      .arrange()
      .arrangeUrl(GET_EXAMPLE)
      .arrangeParam()
      .arrangeKeyValue(PARAM_KEY_1, PARAM_VALUE_1)
      .act()
      ...
```

---

### Parameters

This example shows how to add multiple query parameters to a URL.

```
 get()
      .arrange()
      .arrangeUrl(GET_EXAMPLE)
      .arrangeParam()
      .arrangeKeyValue(PARAM_KEY_1, PARAM_VALUE_1)
      .arrangeKeyValue(PARAM_KEY_2, PARAM_VALUE_2)
      .act()
      ...
```

---

### Parameters (Map)

This example demonstrates how to use a map to append multiple parameters to a URL

```
 get()
      .arrange()
      .arrangeUrl(GET_EXAMPLE)
      .arrangeParam()
      .arrangeKeyValue(PARAM_MAP)
      .act()
      ...
```

---

#### [← back](../../README.md)