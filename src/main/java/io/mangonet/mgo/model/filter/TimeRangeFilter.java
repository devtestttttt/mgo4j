package io.mangonet.mgo.model.filter;

import lombok.Data;

@Data
public class TimeRangeFilter {

    private Long startTime;

    private Long endTime;

    public TimeRangeFilter(Long startTime, Long endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
