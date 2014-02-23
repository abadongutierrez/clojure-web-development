# picture-gallery

An example web application using Clojure.

## Prerequisites

You will need [Leiningen][1] 1.7.0 or above installed.

[1]: https://github.com/technomancy/leiningen

## Running

To start a web server for the application, run:

    lein ring server

Or start REPL:

```
lein repl
user=> (use 'picture-gallery.repl)
user=> (start-server)
(start-server)
picture-gallery is starting
2014-02-23 00:34:29.019:INFO:oejs.Server:jetty-7.6.8.v20121106
2014-02-23 00:34:29.151:INFO:oejs.AbstractConnector:Started SelectChannelConnector@0.0.0.0:8080
Started server on port 8080
You can view the site at http://localhost:8080
```

## Tools, Libs, Framewors

### Compojure

https://github.com/weavejester/compojure

Compojure is a small routing library for Ring that allows web applications to be composed of small, independent parts.

### lib-noir

https://github.com/noir-clojure/lib-noir

A set of utilities and helpers for building ring apps.

## License

Copyright © 2014 FIXME
