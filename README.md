# University Student Record and Campus Route Management System

**Module:** CIT300 — Data Structures and Algorithms
**Assessment:** Graded Practical Assignment 1 (Week 10) — 10% of the final module grade
**Institution:** SLTC Research University
**Language:** Java console application (JDK 17 or later)

A menu-driven Java application for managing university student records and representing connections between campus locations. It demonstrates a linked list, stack, queue, binary search tree (BST), hash table, and adjacency-list graph.

## 1. Group Members

| Member | Name | Student ID | Assigned responsibility |
| --- | --- | --- | --- |
| Member 1 | MSF.Rimasa | 23DA2-0507 | Student model, linked-list storage, and student-record operations |
| Member 2 | ANT.Ahamed | 23DA2-0514 | Recent-action stack and student-service-request queue |
| Member 3 | MUM.Amhar | 23DA2-0673 | BST organization and hash-based student-ID search |
| Member 4 | MIFZ.Azzah | 23DA2-1072 | Campus graph, location/road operations, BFS and DFS |
| All members | — | — | Integration, testing, debugging, documentation, GitHub collaboration, and demonstration—record only the work each member actually performed. |

## 2. Individual Contributions

The descriptions below map members to project components and functions. Before submission, each member must confirm that their contribution statement matches work they personally completed, tested, reviewed, or demonstrated. Update the descriptions to match the actual merged commits and pull requests; do not claim work another person completed.

### Member 1 — MSF.Rimasa (23DA2-0507)

- **Student model:** `src/Student.java` — stores Student ID, name, programme, and marks; validates non-empty text fields and marks from 0 to 100.

- **Linked list:** `src/StudentLinkedList.java` — singly linked student store with `addLast`, `find`, `replace`, `remove`, and `toList` operations.

- **Student-record menu operations:** `src/Main.java` — add, update, delete, and display records (menu options 1–4), including duplicate-ID and missing-record handling.

- **Presentation/test area:** demonstrate linked-list record operations and report any tests or fixes personally completed.

### Member 2 — ANT.Ahamed (23DA2-0514)

- **Action-history stack:** `src/ActionStack.java` — linked LIFO stack with push, pop, and newest-first history display.

- **Service-request queue:** `src/ServiceQueue.java` — linked FIFO queue with enqueue/dequeue operations.

- **Menu integration:** `src/Main.java` — add service requests, process the next request, and display recent actions (options 5–7).

- **Presentation/test area:** demonstrate FIFO request handling and newest-first action history; report any tests or fixes personally completed.

### Member 3 — MUM.Amhar (23DA2-0673)

- **Binary search tree:** `src/StudentBST.java` — insert, search, remove, and in-order traversal by Student ID.

- **Hash table:** `src/StudentHashTable.java` — separate-chaining hash table with ID lookup, insertion, removal, and resizing.

- **Menu integration:** `src/Main.java` — display students sorted with the BST and search student records using hashing (options 8–9).

- **Presentation/test area:** demonstrate sorted BST output and hash lookup, including how indexes reflect student updates/deletions; report any tests or fixes personally completed.

### Member 4 — MIFZ.Azzah (23DA2-1072)

- **Campus graph:** `src/CampusGraph.java` — undirected adjacency-list graph with location/road addition and removal, network and neighbor display, BFS, and DFS.

- **Menu integration:** `src/Main.java` — campus location and road management, network display, and traversal (options 10–15).

- **Presentation/test area:** demonstrate graph operations, invalid/unavailable connections, and BFS/DFS; report any tests or fixes personally completed.

### All Members

Describe only the integration, input validation, tests, debugging, documentation, GitHub reviews/merges, and presentation work each member actually participated in. Every member should be ready to explain and demonstrate their contribution.

## 3. Features and Assignment Requirement Coverage

