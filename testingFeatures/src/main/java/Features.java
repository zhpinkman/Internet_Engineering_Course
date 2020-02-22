import jdk.vm.ci.meta.Local;
import org.joda.time.DateTime;

import java.time.*;

public class Features {
    public static void main(String[] args) {
        LocalTime localTime = LocalTime.now();
        LocalTime newLocalTime = localTime.plusSeconds(140);
        Duration duration = Duration.between(localTime, newLocalTime);
        System.out.println(duration.getSeconds());
        System.out.println(duration.getSeconds() / 60);
        System.out.println(duration.getSeconds() % 60);

    }
}
