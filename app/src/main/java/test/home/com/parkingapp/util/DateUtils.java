package test.home.com.parkingapp.util;

public class DateUtils  {


    public static String elapsedTime(int seconds) {
        if (seconds > 0) {
            String strTime = "";
            int mins = seconds / 60;
            int hours = mins / 60;
            mins %= 60;
            if (hours > 0) strTime += hours + " h ";
            strTime += mins + " min";
            return strTime.trim();
        }
        return "0 min";
    }
}
