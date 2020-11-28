import 'package:flutter/material.dart';
import 'package:audioplayers/audio_cache.dart';

void main() => runApp(XylophoneApp());

class XylophoneApp extends StatelessWidget {
  void playSound({int soundNumber}) {
    final player = AudioCache();
    player.play('note$soundNumber.wav');
  }

  Expanded buildKey({Color buttonColor, int soundNumber}) {
    return Expanded(
        child: FlatButton(
            color: buttonColor,
            onPressed: () {
              playSound(soundNumber: soundNumber);
            }));
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        backgroundColor: Colors.black,
        body: SafeArea(
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.stretch,
            children: [
              buildKey(buttonColor: Colors.red, soundNumber: 1),
              buildKey(buttonColor: Colors.orange, soundNumber: 2),
              buildKey(buttonColor: Colors.yellow, soundNumber: 3),
              buildKey(buttonColor: Colors.green, soundNumber: 4),
              buildKey(buttonColor: Colors.teal, soundNumber: 5),
              buildKey(buttonColor: Colors.blue, soundNumber: 6),
              buildKey(buttonColor: Colors.purple, soundNumber: 7),
            ],
          ),
        ),
      ),
    );
  }
}
