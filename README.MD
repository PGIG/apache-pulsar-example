step1: install apachepulsar/pulsar-all

docker run --name pulsar -dit -p 8080:8080 -p 6650:6650 apachepulsar/pulsar-all bin/pulsar standalone

step2: install apachepulsar/pulsar-dashboard

docker run --name pulsar-dashboard -dit -p 80:80 -e SERVICE_URL=http://pulsar:8080 --link pulsar apachepulsar/pulsar-dashboard

step3: test pulsar clsuter

docker exec -it pulsar bash bin/pulsar-client produce my-topic --messages "hello-pulsar"

step4: access to http://localhost/