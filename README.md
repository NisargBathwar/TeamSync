## 📱 Screenshots

<table>
<tr>
<td align="center">
<b>1. Splash</b><br>
<img src="screenshot/Team%20Sync%20ScreenShot/Screenshot_20260818_181224.png" width="180"/>
</td>

<td align="center">
<b>2. Login</b><br>
<img src="screenshot/Team%20Sync%20ScreenShot/Screenshot_20260818_181301.png" width="180"/>
</td>

<td align="center">
<b>3. Register</b><br>
<img src="screenshot/Team%20Sync%20ScreenShot/Screenshot_20260818_181315.png" width="180"/>
</td>

<td align="center">
<b>4. Dashboard</b><br>
<img src="screenshot/Team%20Sync%20ScreenShot/Screenshot_20260818_181339.png" width="180"/>
</td>
</tr>

<tr>
<td align="center">
<b>5. Get Started</b><br>
<img src="screenshot/Team%20Sync%20ScreenShot/Screenshot_20260818_181407.png" width="180"/>
</td>

<td align="center">
<b>6. Join Team</b><br>
<img src="screenshot/Team%20Sync%20ScreenShot/Screenshot_20260818_181415.png" width="180"/>
</td>

<td align="center">
<b>7. Create Team</b><br>
<img src="screenshot/Team%20Sync%20ScreenShot/Screenshot_20260818_181429.png" width="180"/>
</td>

<td align="center">
<b>8. Team Info</b><br>
<img src="screenshot/Team%20Sync%20ScreenShot/Screenshot_20260818_181446.png" width="180"/>
</td>
</tr>

<tr>
<td align="center">
<b>9. Teams</b><br>
<img src="screenshot/Team%20Sync%20ScreenShot/Screenshot_20260818_181510.png" width="180"/>
</td>

<td align="center">
<b>10. Tasks</b><br>
<img src="screenshot/Team%20Sync%20ScreenShot/Screenshot_20260818_181618.png" width="180"/>
</td>
</tr>
</table>


---

## ✨ Features

- 🔐 User registration and login using Firebase Authentication
- 👤 User profile and account management
- 👥 Create and manage teams
- 🔗 Join teams using an invite code
- 📋 View team members and team information
- 📊 Track member task completion
- ✅ Create and manage team tasks
- ☑️ Mark tasks as completed
- 🔄 Real-time team and task data synchronization using Firebase Firestore
- 🎨 Clean and responsive Jetpack Compose UI
- 🧭 Simple navigation between application screens

---

## 🛠️ Tech Stack

| Technology | Usage |
|------------|-------|
| **Kotlin** | Primary programming language |
| **Jetpack Compose** | UI development |
| **Material 3** | UI components and styling |
| **MVVM** | Presentation architecture |
| **Clean Architecture** | Project organization |
| **Hilt** | Dependency injection |
| **Firebase Authentication** | User authentication |
| **Firebase Firestore** | Cloud database and real-time data |
| **Coroutines** | Asynchronous programming |
| **StateFlow** | UI state management |
| **Navigation Compose** | Screen navigation |
| **Gradle Kotlin DSL** | Build configuration |

---

## 🏗️ Architecture

TeamSync follows **Clean Architecture with MVVM** to keep the application modular, maintainable, and easy to extend.

```text
                    ┌─────────────────────┐
                    │    Presentation     │
                    │                     │
                    │  ViewModels + State │
                    └──────────┬──────────┘
                               │
                               ▼
                    ┌─────────────────────┐
                    │       Domain        │
                    │                     │
                    │ Use Cases + Models  │
                    │ Repository Contracts│
                    └──────────┬──────────┘
                               │
                               ▼
                    ┌─────────────────────┐
                    │        Data         │
                    │                     │
                    │ Repositories + DTOs │
                    │ Firebase Sources    │
                    └──────────┬──────────┘
                               │
                               ▼
                    ┌─────────────────────┐
                    │ Firebase Services   │
                    │                     │
                    │ Auth + Firestore    │
                    └─────────────────────┘

