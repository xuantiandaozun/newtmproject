package com.system.uilibrary.views.attachment.bean;

import java.io.Serializable;

/**
 * Created by zbmobi on 15/3/23.
 */
public class AttachmentEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    public String ID;
    public String FielFullName;
    public String FilePreName;
    public String FileExtension;
    public String FileSize;
    public String FileFolder;
    public int Status;
    public String ModelID;
    public String CreateDate;
    public String LocalPath;
    public String FilePath;

    /**
     * 扩展
     */
    public boolean isCanEdit = true;

    public boolean isCanEdit() {
        return isCanEdit;
    }

    public void setCanEdit(boolean canEdit) {
        isCanEdit = canEdit;
    }

    public String getFilePath() {
        return FilePath;
    }

    public void setFilePath(String filePath) {
        FilePath = filePath;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getFielFullName() {
        return FielFullName;
    }

    public void setFielFullName(String fielFullName) {
        FielFullName = fielFullName;
    }

    public String getFilePreName() {
        return FilePreName;
    }

    public void setFilePreName(String filePreName) {
        FilePreName = filePreName;
    }

    public String getFileExtension() {
        return FileExtension;
    }

    public void setFileExtension(String fileExtension) {
        FileExtension = fileExtension;
    }

    public String getFileSize() {
        return FileSize;
    }

    public void setFileSize(String fileSize) {
        FileSize = fileSize;
    }

    public String getFileFolder() {
        return FileFolder;
    }

    public void setFileFolder(String fileFolder) {
        FileFolder = fileFolder;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public String getModelID() {
        return ModelID;
    }

    public void setModelID(String modelID) {
        ModelID = modelID;
    }

    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String createDate) {
        CreateDate = createDate;
    }

    public String getLocalPath() {

        return LocalPath;
    }

    public void setLocalPath(String localPath) {
        LocalPath = localPath;
    }
}
