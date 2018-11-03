package hrytsenko.metadatamanager;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping(
        value = "api",
        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@AllArgsConstructor
public class ManageFilesResource {

    @PostMapping(
            value = "_create")
    public void create(
            @RequestBody Map<String, ?> request) {
        log.info("Create file '{}'", request.get("id"));
    }

}
