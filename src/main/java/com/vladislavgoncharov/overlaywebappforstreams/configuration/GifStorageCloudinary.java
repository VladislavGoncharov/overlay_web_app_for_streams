package com.vladislavgoncharov.overlaywebappforstreams.configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.util.Map;

@Configuration
public class GifStorageCloudinary {

    private static String gifURL = "";

    @Bean
    public static Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "developervlad",
                "api_key", "186944654822819",
                "api_secret", "6OvzVqdRBj-DZlcxhIH-wqP3zbE",
                "secure", true));
    }

    @SneakyThrows
    public static String getGifURL()  {
        if (gifURL.isEmpty()) {
            File file = new File("src/main/resources/static/image/gif-default/default.gif");

            Map uploadResult = cloudinary().uploader()
                    .upload(file, ObjectUtils.asMap("folder", "owerlay/Default gif"));
            gifURL = String.valueOf(uploadResult.get("url"));
        }
        return gifURL;
    }

    public static void setGifURL(String gifURL) {
        GifStorageCloudinary.gifURL = gifURL;
    }
}
