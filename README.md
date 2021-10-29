# Centric_Project
It is a spring boot application. It is an api having two rest end points and the server will by default run in port 8080 and is connect to MySql DB with product as db schema.

### Prerequisite

1. Java version 11
2. Maven 3.6.3



### Steps to run

- Load the project in sprint tool suite.
- Open terminal run `mvn clean install`  command to get all the dependencies.





### Product Api Specification

- ##### Insert

http://localhost:8080/v1/products/insert 

Method POST

Sample request body

```json
{
    "name": "Blue Shirt",
    "description": "Blue hugo boss shirt",
    "brand": "Hugo Boss",
    "tags": [
        "blue",
        "shirt"
    ],
    "category": "apparel"
}
```



Sample Response

```json
[
    {
        "id": "a51adf80-4eeb-434f-86d0-338b340ba310",
        "name": "Blue Shirt",
        "description": "Blue hugo boss shirt",
        "brand": "Hugo Boss",
        "tags": [
            "blue",
            "shirt"
        ],
        "category": "apparel",
        "created_at": "2021-10-28T08:35:05Z"
    }
]
```



- #### Get Product based on exact match of query

http://localhost:8080/v1/products/category?categoryValue=

An example url 

Sample request

http://localhost:8080/v1/products/category?categoryValue=apparel

Method GET

Sample response 

```json
[
    {
        "id": "a51adf80-4eeb-434f-86d0-338b340ba310",
        "name": "Blue Shirt",
        "description": "Blue hugo boss shirt",
        "brand": "Hugo Boss",
        "tags": [
            "blue",
            "shirt"
        ],
        "category": "apparel",
        "created_at": "2021-10-28T08:35:05Z"
    },
    {
        "id": "faaee69e-c140-400c-8201-3fce11f08494",
        "name": "Blue Shirt",
        "description": "Blue hugo boss shirt",
        "brand": "Hugo Boss",
        "tags": [
            "blue",
            "shirt"
        ],
        "category": "apparel",
        "created_at": "2021-10-28T11:26:18Z"
    },
    {
        "id": "37ac639a-cc91-4446-8287-2d45af4ee580",
        "name": "Red Shirt",
        "description": "Blue hugo boss shirt",
        "brand": "Hugo Boss",
        "tags": [
            "blue",
            "shirt"
        ],
        "category": "apparel",
        "created_at": "2021-10-28T11:26:26Z"
    },
    {
        "id": "6067d1c1-2ba3-4bf1-88f1-30bd46525923",
        "name": "Black Shirt",
        "description": "Blue hugo boss shirt",
        "brand": "Hugo Boss",
        "tags": [
            "blue",
            "shirt"
        ],
        "category": "apparel",
        "created_at": "2021-10-28T11:26:30Z"
    }
]
```

