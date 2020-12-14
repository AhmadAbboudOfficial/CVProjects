import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class TaskTile extends StatelessWidget {
  final tileTitle;
  final bool isChecked;
  final Function toggleCheckbox;
  final Function onLongPress;
  TaskTile(
      {@required this.tileTitle,
      this.isChecked,
      this.toggleCheckbox,
      this.onLongPress});

  @override
  Widget build(BuildContext context) {
    return ListTile(
      title: Text(
        tileTitle,
        style: TextStyle(
          decoration: isChecked ? TextDecoration.lineThrough : null,
        ),
      ),
      trailing: Checkbox(
        activeColor: Colors.lightBlueAccent,
        value: isChecked,
        onChanged: toggleCheckbox,
      ),
      onLongPress: onLongPress,
    );
  }
}
