package id.co.bni.qris.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConfigUtils {
    @Value("${artajasa.url.cb}")
    public String endpoint;

}
