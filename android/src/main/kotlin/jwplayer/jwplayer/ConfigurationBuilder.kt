package jwplayer.jwplayer

import com.jwplayer.pub.api.configuration.PlayerConfig
import com.jwplayer.pub.api.media.playlists.PlaylistItem
import org.json.JSONObject
import com.jwplayer.pub.api.media.ads.AdBreak
import com.jwplayer.pub.api.configuration.ads.VastAdvertisingConfig



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

    private fun getPlaylist(config: JSONObject): List<PlaylistItem> {
        var array = mutableListOf<PlaylistItem>()
        return array
    }

    fun toPlayerConfig(config: JSONObject): PlayerConfig {
        var builder = PlayerConfig.Builder()
        builder.file(getFile(config))
        if (config.has("playlist")) {
            print("has playlist")
            builder.playlist(getPlaylist(config))
        }

        if (config.has("advertising")) {
            print("has advertising")
                var advertising = config.getJSONObject("advertising")


                if (advertising.has("client") && (advertising["client"] as String?) == "VAST") {
                    var schedule = advertising["schedule"]
                    print("?????? schedule " + schedule)
                    if (schedule != null) {
                        var schedule = advertising.getJSONObject("schedule")

                        var tag: String? = null
//
                        try {
                            tag = schedule["tag"] as String?
                        } catch (e: Exception) {
                            TODO("Not yet implemented")
                        }
//
                        var offset: String? = null
                        try {
                            offset = schedule["offset"] as String?
                        } catch (e: Exception) {
                            TODO("Not yet implemented")
                        }
////
////
                    println(">>>> " + tag)
                    println(">>>> " + offset)
////
                        var adSchedule = ArrayList<AdBreak>()

//                        if (tag != null && offset != null) {
                            val adBreak1: AdBreak = AdBreak.Builder()
                                .tag(tag)
                                .offset(offset)
                                .build()

                            adSchedule.add(adBreak1)
//                        }

                        val advertisingConfig: VastAdvertisingConfig =
                            VastAdvertisingConfig.Builder()
                                .schedule(adSchedule)
                                .build()

                        builder.advertisingConfig(advertisingConfig)

                    }
//
//
                }

        }
//        builder
//            .playlistUrl("https://cdn.jwplayer.com/v2/playlists/kYTpOhIk?format=json")
        builder.useTextureView(true)
        return builder.build()
    }
}