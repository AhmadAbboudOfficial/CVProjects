import 'package:flutter/material.dart';
import 'coin_data.dart';
import 'package:flutter/cupertino.dart';
import 'dart:io'
    show Platform; //only a specific class(dart file) of the whole package.

class PriceScreen extends StatefulWidget {
  @override
  _PriceScreenState createState() => _PriceScreenState();
}

class _PriceScreenState extends State<PriceScreen> {
  String selectedCurrency = 'AUD';
  CoinData coinData = CoinData();
  String value = '?';

  DropdownButton<String> androidDropdownButton() {
    List<DropdownMenuItem<String>> items = [];
    for (String currency in currenciesList) {
      var item = DropdownMenuItem(
        child: Text(currency),
        value: currency,
      );
      items.add(item);
    }
    return DropdownButton<String>(
      value: selectedCurrency,
      items: items,
      onChanged: (value) {
        setState(() {
          selectedCurrency = value;
          getData();
        });
      },
      dropdownColor: Colors.lightBlue,
    );
  }

  CupertinoPicker iOSPicker() {
    List<Text> items = [];
    for (String itemValue in currenciesList) {
      Text itemWidget = Text(
        itemValue,
        style: TextStyle(color: Colors.white),
      );
      items.add(itemWidget);
    }
    return CupertinoPicker(
      backgroundColor: Colors.lightBlue,
      itemExtent: 32.0,
      onSelectedItemChanged: (index) {
        selectedCurrency = currenciesList[index];
        getData();
      },
      children: items,
    );
  }

  Map<String, String> coinsValues = {};
  bool isWating = true;
  void getData() async {
    try {
      var map = await coinData.getCoinData(currency: selectedCurrency);
      isWating = false;
      setState(() {
        coinsValues = map;
      });
    } catch (e) {
      print(e);
    }
  }

  @override
  void initState() {
    super.initState();
    getData();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('🤑 Coin Ticker'),
      ),
      body: Column(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        crossAxisAlignment: CrossAxisAlignment.stretch,
        children: <Widget>[
          CryptoCard(
              cryptoCurrency: 'BTC',
              value: isWating ? '?' : coinsValues['BTC'],
              selectedCurrency: selectedCurrency),
          CryptoCard(
              cryptoCurrency: 'ETH',
              value: isWating ? '?' : coinsValues['ETH'],
              selectedCurrency: selectedCurrency),
          CryptoCard(
              cryptoCurrency: 'LTC',
              value: isWating ? '?' : coinsValues['LTC'],
              selectedCurrency: selectedCurrency),
          Container(
            height: 150.0,
            alignment: Alignment.center,
            padding: EdgeInsets.only(bottom: 30.0),
            color: Colors.lightBlue,
            child: Platform.isAndroid ? androidDropdownButton() : iOSPicker(),
          ),
        ],
      ),
    );
  }
}

class CryptoCard extends StatelessWidget {
  final String selectedCurrency;
  final String value;
  final String cryptoCurrency;

  CryptoCard(
      {@required this.cryptoCurrency,
      @required this.value,
      @required this.selectedCurrency});

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: EdgeInsets.fromLTRB(18.0, 18.0, 18.0, 0),
      child: Card(
        color: Colors.lightBlueAccent,
        elevation: 5.0,
        shape: RoundedRectangleBorder(
          borderRadius: BorderRadius.circular(10.0),
        ),
        child: Padding(
          padding: EdgeInsets.symmetric(vertical: 15.0, horizontal: 28.0),
          child: Text(
            '1 $cryptoCurrency = $value $selectedCurrency',
            textAlign: TextAlign.center,
            style: TextStyle(
              fontSize: 20.0,
              color: Colors.white,
            ),
          ),
        ),
      ),
    );
  }
}
