import Flutter
import UIKit
import JWPlayerKit

fileprivate enum Methods: String {
    case getPlatformVersion
    case setLicenseKey
    case setConfig
    case `init`
}

public class JwplayerPlugin:  NSObject, FlutterPlugin {

    public static func register(with registrar: FlutterPluginRegistrar) {
        let channel = FlutterMethodChannel(name: "jwplayer", binaryMessenger: registrar.messenger())
        let instance = JwplayerPlugin()

        let factory = JWPlayerFactory(messenger: registrar.messenger())
        registrar.register(
            factory,
            withId: "<platform-view-type>")
        registrar.addMethodCallDelegate(instance, channel: channel)
    }
    
    public func handle(_ call: FlutterMethodCall, result: @escaping FlutterResult) {
        guard let method = Methods(rawValue: call.method) else {
            result(FlutterMethodNotImplemented)
            return
        }

        switch method {
        case .setLicenseKey:
            let arguments = call.arguments as! [String: Any]
            let key = arguments["licenseKey"] as! String
            JWPlayerKitLicense.setLicenseKey(key)
            result(nil)
        case .`init`:
            result("init")
        case .getPlatformVersion:
            result(JWPlayerKit.sdkVersion)
        case .setConfig:
            print("setConfig")
        default:
            result(FlutterMethodNotImplemented)
        }
    }
}
