#json-transformer

We could find lots of JSON transformer which does change the format of JSON into another file format, but no tools help you to change the actual structure of JSON into another required structure. 

This JSON Transformer helps to transform JSON from one form of structure into another structure form, with help of JSONPath tool (https://github.com/json-path/JsonPath). 

The configuration/template file is created using JSONPath notation to transform one structure of JSON file into any required structure. Configuration file is key value pair combination which is again constructed in JSON format, the key represents the output key and the value represents the JSON Path notation to pull the corresponding data from input JSON file.
The structure of transformation can be of combination of any simple string, object, and array. And as well the combination could be nested as well.

![Screenshot 1](https://github.com/sarathbabur/json-transformer/blob/master/img/Process.JPG)

## Execution: 
Import it as Maven project and simply run the Java programs inside “com.hcl.ers.json.transformer.examples” package. 

Below explained how to transform one form of simple Json into another form using configuration file.

## Input JSON
```
{
  "store": {
    "book": [
      {
        "category": "reference",
        "author": "Nigel Rees",
        "title": "Sayings of the Century",
        "price": 8.95
      },
      {
        "category": "fiction",
        "author": "Evelyn Waugh",
        "title": "Sword of Honour",
        "price": 12.99
      },
      {
        "category": "fiction",
        "author": "Herman Melville",
        "title": "Moby Dick",
        "isbn": "0-553-21311-3",
        "price": 8.99
      },
      {
        "category": "fiction",
        "author": "J. R. R. Tolkien",
        "title": "The Lord of the Rings",
        "isbn": "0-395-19395-8",
        "price": 22.99
      }
    ],
    "bicycle": {
      "color": "red",
      "price": 19.95
    }
  },
  "expensive": 10
}
```

## 1. String
The configuration file can be constructed using sequence of String notation, based on the notation the corresponding data is pulled from input JSON file constructed in the output file

### Configuration :-
```
{
  "Book Name": "$.store.book[*].price",
  "Bicycle Color": "$.store.bicycle.color",
  "Expense Rate": "$.expensive"
}
```
### Output :-
```
{
  "Bicycle Color": "red",
  "Book Name": [
    8.95,
    12.99,
    8.99,
    22.99
  ],
  "Expense Rate": 10
}
```

## 2. Object
The configuration file can be constructed using JSON object which can contains combination string or objects. 

### Configuration :-
```
{
  "Store": {
    "Book": {
      "Book Name": "$.store.book[*].author",
      "Book Details": "$.store.book[*].price"
    }
  },
  "Bicycle Color": "$.store.bicycle.color",
  "Expense Rate": "$.expensive"
}
```
### Output :-
```
{
  "Bicycle Color": "red",
  "Store": {
    "Book": {
      "Book Details": [
        8.95,
        12.99,
        8.99,
        22.99
      ],
      "Book Name": [
        "Nigel Rees",
        "Evelyn Waugh",
        "Herman Melville",
        "J. R. R. Tolkien"
      ]
    }
  },
  "Expense Rate": 10
}
```

## 3. Array
Constructing simple array under key “Book”. And the value “$.store.book[*]”. But the most important the array configuration has addition configuration which is 
      {
        "array": "$.store.book"
      }
In order to construct array, the configuration file should be notified with which array the output is going to be formed


### Configuration :-
```
{
  "Store": {
    "Book": [
      {
        "array": "$.store.book"
      },
      "$.store.book[*]"
    ]
  },
  "Bicycle Color": "$.store.bicycle.color",
  "Expense Rate": "$.expensive"
}
```
### Output :-
```
{
  "Bicycle Color": "red",
  "Store": {
    "Book": [
      {
        "category": "reference",
        "title": "Sayings of the Century",
        "author": "Nigel Rees",
        "price": 8.95
      },
      {
        "category": "fiction",
        "title": "Sword of Honour",
        "author": "Evelyn Waugh",
        "price": 12.99
      },
      {
        "category": "fiction",
        "title": "Moby Dick",
        "author": "Herman Melville",
        "price": 8.99,
        "isbn": "0-553-21311-3"
      },
      {
        "category": "fiction",
        "title": "The Lord of the Rings",
        "author": "J. R. R. Tolkien",
        "price": 22.99,
        "isbn": "0-395-19395-8"
      }
    ]
  },
  "Expense Rate": 10
}
```


The project contains addition example for array with object and nested array with object. 

And as well it contains addition program called “JSONPathHandler” which can able to help to form the configuration in terms of key value pair and validate on the require output.
