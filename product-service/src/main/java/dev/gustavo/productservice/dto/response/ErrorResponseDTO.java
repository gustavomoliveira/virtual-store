package dev.gustavo.productservice.dto.response;

import java.time.LocalDateTime;

public record ErrorResponseDTO(Integer status, String message, LocalDateTime timeStamp) {
}
