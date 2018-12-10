package org.editice.saber.facade.base.evaluator;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author tinglang
 * @date 2018/12/10.
 */
@Builder
public class TimeRecord implements Comparable<TimeRecord> {

    @Getter
    @Setter
    String beanName;

    @Getter
    @Setter
    long timeCost;

    @Override
    public int compareTo(TimeRecord o) {
        if (this.timeCost == o.getTimeCost()) {
            return 0;
        }
        return this.timeCost < o.getTimeCost() ? 1 : -1;
    }
}
