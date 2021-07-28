package algo.util;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MyUtil {

    public static <T> List<T> getDuplicates(List<T> list) {
        return list.stream()
                    .filter( p -> Collections.frequency(list,p) > 1)
                    .distinct()
                    .collect(Collectors.toList());
    }
}
