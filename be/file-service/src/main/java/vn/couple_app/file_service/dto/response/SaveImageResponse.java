package vn.couple_app.file_service.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SaveImageResponse {
    String id;
    String url;
}