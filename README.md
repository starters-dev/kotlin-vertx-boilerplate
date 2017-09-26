# This is just simple hello-world-app written in Kotlin using Vertx

#### Prerequisites
* Make sure you have [JAVA installed](https://www.java.com/en/download/help/download_options.xml)
* Install [SDKMAN](http://sdkman.io/install.html)
* Install [Kotlin lang](https://kotlinlang.org/docs/tutorials/command-line.html#downloading-the-compiler) using **SDKMAN**
* Install [Gradle](https://gradle.org/install/) using **SDKMAN**

#### How to run:

* Clone the repo: `git clone https://github.com/kanzitelli/kotlin-vertx-boilerplate.git`
* Rename the folder if needed: `mv kotlin-vertx-boilerplate new_name`
* `$ cd new_name/`
* `$ gradle build`
* `$ gradle run`
* or to build a Jar and run in production mode
```sh
$ gradle shadowJar
$ java -cp build/libs/kotlin-vertx-boilerplate-all.jar io.vertx.core.Launcher n com.kanzitdinov.boilerplate.verticles.MainVerticle -conf conf/production.json
``` 

#### API ENDPOINTS
- `/home` - returns `html` page rendered using `Thymeleaf` template engine.
- `/home.json` - returns `json` with information gathered from this [link](https://api.myjson.com/bins/6qk2h) with current time added to JSON response.
- `/hello` - returns simple response with `Hello, World!`.
- `/hello.json` - returns `json` with simple message `{ message: "Hello, World!" }`

#### What's included:

* Building a Web application on top of [Vert.x](http://vertx.io/)
* Querying remote services with the [Fuel](https://github.com/kittinunf/Fuel)
* Using [Kovenant](https://github.com/mplatvoet/kovenant) Promises for doing asynchronous tasks
* Using [Gradle](https://gradle.org/) for enabling efficient workflow
* Using [Thymeleaf](http://www.thymeleaf.org/) templates
* Serving JSON with [Jackson Kotlin module](https://github.com/FasterXML/jackson-module-kotlin) and [Kotson](https://github.com/SalomonBrys/Kotson)
* Logging with [Logback](http://logback.qos.ch/)

##### What to add `Maybe ...`:
- add `Vue.JS` support, improve html rendering part
- transfer all handlers to `Handlers` package
- add testing with [KotlinTest](https://github.com/kotlintest/kotlintest)
- add `MongoDB` with other endpoints
- add log-in / sign-up logic using `MongoDB`
- ...
