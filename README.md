# Signature Wallet KMP

A Kotlin Multiplatform mobile and desktop application for secure transaction signing with advanced biometric validation, sensor-based fraud detection, and offline-first architecture.

## 🎯 Features

- **Multi-Platform Support**: iOS, Android, Desktop (Windows/Mac/Linux)
- **Signature Capture**: Real-time signature drawing with pressure, angle, and velocity tracking
- **Sensor Integration**: Gyroscope, Accelerometer, Magnetometer for pattern validation
- **Offline Data Management**: Local-first architecture with SQLite/Room
- **Bank-Grade Security**: Encryption at rest, SSL pinning, secure key storage
- **Responsive UI**: Jetpack Compose Multiplatform with adaptive layouts
- **Hexagonal Architecture**: Clean separation of concerns, loosely coupled components
- **High Testability**: Dependency injection, mocking-friendly design
- **User Validation**: Multi-factor authentication with biometric support

## 🏗️ Architecture

```
signature-wallet-kmp/
├── gradle/                          # Gradle wrapper and build scripts
├── app/                             # Platform entry points
│   ├── android/                     # Android app module
│   ├── ios/                         # iOS app module (Swift)
│   └── desktop/                     # Desktop app module
├── core/                            # Core shared modules
│   ├── common/                      # Utilities, extensions, constants
│   ├── design-system/               # Compose theming, components
│   └── security/                    # Encryption, key management
├── feature/                         # Feature modules (by domain)
│   ├── auth/                        # Authentication feature
│   │   ├── data/
│   │   ├── domain/
│   │   └── presentation/
│   ├── wallet/                      # Wallet management
│   │   ├── data/
│   │   ├── domain/
│   │   └── presentation/
│   ├── signature/                   # Signature capture & validation
│   │   ├── data/
│   │   ├── domain/
│   │   └── presentation/
│   └── transactions/                # Transaction history
│       ├── data/
│       ├── domain/
│       └── presentation/
└── shared/                          # Shared KMP code
    ├── src/commonMain/
    ├── src/androidMain/
    ├── src/iosMain/
    └── src/desktopMain/
```

## 📋 Prerequisites

- JDK 17+
- Kotlin 2.3.21
- Gradle 9.6.1 (included)
- Xcode 14+ (for iOS builds)
- Android SDK API 24+
- Android NDK (for sensors)

## 🚀 Getting Started

```bash
# Clone repository
git clone https://github.com/edwinpaye/signature-wallet-kmp.git
cd signature-wallet-kmp

# Build all modules
./gradlew build

# Run Android
./gradlew :app:android:installDebug

# Run Desktop
./gradlew :app:desktop:run

# Run iOS (requires Xcode)
./gradlew :app:ios:build
```

## 🔧 Project Structure Details

### Clean Architecture Layers

1. **Presentation Layer** (`presentation/`)
   - UI Components (Compose)
   - ViewModels (MVI/MVVM)
   - State Management
   - Navigation

2. **Domain Layer** (`domain/`)
   - Use Cases
   - Entities
   - Repository Interfaces
   - Business Rules

3. **Data Layer** (`data/`)
   - Repository Implementations
   - Data Sources (Local/Remote)
   - Database (Room)
   - Network (Ktor Client)

4. **Security Layer** (`security/`)
   - Encryption/Decryption
   - Key Management
   - Secure Storage

### Key Features Implementation

#### Signature Capture
- Real-time drawing canvas
- Pressure & velocity tracking
- Swipe pattern validation
- Signature verification against baseline

#### Sensor Integration
- Gyroscope: Rotation-based fraud detection
- Accelerometer: Movement pattern analysis
- Magnetometer: Location-based validation
- Compass: Orientation tracking

#### Offline Management
- SQLite with Room ORM
- Encrypted local storage
- Sync queue for offline transactions
- Conflict resolution strategy

#### Security
- AES-256 encryption
- PBKDF2 key derivation
- SSL Certificate pinning
- Biometric authentication

## 📦 Dependencies

### Core
- Kotlin 2.3.21
- Gradle 9.6.1
- Coroutines 1.8.x
- Serialization 1.6.x

### UI
- Compose Multiplatform 1.6.x
- Voyager (Navigation)
- Kamel (Image Loading)

### Data
- Room 2.6.x
- SQLite 3.x
- Ktor Client 2.3.x

### Security
- Bouncy Castle 1.77
- Tink (Google Encryption)

### Testing
- Kotest
- Mockk
- Turbine (Flow testing)

## 🧪 Testing Strategy

- **Unit Tests**: Business logic, use cases
- **Integration Tests**: Data layer, repository
- **UI Tests**: Compose components (Paparazzi)
- **E2E Tests**: Critical user flows

## 📱 Platform-Specific Notes

### Android
- Targets API 24+
- Uses CameraX for capture
- Accelerometer/Gyroscope via SensorManager
- BiometricPrompt for authentication

### iOS
- Targets iOS 14+
- Uses Vision framework for signature processing
- CoreMotion for sensors
- LocalAuthentication for biometrics

### Desktop
- Supports Windows, macOS, Linux
- Uses AWT for drawing (Swing components)
- Keyboard/mouse input handling
- Simulated sensor data

## 🔐 Security Best Practices

- Never log sensitive data
- Encrypt all local storage
- SSL pinning for API communication
- Secure random number generation
- Hardware-backed keystore (Android)
- Secure Enclave (iOS)

## 📝 License

MIT License - See LICENSE file

## 👤 Author

edwinpaye

## 🤝 Contributing

1. Create feature branch
2. Follow Kotlin style guide
3. Add tests for new features
4. Submit pull request

---

**Last Updated**: 2026-07-08
