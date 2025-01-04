#### [← back](../../README.md)

### Arrange Body Binary

- [Single File](#file)
- [Multiple File](#file-multiple)
- [File as List](#file-list)

---

### File

In this example, a single file is added to the request body for upload.

```
 post()
      .arrange()
      .arrangeUrl(POST_EXAMPLE)
      .arrangeBody()
      .arrangeFile(FILE_1)
      .act()
      ...
```

---

### File (Multiple)

This example shows how to add multiple files to the request body.

```
 post()
      .arrange()
      .arrangeUrl(POST_EXAMPLE)
      .arrangeBody()
      .arrangeFile(FILE_1)
      .arrangeFile(FILE_2)
      .act()
      ...
```

---

### File (List)

In this example, multiple files are uploaded as a list.

```
 post()
      .arrange()
      .arrangeUrl(POST_EXAMPLE)
      .arrangeBody()
      .arrangeFiles(List.of(FILE_1, FILE_2))
      .act()
      ...
```

---

#### [← back](../../README.md)