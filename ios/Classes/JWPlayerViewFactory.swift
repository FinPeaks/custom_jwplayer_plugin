
import JWPlayerKit
import Flutter
import Foundation


fileprivate enum Methods: String {
    case create
    case pause
    case stop
    case play
    case setConfig
}

class JWPlayerFactory: NSObject, FlutterPlatformViewFactory, FlutterStreamHandler {
    
    private var messenger: FlutterBinaryMessenger
    private var eventHandler: FlutterEventChannel?
    private var eventSink: FlutterEventSink?
    
    var views: [Int64: PlayerInterface]? = [:]
    var lastView: Int64 = 0
    
    init(messenger: FlutterBinaryMessenger) {
        self.messenger = messenger
        let channel = FlutterMethodChannel(name: "playerview", binaryMessenger: messenger)
        super.init()
        eventHandler = FlutterEventChannel(name: "com.jwplayer.view", binaryMessenger: messenger)
        eventHandler?.setStreamHandler(self)
        channel.setMethodCallHandler(self.handle)
    }
    
    func create(withFrame frame: CGRect, viewIdentifier viewId: Int64, arguments args: Any?) -> FlutterPlatformView {
        let nativeView = JWNativeView()
        views?[viewId] = nativeView
        nativeView.eventSink = eventSink
        lastView = viewId

        let view = nativeView.view()
    
        view.frame =  frame
        return nativeView
    }
    
    public func handle(_ call: FlutterMethodCall, result: @escaping FlutterResult) {
        guard let method = Methods.init(rawValue: call.method) else {
            result(FlutterMethodNotImplemented)
            return
        }
        switch method {
        case .create:
            result(lastView)
        case .play:
            let arguments = call.arguments as! JSON
            let id = arguments["id"] as! Int64
            views?[id]?.play()
        case .pause:
            let arguments = call.arguments as! JSON
            let id = arguments["id"] as! Int64
            views?[id]?.pause()
        case .stop:
             let arguments = call.arguments as! JSON
            let id = arguments["id"] as! Int64
            views?[id]?.stop()
        case .setConfig:
            let arguments = call.arguments as! JSON
            let config = arguments["config"] as! JSON
            let id = arguments["id"] as! Int64

            views?[id]?.setConfig(config: config)

//            let decoder = JSONDecoder()
//            do {
//                let config = try ConfigTransfomer(tranformable: config).toJWConfig()
//                let externalMetaData = JWExternalMetadata(identifier: "external metaData", startTime: 40, endTime: 160)
////                config.externalMetadata([externalMetaData])
////                views?[id]?.set(config: config)
//                views?[id]?.setConfig(config: config)
//            } catch {
//                print(error.localizedDescription)
//            }
        }
    }
    
    func onListen(withArguments arguments: Any?, eventSink events: @escaping FlutterEventSink) -> FlutterError? {
        eventSink = events
        return nil
    }
    
    func onCancel(withArguments arguments: Any?) -> FlutterError? {
       views?.first?.value.eventSink = nil
       return nil
    }
}
