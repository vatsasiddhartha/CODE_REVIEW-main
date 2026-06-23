# 🤖 ToodlesCode – AI Powered Code Review Platform

An AI-powered code review platform built using **Spring Boot** and **Google Gemini AI** that helps developers improve code quality, identify bugs, discover best practices, and learn software engineering concepts through intelligent code analysis.

The vision of ToodlesCode is to evolve from a simple code review tool into a complete developer productivity and learning platform that combines AI-assisted reviews, learning recommendations, note-taking, and open-source project discovery.

---

# 🚀 Overview

Writing code is only one part of software engineering. Understanding code quality, maintainability, performance, design principles, and real-world project structures is equally important.

ToodlesCode helps bridge this gap by allowing developers to submit source code and receive AI-generated feedback powered by Google's Gemini models.

The platform is designed for:

* Students learning programming
* Developers preparing for interviews
* Open-source contributors
* Backend and Full-Stack Developers
* Software Engineering Enthusiasts

---

# ✨ Current Features

### AI-Powered Code Review

Analyze source code using Google Gemini AI and receive detailed feedback.

### Multi-Language Support

Review code written in multiple programming languages.

### Bug Detection

Identify logical issues and potential bugs.

### Code Quality Analysis

Evaluate readability, maintainability, and coding standards.

### Best Practices Recommendations

Receive suggestions based on industry-standard software engineering principles.

### Performance Optimization

Get recommendations for improving efficiency and scalability.

### Session-Based Review Storage

Temporarily store review results during the active user session.

### Responsive User Interface

Built using Thymeleaf, Bootstrap, HTML, and CSS.

---

# 🏗️ Current System Architecture

```text
┌───────────────────────────────────────────────────┐
│                     User                          │
└──────────────────────┬────────────────────────────┘
                       │
                       ▼

┌───────────────────────────────────────────────────┐
│             Web Interface (UI Layer)             │
│                                                   │
│  HTML + CSS + Bootstrap + Thymeleaf             │
└──────────────────────┬────────────────────────────┘
                       │
                       ▼

┌───────────────────────────────────────────────────┐
│             Spring Boot Application              │
└──────────────────────┬────────────────────────────┘
                       │
       ┌───────────────┼────────────────┐
       ▼               ▼                ▼

┌─────────────┐ ┌─────────────┐ ┌─────────────┐
│ Controller  │ │   Service   │ │   Session   │
│   Layer     │ │    Layer    │ │ Management  │
└──────┬──────┘ └──────┬──────┘ └──────┬──────┘
       │               │               │
       └───────────────┼───────────────┘
                       │
                       ▼

┌───────────────────────────────────────────────────┐
│           Gemini Client Service                  │
│      RestTemplate / WebClient Layer             │
└──────────────────────┬────────────────────────────┘
                       │
                       ▼

┌───────────────────────────────────────────────────┐
│              Google Gemini API                   │
│                                                   │
│  • Code Analysis                                 │
│  • Bug Detection                                 │
│  • Best Practices                                │
│  • Optimization Suggestions                      │
│  • Learning Recommendations                      │
└──────────────────────┬────────────────────────────┘
                       │
                       ▼

┌───────────────────────────────────────────────────┐
│              Review Generation                   │
└──────────────────────┬────────────────────────────┘
                       │
                       ▼

┌───────────────────────────────────────────────────┐
│            Review Result Dashboard               │
└───────────────────────────────────────────────────┘
```

---

# 🔄 Application Workflow

### Step 1

User enters:

* Programming Language
* Source Code

### Step 2

The request is submitted to the Spring Boot backend.

### Step 3

Review Service processes the request.

### Step 4

Gemini Client Service sends the code to Google Gemini API.

### Step 5

Gemini analyzes:

* Code Quality
* Bugs
* Performance Issues
* Design Problems
* Best Practices

### Step 6

Analysis results are returned to the application.

### Step 7

Results are displayed on the review dashboard.

---

# 🧱 Technology Stack

| Layer              | Technology               |
| ------------------ | ------------------------ |
| Backend            | Spring Boot              |
| Language           | Java                     |
| Frontend           | HTML                     |
| Styling            | CSS, Bootstrap           |
| Template Engine    | Thymeleaf                |
| AI Service         | Google Gemini API        |
| HTTP Client        | RestTemplate / WebClient |
| Session Management | HttpSession              |
| Build Tool         | Maven                    |
| Version Control    | Git & GitHub             |

