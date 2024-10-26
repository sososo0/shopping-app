package com.server.myselectshop.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.server.myselectshop.dto.SignupRequestDto;
import com.server.myselectshop.dto.UserInfoDto;
import com.server.myselectshop.entity.UserRoleEnum;
import com.server.myselectshop.jwt.JwtUtil;
import com.server.myselectshop.security.UserDetailsImpl;
import com.server.myselectshop.service.FolderService;
import com.server.myselectshop.service.KakaoService;
import com.server.myselectshop.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller // thymeleaf를 사용할 때는 RestController 사용하면 html 적용이 안된다.
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    private final FolderService folderService;
    private final KakaoService kakaoService;

    @Value("${rest.api.key.kakao}")
    private String KAKAO_REST_API_KEY;

    @GetMapping("/user/login-page")
    public String loginPage(Model model) {
        model.addAttribute("restApiKey", KAKAO_REST_API_KEY);
        return "login";
    }

    @GetMapping("/user/kakao/callback")
    public String kakaoLogin(
        @RequestParam String code,
        HttpServletResponse response
    ) throws JsonProcessingException {
        String jwt = kakaoService.kakaoLogin(code);

        Cookie cookie = new Cookie(JwtUtil.AUTHORIZATION_HEADER, jwt);
        cookie.setPath("/");
        response.addCookie(cookie);

        return "redirect:/";
    }

   @GetMapping("/user/signup")
    public String signupPage() {
        return "signup";
    }

    @PostMapping("/user/signup")
    public String signup(
            @Valid SignupRequestDto requestDto,
            BindingResult bindingResult
    ) {
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if(fieldErrors.size() > 0) {
            for(FieldError fieldError : fieldErrors) {
                log.error(fieldError.getField() + " 필드 : " + fieldError.getDefaultMessage());
            }
            return "redirect:/api/user/signup";
        }

        userService.signup(requestDto);

        return "redirect:/api/user/login-page";
    }

    @GetMapping("/user-info")
    @ResponseBody
    public UserInfoDto getUserInfo(
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        String username = userDetails.getUser().getUsername();
        UserRoleEnum role = userDetails.getUser().getRole();
        boolean isAdmin = (role == UserRoleEnum.ADMIN);

        return new UserInfoDto(username, isAdmin);
    }

    // over loading
    // 로그인 한 유저가 메인 페이지를 요청할 때 가지고 있는 폴더를 반환
    @GetMapping("/user-folder")
    public String getUserInfo(
            Model model,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        model.addAttribute("folders", folderService.getFolders(userDetails.getUser()));
        return "index :: #fragment";
    }

}
