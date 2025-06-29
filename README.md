# 📓 NoteMark

**NoteMark** is an Android note-taking app built from scratch using modern development best practices — Jetpack Compose, Clean Architecture, Paging, Ktor, and an offline-first approach.

---

## ✅ Features (Milestone 1 + 2 Completed)

### 📱 Login / Registration Screens
- Fully functional forms with validation:
  - Email, password, repeat password
  - Inline error handling and helper texts
  - Toggle password visibility
- Adaptive layout support (portrait / landscape, phone / tablet)
- Session management
- Navigation to the Note List screen after login
- Backstack cleanup after login or registration

### 🚀 Note List Screen
- Staggered grid layout
- Display:
  - Creation date
  - Title
  - Content preview (limited characters)
- Floating Action Button (FAB) to create a new note
- Long-press to delete with confirmation dialog
- Adaptive UI support

### 📝 Create / Edit Note Screen
- Autofocus on title field
- Default placeholder title: `New Note`
- Load existing note by ID (for editing)
- Confirmation dialog before exiting with unsaved changes
- Update and return flow
- Auto-save behavior

### 🌐 Offline-first Syncing
- All note operations (create, update, delete) are reflected locally first
- Automatic syncing when internet becomes available:
  - Unsynced notes are sent to the backend
  - Deleted notes are removed remotely
- Notes that failed to sync are marked and retried in background

---

## ⚙️ Tech Stack

- **Jetpack Compose**
- **Paging 3 (with RemoteMediator)**
- **Room (Local database)**
- **Ktor Client** (Auth, Serialization, Logging)
- **Hilt (Dependency Injection)**
- **Firebase** (Auth, Analytics, Crashlytics)
- **Material3 Adaptive Components**
- **Accompanist System UI Controller**
- **Kotlin Serialization**
- **Immutable Collections**

---

## 📦 Architecture

- **MVI (Model-View-Intent)** for predictable state management
- **Clean Architecture**
  - `data / domain / presentation` separation
- **Offline-first** pattern
  - Local database as the single source of truth
  - RemoteMediator handles syncing with the server
- **Scoped background sync**
  - Using `@ApplicationScope` to perform sync tasks outside the UI lifecycle

---

## 🧭 Navigation

```kotlin
sealed interface Screens {
    object SplashScreen
    object LandingScreen
    object LoginScreen
    object RegisterScreen
    object NoteListScreen
    data class CreateNoteScreen(val noteId: String? = null)
}
