# Campus Gate Entry/Exit Record Management System

## Motivation
Manual recording of entry and exit times in educational institutions often leads to inaccuracies, delays, and inefficiencies. Additionally, managing leave applications and penalties manually can be time-consuming and prone to errors. This project was developed as a solution to streamline the process of recording entry and exit times of students and individuals from university premises. It offers an efficient and automated system that facilitates leave management, and provides a centralized database for administrators to manage student records effectively.

## Overview
The System consists of two main components: a User Portal and an Admin Portal. The user portal allows individuals to log their entry and exit times, while the admin portal provides administrative staff with tools to manage the database, analyze records, and enforce disciplinary actions.

## Features
### User Portal
- **Log in/Log out:** Records entry and exit times of individuals accessing the campus.
- **ID Verification:** Verifies the validity of student ID numbers to ensure authorized access.
- **Status Tracking:** Maintains the status of each student (inside/outside campus) during specified periods.
- **Penalty Management:** Tracks penalties incurred due to infractions such as late entry or exit.
- **Local/Outstation Mode:** Allows users to specify the purpose of their visit (local/outstation trip).
- **Outstation Leave Check:** Verifies if a valid leave exists for students leaving campus for outstation trips.
- **Defaulting Curfew Times:** Imposes penalties for students who fail to adhere to specified entry/exit times.

### Admin Portal
- **Leave Approval:** Admin can approve or reject leave applications submitted by students.
- **Record Management:** Allows administrators to access past leave records and update penalties.
- **Ban Leaves:** Restricts students from leaving campus as necessary.
- **Time Adjustment:** Enables administrators to set entry and exit times for local leaves.
- **Present Leaves:** Provides visibility into students currently absent from campus.
- **Leave Expiry:** Automatically expires leave approvals after a specified period.

## Tech Stack Used
The System is developed using Java Swing for the user interface and MySQL for the database management. Java Swing offers a robust framework for creating graphical user interfaces, while MySQL provides a reliable and scalable platform for storing and retrieving data.

## Key Highlights
- **Automation:** The system automates the process of recording entry and exit times, reducing manual effort and ensuring accuracy.
- **Comprehensive Features:** Offers a wide range of features including leave management, penalty tracking, and status monitoring.
- **User-Friendly Interface:** Designed with an intuitive user interface to facilitate easy navigation for both users and administrators.
- **Customization:** Administrators have the flexibility to customize entry/exit times and manage penalties according to specific campus policies.
