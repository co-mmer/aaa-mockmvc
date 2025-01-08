#### [← back](../../README.md)

### Answer Section

- [Response as Result Actions](#retrieve-response-as-result-actions)
- [Response as String](#retrieve-response-as-string)
- [Response as Object](#retrieve-response-as-object)
- [Response as List](#retrieve-response-as-list)
- [Response as Set](#retrieve-response-as-set)
- [Response as Map](#retrieve-response-as-map)
- [Response as Byte Array](#retrieve-response-as-byte-array)
- [Response Header](#retrieve-response-header)
- [Response Void](#retrieve-response-as-void)

---

### Retrieve Response as Result Actions

In this example, the response is captured as ResultActions using the `answerAsResultActions()`
method. This approach is useful for accessing advanced testing features, such as chaining additional
assertions directly on the response.

```  

var answer = get()
                ...
                .act()
                .actPerform()
                .answer()
                .answerAsResultActions();

```

---

### Retrieve Response as String

In this example, the response is retrieved as a plain string using the `answerAsString()` method.
This
method is ideal when the response data is plain text or JSON that will be processed separately,
without needing deserialization into a specific object.

```  

var answer = get()
                ...
                .act()
                .actPerform()
                .answer()
                .answerAsString();

```

---

### Retrieve Response as Object

The `answerAsObject` method deserializes the HTTP response content directly into an instance of the
specified class type `(T)`. In this case, it converts the response data into an object of type
`DemoObject`. This allows for streamlined handling of the response, as it is returned as a fully
typed object, eliminating the need for additional parsing or casting steps.

```  

DemoObject demo = get()
                   .arrange()
                   .arrangeUrl(GET_DEMO)
                   .act()
                   .actPerform()
                   .answer()
                   .answerAsObject(DemoObject.class);

```

---

### Retrieve Response as List

The `answerAsList` method deserializes the HTTP response content directly into a list of the
specified class type `(T)`. In this case, it converts the response data into a list of `DemoObject`
objects. This allows for streamlined handling of the response, as it is returned as a fully typed
list, eliminating the need for additional parsing or casting steps.

```  

List<DemoObject> demo = get()
                         .arrange()
                         .arrangeUrl(GET_DEMO)
                         .act()
                         .actPerform()
                         .answer()
                         .answerAsList(DemoObject.class);

```

---

### Retrieve Response as Set

The `answerAsSet` method deserializes the HTTP response content directly into a set of the specified
class type `(T)`. In this case, it converts the response data into a set of `DemoObject` objects.
This allows for streamlined handling of the response, as it is returned as a fully typed set,
eliminating the need for additional parsing or casting steps.

```  

Set<DemoObject> demo = get()
                          .arrange()
                          .arrangeUrl(GET_DEMO)
                          .act()
                          .actPerform()
                          .answer()
                          .answerAsSet(DemoObject.class);

```

---

### Retrieve Response as Map

The `answerAsMap` method deserializes the HTTP response content directly into a map, where the
specified key and value types are provided as parameters `(K and V)`. In this case, it converts the
response data into a map with `Integer` as the key type and `DemoObject` as the value type. This
allows for streamlined handling of the response, as it is returned as a fully typed map, eliminating
the need for additional parsing or casting steps.

```  

Map<Integer, DemoObject> demoA = get()
                                   .arrange()
                                   .arrangeUrl(GET_DEMO)
                                   .act()
                                   .actPerform()
                                   .answer()
                                   .answerAsMap(Integer.class, DemoObject.class);

```

---

### Retrieve Response as Byte Array

In this example, the response is retrieved as a byte array using the `answerAsByte()` method. This
method is particularly useful for handling binary data, such as file downloads or media content,
where the response content is best represented in raw byte form.

```  

var answer = get()
               ...
               .act()
               .actPerform()
               .answer()
               .answerAsByte();

```

---

### Retrieve Response Header

This method retrieves the value of a specific response header.

```  

var answer = get()
                ...
                .act()
                .actPerform()
                .answer()
                .answerHeader(KEY);

```

---

### Retrieve Response as Void

This method retrieves the result of the HTTP request without returning any content. It is used when
the response is not needed.

```  

get()
    ...
    .act()
    .actPerform()
    .answer()
    .answerVoid();

```

---

#### [← back](../../README.md)