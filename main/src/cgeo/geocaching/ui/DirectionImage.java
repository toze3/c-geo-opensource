package cgeo.geocaching.ui;

import cgeo.geocaching.files.LocalStorage;
import cgeo.geocaching.network.Network;
import cgeo.geocaching.network.Parameters;

import ch.boye.httpclientandroidlib.HttpResponse;
import org.apache.commons.lang3.StringUtils;

import java.io.File;

public class DirectionImage {

    public static void getDrawable(final String geocode, final String code) {
        if (StringUtils.isBlank(geocode) || StringUtils.isBlank(code)) {
            return;
        }

        final HttpResponse httpResponse =
                Network.getRequest("http://www.geocaching.com/ImgGen/seek/CacheDir.ashx", new Parameters("k", code));
        if (httpResponse != null) {
            LocalStorage.saveEntityToFile(httpResponse, getDirectionFile(geocode, true));
        }
    }

    public static File getDirectionFile(final String geocode, final boolean createDirs) {
        return LocalStorage.getStorageFile(geocode, "direction.png", false, createDirs);
    }

}
