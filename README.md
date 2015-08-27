# README

This is an implementation of Inventory Allocator as a lightweight HTTP server, and Data Source as a sample HTTP client.
Stream is expected as an http request. Single stream can contain multiple orders.
The required output will be shown in the console standard out of the Inventory Allocator


## Build
### Requirements
 - JRE 1.8
 - Maven

### Steps
1. Clone this repo
2. run the following command:`mvn clean install assembly:assembly -DdescriptorId=jar-with-dependencies`
3. verify that in `target/` you see "shipwire-int-1.0-jar-with-dependencies.jar"

### Run Inventory Allocator web server
1. go to `target/` directory
2. run the following command: `java -cp ./shipwire-int-1.0-jar-with-dependencies.jar com.misha.InventoryServer ..\inventory.dat`

**Note:** `..\inventory.dat` contains the inventory, in the format that was specified in the assignment
3. verify that the server started successfully by putting the following into your browser: `http://localhost:9090/hello`

### Run sample Data Source web client
1. go to `target/` directory
2. run the following command: `java -cp shipwire-int-1.0 -jar-with-dependencies.jar com.misha.DataSource`
3. observe output on the Inventory Allocator console

### Submit orders through HTTP
Send POST to `http://localhost:9090/order`
where the request body should look something like:
`{
     "Orders": [
         {
             "Header": 1,
             "Lines": [
                 {
                     "Product": "A",
                     "Quantity": "1"
                 },
                 {
                     "Product": "C",
                     "Quantity": "1"
                 }
             ]
         },
         {
             "Header": 2,
             "Lines": [
                 {
                     "Product": "E",
                     "Quantity": "5"
                 }
             ]
         }
     ]
 }`

