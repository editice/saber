package framework;

import lombok.Data;

/**
 * @author tinglang
 * @date 2020/3/19.
 */
@Data
public class Pair<T> implements Comparable<Pair> {


    T object;

    Integer priority;

    public Pair(T object, int priority) {
        this.object = object;
        this.priority = priority;
    }

    @Override
    public int compareTo(Pair o) {
        return o.getPriority().compareTo(priority);
    }

}
