package com.nodam.nodam_public.domain.user;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nodam.nodam_public.domain.user.certificate.UserCertificationInfo;



import java.util.Optional;

import javax.transaction.Transactional;

import java.util.Date;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.beans.factory.annotation.Value;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    //
    private final JavaMailSender mailSender;

    // file:///Users/sim/nodamSetting/user/
    @Value("${userProfileImgPath}")
    String userProfileImgPath;
    


    @Value("${spring.mail.username}")
    private String sender;

    public boolean isExistUserEmail(String email){
        return userRepository.existsByEmail(email);
    }

    public boolean signUp(User user){
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);

            return true;
        } catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());

            return false;
        }
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> findByUserId(Long id) {
        return userRepository.findById(id);
    }


    @Transactional
    public Date noSmoking(Long id) {
        Date date = new Date();
        userRepository.noSmoking(date, id);

        return date;
    }

    @Transactional
    public void noSmokingStop(Long id) {
        userRepository.noSmoking(null, id);
    }

   


    // accounts 
    public boolean matchingPassword(User tmp) {
        Optional<User> optionalUser = userRepository.findByEmail(tmp.getEmail());
        User user = optionalUser.get();

        if (passwordEncoder.matches(tmp.getPassword(), user.getPassword())) {
            return true;
        } else {
            return false;
        }
        
    }

    @Transactional
    public boolean changePassword(User tmp) {
        try {
            Optional<User> optionalUser = userRepository.findByEmail(tmp.getEmail());
            User user = optionalUser.get();
                
            String updatePassword = passwordEncoder.encode(tmp.getPassword());
            userRepository.updatePassword(updatePassword, user.getEmail());
            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Transactional
    public Integer createCertificateNumber(User user) {
        SimpleMailMessage message = new SimpleMailMessage();
        Integer certificationNumber = getCertificateNumber();

        saveCertificationInfo(user, certificationNumber);

        message.setTo(user.getEmail());
        message.setFrom(sender);
        message.setSubject("???????????? ???????????? ?????? ??????");
        message.setText("??????????????? "+user.getName()+"???\n" +
                "?????????????????? ???????????? ?????????????????? ????????? ?????? ??????????????? ????????????.\n" +
                "???????????? ?????????????????? ????????? ?????? ??????????????? "+certificationNumber +"?????????.\n" +
                "?????? ??????????????? 30?????? ??????????????? ??????????????? 30?????? ?????? ????????? ?????? ???????????????.");
        mailSender.send(message);

        return certificationNumber;
    }

    public void saveCertificationInfo(User user, Integer certificationNumber) {
        UserCertificationInfo uci = new UserCertificationInfo(certificationNumber, new Date());
        user.setCertificationInfo(uci);
    }


    public int getCertificateNumber(){
        Random random = new Random();
        int randomNumber = random.nextInt(999999 - 100000 + 1) + 100000;
        return randomNumber;
    }


    public String getTempPassword(){
        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        String str = "";

        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            str += charSet[idx];
        }

        return str;
    }

    @Transactional
    public boolean certificate(User user, User tmp) {
        Integer certificateNumber = tmp.getCertificationInfo().getCertificationNumber();
        Date certificateDate = tmp.getCertificationInfo().getCertificateDate();
        Date now = new Date();

        if (user.getCertificationInfo().getCertificationNumber()
                .equals(certificateNumber)) {

            if ((now.getTime() - certificateDate.getTime()) <= 1800) {
                String tmpPassword = getTempPassword();
                user.setPassword(passwordEncoder.encode(tmpPassword));
                
                sendTempPassword(user, tmpPassword);
                
                return true;
            }
            
        } 

        return false;
    }

    @Transactional
    public void sendTempPassword(User user, String tempPassword){
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(user.getEmail());
        message.setFrom(sender);
        message.setSubject("???????????? ?????????????????? ?????? ??????");
        message.setText("??????????????? "+user.getName()+"???\n" +
                "?????????????????? ???????????? ?????????????????? ??????????????????.\n" +
                "???????????? ????????????????????? "+tempPassword +"?????????.");
        mailSender.send(message);
    }


    @Transactional
    public void updateUserProfileImg(User user, String profileImgPath) {        
        userRepository.updateProfileImg(profileImgPath, user.getId());
    }

}
