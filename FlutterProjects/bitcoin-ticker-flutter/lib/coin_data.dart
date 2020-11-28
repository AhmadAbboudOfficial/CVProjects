import 'package:flutter/cupertino.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';

const List<String> currenciesList = [
  'AUD',
  'BRL',
  'CAD',
  'CNY',
  'EUR',
  'GBP',
  'HKD',
  'IDR',
  'ILS',
  'INR',
  'JPY',
  'MXN',
  'NOK',
  'NZD',
  'PLN',
  'RON',
  'RUB',
  'SEK',
  'SGD',
  'USD',
  'ZAR'
];

const List<String> cryptoList = [
  'BTC',
  'ETH',
  'LTC',
];
// this api key is free and only for 100 request, more than enough.
const apiKey = '148E5373-0352-4DA3-A0D7-3056F93FC53F';
const apiURL = 'https://rest.coinapi.io/v1/exchangerate';

class CoinData {
  Future getCoinData({@required String currency}) async {
    Map<String, String> cryptoPrices = {};
    for (String cryptoCoin in cryptoList) {
      var response =
          await http.get(apiURL + '/$cryptoCoin' + '/$currency?apikey=$apiKey');
      if (response.statusCode == 200) {
        var jsonResponse = jsonDecode(response.body);
        double price = jsonResponse['rate'];
        cryptoPrices[cryptoCoin] = price.toStringAsFixed(0);
      } else {
        print('Failed with response code ${response.statusCode}');
      }
    }
    return cryptoPrices;
  }
}
