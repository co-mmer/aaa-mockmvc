#### [← back](../../README.md)

### Arrange Body Binary

- [File (Single)](#file)
- [File (Multiple)](#file-multiple)
- [File (List)](#file-list)

---

### File

This example demonstrates how to send a single file as multipart form data in a structured
request test setup. The file is represented as a MockMultipartFile and handled by the controller
using @RequestPart.

First, the following constants are defined:

``` java
  public static final String PART_FILE_1 = "file1";
  public static final MockMultipartFile FILE_1 = create(PART_FILE_1);
    
  public static final String BODY_FILE_1 = "simple/bodyFile1";
  public static final String ENDPOINT_BODY_FILE_1 = BASE + BODY_FILE_1;
```

The controller defines an endpoint that receives a single file part:

``` java 
  @PostMapping(BODY_FILE_1)
  public ResponseEntity<Void> exampleBodyFile1(
      @RequestPart(PART_FILE_1) MultipartFile file1) {

    return ResponseEntity.ok().build();
  }
```

The corresponding test sets up the request by attaching the file part:

``` java
 post()
      .arrange()
      .arrangeUrl(ENDPOINT_BODY_FILE_1)
      .arrangeBody()
      .arrangeFile(FILE_1)
      .act()
      ...
```

---

### File (Multiple)

This example demonstrates how to send multiple files as multipart form data in a structured
request test setup. Each file is represented as a MockMultipartFile and bound to a separate
@RequestPart in the controller.

First, the following constants are defined:

``` java
  public static final String PART_FILE_1 = "file1";
  public static final String PART_FILE_2 = "file2";
  
  public static final MockMultipartFile FILE_1 = create(PART_FILE_1);
  public static final MockMultipartFile FILE_2 = create(PART_FILE_2);
  
  public static final String BODY_FILE_2 = "simple/bodyFile2";
  public static final String ENDPOINT_BODY_FILE_2 = BASE + BODY_FILE_2;
```

The controller defines an endpoint that receives multiple file parts:

``` java 
  @PostMapping(BODY_FILE_2)
  public ResponseEntity<Void> exampleBodyFile2(
      @RequestPart(PART_FILE_1) MultipartFile file1,
      @RequestPart(PART_FILE_2) MultipartFile file2) {

    return ResponseEntity.ok().build();
  }
```

The corresponding test arranges the request by attaching both file parts:

``` java
 post()
      .arrange()
      .arrangeUrl(ENDPOINT_BODY_FILE_2)
      .arrangeBody()
      .arrangeFile(FILE_1)
      .arrangeFile(FILE_2)
      .act()
      ...
```

---

### File (List)

This example demonstrates how to send a list of files in a multipart request using a single
@RequestPart with repeated keys.

First, the following constants are defined:

``` java
  public static final String PART_FILE = "file";
  public static final List<MockMultipartFile> FILE_LIST = List.of(create(PART_FILE), create(PART_FILE));
  
  public static final String BODY_FILE_LIST = "simple/bodyFileList";
  public static final String ENDPOINT_BODY_FILE_LIST = BASE + BODY_FILE_LIST;
```

The controller defines an endpoint that accepts a list of files under the same part name:

``` java 
  @PostMapping(BODY_FILE_LIST)
  public ResponseEntity<Void> exampleBodyFileList(
      @RequestPart(PART_FILE) List<MultipartFile> files) {

    return ResponseEntity.ok().build();
  }
```

The corresponding test arranges the request by attaching multiple files with the same part name:

``` java
 post()
      .arrange()
      .arrangeUrl(ENDPOINT_BODY_FILE_LIST)
      .arrangeBody()
      .arrangeFiles(FILE_LIST)
      .act()
      ...
```

---

#### [← back](../../README.md)