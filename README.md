# maersk-customer-portal

As a Maersk customer
I want to be able to book containers with Maersk
So that I can deliver cargo to my customers
The aim of this story is to develop two microservice endpoints that enable a 
customer to book a container with Maersk. There is no need to consider 
authentication or authorization mechanisms for this task.
One endpoint will establish if there are enough containers of an appropriate size 
and type at a given container yard to meet the customers booking requirements. 
The service acts as a proxy and will call another external service to fetch the 
data. 
The other endpoint will receive a booking request and store the data in a 
Cassandra database table for later processing by other systems.
