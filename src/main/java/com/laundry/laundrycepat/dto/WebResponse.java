package com.laundry.laundrycepat.dto;


import com.laundry.laundrycepat.entity.CustomerEntity;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class WebResponse<T> {
    private String message;
    private T data;
    private String errors;
}
