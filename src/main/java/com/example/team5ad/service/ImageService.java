package com.example.team5ad.service;

import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ImageService {

    public void saveFileToDirectoryAndListing(MultipartFile[] files, long id) throws IOException;

    public void deleteFileFromDirectoryAndListing(long propId, String filename);

    public void deleteAllFilesOfPropListFromDirectory(long propId);

}
