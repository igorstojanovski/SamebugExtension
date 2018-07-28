[![Codacy Badge](https://api.codacy.com/project/badge/Grade/a50b6f76127c47f5a0075e43e8885b50)](https://www.codacy.com/app/igorce/SamebugExtension?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=igorstojanovski/SamebugExtension&amp;utm_campaign=Badge_Grade) 
[![Codacy Badge](https://api.codacy.com/project/badge/Coverage/a50b6f76127c47f5a0075e43e8885b50)](https://www.codacy.com/app/igorce/SamebugExtension?utm_source=github.com&utm_medium=referral&utm_content=igorstojanovski/SamebugExtension&utm_campaign=Badge_Coverage)

# SamebugExtension

[Samebug](https://samebug.io/) integration for [JUnit 5](https://junit.org/junit5/).

Samebug is a debugging assistant for developers. It analyzes JVM stack traces to provide deep insights, rich context and technical expertise on them.
# Latest Release
[Samebug JUnit5 Extension 1.0.0](https://github.com/igorstojanovski/SamebugExtension/releases/tag/1.0.0)

# Usage

In order to use the extension just add SamebguExtension.class as an extension to your test:
```
@ExtendWith(SamebugExtension.class)
public class SampleTest {

    @Test
    public void shouldCompareNumbers() {
        assertEquals(1, 3);
    }
}
```
Then if and when a test fails, the extension will take the stack trace, take it to Samebug for analysis and will generate a url where
one can see more details about the error and a possible solution for it.

The extension will log the url when you can all the additional info from the analysis:
```
Please visit https://samebug.io/searches/10116757 for more info.
```

## Requirements
- Java 10 is required but that is soon to change
- The URL that you need access to see the results is logged, not printed. Because of that you will need a SLF4J implementation on your class path. Logback being the default choice.

# License

Released under the terms of [Apache License 2.0](/LICENSE).

Samebug is a registered trademark of Samebug, Inc. Other names may be trademarks of their respective owners.
