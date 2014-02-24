MapDbDemo
=========

[MapDB](http://www.mapdb.org) demo application.

In prototypical state.

To run use the tool [RandomDocuments](https://github.com/rodiongork/RandomDocuments)
to generate a set of random documents which should be uploaded to database:

    $ java -jar RndDocs.jar --files=10000 --maxSize=1000000 testdata
    $ mkdir testdb
    $ java -jar target/MapDbDemo.jar

The files from `testdata` folder created by first command are uploaded to
database in the `testdb` folder created by second command.
