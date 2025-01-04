#### [← back](../../README.md)

### Assert Status

- [Literal](#literal)
- [Enum](#enum)
- [Ok](#is-ok)
- [Created](#is-created)
- [Accepted](#is-accepted)
- [Not Found](#is-not-found)
- [Client Error](#is-client-error)
- [Server Error](#is-server-error)
- [Redirect](#is-redirect)
- [Access Forbidden](#is-access-forbidden)
- [Access Unauthorized](#is-access-unauthorized)
- [Range](#in-range)

---

### Literal

In this example, the response status is asserted against a specific HTTP status code using
`assertStatus()`. This literal assertion is useful for cases where an exact status code, such as 200
for success, is expected and needs to be verified directly.

```
  get()
      ...
      .act()
      .actPerform()
      .asserts()
      .assertStatus()
      .assertStatus(200)
```

### Enum

In this example, the response status is asserted using an `HttpStatus` enum value with
`assertStatus()`. This approach allows for clearer and more expressive assertions, as common HTTP
statuses are referenced by their enum names (e.g., HttpStatus.OK), improving readability and
reducing potential for error with numeric codes.

```
  get()
      ...
      .act()
      .actPerform()
      .asserts()
      .assertStatus()
      .assertStatus(HttpStatus.OK)
```

---

### Is Ok

This example shows how to assert that the response status is 200 OK.

```
  get()
      ...
      .act()
      .actPerform()
      .asserts()
      .assertStatus()
      .assertStatusIsOk()
```

---

### Is Created

This example shows how to assert that the response status is 201 Created.

```
  get()
      ...
      .act()
      .actPerform()
      .asserts()
      .assertStatus()
      .assertStatusIsCreated()
```

---

### Is Accepted

This example shows how to assert that the response status is 202 Accepted.

```
  get()
      ...
      .act()
      .actPerform()
      .asserts()
      .assertStatus()
      .assertStatusIsAccepted()
```

---

### Is Not Found

This example shows how to assert that the response status is 404 Not Found.

```
  get()
      ...
      .act()
      .actPerform()
      .asserts()
      .assertStatus()
      .assertStatusIsNotFound()
```

---

### Is Client Error

This example shows how to assert that the response status is within the range of client errors (
400-499).

```
  get()
      ...
      .act()
      .actPerform()
      .asserts()
      .assertStatus()
      .assertStatusIsClientError()
```

---

### Is Server Error

This example shows how to assert that the response status is within the range of server errors (
500-599).

```
  get()
      ...
      .act()
      .actPerform()
      .asserts()
      .assertStatus()
      .assertStatusIsServerError()
```

---

### Is Redirect

This example shows how to assert that the response status indicates a redirection (3xx).

```
  get()
      ...
      .act()
      .actPerform()
      .asserts()
      .assertStatus()
      .assertStatusIsRedirect()
```

---

### Is Access Forbidden

This example shows how to assert that the response status is 403 Access Forbidden.

```
  get()
      ...
      .act()
      .actPerform()
      .asserts()
      .assertStatus()
      .assertStatusIsAccessForbidden()
```

---

### Is Access Unauthorized

This example shows how to assert that the response status is 401 Access Unauthorized.

```
  get()
      ...
      .act()
      .actPerform()
      .asserts()
      .assertStatus()
      .assertStatusIsAccessUnauthorized()
```

---

### In Range

This example shows how to assert that the response status is within a specific range.

```
  get()
      ...
      .act()
      .actPerform()
      .asserts()
      .assertStatus()
      .assertStatusInRange(400, 499)
```

---

#### [← back](../../README.md)
