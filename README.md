# Spring MVC Demonstration Application
This is a demonstration for various technologies in web development.
It covers a variety of topics focusing on Spring MVC, javascript front end coding, and websockets.


It is housed on heroku at <a href="http://donhenton-springmvc3.herokuapp.com/">
http://donhenton-springmvc3.herokuapp.com/</a>

## Building
* mvn clean install -DskipTests=true


## Running Locally for CSS development

* start local postgres (set up with jdatabase set up see docs/db_stuff.txt)
* mvn clean jetty:run-exploded -Pcss -DskipTests=true in one window
* in another window: gulp watch-assets from project root
* serves at http://localhost:8080
* activate the livereload plugin on chrome
* sass modifications are made at src/sass and need to be forced the first time



## CSS Processing
The gulp default task will rebuild the sass--> css file main.css. The main.css
is part of the source tree, so any changes for css will be a multi step process:

* develop locally using profile -Pcss
* run gulp default task
* commit the new css file


This will handle css, js and jsp edits


## Running locally (in general)

```
java -jar target/dependency/jetty-runner.jar target/SpringMVC3-app-1.0-SNAPSHOT.war
```

OR (MAC)

```
/Library/Java/JavaVirtualMachines/jdk1.7.0_51.jdk/Contents/Home/bin/java  -jar target/dependency/jetty-runner.jar target/SpringMVC3-app-1.0-SNAPSHOT.war
```
served at http://localhost:8080





