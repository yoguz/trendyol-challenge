# Trendyol Link Converter
  Web service that helps others to convert Trendyol.com links between mobile and web applications. 
  Web applicatons use URLs and mobile applications use deeplinks. Both applications use links to redirect specific locations inside applications.
  When you want to redirect across applications, you should convert URLs to deeplinks or deeplinks to URLs.

## Running with docker
  `docker-compose up --build`

## How to Use:

  There are 2 API's for converting links.

* `/converter/to_deeplink`

   This API accepts HTTP POST request. Given Web URL in JSON object with key "link", API will return corresponding deeplink in JSON object with same key.
   Example request body:
    ```
    {
      "link": "https://www.trendyol.com/casio/erkek-kol-saat-p-1925865?boutiqueId=439892"
    }
    ```

   Response to this request:
    ```
    {
      "link": "ty://?Page=Product&ContentId=1925865&CampaignId=439892"
    }
    ```


* `/converter/to_web_url`

   This API accepts HTTP POST request. Given deeplink in JSON object with key "link", API will return corresponding Web URL in JSON object with same key.
   Example request body
    ```
    {
      "link": "ty://?Page=Search&Query=elbise"
    }
    ```
   Response to this request:
    ```
    {
      "link": "https://www.trendyol.com/tum--urunler?q=elbise"
    }
    ```
