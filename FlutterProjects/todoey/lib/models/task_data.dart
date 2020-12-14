import 'dart:collection';

import 'package:flutter/foundation.dart';
import 'task.dart';

class TaskData extends ChangeNotifier {
  List<Task> _tasks = [
    Task(name: 'test1'),
    Task(name: 'test2'),
    Task(name: 'test3'),
  ];

  int get taskCount {
    return _tasks.length;
  }

  void addNewTask(String taskName) {
    _tasks.add(Task(name: taskName));
    notifyListeners();
  }

  UnmodifiableListView<Task> get tasks {
    return UnmodifiableListView(_tasks);
  }

  void updateTask(Task task) {
    task.toggleDone();
    notifyListeners();
  }

  void removeTask(Task task) {
    _tasks.remove(task);
    notifyListeners();
  }
}