| Assignment requirement | Implementation | Menu options |
| --- | --- | --- |
| Student ID, name, programme, and marks | `src/Student.java` | 1–4, 8–9 |
| Linked list for student records | `src/StudentLinkedList.java` | 1–4 |
| Stack for recent actions/history | `src/ActionStack.java` | 7 |
| Queue for service requests | `src/ServiceQueue.java` | 5–6 |
| BST/AVL tree organization/search | `src/StudentBST.java` (BST) | 8 |
| Hashing for efficient Student-ID search | `src/StudentHashTable.java` | 9 |
| Graph represented as an adjacency list | `src/CampusGraph.java` | 10–15 |
| Add/remove campus locations and roads | `src/CampusGraph.java` | 10–13 |
| Display connected locations/network | `src/CampusGraph.java` | 14 |
| BFS or DFS graph traversal | Both BFS and DFS in `src/CampusGraph.java` | 15 |
| Add, update, delete, search, and display students | `src/Main.java` and student structures | 1–4, 8–9 |
| Menu-driven console interface and validation | `src/Main.java` | 1–16 |

This project implements a **BST**, not an AVL tree; the assignment permits either.

### Input validation covered

- Blank text input and invalid menu choices.

- Duplicate student IDs and duplicate campus locations.

- Non-numeric marks and marks outside 0–100.

- Missing student records or campus locations.

- Duplicate roads, unavailable connections, and self-connections.

- Requests associated with unknown student IDs are rejected.

## 4. Menu

```
1.  Add Student Record                 9.  Search Student (Hashing)
2.  Update Student Record             10.  Add Campus Location
3.  Delete Student Record             11.  Remove Campus Location
4.  Display All Records               12.  Add Campus Connection/Road
5.  Add Service Request                13.  Remove Campus Connection/Road
6.  Process Next Service Request       14.  Display Campus Network
7.  Display Recent Actions              15.  Traverse Campus (BFS/DFS)
8.  Display Students Sorted (BST)      16.  Exit
```

## 5. Project Structure

```
CIT300-Student-Campus-System/
├── README.md
├── .gitignore
├── run.bat                         # compile and launch on Windows
├── test.bat                        # compile and run available automated tests
├── src/
│   ├── Main.java                   # menu and integration
│   ├── Student.java                # student model
│   ├── StudentLinkedList.java      # primary student records
│   ├── StudentHashTable.java       # Student-ID hash index
│   ├── StudentBST.java             # Student-ID binary search tree
│   ├── ActionStack.java            # recent-action history
│   ├── ServiceQueue.java           # student service requests
│   └── CampusGraph.java            # campus adjacency-list graph, BFS/DFS
└── tests/
    └── DataStructureSmokeTest.java # 20 current smoke checks
```

Member-specific test files can be added to `tests/` as each branch/PR is created, reviewed, and merged. The separate demonstration script is provided outside the project ZIP.

## 6. Requirements and Run Instructions

Install **JDK 17 or later** and ensure `java` and `javac` are available in the Windows PATH. Check in PowerShell:

```
java -version
javac -version
```

### Run the system on Windows

From the project folder, double-click `run.bat` or enter:

```
.\run.bat
```

### Run automated tests on Windows

Double-click `test.bat` or enter:

```
.\test.bat
```

The script compiles the application and available test sources, always runs `DataStructureSmokeTest`, and runs the four member-specific test classes if present. A `SKIP` message means that test class is not included in that local copy yet. After member test PRs have been merged, rerun the script and confirm every suite passes without skips.

The included `DataStructureSmokeTest.java` currently performs **20 checks**. Report additional test counts only after the relevant test files are added and run successfully.

### Manual test scenarios

After launching with `run.bat`, manually try adding, updating, deleting, searching, and displaying students; duplicate IDs and invalid marks; FIFO queue processing; recent-action history; BST order; hash lookup; graph location/road changes; network display; BFS/DFS; and invalid or missing connections. Record the actual test results; do not mark scenarios as passed before running them.

