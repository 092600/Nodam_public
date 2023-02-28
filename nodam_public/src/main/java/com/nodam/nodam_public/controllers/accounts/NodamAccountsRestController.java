package com.nodam.nodam_public.controllers.accounts;

import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nodam.nodam_public.domain.fileStore.NodamFileStoreService;
import com.nodam.nodam_public.domain.sessionManager.NodamSessionManagerService;
import com.nodam.nodam_public.domain.user.User;
import com.nodam.nodam_public.domain.user.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v4/accounts")
@RequiredArgsConstructor
public class NodamAccountsRestController {

    private final UserService userService;
    private final NodamFileStoreService nodamFileStoreService;
    private final NodamSessionManagerService nodamSessionManagerService;

    @GetMapping(value = "/checkEmail")
    public boolean checkEmail(@RequestParam("email") String email){
        return userService.isExistUserEmail(email);
    }

    @PostMapping(value = "/signup")
    public boolean signUp(@RequestBody User user){

        if (!userService.isExistUserEmail(user.getEmail())){
            return userService.signUp(user);
        } 

        return false;

    }


    // mypage 비밀번호 확인
    @PostMapping(value = "/mypage/user/certification")
    public boolean certificateUser(@RequestBody User tmp, Authentication auth) {
        if (auth.getName().equals(tmp.getEmail())) {
            return userService.matchingPassword(tmp);
        } 

        return false;
    }

    // mypage 비밀번호 변경
    @PatchMapping(value = "/mypage/user/password")
    public boolean changePassword(@RequestBody User tmp, Authentication auth) {
        if (auth.getName().equals(tmp.getEmail())) {
            userService.changePassword(tmp);
        }

        return true;
    }
    

    // 비밀번호 찾기
    @GetMapping(value = "/find/password")
    public boolean findUserPassword(@RequestParam("email") String email){
        try {
            Optional<User> user = userService.findByEmail(email);
        
            if (user.isPresent()){
                userService.createCertificateNumber(user.get());
                
                return true;
            } 

            return false;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    // 임시 비밀번호 가입 이메일로 보냄
    @PatchMapping(value = "/find/password")
    public boolean createTempPassword(@RequestBody User tmp) {
        try {
            Optional<User> user = userService.findByEmail(tmp.getEmail());

            if (user.isPresent()) {
                return userService.certificate(user.get(), tmp);
            }

            return false;

        } catch (Exception e) {
            e.printStackTrace();
            
            return false;
        }
    }

    // 유저 프로필 이미지 변경
    @PostMapping(value = "/user/profileImg")
    public boolean uploadUserProfileImg(@RequestParam("profileImg") MultipartFile profileImg, 
                                    @RequestParam("email") String email, HttpSession session) {
        try {
            Optional<User> OptionalUser = userService.findByEmail(email);

            if (OptionalUser.isPresent() && !profileImg.isEmpty()) {
                User user = OptionalUser.get();

                // 유저 프로필 사진 디렉토리 없는 경우 생성
                nodamFileStoreService.createUserProfileImgDirectory(user);
                
                // 이메일로 조회한 User 객체 값 변경 > 프로필 사진 변경사항 세션에 반영함
                String userProfileImgPath = nodamFileStoreService.saveUserProfileImg(profileImg, user);
                user.setProfileImage(userProfileImgPath);
                
                // DB에 profile_image 값 업데이트
                userService.updateUserProfileImg(user, userProfileImgPath);
                // 세션 값 변경
                nodamSessionManagerService.updateNodamUserDetailsSession(user, session);

                return true;
            }

            return false;
        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }
    }


    // 유저 프로필 이미지 삭제 > 기본 이미지로
    @DeleteMapping(value = "/user/profileImg")
    public boolean uploadUserProfileImg(@RequestBody User tmp, HttpSession session) {
        try {
            Optional<User> OptionalUser = userService.findByEmail(tmp.getEmail());

            if (OptionalUser.isPresent()) {
                User user = OptionalUser.get();

                // 이메일로 조회한 User 객체 값 변경 > 프로필 사진 변경사항 세션에 반영함
                String userProfileImgPath = nodamFileStoreService.deleteUserProfileImg();
                user.setProfileImage(userProfileImgPath);
                
                // 기본 프로필 사진 값으로 유저 profile_image 컬럼 값 변경
                userService.updateUserProfileImg(user, userProfileImgPath);

                // 세션 값 변경
                nodamSessionManagerService.updateNodamUserDetailsSession(user, session);

                return true;
            }

            return false;
        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }
    }

    // 금연 
    @PostMapping(value = "/user/noSmoking")
    public Date nosmoking(@RequestBody User tmp, HttpSession session){
        Optional<User> user = userService.findByUserId(tmp.getId());

        try {
            if (user.isPresent()) {
                Date date = userService.noSmoking(user.get().getId());
                user.get().getUserInfo().setNoSmokingDate(date);
                
                nodamSessionManagerService.updateNodamUserDetailsSession(user.get(), session);
                
                return date;
            }

            return null;
        } catch (Exception e) {
            e.printStackTrace();
            
            return null;
        }
    }

    // 흡연
    @DeleteMapping(value = "/user/noSmoking")
    public String noSmokingStop(@RequestBody User tmp, HttpSession session) {
        Optional<User> OptionalUser = userService.findByUserId(tmp.getId());

        try {
            if (OptionalUser.isPresent()) {
                User user = OptionalUser.get();
                    
                Date noSmokingStartDate = user.getUserInfo().getNoSmokingDate();
                if (noSmokingStartDate != null) {
                    Date now = new Date();
                    userService.noSmokingStop(user.getId());

                    Long sec = (now.getTime() - noSmokingStartDate.getTime()) / 1000;
                    String days = String.valueOf(sec / (24*60*60)); // 일자수
    
                    // session
                    user.getUserInfo().setNoSmokingDate(null);

                    nodamSessionManagerService.updateNodamUserDetailsSession(user, session);
                    //

                    return days;
                }
            }
            
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
