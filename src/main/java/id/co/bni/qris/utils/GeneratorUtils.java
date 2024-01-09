package id.co.bni.qris.utils;

import com.google.gson.Gson;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;

@Component
public class GeneratorUtils {

    public long startTime(){
        return System.nanoTime();
    }

    public long elapsedTime(long startTime) {
        long endTime = System.nanoTime();
        return (endTime-startTime)/1000000;
    }

    public String generateGMTDatetime(){
        OffsetDateTime now = OffsetDateTime.now();
        Instant ins = now.toInstant();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("EEE, dd LLL yyyy HH:mm:ss");
        OffsetDateTime trxDateUTC = ins.atOffset(ZoneOffset.UTC);
        return trxDateUTC.format(dtf) + " GMT";
    }

    public String generateTransmissionDateTimeMMdd() {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMdd");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return dateFormat.format(currentDate);
    }

    public String generateTransmissionDateTime() {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMddHHmmss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return dateFormat.format(currentDate);
    }

    public String getRandomNumberString() {
        // It will generate 6 digit random Number.
        // from 0 to 999999
        Random rnd = new Random();
        int number = rnd.nextInt(999999) + 1;

        // this will convert any number sequence into 6 character.
        String numberString = String.format("%06d", number);
        if (numberString.equals("999999"))
            return "000001";
        else
            return numberString;
    }

    public String generateTimeGeneral(String pattern) {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(currentDate);
    }

    public String convertToOneLine(@NotNull String string) {
        return string.replaceAll("\r*\n*", "");
    }
    public String eraseWhiteSpace(@NotNull String string) {
        return string.replaceAll(" ", "");
    }

    public String convertObjectToString(Object object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }

    public Map convertStringToMap(String json){
        Gson gson = new Gson();
        return gson.fromJson(json.toString(), Map.class);
    }

    public String jsonToMap(String json){
        Gson gson = new Gson();
        return gson.toJson(json, Map.class);
    }

}
