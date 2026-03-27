# Design Notes

- **ArrayList** is used instead of arrays for dynamic resizing and convenient add/remove operations.
- **Static members** in `IdGenerator` centralize ID generation for Students, Courses, and Enrollments.
- **Inheritance**:
  - `Person` is the base type.
  - `Student` and `Trainer` extend `Person`, reusing id/name/email fields.
- **Encapsulation**:
  - All fields are private with public getters/setters.
- **Separation of Concerns**:
  - `entity` for data classes.
  - `service` for business logic.
  - `ui` for console interactions.
  - `exception` for domain errors.
  - `util` for helpers.