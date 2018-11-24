OpenLDBWS Java Example
======================

This repository contains examples of how to use the National Rail Live
Departure Boards Web Service (OpenLDBWS), located at the following URL:

* https://lite.realtime.nationalrail.co.uk/OpenLDBWS/

To use the service, you will need a token which is available by
signing up at the following URL:

* https://realtime.nationalrail.co.uk/OpenLDBWSRegistration/

Edit ```GetDepartureBoardExample``` and set LDB_TOKEN to your own token.

Next, generate a set of classes from the WSDL file by running:

```mvn generate-sources```

This will create a number of files in src/main/java/com/thalesgroup
which you can use in your own code to call the OpenLDBWS.

Runing ```GetDepartureBoardExample``` will show you departures for
London Euston.

Updating the WSDL
-----------------

Periodically, a new version of the WSDL will be released at:

* https://lite.realtime.nationalrail.co.uk/OpenLDBWS/

This code is written for version 2017-10-01.  To update it to use a
later version, edit ```pom.xml``` and change the URL for the WSDL
inside the ```plugins``` tag.  

Support
-------

For support and questions with using the OpenLDBWS, please use the
forum at the following URL:
 
 * https://groups.google.com/group/openraildata-talk
