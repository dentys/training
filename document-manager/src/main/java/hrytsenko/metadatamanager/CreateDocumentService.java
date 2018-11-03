package hrytsenko.metadatamanager;

import hrytsenko.metadatamanager.model.Document;
import hrytsenko.metadatamanager.model.File;
import hrytsenko.metadatamanager.model.Metadata;
import lombok.AllArgsConstructor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

@Component
@AllArgsConstructor
class CreateDocumentService {

    MetadataManagerPort metadataManagerPort;
    FileManagerPort fileManagerPort;

    void create(Document document) {
        metadataManagerPort.create(Metadata.builder()
                .id(document.getId() + "-metadata")
                .build());
        fileManagerPort.create(File.builder()
                .id(document.getId() + "-file")
                .build());
    }

    @FeignClient(
            name = "settings-manager-client",
            url = "${metadata-manager.url}")
    public interface MetadataManagerPort {

        @PostMapping(
                value = "/api/_create",
                produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
                consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
        void create(Metadata metadata);

    }

    @FeignClient(
            name = "settings-manager-client",
            url = "${file-manager.url}")
    public interface FileManagerPort {

        @PostMapping(
                value = "/api/_create",
                produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
                consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
        void create(File metadata);

    }

}
