# University Student Record and Campus Route Management System

**Module:** CIT300 — Data Structures and Algorithms  
**Assignment:** Graded Practical Assignment 1 (Week 10)  
**Language:** Java (console application)

A menu-driven application demonstrating student-record management and campus connectivity with a custom linked list, stack, queue, binary search tree, hash table, and undirected graph.

> **Contribution note:** The statements below allocate the project's work according to the assignment's suggested roles. Use them as your group's contribution plan; before submission, change any statement that does not match what that member actually did. Do not claim implementation, testing, or presentation work that was not performed by that member.

## Group members and responsibilities

| Student ID | Name | Assigned responsibility | Individual contribution statement |
|---|---|---|---|
| 23DA2-0507 | MSF.Rimasa | Student model and linked-list student-record management | Responsible for the student-record module: add, update, delete, search within the list, and display operations; explain and test the linked-list behavior. |
| 23DA2-0514 | ANT.Ahamed | Stack-based recent-action history and FIFO service-request queue | Responsible for the action-history stack and service-request queue; explain and test LIFO history and FIFO request processing. |
| 23DA2-0673 | MUM.Amhar | Student-ID hash table and BST operations | Responsible for hash-based ID lookup and BST search/sorted display; explain and test the indexes and keep them consistent with student records. |
| 23DA2-1072 | MIFZ.Azzah | Campus graph and traversal | Responsible for the adjacency-list campus graph, location/road operations, network display, and BFS/DFS; explain and test graph behavior. |

**All members:** Contribute to integration, input-validation and system testing, debugging, documentation, GitHub collaboration, and the final demonstration. Each member should describe their own actual work in the video and ensure the final contribution statements reflect the work they personally completed.

## Requirements implemented

- Student records include Student ID, name, programme, and marks (0–100).
- Custom singly linked list is the primary record store.
- Custom linked stack records recent actions, displayed newest first.
- Custom linked FIFO queue manages requests associated with registered students.
- Custom BST is keyed by Student ID and displays records in-order.
- Custom separate-chaining hash table provides Student-ID lookup and resizes as it grows.
- Campus graph uses an adjacency list and undirected roads, with add/remove location and road operations, network display, BFS, and DFS.
- Menu supports add, update, delete, search, and display student records.
- IDs and campus names are matched case-insensitively. Blank input, invalid menu choices/marks, duplicate IDs/locations, unknown records/locations, duplicate or missing roads, and self-connections are handled.

## Project structure

```text
CIT300_Student_Campus_System/
├── README.md
├── DEMO_SCRIPT.md
├── .gitignore
├── src/
│   ├── Main.java
│   ├── Student.java
│   ├── StudentLinkedList.java
│   ├── StudentHashTable.java
│   ├── StudentBST.java
│   ├── ActionStack.java
│   ├── ServiceQueue.java
│   └── CampusGraph.java
└── tests/
    └── DataStructureSmokeTest.java
```

## Requirements

- JDK 17 or later (the application uses modern Java switch syntax).
- A terminal/command prompt.

Check Java availability:

```bash
java -version
javac -version
```

## Compile and run

Run these commands from the project folder:

```bash
mkdir -p out
javac -d out src/*.java
java -cp out Main
```

The program stores records in memory for the current run; data is not saved after exiting. For a graded practical demonstration, add sample students and campus locations after launch.

## Run the smoke test

```bash
mkdir -p out
javac -d out src/*.java tests/DataStructureSmokeTest.java
java -cp out DataStructureSmokeTest
```

The test checks linked-list operations, hash lookup/removal, BST ordering/deletion, stack and queue order, plus graph connections and traversals.

## Suggested demonstration sequence

1. Add two students and show that a duplicate ID is rejected.
2. Search an ID through hashing; display linked-list order and BST-sorted order.
3. Update one student's details, then search again to show the index reflects the update.
4. Add a service request for each registered student and process one to show FIFO order.
5. Display recent actions to show LIFO history.
6. Create three campus locations and roads, display the adjacency list, then run BFS and DFS.
7. Remove a road and a location; display the graph to show the changes.
8. Try invalid marks and a missing student/location to demonstrate validation.

The detailed member-by-member demonstration script is provided as a separate file, outside the project ZIP.

## Before submitting (from the assignment brief)

- [ ] Confirm member names and IDs above are exact; correct any mismatches.
- [ ] Confirm and, if needed, edit each contribution statement so it describes that member's **actual individual contribution**.
- [ ] Merge the members' individually recorded demonstration sections into one video of **less than 15 minutes**; keep all four faces clearly visible in a gallery throughout the final video.
- [ ] Push the complete project to the group's GitHub repository and include collaboration evidence (commits/branches/pull requests where applicable).
- [ ] If using Google Drive, upload the complete project and put its folder link in a `.txt` file for LMS upload.
- [ ] Before submitting, give **Editor** access to `asanka.r@sltc.ac.lk` and `kaushika.w@sltc.ac.lk`, and verify both permissions.
- [ ] Submit through the designated LMS link on or before **29 September** (the assignment deadline); the brief says late email does not count as submission.

The group is responsible for checking the actual LMS deadline/time and completing the external submission steps. Do not include passwords or private credentials in the repository.
