#CryptoConverter 
This app was written as an entry to ALC intermediate Android challenge. Go to this [link](https://docs.google.com/document/d/1BFXX81t1G9prokAI_9uMIB7ubuvkFmkCZUOpaD1kphQ/mobilebasic) to view the challenge in detail. 

##About The App 
The app shows the exchange rate between BTC and ETH. It also allows the creation of currency cards which show the exchange rate between the selected currency and the cryptocurrencies. Clicking on each card will go to a conversion page where amount could be specified in BTC, ETH, or the selected currency. The specified amount will be converted to BTC, ETH, and the selected currency. **The exchange rate data is fetch from [CryptoCompare](https://www.cryptocompare.com)**. 

##Supported Currencies
The app supports the following currencies:
* USD - US Dollar
* CAD - Canadian Dollar 
* EUR - Euro
* GBP - British Pound
* CNY - Chinese Yuan
* CHF - Swiss Franc
* AUD - Australian Dollar 
* JPY - Japanese Yen 
* SEK - Swedish Krona
* MXN - Mexican Peso
* NZD - New Zealand Dollar 
* SGD - Singapore Dollar 
* HKD - Hong Kong Dollar 
* NOK - Norwegian Krone
* TRY - Turkish Lira
* RUB - Russian Ruble 
* ZAR - South African Rand
* BRL - Brazilian Real
* MYR - Malaysian Ringgit 
* NGN - Nigerian Naira 

##Library Used 
The retrofit library was used in this project. 
```
compile 'com.squareup.retrofit2:converter-gson:2.1.0'
compile 'com.squareup.retrofit2:retrofit:2.1.0'
```