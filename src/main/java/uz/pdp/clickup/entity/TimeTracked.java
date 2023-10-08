package uz.pdp.clickup.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "time_tracked")
public class TimeTracked extends BaseEntity {
    @ManyToOne(optional = false)
    private Task task;

    @Column
    private LocalDateTime startedAt;

    @Column
    private LocalDateTime stoppedAt;
}
