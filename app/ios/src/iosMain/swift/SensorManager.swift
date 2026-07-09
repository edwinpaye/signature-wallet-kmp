public class SensorManager: NSObject {
    public static let shared = SensorManager()
    
    private override init() {
        super.init()
    }
    
    public func startSensorMonitoring() {
        print("Starting sensor monitoring...")
    }
    
    public func stopSensorMonitoring() {
        print("Stopping sensor monitoring...")
    }
    
    public func getSensorData() -> [String: Double] {
        return [:]
    }
}
