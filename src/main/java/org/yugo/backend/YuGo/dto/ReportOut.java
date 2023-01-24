package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ReportOut {
    private List<LocalDate> keys;
    private List<Double> values;

    public ReportOut(List<LocalDate> keys, List<Double> values) {
        this.keys = keys;
        this.values = values;
    }
}
