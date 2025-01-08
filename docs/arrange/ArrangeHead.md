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

In this example, the Accept header is set, indicating that the client expects a response in JSON
format.

```
 get()
      .arrange()
      .arrangeUrl(GET_EXAMPLE)
      .arrangeHead()
      .arrangeAccept(APPLICATION_JSON)
      .act()
      ...
```

---

### Auth

This example demonstrates how to add an authorization token to the request headers.

```
 get()
      .arrange()
      .arrangeUrl(GET_EXAMPLE)
      .arrangeHead()
      .arrangeAuth(TOKEN)
      .act()
      ...
```

---

### Content-Type

In this example, the Content-Type header is defined.

```
 get()
      .arrange()
      .arrangeUrl(GET_EXAMPLE)
      .arrangeHead()
      .arrangeContentType(APPLICATION_JSON)
      .act()
      ...
```

---

### Key-Value (Single)

This example shows how to set a single custom header.

```
 get()
      .arrange()
      .arrangeUrl(GET_EXAMPLE)
      .arrangeHead()
      .arrangeKeyValue(HEAD_X_CUSTOM_1, HEAD_X_CUSTOM_VALUE_1)
      .act()
      ...
```

---

### Key-Value (Multiple)

This example demonstrates how to set multiple custom headers in a single request.

```
 get()
      .arrange()
      .arrangeUrl(GET_EXAMPLE)
      .arrangeHead()
      .arrangeKeyValue(HEAD_X_CUSTOM_1, HEAD_X_CUSTOM_VALUE_1)
      .arrangeKeyValue(HEAD_X_CUSTOM_2, HEAD_X_CUSTOM_VALUE_2)
      .act()
      ...
```

---

### Key-Value (Map)

This example shows how to use a map to set multiple custom headers.

```
 get()
      .arrange()
      .arrangeUrl(GET_EXAMPLE)
      .arrangeHead()
      .arrangeKeyValue(HEAD_X_CUSTOM_MAP)
      .act()
      ...
```

---

#### [← back](../../README.md)