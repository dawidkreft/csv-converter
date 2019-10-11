# csv-converter
Simple csv file converter service from json data

You need set correct  path to save csv file in application.yml:
self.csv.file-path:/home/user/csv_files

enpodint: PUT localhost:7200/csv-converter/convert?name="data-name" 
Json example(Request body): 

> [ {
  "item" : "No. 9 Sprockets",
  "quantity" : 12,
  "unitPrice" : 1.23
}, {
  "item" : "Widget (10mm)",
  "quantity" : 4,
  "unitPrice" : 3.45
} ]
