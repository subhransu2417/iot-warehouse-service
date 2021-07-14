# iot-warehouse-service
A shop in London has 2 million IoT tracking devices in the warehouse for sale, of which half needs configured to meet UK standards. A configured device will have a status "READY" and an ideal temperature between (-25'C to 85'C).
The configuration process requires a given IoT device to be associated with a SIM (Subscriber Identification Module) card. The SIM card holds information such as:
•	SIM ID - uniquely identify the SIM card.
•	Operator code – uniquely identify a mobile operator
•	Country – country name, e.g. Italy
•	Status – devices status can be Active, Waiting for activation, Blocked or Deactivated.
 
The shop can sell a device only if it meets the UK government's industry standard.

The service provide below functionalities:
a.	Returns all devices in the warehouse that are waiting for activation. 
b.	Management endpoints that enable the shop manager to remove or update a device configuration status.
c.	Returns an ordered result of devices available for sale.
d.	Expected response format should be in JSON.

Technical Stack
 1. Spring Boot
 2. Postgresql
 3. Spring Basic Authentication
 4. Spring data validation
 5. Spring test with Mockito
 
 Start application using below command:
 
 mvn spring-boot:run
 
 