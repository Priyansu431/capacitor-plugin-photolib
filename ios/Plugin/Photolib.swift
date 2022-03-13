import Foundation

@objc public class Photolib: NSObject {
    @objc public func echo(_ value: String) -> String {
        print(value)
        return value
    }
}
