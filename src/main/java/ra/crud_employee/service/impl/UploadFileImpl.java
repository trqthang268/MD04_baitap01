package ra.crud_employee.service.impl;

import com.google.cloud.storage.*;
import jakarta.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import ra.crud_employee.service.UploadFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UploadFileImpl implements UploadFile {
    @Autowired
    private ServletContext servletContext;
    @Value("${bucket_name}")
    private String bucket_name;
    @Autowired
    private Storage storage;

    @Override
    public String uploadToLocal(MultipartFile file) {
        String path = servletContext.getRealPath("/uploads");
        File f = new File(path);
        if (!f.exists()) {
            f.mkdirs();
        }
//        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String filename = UUID.randomUUID() +file.getOriginalFilename();
        String localPath = f.getAbsoluteFile()+File.separator+filename;
        try {
            FileCopyUtils.copy(file.getBytes(),new File(localPath));
            return uploadToFirebase(localPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String uploadToFirebase(String localPath) {
        Path path = Paths.get(localPath);
        String fileName = path.getFileName().toString();
        BlobId blobId = BlobId.of(bucket_name,fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        List<Acl> acls = new ArrayList<>();
        acls.add(Acl.of(Acl.User.ofAllUsers(),Acl.Role.READER));
        blobInfo = blobInfo.toBuilder().setAcl(acls).build();
        try {
            Blob blob = storage.create(blobInfo, Files.readAllBytes(path));
            return blob.getMediaLink();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
