# multi-source-aggregator

## Introduce 
We could aggrete one or more numbers in first phase we used `BigDecimal` type, 
which is using for the finance logic.

First aggregation is ADDING two or more numbers.

## Application

Main logic is in class `Application`.

`connectors` - feeding data from 3 sources:
 + `ExternalApiConnector` - getting mid value from rates from polish national bank NBP, 
 from table A, for the most popular currencies like f.e. USD, GBP and so on 
 + `FileConnector` - getting one of value from file (with specific file name) radomly. 
 Line of file is splitting by comma and more than one space.
 + `RandomConnector` - getting one of `BigDecimal` number from 0 to selected range.  

`models` - we are using them to map data from NBP API.

`service` - one service for aggregation, which could be developing in the future
for next aggregation. For now is only summary service.

## Tests

I wrote few test for main logic in this application. To run tests please execute command:
```
mvn test
```