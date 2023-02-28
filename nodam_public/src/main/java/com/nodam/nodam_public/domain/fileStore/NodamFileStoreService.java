package com.nodam.nodam_public.domain.fileStore;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Service;

import com.nodam.nodam_public.domain.fileStore.type.NodamFileType;
import com.nodam.nodam_public.domain.user.User;

import org.springframework.web.multipart.MultipartFile;

@Service
public class NodamFileStoreService {
    
    public boolean createUserProfileImgDirectory(User user) {
        NodamFileStore nodamFileStore = new NodamFileStore(null, NodamFileType.PROFILEIMG, user);
        File profileImgDirectory = new File(nodamFileStore.getRootPath());

        if (!profileImgDirectory.exists()) {    
            profileImgDirectory.mkdir();
            
            return true;
        } else {
            return false;
        }
    }

    public String saveUserProfileImg(MultipartFile profileImg, User user) {
        NodamFileStore nodamFileStore = new NodamFileStore(null, NodamFileType.PROFILEIMG, user);
        try {
            File img = new File(nodamFileStore.getImgPath());
            profileImg.transferTo(img);
            
            return nodamFileStore.getImgPath().replace("/Users/sim/nodamFiles/user/", "/userProfileImgPath/");
        } catch (IOException e) {
            e.printStackTrace();

            return "/userProfileImgPath/profile/default/defaultProfile.JPG";
        }
    }

    public String deleteUserProfileImg() {
        return "/userProfileImgPath/profile/default/defaultProfile.JPG";
    }
}
