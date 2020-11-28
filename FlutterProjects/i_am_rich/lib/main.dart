import 'package:flutter/material.dart';

void main() {
  runApp(
    MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: Text("I Am Rich"),
          backgroundColor: Colors.blueGrey[900],
        ),
        backgroundColor: Colors.blueGrey,
        body: Center(
          child: Image(
            image:
                //getting an image from the internet
                //NetworkImage('https://www.w3schools.com/w3images/lights.jpg'),
                //getting an image from assets, adding it to pubspec.yaml and in a folder before that
                AssetImage('images/diamond.png'),
          ),
        ),
      ),
    ),
  );
}
