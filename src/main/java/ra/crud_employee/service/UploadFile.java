package ra.crud_employee.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadFile {
    public String uploadToLocal(MultipartFile file);
    public String uploadToFirebase(String localPath);
}
