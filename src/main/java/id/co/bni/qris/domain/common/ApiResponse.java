package id.co.bni.qris.domain.common;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse <T> {
    private String message;
    private T data;
    private List<String> errors;
}