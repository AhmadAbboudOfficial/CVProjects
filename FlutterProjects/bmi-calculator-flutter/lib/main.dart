import 'package:flutter/material.dart';
import 'screens/input_page.dart';
import 'screens/results_page.dart';

void main() => runApp(BMICalculator());

class BMICalculator extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      //customizing app colors by making new app theme
      theme: ThemeData.dark().copyWith(
        primaryColor: Color(0xFF0A0E21), // scaffold appbar color
        scaffoldBackgroundColor: Color(0xFF0A0E21), // scaffold body color
      ),
      home: InputPage(),
    );
  }
}
