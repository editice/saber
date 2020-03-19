package framework;

import lombok.Data;

/**
 * @author tinglang
 * @date 2020/3/19.
 */
@Data
public class Triple<T, V> implements Comparable<Triple> {


    T t;

    V v;

    Integer priority;

    public Triple(T t, V v, int priority) {
        this.t = t;
        this.v = v;
        this.priority = priority;
    }

    @Override
    public int compareTo(Triple o) {
        return o.getPriority().compareTo(priority);
    }

}
