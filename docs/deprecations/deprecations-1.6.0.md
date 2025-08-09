# 🗑️ Deprecations in 1.6.0

Version **1.6.0** introduces a new unified naming convention for all `assert` methods to improve
readability and consistency.  
All new method names start with the prefix `assertContent...`.

The old method names are **deprecated** as of 1.6.0 and will be removed in **2.0.0**.

### New Naming Convention

- Consistent `assertContent...` prefix for all assertion methods.
- Same method names across different data types (String, Byte, Class, Collection, Map).
- More descriptive method names for matching, containment, and equality checks.

## Deprecated Methods and Their Replacements

| Old Method Name                    | New Method Name                 |
|------------------------------------|---------------------------------|
| `assertStringNotEmpty`             | `assertContentIsNotEmpty`       |
| `assertStringEmpty`                | `assertContentIsEmpty`          |
| `assertStringLength`               | `assertContentLength`           |
| `assertStringEquals`               | `assertContentEquals`           |
| `assertByteNotEmpty`               | `assertContentIsNotEmpty`       |
| `assertByteEmpty`                  | `assertContentIsEmpty`          |
| `assertByteLength`                 | `assertContentLength`           |
| `assertByteEquals`                 | `assertContentEquals`           |
| `assertClassNotEmpty`              | `assertContentIsNotEmpty`       |
| `assertClassEmpty`                 | `assertContentIsEmpty`          |
| `assertClassEquals`                | `assertContentEquals`           |
| `assertClassMatchAll`              | `assertContentMatchAll`         |
| `assertClassMatchAny`              | `assertContentMatchAny`         |
| `assertClassMatchNone`             | `assertContentMatchNone`        |
| `assertCollectionNotEmpty`         | `assertContentIsNotEmpty`       |
| `assertCollectionEmpty`            | `assertContentIsEmpty`          |
| `assertCollectionSize`             | `assertContentSize`             |
| `assertCollectionEquals`           | `assertContentEquals`           |
| `assertCollectionContains`         | `assertContentContains`         |
| `assertCollectionContainsAnyOrder` | `assertContentContainsAnyOrder` |
| `assertCollectionNotContains`      | `assertContentNotContains`      |
| `assertCollectionMatchAll`         | `assertContentMatchAll`         |
| `assertCollectionMatchAny`         | `assertContentMatchAny`         |
| `assertCollectionMatchNone`        | `assertContentMatchNone`        |
| `assertMapNotEmpty`                | `assertContentIsNotEmpty`       |
| `assertMapEmpty`                   | `assertContentIsEmpty`          |
| `assertMapSize`                    | `assertContentSize`             |
| `assertMapEquals`                  | `assertContentEquals`           |

## Deprecated Without Replacement

The following methods are deprecated in **1.6.0** and have **no replacement**.

- `actPerform` — obsolete because the action is now fully executed by calling `act` alone. The
  separate `actPerform` step is no longer needed.
- `assertCustom` — removed without replacement.

## Removal Timeline

- **Deprecated in:** 1.6.0
- **Will be removed in:** 2.0.0