package com.server.myselectshop.controller;

import com.server.myselectshop.dto.FolderRequestDto;
import com.server.myselectshop.dto.FolderResponseDto;
import com.server.myselectshop.exception.RestApiException;
import com.server.myselectshop.security.UserDetailsImpl;
import com.server.myselectshop.service.FolderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class FolderController {

    private final FolderService folderService;

    @PostMapping("/folders")
    public void addFolders(
            @RequestBody FolderRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        List<String> folderNames = requestDto.getFolderNames();
        folderService.addFolders(folderNames, userDetails.getUser());
    }

    @GetMapping("/folders")
    public List<FolderResponseDto> getFolders(
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        return folderService.getFolders(userDetails.getUser());
    }

    @ExceptionHandler({IllegalArgumentException.class}) // IllegalArgumentException이 발생하면 처리한다.
    public ResponseEntity<RestApiException> handleException(IllegalArgumentException ex) {
        System.out.println("FolderController.handleException");
        RestApiException restApiException = new RestApiException(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(
                // HTTP body
                restApiException,
                // HTTP status code
                HttpStatus.BAD_REQUEST
        );
    }
}
