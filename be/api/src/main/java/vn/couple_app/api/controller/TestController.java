package vn.couple_app.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.couple_app.api.dto.request.DeleteImageRequest;
import vn.couple_app.api.dto.response.ResponseApi;
import vn.couple_app.api.service.RabbitMQProducer;

@RequestMapping("/tests")
@RestController
@RequiredArgsConstructor
public class TestController {
    final RabbitMQProducer producer;
    @GetMapping("/rabbit")
    public ResponseApi<?> testRabbit() {
        producer.delImage(DeleteImageRequest.builder().id("id:22399322334433").build());
        return ResponseApi.createSuccess();
    }
}
