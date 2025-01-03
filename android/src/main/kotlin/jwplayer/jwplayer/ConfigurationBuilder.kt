package jwplayer.jwplayer

import com.jwplayer.pub.api.configuration.PlayerConfig
import com.jwplayer.pub.api.configuration.ads.VastAdvertisingConfig
import com.jwplayer.pub.api.configuration.ads.ima.ImaAdvertisingConfig
import com.jwplayer.pub.api.media.ads.AdBreak
import com.jwplayer.pub.api.media.playlists.PlaylistItem
import org.json.JSONArray
import org.json.JSONObject


class ConfigurationBuilder {

    fun normalizeAdConfig(config: JSONObject): JSONObject {
        val advertising: JSONObject = config.getJSONObject("advertising")
        if (advertising.has("client")) {
            if ((advertising["client"] as String).contains("googima")) {
                advertising.put("client", "GOOGIMA")
            } else if ((advertising["client"] as String).contains("dai")) {
                advertising.put("client", "IMA_DAI")
            } else if ((advertising["client"] as String).contains("vast")) {
                advertising.put("client", "VAST")
            } else if ((advertising["client"] as String).contains("freewheel")) {
                advertising.put("client", "FREEWHEEL")
            }
            config.put("advertising", advertising)
        }
        return config
    }

    private fun getFile(config: JSONObject): String {
        return config.getString("file")
    }

    private fun getPlaylistItems(config: JSONObject): List<PlaylistItem> {
        var image: String? = getImage(config)
        val builder = PlaylistItem.Builder()
            builder.file(getFile(config))
            builder.startTime(getStartTime(config))

        if(image != null){
            builder.image(image)
        }
        var playlistItem : PlaylistItem  =  builder.build()
        var array = mutableListOf<PlaylistItem>()
        array.add(playlistItem)
        return array
    }

    private fun getImage(config: JSONObject): String? {
        if (config.has("image")) {
            if (config["image"] is String) {
                var image = config.getString("image")
                return image;
            }
        }
        return null
    }

    private fun getStartTime(config: JSONObject): Double {
        if (config.has("startTime")) {
            var startTime = config["startTime"]
            println(startTime)
            if (startTime is Double) {
                return startTime
            }
        }
        return 0.0
    }

    fun toPlayerConfig(config: JSONObject): PlayerConfig {
        var builder = PlayerConfig.Builder()
        var playlist: List<PlaylistItem> = getPlaylistItems(config)
        builder.playlist(playlist)

        try {
            if (config.has("advertising")) {
                if (config["advertising"] is JSONObject) {
                    var advertising = config.getJSONObject("advertising")

                    if (advertising.has("client") && (advertising["client"]) == "VAST") {

                        var schedules = advertising["schedules"]
                        if (schedules is JSONArray) {
                            var adBreaks = toListAdBreaks(advertising.getJSONArray("schedules"))
                            if (!adBreaks.isEmpty()) {
                                val advertisingConfig: VastAdvertisingConfig =
                                    VastAdvertisingConfig.Builder()
                                        .schedule(adBreaks)
                                        .build()

                                builder.advertisingConfig(advertisingConfig)
                            }
                        }
                    }

                    if (advertising.has("client") && (advertising["client"]) == "GOOGIMA") {

                        var schedules = advertising["schedules"]
                        if (schedules is JSONArray) {
                            var adBreaks = toListAdBreaks(advertising.getJSONArray("schedules"))
                            if (!adBreaks.isEmpty()) {
                                val advertisingConfig: ImaAdvertisingConfig =
                                    ImaAdvertisingConfig.Builder()
                                        .schedule(adBreaks)
                                        .build()

                                builder.advertisingConfig(advertisingConfig)
                            }
                        }
                    }
                }

            }
        } catch (e: Exception) {
            println(e)
        }

//        try {
//            if (config.has("playlistUrl")) {
//                var playlistUrl = config["playlistUrl"]
//                if (playlistUrl is String) {
//                    builder.playlistUrl(playlistUrl)
//                }
//            }
//        } catch (e: Exception) {
//            println(e)
//        }

        if (config.has("autostart")) {
            if (config["autostart"] == true) {
                builder.autostart(true)
            } else {
                builder.autostart(false)
            }
        }

        builder.useTextureView(true)
        return builder.build()
    }

    fun toListAdBreaks(schedules: JSONArray): ArrayList<AdBreak> {
        var adSchedule = ArrayList<AdBreak>()
        for (i in 0 until schedules.length()) {
            val schedule = schedules.getJSONObject(i)
            var tag = schedule["tag"]
            var offset = schedule["offset"]
            if (tag is String && offset is String) {
                val adBreak: AdBreak = AdBreak.Builder()
                    .tag(tag)
                    .offset(offset)
                    .build()
                adSchedule.add(adBreak)
            }
        }
        return adSchedule
    }
}