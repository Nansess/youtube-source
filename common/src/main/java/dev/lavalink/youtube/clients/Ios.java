package dev.lavalink.youtube.clients;

import com.sedmelluq.discord.lavaplayer.tools.JsonBrowser;
import com.sedmelluq.discord.lavaplayer.tools.io.HttpInterface;
import dev.lavalink.youtube.clients.skeleton.StreamingNonMusicClient;

public class Ios extends StreamingNonMusicClient {
    public static String CLIENT_VERSION = "19.07.5";

    public static ClientConfig BASE_CONFIG = new ClientConfig()
        .withApiKey("AIzaSyB-63vPrdThhKuerbB2N_l7Kwwcxj6yUAc")
        .withUserAgent(String.format("com.google.ios.youtube/%s (iPhone14,5; U; CPU iOS 15_6 like Mac OS X)", CLIENT_VERSION))
        .withClientName("IOS")
        .withClientField("clientVersion", CLIENT_VERSION)
        .withUserField("lockedSafetyMode", false);

    @Override
    protected ClientConfig getBaseClientConfig(HttpInterface httpInterface) {
        return BASE_CONFIG.copy();
    }

    @Override
    protected JsonBrowser extractPlaylistVideoList(JsonBrowser json) {
        return json.get("contents")
            .get("singleColumnBrowseResultsRenderer")
            .get("tabs")
            .index(0)
            .get("tabRenderer")
            .get("content")
            .get("sectionListRenderer")
            .get("contents")
            .index(0)
            .get("itemSectionRenderer")
            .get("contents")
            .index(0)
            .get("playlistVideoListRenderer");
    }

    @Override
    public String getPlayerParams() {
        return MOBILE_PLAYER_PARAMS;
    }

    @Override
    public String getIdentifier() {
        return BASE_CONFIG.getName();
    }
}