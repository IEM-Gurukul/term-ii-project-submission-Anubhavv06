# PCCCS495 – Term II Project

## Smart Task Scheduler using Strategy and Observer Design Patterns


## Problem Statement (max 150 words)

Managing multiple tasks efficiently is challenging for students and professionals. Traditional task management
systems often rely on fixed priority rules that cannot adapt to changing conditions such as approaching deadlines or
varying task importance. The proposed Smart Task Scheduler aims to address this problem by implementing a flexible
scheduling system using Object-Oriented Programming in Java. The system allows users to create and manage
different types of tasks with deadlines and priorities. Different scheduling strategies will determine task execution
order dynamically. Design patterns such as Strategy and Observer will be used to support flexible priority calculation
and task notifications. Java collections will manage tasks efficiently, while exception handling will ensure reliable
input validation.


## Target User

• Students managing study tasks
• Professionals organizing work schedules
• Individuals tracking personal tasks

## Core Features

• Add and manage tasks with deadlines and priority levels
• Support multiple task types (study, work, personal)
• Dynamic task scheduling using priority strategies
• Display tasks in priority order
• Mark tasks as completed
• Notify when tasks become urgent
• Save and load tasks using file handling

## OOP Concepts Used

• Abstraction: Abstract Task class representing common task attributes.
• Inheritance: StudyTask, WorkTask, and PersonalTask extend Task.
• Polymorphism: Different scheduling strategies implement the PriorityStrategy interface.
• Encapsulation: Task properties such as deadline, priority, and status are private with controlled access.
• Exception Handling: Invalid inputs such as incorrect deadlines will be handled using exceptions.
• Collections: PriorityQueue and ArrayList used for task management.


## Proposed Architecture Description

Proposed Architecture Description
The system will follow a modular architecture with multiple interacting classes. An abstract Task class will define
common task properties, while specific task types extend this class. A PriorityStrategy interface will implement
different scheduling strategies used by the TaskScheduler. The Observer pattern will notify components when task
status changes. Tasks will be stored using Java collections such as PriorityQueue to maintain priority ordering. A
FileManager class will handle data persistence, and the Main class will provide a console-based interface for
interacting with the system.

## How to Run

To run the Smart Task Scheduler, ensure Java (JDK 8 or above) is installed on your system. Navigate to the project’s src directory in the terminal. Compile the project using javac scheduler/Main.java, which will compile all dependent classes. After successful compilation, execute the program using java scheduler.Main. The application will launch a menu-driven console interface where users can add tasks, view tasks, change scheduling strategies, and save or load data. Make sure to enter the deadline in the correct format (YYYY-MM-DDTHH:MM). Follow the on-screen instructions to interact with the system smoothly.

