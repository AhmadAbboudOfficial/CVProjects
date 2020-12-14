import 'package:flutter/material.dart';
import 'task_tile.dart';
import 'package:provider/provider.dart';
import 'package:todoey/models/task_data.dart';

class TasksList extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Consumer<TaskData>(
      builder: (context, taskData, child) {
        return ListView.builder(
          padding: EdgeInsets.symmetric(horizontal: 20.0),
          itemBuilder: (context, index) {
            final task = taskData.tasks[index];
            return TaskTile(
              tileTitle: task.name,
              isChecked: task.isDone,
              toggleCheckbox: (val) {
                taskData.updateTask(task);
              },
              onLongPress: () {
                taskData.removeTask(task);
              },
            );
          },
          itemCount: taskData.taskCount,
        );
      },
    );
  }
}
