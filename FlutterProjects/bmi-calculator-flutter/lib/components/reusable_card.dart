import 'package:flutter/material.dart';

class ReusableCard extends StatelessWidget {
  final Color
      colour; // StatelessWidget properties should be immutable, unchangeable so should be final
  final Widget cardChild;
  final Function onPress;
  ReusableCard({@required this.colour, this.cardChild, this.onPress});
  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: onPress,
      child: Container(
        child: cardChild,
        margin: EdgeInsets.all(15.0),
        decoration: BoxDecoration(
          color: colour, //color of container should be added here.
          borderRadius: BorderRadius.circular(10.0), //  circular edges
        ),
      ),
    );
  }
}
