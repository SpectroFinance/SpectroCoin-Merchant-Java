SpectroCoin-Merchant-Java
========================

Sample Java API client for communicating with [Spectro Coin](https://spectrocoin.com) Merchant API service. [API specification](http://spectrofinance.github.io/SpectroCoin-Merchant-API/).

# Contents

`SCMerchantClient.java` is SpectroCoin Merchant API sample Java client. You should use it as it is or update it for your needs. `src/main` folder contains generic API client part.

`src/test` folder contains test classes which are used for testing API client.

`src/test/resources` contains test API certificates. You must [generate](http://spectrofinance.github.io/SpectroCoin-Merchant-API/#merchant-key-pair) your own for productional use.

You can run `HandlerTest` test. It starts simple HTTP server and waits for incomming callback. You can test merchant API callback handling.
