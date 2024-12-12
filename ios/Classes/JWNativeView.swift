
import JWPlayerKit
import Flutter

protocol PlayerInterface: AnyObject {
    var eventSink: FlutterEventSink? { get set }
//    func setConfig(config: JWPlayerConfiguration)
    func setConfig(config: JSON)
    func play()
    func pause()
    func stop()
}


class JWNativeView: NSObject, FlutterPlatformView, PlayerInterface {
    
    private let controller = PlayerViewController()
    
    var eventSink: FlutterEventSink? {
        didSet {
            controller.setEventSink(eventSink)
        }
    }
    
    func view() -> UIView {
        return controller.view
    }
    
//    func setConfig(config: JWPlayerConfiguration) {
//        controller.player.configurePlayer(with: config)
//    }

    func setConfig(config: JSON) {
//    let arguments = call.arguments as! JSON
//                let config = arguments["config"] as! JSON
//                let id = arguments["id"] as! Int64
                let decoder = JSONDecoder()
                do {
                    let config1 = try ConfigTransfomer(tranformable: config).toJWConfig()
                    let externalMetaData = JWExternalMetadata(identifier: "external metaData", startTime: 40, endTime: 160)
    //                config.externalMetadata([externalMetaData])
    //                views?[id]?.set(config: config)
//                    views?[id]?.setConfig(config: config)
controller.player.configurePlayer(with: config1)
                } catch {
                    print(error.localizedDescription)
                }
//        controller.player.configurePlayer(with: config)
    }
    
    func play() {
        controller.player.play()
    }
    
    func pause() {
        controller.player.pause()
    }
    
    func stop() {
        controller.player.stop()
    }
}