## 7. Data Structure Notes and Complexity

| Structure | Operations | Typical complexity |
| --- | --- | --- |
| Singly linked list | Add at tail, find, replace, remove, traverse | Add at tail O(1); find/update/remove O(n) |
| Linked stack | Push, pop, newest-first history | Push/pop O(1) |
| Linked queue | Enqueue/dequeue service requests | Enqueue/dequeue O(1) |
| Binary search tree | Insert, search, delete, in-order traversal | Average O(log n); worst case O(n) |
| Separate-chaining hash table | Put, lookup, remove | Average O(1); worst case O(n) |
| Adjacency-list graph | Add/remove roads; BFS/DFS | BFS/DFS O(V + E) |

## 8. GitHub Collaboration Record

The following is the **planned branch-to-component mapping**, not a claim that every branch or PR already exists. Update the branch names and content to match the actual GitHub history after contributions are pushed and merged. Each member should use their own GitHub account and submit a genuine contribution for peer review.

| Branch | Member | Planned content / contribution |
| --- | --- | --- |
| `main` (initial project upload) | Repository owner — enter the actual member/account | Initial project files, README, and baseline smoke test; update this row to show who actually made the upload and what it included. |
| `rimasa-linked-list-tests` | MSF.Rimasa (23DA2-0507) | Linked-list edge-case tests and any confirmed linked-list/student validation fixes. |
| `ahamed-stack-queue-tests` | ANT.Ahamed (23DA2-0514) | Stack/queue tests and any confirmed request/history fixes. |
| `amhar-bst-hash-tests` | MUM.Amhar (23DA2-0673) | BST/hash tests and any confirmed index fixes. |
| `azzah-graph-tests` | MIFZ.Azzah (23DA2-1072) | Campus graph/traversal tests and any confirmed graph fixes. |
| `main` (after review and merge) | All contributing members | Final reviewed project after the genuine member pull requests are merged. |

Do not leave proposed branches listed as completed work if they were never created. Add actual pull-request links or commit references only after they exist.

## 9. Demonstration Video Run Sheet

Target final duration: approximately **13 minutes 30 seconds**, and in all cases less than the assignment's **15-minute maximum**. The members may record their demonstration sections separately and merge them in order. Follow the assignment instruction that all four faces remain clearly visible throughout the final video.

| Time | Presenter | Content to show |
| --- | --- | --- |
| 00:00–00:50 | All members (short clips edited together) | Group introduction, names, IDs, and assigned responsibilities. Keep the four-face gallery visible in the final edit. |
| 00:50–04:20 | MSF.Rimasa | Linked-list records; add, update, delete, display; duplicate-ID and invalid-mark validation. |
| 04:20–06:45 | ANT.Ahamed | Queue: add/process service requests in FIFO order; stack: display recent actions newest first; empty-queue handling. |
| 06:45–08:45 | MUM.Amhar | Hash-based Student-ID search and BST in-order sorted display; confirm indexes reflect updates/deletions. |
| 08:45–12:45 | MIFZ.Azzah | Add/remove locations and roads, adjacency-list display, invalid connections, BFS, and DFS. |
| 12:45–13:30 | MSF.Rimasa and all members (separate short clips) | Run `test.bat` and show the actual results; briefly show the real GitHub commits/branches/merged PRs; each member states their genuine contribution; close the presentation. |

## 10. Group Declaration

**Include the declaration below only after all four members have reviewed it and confirmed it is accurate and allowed by the module's rules. If it is not accurate, revise it before submission.**

> We declare that this project is our own group work for CIT300 Data Structures and Algorithms. Each member can explain and demonstrate their own contribution as listed above.

| Name | Student ID |
| --- | --- |
| MSF.Rimasa | 23DA2-0507 |
| ANT.Ahamed | 23DA2-0514 |
| MUM.Amhar | 23DA2-0673 |
| MIFZ.Azzah | 23DA2-1072 |
