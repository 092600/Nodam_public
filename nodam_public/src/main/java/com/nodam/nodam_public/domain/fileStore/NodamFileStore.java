package com.nodam.nodam_public.domain.fileStore;

import com.nodam.nodam_public.domain.fileStore.type.NodamFileType;
import com.nodam.nodam_public.domain.user.User;

import lombok.Getter;
import lombok.Setter;;


@Setter
@Getter
public class NodamFileStore {

    private User user;

    private String fileName;
    private NodamFileType FileType;
    
    private String rootPath = "/Users/sim/nodamFiles/";
    private String imgPath;
    

    public NodamFileStore(String fileName, NodamFileType fileType, User user) {
        switch (fileType) {
            case PROFILEIMG:
                this.user = user;
                
                this.FileType = NodamFileType.PROFILEIMG;
                this.fileName = "profileImg.JPG";

                this.rootPath += "user/profile/"+user.getId();
                this.imgPath = rootPath+"/profileImg.jpg";
                
                break;

            case IMG:
                this.FileType = NodamFileType.IMG;
                this.fileName = fileName;
                break;
        }
    }


}
