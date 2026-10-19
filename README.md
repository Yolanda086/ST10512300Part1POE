# ST10512300Part1POE
# QuickChatApp - PROG5121 Part 1 and Part 2

 ## PART 1
User registration and login system built with Java.

## Features
- Username validation (contains underscore, max 5 characters)
- Password complexity check (≥8 chars, uppercase, digit, special character)
- Cell phone validation (South African format: +27 followed by 9 digits)
- Registration flow → Login flow
- JUnit 4.13.2 unit tests (12 tests, all passing)

## Files
- `Login.java` — validation and authentication logic
- `Main.java` — console interface
- `LoginTest.java` — JUnit test suite

  ## PART 2
## Features
**User Registration & Login:** 
    *   Validates usernames (must contain an underscore, max 5 characters).
    *   Validates passwords (min 8 characters, 1 capital, 1 number, 1 special character).
    *   Validates South African cell phone numbers using Regular Expressions (`+27...`).
**Messaging System:**
    *   Generate unique 10-digit Message IDs.
    *   Create custom Message Hashes (e.g., `00:0:HITHANKS`).
    *   Send, Disregard, or Store messages.
**Data Storage:**
    *   Stores messages in a local `messages.json` file for persistent data.
**Automated Testing:**
    *   Includes a comprehensive suite of JUnit 4 unit tests.
    *   Integrated with GitHub Actions for Continuous Integration (CI) to automatically run tests on every push.
    
## Files
    *   Message.java
    *   MessageTest.java
    *   Main.java
    *   Login.java
    *   LoginTest.java

## Author
Yolanda086 - Andiswa Mkhize