---

# 📁 Project Structure

```text
src
│
├── controller
│   └── ReviewController
│
├── service
│   ├── ReviewService
│   └── GeminiClientService
│
├── config
│
├── templates
│   ├── index.html
│   └── result.html
│
├── static
│   ├── css
│   ├── js
│   └── assets
│
└── application.properties
```

---

# ⚙️ Local Development Setup

## Prerequisites

* Java 17+
* Maven 3.8+
* Google Gemini API Key

---

## Clone Repository

```bash
git clone https://github.com/vatsasiddhartha/CODE_REVIEW-main.git

cd CODE_REVIEW-main
```

---

## Configure Environment

application.properties

```properties
gemini.api.key=YOUR_API_KEY
server.port=5000
```

---

## Run Application

```bash
mvn spring-boot:run
```

---

## Access Application

```text
http://localhost:5000
```

---

# 🗺️ Product Roadmap

## Phase 1 – Core AI Review Platform

✅ Spring Boot Backend

✅ Gemini API Integration

✅ AI Code Review

✅ Review Dashboard

✅ Session Management

---

## Phase 2 – Production Readiness

🚧 Deployment Infrastructure

🚧 Error Handling Improvements

🚧 Enhanced Prompt Engineering

🚧 Frontend Redesign

🚧 Performance Optimizations

---

## Phase 3 – React Frontend Migration

A modern React-based frontend is planned to improve scalability and user experience.

### Planned Stack

* React
* TypeScript
* Tailwind CSS
* REST APIs
* Responsive Dashboard

Benefits:

* Better UI/UX
* Faster interactions
* Modular architecture
* Improved maintainability

---

# 💡 Future Features

## AI Generated TODO Sidebar

After reviewing code, AI will automatically generate actionable tasks.

Example:

□ Fix Naming Conventions

□ Reduce Time Complexity

□ Improve Exception Handling

□ Refactor Duplicate Logic

□ Follow SOLID Principles

---

## Smart Notes Workspace

A dedicated learning and productivity section.

Users can:

* Save review insights
* Create personal notes
* Track learning progress
* Store engineering observations

---

## Open Source Repository Discovery

Developers often struggle to find quality projects for learning.

This feature will recommend GitHub repositories based on:

* Programming Language
* Technology Stack
* Project Category
* Difficulty Level

Examples:

Java → Spring Boot Applications

React → Frontend Projects

Python → AI Projects

Node.js → Backend APIs

The objective is to help developers learn from production-grade codebases.

---

## Personalized Learning Recommendations

Based on submitted code, the platform will suggest:

* Topics to study
* Documentation
* Open Source Projects
* Design Patterns
* Learning Resources

---

## Authentication & User Profiles

Planned features:

* Secure Login
* User Profiles
* Saved Reviews
* Review History
* Learning Analytics

---

# 🏛️ Future System Architecture

```text
User
 │
 ▼

React Frontend
 │
 ▼

Spring Boot REST API Layer
 │
 ├── Authentication Service
 │
 ├── AI Review Service
 │
 ├── Notes Service
 │
 ├── Repository Discovery Service
 │
 ├── Learning Recommendation Service
 │
 └── Dashboard Service
 │
 ▼

MySQL / PostgreSQL Database
 │
 ▼

Google Gemini API
```

---

# 🤝 Contributing

Contributions are welcome.

Areas for contribution include:

* Backend Development
* Frontend Development
* UI/UX Improvements
* Testing
* Documentation
* AI Prompt Engineering
* Performance Optimization

Please open an issue before submitting major feature requests or architectural changes.

---

# 🌟 Vision

ToodlesCode aims to become an AI-powered developer companion that helps engineers:

* Write Better Code
* Learn Software Engineering
* Discover Open Source Projects
* Track Technical Growth
* Improve Problem Solving Skills
* Build Production-Ready Applications

The long-term goal is to create a platform where developers can learn, review, improve, and grow—all in one place.

---

Built with ❤️ using Java, Spring Boot, Thymeleaf, and Google Gemini AI.
