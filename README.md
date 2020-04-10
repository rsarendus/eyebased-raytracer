# Eye-based Raytracer

This is just a hobby project - a re-write of an old hobby project that I was working on many years ago. The current primary purpose of this project is to practice writing clean and easy to understand (and hopefully performant) Java code, nothing more.

## Build

Building the project requires at least **JDK 11** and [Apache Maven](https://maven.apache.org/).

1. Fetch and build [vecmath](https://github.com/rsarendus/vecmath) library

2. Fetch the project from GitHub:
<br>`git clone https://github.com/rsarendus/eyebased-raytracer.git`

3. Navigate into the project's root directory:
<br>`cd eyebased-raytracer`

3. Build the project using Maven:
   * Compile and package as a JAR into the project's `target` directories:
   <br>`mvn clean package`
   * Or compile, package and install into your local repository:
   <br>`mvn clean install`
   * Or for more options read about [Maven Build Lifecycle](https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html)
