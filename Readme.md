# Excel Data Application
This application provide apis for import and export of excel data

NOTE: You don't need to create DB tables ,it will create itself once you build the Project.
## Apis
 It contains the following apis:
 * GET "v1/excel/download" : This api  is used to export excel data from db

NOTE : Paste the url in the browser to download the excel file

==========================================================
curl --location --request GET 'http://localhost:8080/v1/excel/download' \
--form 'file=@"/path/to/file"'

* POST "v1/excel/upload": This api is used to import excel file data into db

=========================================================
curl --location --request POST 'http://localhost:8080/v1/excel/upload' \
--form 'file=@"/C:/Users/Divya/Downloads/document.xlsx"'

* POST "v1/excel/mail": This api is used to send email 

============================================================
curl --location --request POST 'http://localhost:8080/v1/excel/mail'