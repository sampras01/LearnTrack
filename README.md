# LearnTrack - Student & Course Management (Console)

LearnTrack is a console-based Core Java project to practice:
- Java basics, classes, objects
- Encapsulation, inheritance, simple polymorphism
- ArrayList for in-memory storage
- Basic exception handling
- Menu-driven console UI

## How to Compile

From project root:

```bash
javac -d build/classes -sourcepath src src/com/airtribe/learntrack/**/*.java
```

## How to Run

```bash
cd build/classes
java com.airtribe.learntrack.ui.Main
```

Or build a JAR and run with `java -jar jar/LearnTrack.jar`.