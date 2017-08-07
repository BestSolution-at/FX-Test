# FX-Test

[![Build Status](https://travis-ci.org/BestSolution-at/FX-Test.svg?branch=master)](https://travis-ci.org/BestSolution-at/FX-Test)

FX-Test is a library to implement JUnit-Test for JavaFX applications

## Why FX-Test

FX-Test is similar to [TestFX][1] in its purpose but there are multiple reasons why FX-Test has been created:
* There are License problems hence [TestFX][1] can not be used in [Eclipse.org][3] projects like [e(fx)clipse][4]
* Test-FX' bootstrap process is not compatible with e4 OSGi-Applications (with some trickery you can get it running)

While the 2nd problem could most likely get addressed by contributing to [TestFX][1] the first one is an unresolvable
blocker and so we are NOT suffering from [NIHS][5].

## Usage

### Plain Java Applications

#### Testing components

To test components the simplest way is to subclass `at.bestsolution.fx.test.junit.FXComponentTest` and implement its `createComponent` method.

Let's suppose you have a `LoginComponent` like this:
```java
public class LoginComponent {
  private TextField username;
  private PasswordField password;
  private Button login;
  
  public LoginComponent(BorderPane parent) {
    // ....
  }
}
```

You create a subclass of `FXComponentTest` like this:
```java
public class LoginComponentTest extends FXComponentTesty<BorderPane,LoginComponent> {
  public LoginComponentTest() {
    super(BorderPane.class);
  }

  @Override
  protected LoginComponent createComponent(BorderPane parent) {
    return new LoginComponent(parent);
  }
}
```

And start writing you test methods like this:
```java
@Test
public void simpleLoginTest() {
  rcontroller().cssFirst(".text-field").get().typeText("tom@bestsolution.at");
  rcontroller().cssFirst(".password-field").get().typeText("test");
  rcontroller().cssFirst(".button").get().click();
}
```

Finally you need to make a choice:
* You run the complete Test-Class with a specialized JUnit-Runner named `at.bestsolution.fx.test.junit.FXRunner`
* You annotate your Test-Methods with `@at.bestsolution.fx.test.junit.FXTest`

In general we recommend using the `FXRunner` but eg if you want to use another JUnit-Runner like `org.junit.runners.Parameterized` the FXTest-Rule is be a handy thing.

### e4 on JavaFX Applications

If you use the standard-layout proposed by the e(fx)clipse development team your application structure should look like this:
* my.app
* my.app.feature
* my.app.product
* my.app.releng

To start adding JUnit-Tests you create a 5th project `my.app.junit` and add a pom.xml like this:

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <name>My e4 app - app junit bundle</name>
  <groupId>my.app</groupId>
  <artifactId>my.app.junit</artifactId>
  <packaging>eclipse-test-plugin</packaging>

  <parent>
    <groupId>my.app</groupId>
    <artifactId>my.app.releng</artifactId>
    <relativePath>../my.app.releng/pom.xml</relativePath>
    <version>1.0.0-SNAPSHOT</version>
  </parent>

  <build>
    <resources>
      <resource>
        <directory>.</directory>
        <includes>
          <include>META-INF/</include>
        </includes>
      </resource>
    </resources>
    <plugins>
      <plugin>
        <groupId>org.eclipse.tycho</groupId>
        <artifactId>tycho-surefire-plugin</artifactId>
        <version>${tycho-version}</version>
        <configuration>
          <argLine>-Dorg.osgi.framework.bundle.parent=ext</argLine>
        </configuration>
      </plugin>
      <plugin>
        <groupId>org.eclipse.tycho</groupId>
        <artifactId>target-platform-configuration</artifactId>
        <version>${tycho-version}</version>
        <configuration>
          <dependency-resolution>
            <extraRequirements>
              <requirement>
                <type>eclipse-feature</type>
                <id>org.eclipse.fx.runtime.e4fx.feature</id>
                <versionRange>0.0.0</versionRange>
              </requirement>
              <requirement>
                <type>eclipse-feature</type>
                <id>my.app.feature</id>
                <versionRange>0.0.0</versionRange>
              </requirement>
            </extraRequirements>
          </dependency-resolution>
        </configuration>
      </plugin>
    </plugins>
  </build>
</project>
```
To implement the JUnit-Test you subclass `at.bestsolution.fx.test.e4.junit.E4JunitTestCase` like this:

```java
@RunWith(FXRunner.class)
class ApplicationTest extends E4JunitTestCase {
  public ApplicationTest() {
    super("my.app.product");
  }
}
```

Now you can start implementing your test methods.

## Other OpenSource JavaFX TestFrameworks / Tools

* [TestFX][1] - JUnit Test Library similar to FX-Test
* [Jubula][2] - Test Tool with a visual Test-Designer

[1]: https://github.com/TestFX/TestFX
[2]: http://eclipse.org/jubula
[3]: https://www.eclipse.org/
[4]: http://efxclipse.org/
[5]: https://de.wikipedia.org/wiki/Not-invented-here-Syndrom
