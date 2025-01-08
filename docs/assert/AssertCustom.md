#### [← back](../../README.md)

### Assert Custom

- [Result Matcher](#result-matcher)

---

### Result Matcher

In this example, the **`assertCustomResultMatcher`** method demonstrates how to use custom
assertions through the `ResultMatcher` interface.

In the provided code snippet, a custom assertion is made to check for the existence
of a cookie named `"sessionId"` in the response. The syntax `cookie().exists("sessionId")` is a
specific `ResultMatcher` that verifies if the specified cookie is present in the response.

```  

get()
    ...
    .act()
    .actPerform()
    .asserts()
    .assertCustom()
    .assertCustomResultMatcher(cookie().exists("sessionId"))

```

---

#### [← back](../../README.md)