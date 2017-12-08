# Vert.x + Kotlin (w/ coroutines) boilerplate 🛠

### Prerequisites
* Make sure you have [JAVA installed](https://www.java.com/en/download/help/download_options.xml)
* Install [SDKMAN](http://sdkman.io/install.html)
* Install [Kotlin lang](https://kotlinlang.org/docs/tutorials/command-line.html#downloading-the-compiler) using **SDKMAN**
* Install [Gradle](https://gradle.org/install/) using **SDKMAN**

### How to run:

* Clone the repo: `git clone https://github.com/kanzitelli/kotlin-vertx-boilerplate.git`
* Rename the folder if needed: `mv kotlin-vertx-boilerplate new_name` (*note:* if you want to rename the package then it would be safer to do it directly in the IntelliJ Idea)
* `$ cd new_name/`
* `$ gradle build`
* `$ gradle run`
* or to build a Jar and run in production mode
```sh
$ gradle shadowJar
$ java -jar build/libs/app-shadow.jar
``` 

### Heroku deployment
* `$ heroku login`
* `$ heroku create`
* `$ git push heroku master`
* `$ heroku open`
* **NOTE:** Don't forget to go to [Heroku dashboard](https://dashboard.heroku.com) → your app → Settings, and then add Config Vars: 
    * `PORT=80` 
    * `HOST=https://your-app-name.herokuapp.com` 

For more information check [this link](https://devcenter.heroku.com/articles/getting-started-with-kotlin).

### API ENDPOINTS
* `/home{.json}` - returns `json` with information gathered from this [link](https://api.myjson.com/bins/6qk2h) with current time added to JSON response.
* `/hello{.json}` - returns `json` with simple message `{ message: "Hello, World!" }`.
* `/todos` - simple local (data stored in an array) implementation of todos CRUD actions .

### What's included:

* Building a Web application on top of [Vert.x](http://vertx.io/)
* [Kotlin coroutines](http://vertx.io/docs/vertx-lang-kotlin-coroutines/kotlin/) 🎉 
* Querying remote services with the [Retrofit2](https://github.com/square/retrofit) (+ [Kotlin coroutines await() for Retrofit2 Call](https://github.com/gildor/kotlin-coroutines-retrofit))
* Using [Gradle](https://gradle.org/) for enabling efficient workflow
* Manipulating JSON with [Kotson](https://github.com/SalomonBrys/Kotson)
* Logging with [Logback](http://logback.qos.ch/)