package com.rest_jpa.fileUploading;

import com.rest_jpa.entity.to.StringResponse;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.nio.file.Path;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/files")
@AllArgsConstructor
public class FileUploadController {

    private final StorageService storageService;

    @GetMapping("/")
    public String listUploadedFiles(Model model) {
        model.addAttribute("files", storageService.loadAll().map(path ->
                        MvcUriComponentsBuilder.fromMethodName(
                                FileUploadController.class,
                    "serveFile", path.getFileName().toString()
                        ).build().toUri().toString())
                .collect(Collectors.toList()));
        return "uploadForm";
    }

    @GetMapping("/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = storageService.loadAsResource(filename);
        Path path = storageService.load(filename);
        return ResponseEntity.ok()
                .contentLength(path.toFile().length())
                .contentType(MediaType.IMAGE_JPEG)
                .body(file);
    }

    @PostMapping(value = "/")
    public ResponseEntity<StringResponse> saveFile(@RequestParam("file") MultipartFile file) {
        String savedFileName = storageService.store(file);
        return new ResponseEntity<>(new StringResponse(savedFileName), HttpStatus.CREATED);
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }
}
