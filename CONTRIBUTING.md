# Contributing to Signature Wallet KMP

## Development Setup

1. Clone the repository
2. Install JDK 17+
3. Install Android SDK (API 24+)
4. For iOS: Install Xcode 14+

## Architecture Guidelines

### Hexagonal Architecture Layers

- **Domain Layer**: Pure Kotlin, no platform dependencies
- **Data Layer**: Repository implementations, data sources
- **Presentation Layer**: Compose UI, ViewModels
- **Infrastructure**: Sensors, Network, Storage

### Code Style

- Follow [Kotlin conventions](https://kotlinlang.org/docs/coding-conventions.html)
- Use meaningful variable/function names
- Add KDoc comments for public APIs
- Keep functions small and focused

### Testing

- Unit tests for business logic (use cases)
- Integration tests for repositories
- UI tests for critical flows

```bash
./gradlew test
```

### Submitting Changes

1. Create feature branch: `git checkout -b feature/your-feature`
2. Commit with clear messages
3. Push and create pull request
4. Ensure all checks pass

## Reporting Issues

Include:
- Platform (Android/iOS/Desktop)
- Reproduction steps
- Expected vs actual behavior
- Logs/screenshots
