import Foundation

public class SignatureWalletApp: NSObject {
    @objc public static let shared = SignatureWalletApp()
    
    override private init() {
        super.init()
    }
    
    @objc public func initialize() {
        print("Signature Wallet initialized")
    }
    
    @objc public func startSignatureCapture() {
        print("Starting signature capture...")
    }
    
    @objc public func processSignature(_ points: [[String: NSNumber]]) {
        print("Processing signature with \(points.count) points")
    }
}
