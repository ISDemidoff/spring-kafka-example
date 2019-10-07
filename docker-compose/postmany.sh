#!/bin/bash
for i in {1..10}
do
  # Copy from Postman prepared query
  curl -X POST "http://localhost:8000/kafka?msg=Hello%20$i" -H 'Accept: */*' -H 'Accept-Encoding: gzip, deflate' -H 'Cache-Control: no-cache' -H 'Content-Length: 0' -H 'Host: localhost:8000' -H 'cache-control: no-cache'
done
