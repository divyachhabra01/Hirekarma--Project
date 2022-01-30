# Excel Data Application
This application provide apis for import and export of excel data

## Apis
 It contains the following apis:
 * GET "v1/excel/download" : This api  is used to export excel data.

NOTE : Paste the url in the browser to download the excel file

==============================================

curl --location --request GET 'https://hire-karma.herokuapp.com/v1/excel/download'

* POST "v1/excel/upload": This api is used to import excel file data.

=========================================================
curl --location --request POST 'https://hire-karma.herokuapp.com/v1/excel/upload'

* POST "v1/excel/mail": This api is used to send email 

============================================================
curl --location --request POST 'https://hire-karma.herokuapp.com/v1/excel/mail'