
import JWPlayerKit
import Flutter
import Foundation


struct PlayerConfigData: Decodable  {
    let file: String
    let image: String?
    let startTime: CDouble?
    let endTime: CDouble?
}

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
                let decoder = JSONDecoder()
                let encoder = JSONEncoder()
                do {
//                    let jsonData = try encoder.encode(config)
//                    let configData = try decoder.decode(PlayerConfigData.self, from: jsonData)
//                    let config1 = try ConfigTransfomer(tranformable: config).toJWConfig()
//                    let externalMetaData = JWExternalMetadata(identifier: "external metaData", startTime: 40, endTime: 160)
    //                config.externalMetadata([externalMetaData])
    //                views?[id]?.set(config: config)
//                    views?[id]?.setConfig(config: config)
//                let file = configData.file as String
                let file = config["file"] as? String
                let image = config["image"] as? String
                let startTime = config["startTime"] as? Double
                let itemBuilder = try JWPlayerItemBuilder()
                    .file(URL(string:file!)!)

                if startTime != nil {
                    try itemBuilder.startTime(ceil(startTime!))
                }

                if image != nil && !image!.isEmpty{
                    try itemBuilder.posterImage(URL(string:image!)!)
                }

                let item = try itemBuilder.build()

                let config1 = try JWPlayerConfigurationBuilder()
                    .playlist([item])
                    .build()

                controller.player.configurePlayer(with: config1)


//                let config1 = try ConfigTransfomer(tranformable: config).toJWConfig()
//        controller.player.configurePlayer(with: config1)
                } catch {
                    print(error.localizedDescription)
                }
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
